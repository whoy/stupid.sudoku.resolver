package ru.whoy.lmbd;

/**
 * Created by SBT-Sergunin-SV on 21.01.2017.
 */
public class DoSmth {

    GetIntNext getInt;
    GetIntMain getMain;

    public DoSmth(GetIntNext getInt) {
        this.getInt = getInt;
    }

    public void exec(){
        System.out.println(getInt.getInt(10));
    }
//
//    public DoSmth(GetIntMain getMain) {
//        this.getMain = getMain;
//    }
//
//    public void exec2(){
//        System.out.println(getMain.getInt(10));
//    }
}
