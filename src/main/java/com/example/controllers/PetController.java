package com.example.controllers;

import com.example.controllers.dto.PetDto;
import com.example.repositories.PetRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller("/pets")
class PetController {

    private final PetRepository petRepository;

    PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Get
    Flux<PetDto> all() {
        return petRepository.list();
    }

    @Get("/{name}")
    Mono<PetDto> byName(String name) {
        return petRepository.findByName(name);
    }

}