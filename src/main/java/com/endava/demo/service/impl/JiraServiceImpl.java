package com.endava.demo.service.impl;

import com.endava.demo.entity.Issue;
import com.endava.demo.service.JiraService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class JiraServiceImpl implements JiraService {


    @Autowired
    RestTemplate restTemplate;
    @Value("${rest.addres}")
    public String REST_SERVICE_URI;

    @Value("${jira.username}")
    private String username;

    @Value("${jira.password}")
    private String password;


    @Override
    public HttpHeaders createHttpHeaders() {

        String notEncoded = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(notEncoded.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + encodedAuth);
        return headers;
    }


    @Override
    public ResponseEntity<String> getAllTasksByUsernameCreatedToday(String user) {
        String url = REST_SERVICE_URI + "rest/api/2/search?jql=project=\"IIA\"AND assignee =" + "\"" + user + "\"AND createdDate>=startOfDay()";

        HttpHeaders headers = createHttpHeaders();
        HttpEntity request = new HttpEntity<>(headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        return result;
    }

    @Override
    public String getAllTasksByDate(String date) {
        return null;
    }

    @Override
    public ResponseEntity<String> getIssueById(String id) {
        String url = REST_SERVICE_URI + "rest/api/2/issue/" + id;
        HttpHeaders headers = createHttpHeaders();
        HttpEntity request = new HttpEntity<>(headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        return result;
    }

    @Override
    public void updateIssue(String id, Issue issue) {
        String url = REST_SERVICE_URI + "rest/api/2/issue/" + id;

        HttpHeaders headers = createHttpHeaders();
        HttpEntity request = new HttpEntity<>(issue, headers);

        restTemplate.exchange(url, HttpMethod.PUT, request, Issue.class);
    }

    @Override
    public ResponseEntity<?> createIssue(Issue issue) {
        String url = REST_SERVICE_URI + "/rest/api/2/issue";
        HttpHeaders headers = createHttpHeaders();
        HttpEntity request = new HttpEntity<>(issue, headers);

        Object obj = restTemplate.postForObject(url, request, Issue.class);

        return new ResponseEntity<>(obj, HttpStatus.OK);

    }


}
