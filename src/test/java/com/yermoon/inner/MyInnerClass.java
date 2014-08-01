package com.yermoon.inner;

/**
 * To change this template use File | Settings | File Templates.
 *
 * @author wangqing
 * @since 14-4-25 上午10:08
 */
public class MyInnerClass {

    public static void main(String[] args) {
        MyInnerClass mm=new MyInnerClass();
        Man dd = mm.getMan();

    }

    public Man getMan() {
        return new Man();
    }

    private class Man {
        private String name;

        private String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }
    }
}
