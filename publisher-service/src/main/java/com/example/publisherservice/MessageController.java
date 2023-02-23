package com.example.publisherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired SimpleSource simpleSource;
    public MessageController() {

    }

    @GetMapping("send")
    public String send(){
        License license = simpleSource.publishOrganizationChange();
        return license.id;
    }
}
