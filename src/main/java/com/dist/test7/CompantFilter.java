package com.dist.test7;

import java.io.FilterInputStream;

/**
 * Created by Administrator on 2019/4/15.
 */
public class CompantFilter implements Compant {
    private  Compant compant;

    public CompantFilter(Compant compant) {
        this.compant = compant;
    }

    @Override
    public void dosomething() {
        compant.dosomething();
        System.out.println("b 功能");
    }
}
