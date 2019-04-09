package com.endava.demo.controller;

import com.endava.demo.entity.Issue;
import com.endava.demo.service.impl.JiraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@PropertySource("classpath:application.properties")
public class JIRARestController {

    @Autowired
    JiraServiceImpl jiraService;


    @GetMapping("/test")
    public HttpHeaders getTest() {
        return jiraService.createHttpHeaders();
    }

    @GetMapping("/getIssueByUser")
    public ResponseEntity<String> getAllTasksByUsername(@RequestParam String user) {
        return jiraService.getAllTasksByUsernameCreatedToday(user);
    }

    @GetMapping("/getIssue/{id}")
    public ResponseEntity<String> getIssue(@PathVariable String id) {
        return jiraService.getIssueById(id);
    }

    @PutMapping("/createIssue")
    public ResponseEntity<?> createIssue(@RequestBody Issue issue) {
        return jiraService.createIssue(issue);
    }

    @PostMapping("/updateIssue/{id}")
    public void updateIssue(@PathVariable String id, @RequestBody Issue issue) {
       jiraService.updateIssue(id,issue);
    }


}
