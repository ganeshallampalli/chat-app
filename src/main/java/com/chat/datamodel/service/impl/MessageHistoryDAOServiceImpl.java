package com.chat.datamodel.service.impl;

import com.chat.datamodel.MessageHistory;
import com.chat.datamodel.repository.ChatDocRepository;
import com.chat.datamodel.repository.MessageHistoryRepository;
import com.chat.datamodel.service.MessageHistoryDAOService;
import com.chat.model.BaseRequestResponse.BaseResponse;
import com.chat.model.FetchMessageRequestResponse.FetchMessageResponse;
import com.chat.model.FetchMessageRequestResponse.Message;
import com.chat.model.SaveMessageRequestResponse.SaveMessageRequest;
import com.chat.model.SaveMessageRequestResponse.SaveMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ganeshallampalli
 */

@Service
@Slf4j
public class MessageHistoryDAOServiceImpl implements MessageHistoryDAOService {

    @Autowired
    MessageHistoryRepository messageHistoryRepository;

    @Autowired
    ChatDocRepository chatDocRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

    @Override
    public BaseResponse<SaveMessageResponse> saveMessage(SaveMessageRequest saveMessageRequest) {
        BaseResponse<SaveMessageResponse> baseResponse = new BaseResponse<>();

        String from = saveMessageRequest.getFrom();
        String to = saveMessageRequest.getTo();

        if (StringUtils.isBlank(saveMessageRequest.getChatId())) {
            baseResponse.setCode("1000004");
            baseResponse.setMessage("Chat ID can't be empty.");
            baseResponse.setResponseData(null);
            return baseResponse;
        }

        if (StringUtils.isBlank(from) || StringUtils.isBlank(to)) {
            baseResponse.setCode("1000005");
            baseResponse.setMessage("Either of participants are invalid. Please check and try again.");
            baseResponse.setResponseData(null);
            return baseResponse;
        }

        MessageHistory messageHistory = new MessageHistory();
        messageHistory.setChatId(saveMessageRequest.getChatId());
        messageHistory.setInitiatedFrom(from);
        messageHistory.setInitiatedTo(to);
        messageHistory.setMessage(saveMessageRequest.getMessage());
        messageHistory.setSentTime(new Date());

        try {
            MessageHistory savedMessageHistory = messageHistoryRepository.save(messageHistory);
            if (null == savedMessageHistory) {
                baseResponse.setCode("1000006");
                baseResponse.setMessage("Unable to save message. Please try again.");
                baseResponse.setResponseData(null);
                return baseResponse;
            }
            log.debug("Message saved successfully.");
            SaveMessageResponse saveMessageResponse = new SaveMessageResponse();
            saveMessageResponse.setId(savedMessageHistory.getId());
            saveMessageResponse.setMessage(savedMessageHistory.getMessage());
            saveMessageResponse.setSentTime(sdf.format(savedMessageHistory.getSentTime()));

            baseResponse.setResponseData(saveMessageResponse);
            baseResponse.setCode("000");
            baseResponse.setMessage("Message saved successfully.");
            return baseResponse;
        } catch (Exception ex) {
            log.debug("Internal Error Occurred.");
            baseResponse.setCode("1000007");
            baseResponse.setMessage("There's seems to be some internal issues while creating chat. Please try again.");
            baseResponse.setResponseData(null);
            return baseResponse;
        }
    }

    @Override
    public BaseResponse<FetchMessageResponse> fetchMessages(String chatId) {
        BaseResponse<FetchMessageResponse> baseResponse = new BaseResponse<>();

        if (StringUtils.isBlank(chatId) || null == chatDocRepository.findByChatId(chatId)) {
            baseResponse.setCode("1000008");
            baseResponse.setMessage("Invalid chat session. Please try again.");
            baseResponse.setResponseData(null);
            return baseResponse;
        }

        List<MessageHistory> messageHistories = messageHistoryRepository.findAllByChatIdOrderBySentTimeAsc(chatId);

        if (messageHistories.isEmpty()) {
            baseResponse.setCode("1000009");
            baseResponse.setMessage("There's no messages here. Please type your message.");
            baseResponse.setResponseData(null);
            return baseResponse;
        }

        FetchMessageResponse fetchMessageResponse = convertMessageHistoryToResponse(messageHistories, chatId);

        baseResponse.setCode("000");
        baseResponse.setMessage("Fetched Messages successfully.");
        baseResponse.setResponseData(fetchMessageResponse);

        return baseResponse;
    }

    private FetchMessageResponse convertMessageHistoryToResponse(List<MessageHistory> messageHistories, String chatId) {

        FetchMessageResponse fetchMessageResponse = new FetchMessageResponse();
        List<Message> messages = new ArrayList<>();
        messageHistories.forEach(messageHistory -> {
            Message message = new Message(messageHistory.getId(), messageHistory.getInitiatedFrom(), messageHistory.getInitiatedTo(), sdf.format(messageHistory.getSentTime()));
            messages.add(message);
        });

        fetchMessageResponse.setChatId(chatId);
        fetchMessageResponse.setMessages(messages);

        return fetchMessageResponse;
    }
}
