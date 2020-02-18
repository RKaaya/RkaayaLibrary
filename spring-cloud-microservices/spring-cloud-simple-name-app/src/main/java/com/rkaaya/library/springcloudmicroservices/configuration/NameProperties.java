package com.rkaaya.library.springcloudmicroservices.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("simple-name-app")
@Getter
@Setter
public class NameProperties {
    private String heroName;
}
