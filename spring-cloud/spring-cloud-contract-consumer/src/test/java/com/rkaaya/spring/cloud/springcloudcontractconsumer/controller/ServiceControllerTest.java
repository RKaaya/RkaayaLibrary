package com.rkaaya.spring.cloud.springcloudcontractconsumer.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "com.rkaaya.spring.cloud:spring-cloud-contract-producer:+:stubs:8090")
@EmbeddedKafka(partitions = 1, topics = {"toyBook"})
public class ServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Ignore
    public void contract_test_book_crud() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/toyBook/createBook?id=15&name=TestBook&page=9")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/toyBook/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/toyBook/book?id=15")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void contract_test_create_book() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/toyBook/createBook?id=19&name=Book&page=13")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
