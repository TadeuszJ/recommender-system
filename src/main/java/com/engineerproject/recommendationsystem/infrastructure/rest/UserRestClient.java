package com.engineerproject.recommendationsystem.infrastructure.rest;

import com.engineerproject.recommendationsystem.infrastructure.rest.utils.UrlConstant;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UserRestClient {
    private static final String USERS_URI = UrlConstant.BASE_URI + "/user";

    public List<String> getPotentialNeighbors(String userId) {
        final String uri = USERS_URI + "/" + userId + "/potential-neighbors";
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<List<String>> listOfStringsType = new ParameterizedTypeReference<List<String>>() {
        };

        ResponseEntity<List<String>> result = restTemplate.exchange(uri, HttpMethod.GET, null, listOfStringsType);

        return result.getBody();
    }
}
