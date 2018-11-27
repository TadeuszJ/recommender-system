package com.engineerproject.recommendationsystem.api;

import com.engineerproject.recommendationsystem.app.neighbors.NeighborsService;
import com.engineerproject.recommendationsystem.infrastructure.mongodb.user.Users;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/neighbors")
public class NeighborsEndpoint {

    private final NeighborsService neighborsService;

    @GetMapping("/{id}/update")
    private ResponseEntity<Users> update(@PathVariable String id) {
        return ResponseEntity.ok(neighborsService.updateNeighbors(id));
    }

    @GetMapping("/{userId}/update/business/{businessId}")
    private ResponseEntity<Users> update(@PathParam("userId") String userId, @PathParam("businessId") String businessId) {
        return ResponseEntity.ok(neighborsService.updateNeighbors(userId));
    }
}
