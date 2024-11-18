package com.cengiz.ilanapiproject.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

@RequiredArgsConstructor
@Getter
public enum HttpStatus {
    OK(200, "The request has succeeded.", true),
    NO_CONTENT(204, "The request has succeeded.", true),
    BAD_REQUEST(400, "Bad Request", false),
    UNAUTHORIZED(401, "Unauthorized", false),
    FORBIDDEN(403, "Forbidden", false),
    NOT_FOUND(404, "Not Found", false),
    PRECONDITION_REQUERED(428, "Precondition Required", false),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", false);

    private final int val;
    private final String reasonPhrase;
    private final boolean success;


    public static HttpStatus of(int statusCode) {
        for (HttpStatus status : values()) {
            if (status.val == statusCode) {
                return status;
            }
        }
        return HttpStatus.BAD_REQUEST;
    }

    public org.springframework.http.HttpStatus getSpringHttpStatus() {
        return org.springframework.http.HttpStatus.valueOf(this.val);
    }

}