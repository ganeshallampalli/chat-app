package com.chat.controller;

import com.chat.datamodel.service.MessageHistoryDAOService;
import com.chat.model.BaseRequestResponse.*;
import com.chat.model.SaveMessageRequestResponse.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
