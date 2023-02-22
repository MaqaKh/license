package com.example.organization;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    Service service;

    @GetMapping("license/{orgId}/{licenseId}")
    public License call(@PathVariable("orgId") String orgId, @PathVariable("licenseId") String licenseId) {
        ResponseEntity<License> license = service.getExchange(orgId, licenseId);
        return license.getBody();
    }

    @GetMapping("license/{orgId}/{licenseId}/resilent")
    public License resilent(@PathVariable("orgId") String orgId, @PathVariable("licenseId") String licenseId) {
        ResponseEntity<License> license = service.getExchangeResilent(orgId, licenseId);
        return license.getBody();
    }


}
