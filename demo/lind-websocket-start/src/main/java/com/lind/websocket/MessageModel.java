package com.lind.websocket;

import lombok.Data;

@Data
public class MessageModel {
    private String toUserId;
    private String contentText;
}
