package com.chat.datamodel.service;

import com.chat.datamodel.MessageHistory;
import com.chat.model.BaseRequestResponse.*;
import com.chat.model.FetchMessageRequestResponse.*;
import com.chat.model.SaveMessageRequestResponse.*;
import com.chat.model.SaveMessageRequestResponse.SaveMessageRequest;

/**
 * @author ganeshallampalli
 */

public interface MessageHistoryDAOService {

    BaseResponse<SaveMessageResponse> saveMessage(SaveMessageRequest saveMessageRequest);

    BaseResponse<FetchMessageResponse> fetchMessages(String chatId);
}
