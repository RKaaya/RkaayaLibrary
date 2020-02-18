package com.rkaaya.library.springcloudmicroservices.controller;

import com.rkaaya.library.springcloudmicroservices.configuration.NameProperties;
import com.rkaaya.library.springcloudmicroservices.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {

    @Autowired
    private NameProperties nameProperties;

    @GetMapping("/name")
    public NameService retrieveNameFromConfiguration() {
        return new NameService(nameProperties.getHeroName());
    }
}
