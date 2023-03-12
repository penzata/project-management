//package org.project.management.model.message;
//
//public class MessagingService {
//    private static MessagingService messagingServiceInstance;
//
//    private MessagingService() {
//    }
//
//    public static MessagingService getInstance() {
//        if (messagingServiceInstance == null) {
//            messagingServiceInstance = new MessagingService();
//        }
//        return messagingServiceInstance;
//    }
//
//    public void produceEvent(String event) {
//        MessagingBroker.produceEvent(event);
//    }
//}