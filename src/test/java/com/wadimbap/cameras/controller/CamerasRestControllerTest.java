package com.wadimbap.cameras.controller;

import com.wadimbap.cameras.dto.AggregatedDataDTO;
import com.wadimbap.cameras.model.UrlType;
import com.wadimbap.cameras.service.CameraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebFluxTest(CamerasRestController.class)
public class CamerasRestControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CameraService cameraService;

    @Test
    public void testGetAggregatedData_Success() {
        AggregatedDataDTO aggregatedDataDTO = new AggregatedDataDTO(
                1,
                UrlType.LIVE.toString(),
                "http://example.com/video",
                "tokenValue",
                3600
        );

        given(cameraService.getAggregatedData()).willReturn(Flux.just(aggregatedDataDTO));

        webTestClient.get().uri("/api/v1/cameras/aggregated")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBodyList(AggregatedDataDTO.class)
                .hasSize(1)
                .contains(aggregatedDataDTO);
    }

    @Test
    public void testGetAggregatedData_Failure() {
        given(cameraService.getAggregatedData()).willReturn(Flux.error(new RuntimeException("Service error")));

        webTestClient.get().uri("/api/v1/cameras/aggregated")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.status").isEqualTo(500)
                .jsonPath("$.error").isEqualTo("Internal Server Error")
                .jsonPath("$.message").isEqualTo("Service error");
    }

}
