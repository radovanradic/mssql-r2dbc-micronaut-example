package com.example.repositories;

import com.example.controllers.dto.PetDto;
import com.example.entity.Pet;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import io.r2dbc.spi.Connection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@R2dbcRepository(dialect = Dialect.SQL_SERVER)
public interface PetRepository extends ReactorCrudRepository<Pet, Long> {
    Flux<PetDto> list();

    Mono<PetDto> findByName(String name);

    Flux<Pet> saveAll(@Valid @NotNull Iterable<Pet> entities, Connection connection);
}
