package com.example.organization;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class Service {
    private final Logger log = LoggerFactory.getLogger(Service.class);
    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<License> getExchange(String orgId, String licenseId) {
        return restTemplate.exchange("http://license/v1/organizations/{orgId}/licenses/{id}", HttpMethod.GET,
                null, License.class, orgId, licenseId);
    }

//    @CircuitBreaker(name = "licenseService", fallbackMethod = "fallbackMethod") // enable circuit breaker
    @Retry(name = "retryLicenseService", fallbackMethod = "fallbackMethod")
    public ResponseEntity<License> getExchangeResilent(String orgId, String licenseId) {
        log.info("resilent exchange");
        return restTemplate.exchange("http://license/v1/organizations/{orgId}/licenses/{id}/timeout", HttpMethod.GET,
                null, License.class, orgId, licenseId);
    }

    public ResponseEntity<License> fallbackMethod(Throwable t) {
        log.info("fall back method call");
        return ResponseEntity.of(Optional.of(new License("1", "1", "1", "1")));
    }
}
