package com.rkaaya.spring.cloud.springcloudcontractconsumer.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.rkaaya.spring.cloud.springcloudcontractconsumer.SpringCloudContractConsumerApplication;
import org.assertj.core.api.BDDAssertions;
import org.awaitility.Awaitility;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
    @Ignore
    public void contextLoads() {
        this.trigger.trigger("trigger");

        Awaitility.await().untilAsserted(() -> {
            String response = this.application.storedFoo;
            BDDAssertions.then(response).isNotNull();
            BDDAssertions.then(response).contains("\"id\":");
            BDDAssertions.then(response).contains("\"name\":");
            BDDAssertions.then(response).contains("\"page\":");

            DocumentContext parsedJson = JsonPath.parse(response);

            assertThatJson(parsedJson).field("['id']").matches("([1-9]\\d*)");
            assertThatJson(parsedJson).field("['page']").matches("([1-9]\\d*)");
        });
    }

}
