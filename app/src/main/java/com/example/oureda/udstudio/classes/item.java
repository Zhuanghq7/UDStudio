package com.example.oureda.udstudio.classes;

/**
 * Created by Zhuangh7 on 2016/9/26.
 */
public class item {
    private String name;
    private int funNum;
    private String[]  funs;

    public item(String name,int funNum,String[] strings){
        this.name = name;
        this.funNum = funNum;
        funs = new String[funNum];
        for(int i = 0 ;i<funNum;i++) {
            funs[i] = strings[i];
        }
    }

    public String getName(){
        return name;
    }

    public int getFunNum() {
        return funNum;
    }

    public String[] getFuns() {
        return funs;
    }

}
