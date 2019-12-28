package com.chat.model;

import lombok.Data;

/**
 * @author ganeshallampalli
 */

public interface BaseRequestResponse {

    /**
     * Basic Request Template
     */
    @Data
    public class BaseRequest {

    }

    /**
     * Basic Response body of all response model from application.
     */
    @Data
    public class BaseResponse<T> {

        private String code;

        private String message;

        private T responseData;

    }
}
