package com.rkaaya.spring.cloud.springcloudcontractconsumer.controller;

import com.rkaaya.spring.cloud.springcloudcontractconsumer.SpringCloudContractConsumerApplication;
import org.assertj.core.api.BDDAssertions;
import org.awaitility.Awaitility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = "com.rkaaya.spring.cloud:spring-cloud-contract-producer", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@AutoConfigureMessageVerifier
@EmbeddedKafka(partitions = 1, topics = {"toyBook"})
public class KafkaControllerTest {

    @Autowired
    StubTrigger trigger;
    @Autowired
    SpringCloudContractConsumerApplication application;

    @Test
    public void contextLoads() {
        this.trigger.trigger("trigger");

        Awaitility.await().untilAsserted(() -> {
            BDDAssertions.then(this.application.storedFoo).isNotNull();
            BDDAssertions.then(this.application.storedFoo).isEqualTo("{\"id\":13,\"name\":\"Kirby\",\"page\":9}");
        });
    }

}
