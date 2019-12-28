package com.chat.datamodel.service.impl;

import com.chat.datamodel.ChatDoc;
import com.chat.datamodel.repository.ChatDocRepository;
import com.chat.datamodel.repository.UserRepository;
import com.chat.datamodel.service.ChatDocDAOService;
import com.chat.model.BaseRequestResponse.BaseResponse;
import com.chat.model.CreateChatRequestResponse.CreateChatRequest;
import com.chat.model.CreateChatRequestResponse.CreateChatResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ganeshallampalli
 */

@Service
public class ChatDocDAOServiceImpl implements ChatDocDAOService {

    @Autowired
    ChatDocRepository chatDocRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public BaseResponse<CreateChatResponse> createChat(CreateChatRequest createChatRequest) {
        BaseResponse<CreateChatResponse> baseResponse = new BaseResponse<>();
        String initiatedFrom = createChatRequest.getInitiatedFrom();
        String initiatedTo = createChatRequest.getInitiatedTo();

        if (null == userRepository.findByUserId(initiatedFrom) || null == userRepository.findByUserId(initiatedTo)) {
            baseResponse.setCode("1000001");
            baseResponse.setMessage("Either of the participants in the chat is not registered.");
            baseResponse.setResponseData(null);
            return baseResponse;
        }

        String chatId = RandomStringUtils.randomAlphanumeric(32);
        ChatDoc chatDoc = new ChatDoc();
        chatDoc.setChatId(chatId);
        chatDoc.setCreatedOn(new Date());
        chatDoc.setInitiatedFrom(initiatedFrom);
        chatDoc.setInitiatedTo(initiatedTo);
        chatDoc.setUpdatedOn(new Date());

        try {
            ChatDoc savedChatDoc = chatDocRepository.save(chatDoc);
            if (null == savedChatDoc) {
                baseResponse.setCode("1000002");
                baseResponse.setMessage("Unable to create the chat. Please try again.");
                baseResponse.setResponseData(null);
                return baseResponse;
            }
            CreateChatResponse createChatResponse = new CreateChatResponse();

            createChatResponse.setChatId(savedChatDoc.getChatId());
            createChatResponse.setInitiatedFrom(savedChatDoc.getInitiatedFrom());
            createChatResponse.setInitiatedTo(savedChatDoc.getInitiatedTo());

            baseResponse.setCode("000");
            baseResponse.setMessage("Chat created successfully.");
            baseResponse.setResponseData(createChatResponse);
            return baseResponse;

        } catch (Exception ex) {
            baseResponse.setCode("1000003");
            baseResponse.setMessage("There's seems to be some internal issues while creating chat. Please try again.");
            baseResponse.setResponseData(null);
            return baseResponse;
        }
    }
}
