package org.project.management.model.service.impl;

import org.project.management.model.service.MessagingBroker;

public class MessagingService {

    private static MessagingService messagingServiceInstance;
    MessagingBroker messagingBroker;

    private MessagingService() {
    }

    public static MessagingService getInstance() {
        if (messagingServiceInstance == null) {
            messagingServiceInstance = new MessagingService();
        }
        return messagingServiceInstance;
    }

    public void produceEvent(String event) {
        messagingBroker.produceEvent(event);
    }
}
