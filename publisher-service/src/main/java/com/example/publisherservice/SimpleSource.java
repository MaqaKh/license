package com.example.publisherservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class SimpleSource {
    private static final Logger logger =
            LoggerFactory.getLogger(SimpleSource.class);

    private Source source;

    public SimpleSource(Source source) {
        this.source = source;
    }

    public License publishOrganizationChange() {
        logger.debug("Sending Kafka message");
        License change = new License(UUID.randomUUID().toString(), "Name" +new Random().nextInt(8));
        source.output().send(MessageBuilder
                .withPayload(change)
                .build());
        return change;
    }
}
