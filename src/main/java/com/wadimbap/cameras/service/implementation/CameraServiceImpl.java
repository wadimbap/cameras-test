package com.wadimbap.cameras.service.implementation;

import com.wadimbap.cameras.dto.AggregatedDataDTO;
import com.wadimbap.cameras.dto.mapper.CameraMapper;
import com.wadimbap.cameras.model.request.Camera;
import com.wadimbap.cameras.model.request.SourceData;
import com.wadimbap.cameras.model.request.TokenData;
import com.wadimbap.cameras.service.CameraService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CameraServiceImpl implements CameraService {

    private final WebClient webClient;
    private final CameraMapper mapper = CameraMapper.INSTANCE;

    public CameraServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<AggregatedDataDTO> getAggregatedData() {
        return webClient.get()
                .uri("/v3/362027cf-d1df-4c6c-87c3-1c9ab1a8a3d6")
                .retrieve()
                .bodyToFlux(Camera.class)
                .flatMap(this::aggregateData);
    }

    private Mono<AggregatedDataDTO> aggregateData(Camera camera) {
        Mono<SourceData> sourceDataMono = getSourceDataMono(camera);
        Mono<TokenData> tokenDataMono = getTokenDataMono(camera);

        return Mono.zip(sourceDataMono, tokenDataMono)
                .map(tuple -> mapper.toAggregatedDataDTO(camera, tuple.getT1(), tuple.getT2()));
    }

    private Mono<TokenData> getTokenDataMono(Camera camera) {
        return webClient.get()
                .uri(camera.getTokenDataUrl())
                .retrieve()
                .bodyToMono(TokenData.class)
                .onErrorResume( e -> {
                    System.err.println("Error fetching token data: " + e.getMessage());
                    return Mono.empty();
                });
    }

    private Mono<SourceData> getSourceDataMono(Camera camera) {
        return webClient.get()
                .uri(camera.getSourceDataUrl())
                .retrieve()
                .bodyToMono(SourceData.class)
                .onErrorResume( e -> {
                    System.err.println("Error fetching source data: " + e.getMessage());
                    return Mono.empty();
                });
    }
}