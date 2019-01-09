package com.engineerproject.recommendationsystem.api.test;

import com.engineerproject.recommendationsystem.app.ann.dto.DataSetRowDTO;
import com.engineerproject.recommendationsystem.infrastructure.rest.AnnRestClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TestEndpoint {

    private final AnnRestClient annRestClient = new AnnRestClient();

    @GetMapping(value = "/test")
    private ResponseEntity<List<DataSetRowDTO>> testMethod(Pageable pageable) {
        log.info("start test");
        return ResponseEntity.ok(annRestClient.getDataSetForPrediction("Ue6-WhXvI-_1xUIuapl0zQ", Collections.singletonList("gVmUR8rqUFdbSeZbsg6z_w")));
    }
}
