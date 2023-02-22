package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.services.LicenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(value = "v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
    private static final Logger logger =
            LoggerFactory.getLogger(LicenseServiceController.class);
    @Autowired
    private LicenseService licenseService;

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    public License getLicenses(@PathVariable("organizationId") String organizationId,
                               @PathVariable("licenseId") String licenseId) {

        logger.info("calling method");
        //return licenseService.getLicense(licenseId);
        return new License()
                .withId(licenseId)
                .withOrganizationId(organizationId)
                .withProductName("Teleco")
                .withLicenseType("Seat");
    }

    @RequestMapping(value = "/{licenseId}/timeout", method = RequestMethod.GET)
    public License getLicensesTimeout(@PathVariable("organizationId") String organizationId,
                                      @PathVariable("licenseId") String licenseId) {
        try {
            Thread.sleep(5000);
            throw new TimeoutException("Exception happened");
        } catch (InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }

    }

    @RequestMapping(value = "{licenseId}", method = RequestMethod.PUT)
    public String updateLicenses(@PathVariable("licenseId") String licenseId) {
        return String.format("This is the put");
    }

    @RequestMapping(value = "{licenseId}", method = RequestMethod.POST)
    public String saveLicenses(@PathVariable("licenseId") String licenseId) {
        return String.format("This is the post");
    }

    @RequestMapping(value = "{licenseId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteLicenses(@PathVariable("licenseId") String licenseId) {
        return String.format("This is the Delete");
    }
}
