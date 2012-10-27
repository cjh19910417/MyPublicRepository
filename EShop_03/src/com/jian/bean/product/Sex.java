package com.jian.bean.product;

public enum Sex
{
    NONE
    {
        @Override
        public String getName()
        {

            return "男女不限";
        }
    },
    MAN
    {
        @Override
        public String getName()
        {

            return "男";
        }
    },
    WOMAN
    {
        @Override
        public String getName()
        {

            return "女";
        }
    };
    public abstract String getName();
}
