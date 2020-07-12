package com.hawkeye.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message<T> implements Serializable {
    private String id;
    private T content;
}