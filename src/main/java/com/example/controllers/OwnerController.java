package com.example.controllers;

import com.example.controllers.dto.OwnerDto;
import com.example.entity.Owner;
import com.example.repositories.OwnerRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller("/owners")
class OwnerController {

    private final OwnerRepository ownerRepository;

    OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Get
    Flux<OwnerDto> all() {
        return ownerRepository.list();
    }

    @Get("/{name}")
    Mono<OwnerDto> byName(@NotBlank String name) {
        return ownerRepository.findByName(name);
    }

    @Post("/")
    Mono<OwnerDto> save(@Valid @Body OwnerDto owner) {
        return Mono.from(ownerRepository.save(new Owner(owner.getName(), owner.getAge()))
            .map(o -> new OwnerDto(o.getId(), o.getName(), o.getAge())));
    }

    @Delete("/{id}")
    Mono<HttpResponse<?>> delete(@NotNull Long id) {
        return Mono.from(ownerRepository.deleteById(id))
                     .map(c -> c > 0 ? HttpResponse.noContent() : HttpResponse.notFound());
    }

    @Put("/")
    Mono<OwnerDto> update(@Valid @Body OwnerDto owner) {
        return Mono.from(ownerRepository.update(new Owner(owner.getId(), owner.getName(), owner.getAge()))
            .map(o -> new OwnerDto(o.getId(), o.getName(), o.getAge())));
    }

}
