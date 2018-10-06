package com.engineerproject.recommendationsystem.infrastructure.rest;

import com.engineerproject.recommendationsystem.app.neighbors.dto.RatesPairDTO;
import com.engineerproject.recommendationsystem.infrastructure.rest.dto.UsersPairDTO;
import com.engineerproject.recommendationsystem.infrastructure.rest.utils.UrlConstant;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class ReviewRestClient {
    private static final String REVIEW_URI = UrlConstant.BASE_URI + "/review";

    public List<RatesPairDTO> getCommonsStars(String activeUserId, String selectedUserId) {
        String uri = REVIEW_URI + "/rates-pairs";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        UsersPairDTO body = new UsersPairDTO(activeUserId, selectedUserId);

        HttpEntity<UsersPairDTO> entity = new HttpEntity<>(body, headers);

        ResponseEntity<List<RatesPairDTO>> result = restTemplate.exchange(uri, HttpMethod.POST, entity, new ParameterizedTypeReference<List<RatesPairDTO>>() {
        });

        return result.getBody();
    }
}
