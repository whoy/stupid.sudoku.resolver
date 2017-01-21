package ru.whoy.lmbd;

import java.util.ArrayList;

/**
 * Created by SBT-Sergunin-SV on 20.01.2017.
 */
public class MainTest {

    public int t = 100;
    public static void main(String[] args) {
        GetInt a = () -> 4;
        System.out.println(a.getInt());

        MainTest t = new MainTest();
        int x = 10;

        new DoSmth((n) -> (n+x)).exec();

        GetIntMain mmm = (t2) -> {
            System.out.println("fffffff: " + t2.t);
        };

        mmm.getInt(t);

        t.t=13;

        mmm.getInt(t);

        ArrayList<String> strings = new ArrayList<>();
    }

}
