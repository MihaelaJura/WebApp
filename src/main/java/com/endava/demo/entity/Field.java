package com.endava.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Field {
    private Project project;
    private String summary;
    private String description;
    private Issuetype issuetype;
    private String[] labels;
    private Assignee assignee;
}
