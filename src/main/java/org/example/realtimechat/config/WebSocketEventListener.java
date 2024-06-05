package org.example.realtimechat.config;

import jakarta.websocket.Session;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.realtimechat.enums.MessageType;
import org.example.realtimechat.models.ChatMessage;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@AllArgsConstructor
@Slf4j
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageSendingOperations;
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        ChatMessage chatMessage = null;
        if (username != null) {
            log.info("User disconnected! {}", username);
            chatMessage = ChatMessage.builder()
                    .messageType(MessageType.LEAVE)
                    .build();
        }
        messageSendingOperations.convertAndSend("/topic/public", chatMessage);
    }
}
