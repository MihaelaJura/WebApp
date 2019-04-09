package com.endava.demo.service.impl;

import com.endava.demo.entity.Issue;
import com.endava.demo.entity.Session;
import com.endava.demo.service.JiraService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class JiraServiceImpl implements JiraService {

    Session scookie = new Session();


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
    public String getCookie() {
        String url = REST_SERVICE_URI + "rest/auth/1/session";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);

        HttpEntity request = new HttpEntity<>(body, headers);
        String obj = restTemplate.postForObject(url, request, String.class);
        String cookie = obj.substring(41, 73);

        scookie.setValue(cookie);
        return cookie;
    }


    @Override
    public ResponseEntity<String> getAllTasksByUsernameCreatedToday(String user) {
        String url = REST_SERVICE_URI + "rest/api/2/search?jql=project=\"IIA\"AND assignee =" + "\"" + user + "\"AND createdDate>=startOfDay()";

        HttpHeaders headers = new HttpHeaders();
        headers.set("cookie", "JSESSIONID=" + scookie.getValue());

        HttpEntity request = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        return result;
    }


    @Override
    public ResponseEntity<String> getIssueById(String id) {
        String url = REST_SERVICE_URI + "rest/api/2/issue/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.set("cookie", "JSESSIONID=" + scookie.getValue());

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
