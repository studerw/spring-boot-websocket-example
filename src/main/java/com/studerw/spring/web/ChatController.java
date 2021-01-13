package com.studerw.spring.web;

import com.studerw.spring.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

  @MessageMapping("/message")
  @SendTo("/topic/chat")
  public ChatMessage greeting(ChatMessage message) throws Exception {
    LOGGER.info("Got chat message: {}", message);

    Thread.sleep(500); // simulated delay
    return message;
  }

  @GetMapping(value = "/ping", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> ping() {
    LOGGER.debug("Getting ping");

    return ResponseEntity
        .status(HttpStatus.OK)
        .body("pong");
  }

}
