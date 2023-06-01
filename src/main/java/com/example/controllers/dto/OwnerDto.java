package com.example.controllers.dto;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class OwnerDto {

    @Nullable
    private Long id;
    private String name;
    private int age;

    public OwnerDto(@Nullable Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
