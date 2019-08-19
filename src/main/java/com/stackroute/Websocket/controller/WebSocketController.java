package com.stackroute.Websocket.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


import java.util.Map;

@Controller
public class WebSocketController {
    private SimpMessagingTemplate template;


    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {

    this.template = template;
    }



    @MessageMapping("/send/message")
    public void onReceievingMessage(String message) throws Exception {
;
        System.out.println("Is this Working ???");
//        ObjectMapper objectMapper = new ObjectMapper();
//        Message someMessage =  objectMapper.readValue(message, Message.class);
//
//        System.out.println(someMessage.getMessage()+".............................................." + someMessage.getSessionId());

        String name = new Gson().fromJson(message, Map.class).get("message").toString();
        String session = new Gson().fromJson(message, Map.class).get("sessionId").toString();

        this.template.convertAndSendToUser(session, "/reply",name);
    }
}
