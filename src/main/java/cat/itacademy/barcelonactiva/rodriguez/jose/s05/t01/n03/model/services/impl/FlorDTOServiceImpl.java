package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t01.n03.model.services.impl;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t01.n03.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t01.n03.model.services.FlorDTOService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class FlorDTOServiceImpl implements FlorDTOService {

    final
    WebClient webClient;

    public FlorDTOServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<FlorDTO> findAll() {
        return webClient.get()
                .uri("/flor/getAll")
                .retrieve()
                .bodyToFlux(FlorDTO.class)
                .timeout(Duration.ofMillis(10_000));
    }

    @Override
    public Mono<FlorDTO> findById(Long id) {
        return webClient.get()
                .uri("/flor/getOne/" + id)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        clientResponse -> Mono.empty())
                .bodyToMono(FlorDTO.class);
    }

    @Override
    public Mono<FlorDTO> create(FlorDTO e) {
        return webClient.post()
                .uri("/flor/add")
                .body(Mono.just(e), FlorDTO.class)
                .retrieve()
                .bodyToMono(FlorDTO.class)
                .timeout(Duration.ofMillis(10_000));
    }

    @Override
    public Mono<FlorDTO> update(FlorDTO e) {
        return webClient.put()
                .uri("/flor/update/" + e.getPk_FlorID())
                .body(Mono.just(e), FlorDTO.class)
                .retrieve()
                .bodyToMono(FlorDTO.class);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return webClient.delete()
                .uri("/flor/delete/" + id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}