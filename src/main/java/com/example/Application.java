package com.example;

import com.example.entity.Owner;
import com.example.entity.Pet;
import com.example.repositories.OwnerRepository;
import com.example.repositories.PetRepository;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.data.r2dbc.operations.R2dbcOperations;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.exceptions.ApplicationStartupException;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Singleton
public class Application {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final R2dbcOperations operations;

    public Application(OwnerRepository ownerRepository, PetRepository petRepository, R2dbcOperations operations) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.operations = operations;
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }

    @EventListener
    void init(StartupEvent event) {
        Owner fred = new Owner();
        fred.setName("Fred");
        fred.setAge(45);
        Owner barney = new Owner();
        barney.setName("Barney");
        barney.setAge(40);

        Pet dino = new Pet();
        dino.setName("Dino");
        dino.setOwner(fred);
        Pet bp = new Pet();
        bp.setName("Baby Puss");
        bp.setOwner(fred);
        bp.setType(Pet.PetType.CAT);
        Pet hoppy = new Pet();
        hoppy.setName("Hoppy");
        hoppy.setOwner(barney);

        try {
            Flux.from(operations.withTransaction((status) ->
                    Flux.from(ownerRepository.saveAll(Arrays.asList(fred, barney), status.getConnection()))
                        .collectList().flatMapMany(owners ->
                            petRepository.saveAll(Arrays.asList(dino, bp, hoppy), status.getConnection())
                    )
            )).collectList().block();
        } catch (Exception e) {
            throw new ApplicationStartupException("Error saving initial data: " + e.getMessage());
        }
    }
}
