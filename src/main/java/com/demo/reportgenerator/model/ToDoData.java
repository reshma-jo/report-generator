package com.demo.reportgenerator.model;

import lombok.Data;

import java.util.Date;

@Data
public class ToDoData {
    private long id;
    private String title;
    private boolean completed;
    private String dueDate;
    private Date createdAt;
}
