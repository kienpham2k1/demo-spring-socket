package org.example.realtimechat.models;


import lombok.*;
import org.example.realtimechat.enums.MessageType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChatMessage {
    private  String content, sender;
    private MessageType messageType;
}
