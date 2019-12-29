package com.chat.controller;

import com.chat.datamodel.service.MessageHistoryDAOService;
import com.chat.model.BaseRequestResponse.*;
import com.chat.model.FetchMessageRequestResponse.*;
import com.chat.model.SaveMessageRequestResponse.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ganeshallampalli
 */

@Api(value = "APIs exposed to serve message operations.")
@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageHistoryDAOService messageHistoryDAOService;

    @PostMapping("/v1/message")
    public BaseResponse<SaveMessageResponse> saveMessage(@RequestBody SaveMessageRequest saveMessageRequest) {
        return messageHistoryDAOService.saveMessage(saveMessageRequest);
    }

    @GetMapping("/v1/messages")
    public BaseResponse<FetchMessageResponse> fetchMessages(@RequestParam("chatId") String chatId) {
        return messageHistoryDAOService.fetchMessages(chatId);
    }
}
