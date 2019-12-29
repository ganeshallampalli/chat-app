package com.chat.model;

import lombok.Data;

import java.util.List;

/**
 * @author ganeshallampalli
 */

public interface FetchMessageRequestResponse {

    /*@Data
    public class FetchMessageRequest extends BaseRequestResponse.BaseRequest {

        private String chatId;
    }*/

    @Data
    public class FetchMessageResponse {

        private String chatId;

        private List<Message> messages;
    }

    @Data
    public class Message {

        private Integer id;

        private String initiatedFrom;

        private String initiatedTo;

        private String sentTime;

        public Message(Integer id, String initiatedFrom, String initiatedTo, String sentTime) {
            this.id = id;
            this.initiatedFrom = initiatedFrom;
            this.initiatedTo = initiatedTo;
            this.sentTime = sentTime;
        }
    }
}
