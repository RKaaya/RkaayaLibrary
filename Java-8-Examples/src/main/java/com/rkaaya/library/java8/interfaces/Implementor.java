package com.rkaaya.library.java8.interfaces;

public class Implementor implements StringProducer, StringProducer2 {

    //Need to override, because method exists in booth interface
    @Override
    public String defaultMethod() {
        return StringProducer.super.defaultMethod();
    }

    @Override
    public String methodFromImplementors() {
        return "This method is from Recruit";
    }

}
