package com.engineerproject.recommendationsystem.infrastructure.rest;

import com.engineerproject.recommendationsystem.app.ann.dto.RowDataSetDTO;
import com.engineerproject.recommendationsystem.app.neighbors.dto.RatesPairDTO;
import com.engineerproject.recommendationsystem.infrastructure.rest.dto.UsersPairDTO;
import com.engineerproject.recommendationsystem.infrastructure.rest.utils.HttpUtils;
import com.engineerproject.recommendationsystem.infrastructure.rest.utils.UrlConstant;
import org.apache.commons.lang3.StringUtils;
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

    public Integer getRate(String userId, String businessId) {
        String uri = REVIEW_URI + "/rate/user/" + userId + "/business/" + businessId;
        HttpEntity<Object> entity = HttpUtils.getBasicHttpEntity();

        ResponseEntity<Integer> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Integer.class);
        return result.getBody();
    }

    public List<RowDataSetDTO> getDataSet(List<String> userIds, String businessId) {
        String joinedUserIds = StringUtils.join(userIds, ",");
        String uri = REVIEW_URI + "/ann-data-set/user/" + joinedUserIds + "/business/" + businessId;
        HttpEntity<Object> entity = HttpUtils.getBasicHttpEntity();

        ResponseEntity<List<RowDataSetDTO>> result = restTemplate.exchange(uri, HttpMethod.GET, entity, HttpUtils.getRowDataSetListType());
        return result.getBody();
    }
}
