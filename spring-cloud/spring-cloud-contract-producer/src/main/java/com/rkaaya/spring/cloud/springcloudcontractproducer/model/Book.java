package com.rkaaya.spring.cloud.springcloudcontractproducer.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Book implements Serializable {
    private Long id;
    private String name;
    private Integer page;
}
