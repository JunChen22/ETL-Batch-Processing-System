package io.github.jun.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Consumer;

@Configuration
public class MessageProcessorConfig {

    private static final Logger LOG = LoggerFactory.getLogger(MessageProcessorConfig.class);

    /**
     * Process incoming
     */
//    @Bean
//    public Consumer<PmsReviewEvent> reviewMessageProcessor() {
//        // lambda expression of override method accept
//        return event -> {
//            LOG.info("Process message created at {}...", event.getEventCreatedAt());
//
//            ProductReview review = event.getReview();
//            String skuCode = review.review().skuCode();
//            UUID userId = event.getUserId();
//
//            switch (event.getEventType()) {
//                default:
//                    String errorMessage = "Incorrect event type:" + event.getEventType() + ", expected CREATE_REVIEW, UPDATE_REVIEW, DELETE_REVIEW event";
//                    LOG.warn(errorMessage);
//                    throw new RuntimeException(errorMessage);
//            }
//        };
//    }
}
