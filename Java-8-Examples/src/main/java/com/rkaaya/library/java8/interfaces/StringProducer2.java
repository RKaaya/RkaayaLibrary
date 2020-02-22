package com.rkaaya.library.java8.interfaces;

public interface StringProducer2 {

    static String methodFromInterface() {
        return "Method from interface.";
    }

    default String defaultMethod(){
        return "Default method from Interface.";
    }

    String methodFromImplementors();
}
