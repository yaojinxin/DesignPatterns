package cn.aeolia.Others;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.util.*;

/**
 * @Author aeolia
 * @Date 2021/6/27 15:21
 */

public class Test8 {
    //爬虫网站(不要动)
    //https://wallhaven.cc/search?categories=110&purity=100&ratios=16x10&sorting=date_added&order=desc&page=6
    //https://wallhaven.cc/latest?
    //https://wallhaven.cc/search?q=One%20Piece&categories=110&purity=100&sorting=date_added&order=desc&
    static String urlPath = "https://wallhaven.cc/search?categories=010&purity=100&sorting=hot&order=desc&";
    //保存文件路径(改成自己电脑的绝对路径放进来)
    static String saveFilePath = "G:\\Anime16_10_2\\";
    //开始和结束页码(取值1~14776  startPage要比overPage小)
    static int startPage = 1;
    static int overPage = 13;
    //数据请求超时时间
    static int pageRequestTimeout=60000;
    //请求失败时的最大重试次数
    static int maximumNumberOfRetries = 10;
    //重试等待时间
    static int retryWaitingTime = 3000;
    //浏览器伪装
    static String User_Agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36";
    //线程集合
    static List<Thread> threadList = new Vector<>();
    static Document document;
    //是否完成
    static boolean finished = false;
    //计数锁
    static Object lock = new Object();
    //计数
    static int successCount=0;
    static int repeatCount=0;
    static int failCount=0;

    public static void main(String[] args) throws Exception {
        File file=new File(saveFilePath);
        if (!file.exists()){
            file.mkdirs();
        }

        List<String> list = new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        long startTime = System.currentTimeMillis();

        System.out.println("开始解析网页数据");
        pointA:
        for (int i = startPage; i <= overPage; i++) {
            //分页url
            URL url = new URL(urlPath + "page=" + i);
            pointB:
            for (int j = 0; j < maximumNumberOfRetries; j++) {
                if (tryParsePage(url)) {
                    Elements figures = document.getElementById("thumbs").getElementsByTag("figure");
                    for (Element figure : figures) {
                        Elements preview = figure.getElementsByClass("preview");
                        String href = preview.attr("href");
                        list.add(href);
                    }
                    continue pointA;
                } else {
                    continue pointB;
                }
            }

        }
        System.out.println("开始解析网页图片");
        //百分比监控
        Thread percentageMonitoring = new Thread(() -> {
            while (!finished)
                try {
                    Thread.sleep(1000);
                    NumberFormat numberFormat = NumberFormat.getPercentInstance();
                    numberFormat.setMinimumFractionDigits(2);
                    double i = 0;
                    synchronized (lock) {
                        i = successCount + repeatCount;
                    }
                    String percent = numberFormat.format(i / (double) list.size());
                    System.out.println("完成进度：" + percent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });
        percentageMonitoring.start();
        point1:
        for (String urlPath : list) {
            point2:
            for (int i = 0; i < maximumNumberOfRetries; i++) {
                if (tryDownload(urlPath)) {
                    continue point1;
                } else {
                    if (i == maximumNumberOfRetries - 1) {
                        failCount++;
                    }
                    continue point2;
                }
            }
        }
        long overTime = System.currentTimeMillis();
        for (Thread thread : threadList) {
            thread.join();
        }
        finished = true;
        percentageMonitoring.join();
        System.out.println("所有图片下载完毕");
        System.out.println("总计：" + list.size() + "张图片\t完成：" + successCount + "张图片\t失败：" + failCount + "张\t重复：" + repeatCount + "张\t" + "总耗时：" + timeProcessing(overTime - startTime));


    }


    /**
     * 传入img对象，通过图片路径解析出文件名
     *
     * @param imgSrc
     * @return
     */
    public static String parseFileName(String imgSrc) {
        String[] split = imgSrc.split("/");
        String fileName = split[split.length - 1];
        return fileName;
    }

    /**
     * 下载解析
     *
     * @param urlPath
     * @return
     * @throws Exception
     */
    public static boolean tryDownload(String urlPath) throws Exception {
        try {
            System.out.println("尝试请求图片路径：" + urlPath);
            Document document = Jsoup.connect(urlPath).timeout(pageRequestTimeout).header("User-Agent",User_Agent).method(Connection.Method.GET).maxBodySize(0).followRedirects(false).get();
            System.out.println(urlPath + "请求成功,开始下载");
            Element wallpaper = document.getElementById("wallpaper");
            String src = wallpaper.attr("src");
            String fileName = parseFileName(src);
            Thread thread = new Thread(() -> {
                try {
                    download(src, fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            threadList.add(thread);
            return true;
        } catch (Exception e) {
            System.out.println(urlPath + "请求失败");
            System.out.println("等待重试。。。");
            Thread.sleep(retryWaitingTime);
            return false;
        }
    }

    /**
     * 下载
     *
     * @param src
     * @param fileName
     * @throws Exception
     */
    public static void download(String src, String fileName) throws Exception {
        File file = new File(saveFilePath + fileName);
        if (!file.exists()) {
            //获取输入输出流
            URL target = new URL(src);
            URLConnection conn = target.openConnection();
            InputStream inputStream = conn.getInputStream();
            OutputStream outputStream = new FileOutputStream(saveFilePath + fileName);
            //保存文件
            byte[] buff=new byte[1024*500];
            int len;
            while ((len = inputStream.read(buff)) != -1) {
                outputStream.write(buff,0,len);
                outputStream.flush();
            }
            if (null != inputStream) {
                inputStream.close();
            }
            if (null != outputStream) {
                outputStream.close();
            }
            System.out.println(fileName + "下载完毕");
            synchronized (lock) {
                successCount++;
            }
        } else {
            synchronized (lock) {
                repeatCount++;
            }
            System.out.println("该文件已存在");
        }
    }

    /**
     * 页面解析
     *
     * @param url
     * @return
     * @throws InterruptedException
     */
    public static boolean tryParsePage(URL url) throws InterruptedException {
        try {
            document = Jsoup.connect(url.toString()).timeout(pageRequestTimeout).header("User-Agent",User_Agent).method(Connection.Method.GET).maxBodySize(0).followRedirects(false).get();

            System.out.println("网页" + url + "解析成功");
            return true;
        } catch (Exception e) {
            Thread.sleep(retryWaitingTime);
            System.out.println("网页" + url + "解析失败");
            System.out.println("等待重试。。。");
            return false;
        }
    }

    /**
     * 时间格式化
     *
     * @param time
     * @return
     */
    public static String timeProcessing(long time) {
        long h = 0, m = 0, s = 0, ms = 0;
        if (time >= 1000) {
            s = time / 1000;
            ms = time % 1000;
            if (s >= 60) {
                m = s / 60;
                s = s % 60;
                if (m >= 60) {
                    h = m / 60;
                    m = m % 60;
                }
            }
        }
        String timeString = (h == 0 ? "" : h + "小时") + (m == 0 ? "" : m + "分钟") + (s == 0 ? "" : s + "秒") + (ms == 0 ? "" : ms + "毫秒");
        return timeString;
    }

}


