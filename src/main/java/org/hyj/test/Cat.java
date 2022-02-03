package org.hyj.test;


/**
 * Created by Administrator on 18-4-21.
 */
public class Cat {
    public String name = "miaomiao";

    public void changeName(String newName) {
        name = newName;
    }

    public void fuck(Cat cat) {
        System.out.println("I am Cat " + name + ", and I am " + cat.name);
    }
}
