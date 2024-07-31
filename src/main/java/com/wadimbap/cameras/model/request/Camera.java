package com.wadimbap.cameras.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Camera {
    private int id;
    private String sourceDataUrl;
    private String tokenDataUrl;
}

