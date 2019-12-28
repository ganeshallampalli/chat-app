package com.chat.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ganeshallampalli
 */
public interface SaveMessageRequestResponse {

    @Data
    public class SaveMessageRequest extends BaseRequestResponse.BaseRequest {

        private String chatId;

        private String from;

        private String to;

        private String message;
    }

    @Data
    public class SaveMessageResponse {

        private Integer id;

        private String message;

        private String sentTime;
    }

}
