package com.chat.datamodel.service;

import com.chat.model.BaseRequestResponse.*;
import com.chat.model.CreateChatRequestResponse.*;

/**
 * @author ganeshallampalli
 */

public interface ChatDocDAOService {

    BaseResponse<CreateChatResponse> createChat(CreateChatRequest createChatRequest);

}
