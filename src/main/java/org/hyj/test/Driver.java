package org.hyj.test;

/**
 * Created by Administrator on 18-4-21.
 */
public class Driver {

    public static void main(String[] args) {
        Cat cat = new ChinaCat();
        Cat cat2 = new PersianCat();
        cat2.changeName("tutu");

        cat = cat2;

        //System.out.println(cat.name);
        //System.out.println(cat2.name);

        cat.fuck(cat2);
        cat2.fuck(cat);
        fuck(cat, cat2);
    }

    public static void fuck(Cat cat1, Cat cat2) {
        System.out.println("I am Cat " + cat1.name + ", and I am fucking " + cat2.name);
    }
}
