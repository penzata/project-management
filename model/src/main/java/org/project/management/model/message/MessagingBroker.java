package org.project.management.model.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface MessagingBroker {
    Logger log = LoggerFactory.getLogger(MessagingBroker.class);

    static void produceEvent(String event) {
        log.info("Event produced: {}", event);
    }
}