package com.wadimbap.cameras.service;

import com.wadimbap.cameras.dto.AggregatedDataDTO;
import reactor.core.publisher.Flux;

public interface CameraService {
    Flux<AggregatedDataDTO> getAggregatedData();
}