package com.endava.demo.service;

import com.endava.demo.entity.Issue;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface JiraService {

    HttpHeaders createHttpHeaders();
    String getCookie();
    ResponseEntity<String> getAllTasksByUsernameCreatedToday(String user);
    ResponseEntity<String> getIssueById(String id);
    void updateIssue( String id, Issue issue);
    ResponseEntity<?> createIssue(Issue issue);

}
