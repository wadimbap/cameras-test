package com.wadimbap.cameras.model.response;

import com.wadimbap.cameras.model.UrlType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AggregatedData {
    private int id;
    private UrlType urlType;
    private String videoUrl;
    private String value;
    private int ttl;
}
