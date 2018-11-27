package com.engineerproject.recommendationsystem.infrastructure.rest.utils;

import com.engineerproject.recommendationsystem.app.ann.dto.RowDataSetDTO;
import com.engineerproject.recommendationsystem.app.neighbors.dto.RatesPairDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.List;

public class HttpUtils {
    public static HttpHeaders getBasicHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public static <T> HttpEntity<T> getBasicHttpEntity(T body) {
        return new HttpEntity<>(body, getBasicHeaders());
    }

    public static HttpEntity<Object> getBasicHttpEntity() {
        return new HttpEntity<>(getBasicHeaders());
    }

    public static ParameterizedTypeReference<List<RatesPairDTO>> getRatesPairListType() {
        return new ParameterizedTypeReference<List<RatesPairDTO>>() {
        };
    }

    public static ParameterizedTypeReference<List<RowDataSetDTO>> getRowDataSetListType() {
        return new ParameterizedTypeReference<List<RowDataSetDTO>>() {
        };
    }
}
