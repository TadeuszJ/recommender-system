package com.engineerproject.recommendationsystem.infrastructure.rest;

import com.engineerproject.recommendationsystem.app.ann.dto.DataSetRowDTO;
import com.engineerproject.recommendationsystem.infrastructure.rest.utils.HttpUtils;
import com.engineerproject.recommendationsystem.infrastructure.rest.utils.UrlConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class AnnRestClient {

    private static final String ANN_URI = UrlConstant.BASE_URI + "/ann";
    private static final String DATA_SET = "/data-set";
    private RestTemplate restTemplate = new RestTemplate();

    public List<DataSetRowDTO> getDataSetForLearn(List<String> userIds) {
        String joinedUserIds = StringUtils.join(userIds, ",");
        String uri = ANN_URI + "/user/" + joinedUserIds + DATA_SET;
        HttpEntity<Object> entity = HttpUtils.getBasicHttpEntity();

        ResponseEntity<List<DataSetRowDTO>> result = restTemplate.exchange(uri, HttpMethod.GET, entity, HttpUtils.getRowDataSetListType());
        return result.getBody();
    }

    public List<DataSetRowDTO> getDataSetForPrediction(String userId, List<String> neighborIds) {
        String joinedNeighborIds = StringUtils.join(neighborIds, ",");
        String uri = ANN_URI + "/user/" + userId + "/neighbors/" + joinedNeighborIds + DATA_SET;
        HttpEntity<Object> entity = HttpUtils.getBasicHttpEntity();

        ResponseEntity<List<DataSetRowDTO>> result = restTemplate.exchange(uri, HttpMethod.GET, entity, HttpUtils.getRowDataSetListType());
        return result.getBody();
    }

    public List<DataSetRowDTO> getDataSet(Pageable pageable) {
        String uri = UriComponentsBuilder
                .fromUriString(ANN_URI + DATA_SET)
                .queryParam("size", pageable.getPageSize())
                .queryParam("page", pageable.getPageNumber())
                .build().toUriString();
        HttpEntity<Object> entity = HttpUtils.getBasicHttpEntity();

        ResponseEntity<List<DataSetRowDTO>> result = restTemplate.exchange(uri, HttpMethod.GET, entity, HttpUtils.getRowDataSetListType());
        return result.getBody();
    }
}
