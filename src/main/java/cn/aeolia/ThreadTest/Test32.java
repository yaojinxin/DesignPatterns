package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @Author aeolia
 * @Date 2021/7/13 10:41
 */
@Slf4j(topic = "c.Test32")
public class Test32 {
    public static void main(String[] args) {
        GarbageBag garbageBag=new GarbageBag("满的垃圾袋");

        AtomicMarkableReference<GarbageBag> refGarbageBag = new AtomicMarkableReference<>(garbageBag, true);

        log.debug("start...");
        GarbageBag prev = refGarbageBag.getReference();
    }
}

class GarbageBag{
    String desc;

    public GarbageBag(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return super.toString() + " " + desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
