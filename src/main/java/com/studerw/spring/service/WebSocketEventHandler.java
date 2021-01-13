package com.studerw.spring.service;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

/**
 * This just shows how Websocket Events can be caught to insert custom logic. We only log the events for now.
 */
@Component
public class WebSocketEventHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventHandler.class);
  private final Random random = new Random();
  private SimpMessageSendingOperations messaging;
  private Environment env;

  @EventListener
  private void handleSessionConnected(SessionConnectEvent event) {
    try {
      String sessionId = (String) event.getMessage().getHeaders().get(SimpMessageHeaderAccessor.SESSION_ID_HEADER);
      LOGGER.info("Session connected: {}", sessionId);
    } catch (Exception e) {
      LOGGER.warn("Unable to parse Stomp session Disconnect: {}", event);
    }
  }

  @EventListener
  private void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
    try {
      String sessionId = (String) event.getMessage().getHeaders().get(SimpMessageHeaderAccessor.SESSION_ID_HEADER);
      String subscriptionId = (String) event.getMessage().getHeaders().get(SimpMessageHeaderAccessor.SUBSCRIPTION_ID_HEADER);
      LOGGER.info("Subscription: sessionId={}, subscriptionId={}", sessionId, subscriptionId);
    } catch (Exception e) {
      LOGGER.warn("Unable to parse Stomp subscription event: {}", event);
    }
  }

  @EventListener
  private void handleSessionUnsubscribeEvent(SessionUnsubscribeEvent event) {
    try {
      String subscriptionId = (String) event.getMessage().getHeaders().get(SimpMessageHeaderAccessor.SUBSCRIPTION_ID_HEADER);
      String sessionId = (String) event.getMessage().getHeaders().get(SimpMessageHeaderAccessor.SESSION_ID_HEADER);
      LOGGER.info("Unsubscribe: sessionId={}, subscriptionId={}", sessionId, subscriptionId);
    } catch (Exception e) {
      LOGGER.warn("Unable to parse Stomp un-subscription event: {}", event);
    }
  }

  @EventListener
  private void handleSessionDisconnect(SessionDisconnectEvent event) {
    try {
    String sessionId = (String) event.getMessage().getHeaders().get(SimpMessageHeaderAccessor.SESSION_ID_HEADER);
    LOGGER.info("Session disconnected: {}", sessionId);
    } catch (Exception e) {
      LOGGER.warn("Unable to parse Stomp session Disconnect: {}", event);
    }
  }

}
