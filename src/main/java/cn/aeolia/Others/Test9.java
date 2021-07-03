package cn.aeolia.Others;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @Author aeolia
 * @Date 2021/6/29 17:15
 */
public class Test9 {

    public static void main(String[] args) throws Exception {
        //页数范围
        int startPageNum = 11;
        int overPageNum = 20;
        //搜索关键字(多个关键字用+间隔，如：海贼王+路飞)
        String keyWords = "海贼王";
        //页面请求超时时间
        int pageRequestTimeout = 600000;
        int count=0;

        WebPage webPage = new WebPage("https://m.bcoderss.com/", new Parameter<>("page", 1), new Parameter<>("s", "海贼王"));

        for (int i = startPageNum; i <= overPageNum; i++) {
            webPage.setPage(new Parameter<>("page", i));
            Document document = Jsoup.connect(webPage.getUrlAsString()).timeout(pageRequestTimeout).method(Connection.Method.GET).maxBodySize(0).followRedirects(false).get();
            Elements as = null;
            try {
                Element main = document.getElementById("main");
                if (null == main) {
                    count++;
                    continue;
                }
                as = main.getElementsByTag("a");
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            for (Element a : as) {
                Document document1 = Jsoup.connect(a.attr("href")).timeout(pageRequestTimeout).method(Connection.Method.GET).maxBodySize(0).followRedirects(false).get();
                Elements single_wallpaper = document1.getElementsByClass("single-wallpaper");
                String src = single_wallpaper.get(0).getElementsByTag("img").get(0).attr("src");
                String fileName = Test8.parseFileName(src);
                Test8.download(src, fileName);
            }
        }
        System.out.println(count);


    }

}

class WebPage {
    String url;
    Parameter<String, Integer> page;
    Parameter<String, String> keyword;

    public WebPage() {
    }

    public WebPage(String url, Parameter<String, Integer> page, Parameter<String, String> keyword) {
        this.url = url;
        this.page = page;
        this.keyword = keyword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Parameter<String, Integer> getPage() {
        return page;
    }

    public void setPage(Parameter<String, Integer> page) {
        this.page = page;
    }

    public Parameter<String, String> getKeyword() {
        return keyword;
    }

    public void setKeyword(Parameter<String, String> keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return url + "/" + page.getKey() + "/" + page.getValue() + "/?" + keyword.getKey() + "=" + keyword.getValue();
    }

    public String getUrlAsString() {
        return toString();
    }
}

class Parameter<T, E> {
    private T key;
    private E value;

    public Parameter(T t, E e) {
        this.key = t;
        this.value = e;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

}
