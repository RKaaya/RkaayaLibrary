package com.rkaaya.spring.cloud.springcloudcontractconsumer.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.rkaaya.spring.cloud.springcloudcontractconsumer.controllers.ServiceController;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.net.URI;
import java.util.UUID;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ServiceController serviceController;

    @Test
    public void contract_test_book_crud() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/toyBook/createBook?id=15&name=TestBook&page=9")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        JsonArray expectedArray = new JsonArray();
        JsonObject expectedObject = new JsonObject();
        expectedObject.addProperty("id", 15);
        expectedObject.addProperty("name", "TestBook");
        expectedObject.addProperty("page", 9);
        expectedArray.add(expectedObject);

        mockMvc.perform(MockMvcRequestBuilders.get("/toyBook/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedArray.toString()));

        mockMvc.perform(MockMvcRequestBuilders.get("/toyBook/book?id=15")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedObject.toString()));
    }

    @Test
    @Ignore
    public void test_get_all_book() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        URI booksGetUrl = new URI("http://localhost:8090/bookdb/");
        ResponseEntity<String> responseEntity = restTemplate.exchange(booksGetUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(responseEntity.getHeaders().getContentType(), equalTo(MediaType.APPLICATION_JSON));
        assertTrue(responseEntity.getBody().contains("\"id\":"));
        assertTrue(responseEntity.getBody().contains("\"name\":"));
        assertTrue(responseEntity.getBody().contains("\"page\":"));

        DocumentContext parsedJson = JsonPath.parse(responseEntity.getBody());
        assertThatJson(parsedJson).field("['id']").matches("([1-9]\\d*)");
        assertThatJson(parsedJson).field("['name']").contains(anything());
        assertThatJson(parsedJson).field("['page']").matches("([1-9]\\d*)");
    }

    @Test
    public void test_post_book() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        URI booksPostUrl = new URI("http://localhost:8090/bookdb/");

        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("id", 9);
        responseObject.addProperty("name", "Random Kirby Book");
        responseObject.addProperty("page", 999);

        ResponseEntity<String> responseEntity = restTemplate.exchange(booksPostUrl, HttpMethod.POST, new HttpEntity<>(responseObject.toString(), httpHeaders), String.class);

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(responseEntity.getHeaders().getContentType(), equalTo(MediaType.APPLICATION_JSON));
        assertTrue(responseEntity.getBody().contains("\"id\":"));
        assertTrue(responseEntity.getBody().contains("\"name\":"));
        assertTrue(responseEntity.getBody().contains("\"page\":"));

        DocumentContext parsedJson = JsonPath.parse(responseEntity.getBody());
        assertThatJson(parsedJson).field("['id']").matches("([1-9]\\d*)");
        assertThatJson(parsedJson).field("['page']").matches("([1-9]\\d*)");
    }

}


