package com.chat.model;

import lombok.Data;

/**
 * @author ganeshallampalli
 */

public interface CreateChatRequestResponse {

    @Data
    public class CreateChatRequest extends BaseRequestResponse.BaseRequest {

        private String initiatedFrom;

        private String initiatedTo;
    }

    @Data
    public class CreateChatResponse {

        private String initiatedFrom;

        private String initiatedTo;

        private String chatId;
    }
}
