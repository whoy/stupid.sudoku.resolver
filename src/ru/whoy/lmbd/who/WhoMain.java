package ru.whoy.lmbd.who;

/**
 * Created by SBT-Sergunin-SV on 21.01.2017.
 */
public class WhoMain {

    public static void main(String[] args) {
        System.out.println(getWhoMain(WhoContain::whoContain));
    }

    public static String getWhoMain(WhoInt whoInt) {
        return whoInt.getWho();
    }

}
