package com.dist.test7;

/**
 * Created by Administrator on 2019/4/15.
 */
public class CompantFilterImpl extends CompantFilter  {


    public CompantFilterImpl(Compant compant) {
        super(compant);
    }

    @Override
    public void dosomething() {
        super.dosomething();
        doanthorthing();
    }

    public void doanthorthing(){
        System.out.println("c 功能");
    }

    public static void main(String [] ags){
        Compant compant=new CompantFilterImpl(new CpmpantImpl());
        compant.dosomething();
    }
}
