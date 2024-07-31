package com.wadimbap.cameras.controller;

import com.wadimbap.cameras.dto.AggregatedDataDTO;
import com.wadimbap.cameras.service.CameraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/cameras")
@RequiredArgsConstructor
public class CamerasRestController {

    private final CameraService cameraService;

    @GetMapping("/aggregated")
    public Flux<AggregatedDataDTO> getAggregatedData() {
        return cameraService.getAggregatedData();
    }
}

