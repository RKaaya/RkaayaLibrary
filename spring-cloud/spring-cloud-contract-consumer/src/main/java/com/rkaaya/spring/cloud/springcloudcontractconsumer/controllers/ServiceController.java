package com.rkaaya.spring.cloud.springcloudcontractconsumer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("toyBook")
public class ServiceController {

    @Autowired
    private RestTemplate restTemplate;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/books")
    public String getAllBook() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8090/book/",
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        return responseEntity.getBody();
    }

    @PostMapping("/createBook")
    public String createBook(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("page") Integer page) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8090/book/",
                HttpMethod.POST,
                new HttpEntity<>("{'id':15,'name':'Lord','page':9}", httpHeaders),
                String.class);

        return responseEntity.getBody();
    }

    @GetMapping("/book")
    public String getBook(@RequestParam("id") Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8090/book/id?id=" + id,
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        return responseEntity.getBody();
    }




}
