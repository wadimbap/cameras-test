package com.wadimbap.cameras.model.request;

import com.wadimbap.cameras.model.UrlType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SourceData {
    private UrlType urlType;
    private String videoUrl;
}
