package com.example.repositories;

import com.example.controllers.dto.OwnerDto;
import com.example.entity.Owner;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import io.r2dbc.spi.Connection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@R2dbcRepository(dialect = Dialect.SQL_SERVER)
public interface OwnerRepository extends ReactorCrudRepository<Owner, Long> {
    Flux<OwnerDto> list();

    Mono<OwnerDto> findByName(String name);

    Mono<OwnerDto> getByName(String name);

    Flux<Owner> saveAll(@Valid @NotNull Iterable<Owner> entities, Connection connection);
}
