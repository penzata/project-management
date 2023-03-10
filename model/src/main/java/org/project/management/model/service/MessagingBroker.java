package org.project.management.model.service;

public interface MessagingBroker {

    void produceEvent(String event);
}