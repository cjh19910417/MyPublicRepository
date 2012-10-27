package com.jian.bean.user;

public enum Gender
{
    MAN{
        public String getName(){
            return "男";
        }
    }
    ,
    WOMAN{
        public String getName(){
            return "女";
        }
    };
    public abstract String getName();
}
