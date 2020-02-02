package com.rkaaya.spring.cloud.springcloudcontractconsumer.controllers;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("toyBook")
public class ServiceController {

    private static String URL = "http://localhost:8090";

    @Autowired
    private RestTemplate restTemplate;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/books")
    public String getAllBook() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                URL + "/book/",
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        return responseEntity.getBody();
    }

    @PostMapping("/createBook")
    public String createBook(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("page") Integer page) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        JsonObject responseObject = new JsonObject();
//        responseObject.addProperty("id", id);
        responseObject.addProperty("id", id);
        responseObject.addProperty("name", name);
        responseObject.addProperty("page", page);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                URL + "/book/",
                HttpMethod.POST,
                new HttpEntity<>(responseObject.toString(), httpHeaders),
                String.class);

        return responseEntity.getBody();
    }

    @GetMapping("/book")
    public String getBook(@RequestParam("id") Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                URL + "/book/id?id=" + id,
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        return responseEntity.getBody();
    }




}
