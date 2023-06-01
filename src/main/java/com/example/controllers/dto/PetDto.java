package com.example.controllers.dto;

import com.example.entity.Pet;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class  PetDto {

    private Long id;
    private String name;
    private Pet.PetType type;

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

    public Pet.PetType getType() {
        return type;
    }

    public void setType(Pet.PetType type) {
        this.type = type;
    }
}
