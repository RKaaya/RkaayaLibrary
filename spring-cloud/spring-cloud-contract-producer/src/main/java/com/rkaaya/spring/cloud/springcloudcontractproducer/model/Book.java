package com.rkaaya.spring.cloud.springcloudcontractproducer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private Long id;
    private String name;
    private Integer page;
}
