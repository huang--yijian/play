package org.hyj.test;

/**
 * Created by Administrator on 18-4-21.
 */
public class ChinaCat extends Cat {
    private String chineseName = "中国猫";
    public void fuck(Cat cat) {
        System.out.println("I am a Chinese Cat. I fuck differently. Chinese name: " + chineseName);
    }
}
