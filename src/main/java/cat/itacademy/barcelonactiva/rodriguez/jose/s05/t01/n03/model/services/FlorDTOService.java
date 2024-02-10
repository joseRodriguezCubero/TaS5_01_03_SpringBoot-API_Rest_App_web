package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t01.n03.model.services;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t01.n03.model.dto.FlorDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlorDTOService {
    Flux<FlorDTO> findAll();

    Mono<FlorDTO> findById(Long id);

    Mono<FlorDTO> create(FlorDTO e);

    Mono<FlorDTO> update(FlorDTO e);

    Mono<Void> delete(Long id);
}