package com.rkaaya.library.java8.interfaces;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeroHQ {

    public static void main(String[] args) {
        Implementor implementor = new Implementor();
        log.info("From interface: " + StringProducer.methodFromInterface());
        log.info("From implementor: " + implementor.methodFromImplementors());
        log.info("From implementor: " + implementor.defaultMethod());
    }

}