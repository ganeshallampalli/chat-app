package com.chat.controller;

import com.chat.datamodel.service.ChatDocDAOService;
import com.chat.model.BaseRequestResponse.*;
import com.chat.model.CreateChatRequestResponse.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganeshallampalli
 */

@RestController
@Api(value = "APIs exposed for chat operations.")
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private ChatDocDAOService chatDocDAOService;

    @PostMapping("/v1/chat")
    public BaseResponse<CreateChatResponse> createChat(@RequestBody CreateChatRequest createChatRequest) {
        return chatDocDAOService.createChat(createChatRequest);
    }
}
