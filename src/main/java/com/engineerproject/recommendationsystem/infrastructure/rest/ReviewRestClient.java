package com.engineerproject.recommendationsystem.infrastructure.rest;

import com.engineerproject.recommendationsystem.app.neighbors.dto.RatesPairDTO;
import com.engineerproject.recommendationsystem.infrastructure.rest.dto.UsersPairDTO;
import com.engineerproject.recommendationsystem.infrastructure.rest.utils.HttpUtils;
import com.engineerproject.recommendationsystem.infrastructure.rest.utils.UrlConstant;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ReviewRestClient {
    private static final String REVIEW_URI = UrlConstant.BASE_URI + "/review";
    private RestTemplate restTemplate = new RestTemplate();

    public List<RatesPairDTO> getCommonsStars(UsersPairDTO usersPair) {
        String uri = REVIEW_URI + "/rates-pairs";
        HttpEntity<UsersPairDTO> entity = HttpUtils.getBasicHttpEntity(usersPair);

        ResponseEntity<List<RatesPairDTO>> result = restTemplate.exchange(uri, HttpMethod.POST, entity, HttpUtils.getRatesPairListType());
        return result.getBody();
    }
}
