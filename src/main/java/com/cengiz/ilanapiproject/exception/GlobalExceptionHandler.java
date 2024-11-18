package com.cengiz.ilanapiproject.exception;


import com.cengiz.ilanapiproject.base.dto.BaseDto;
import com.cengiz.ilanapiproject.config.domain.ResponseHelper;
import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(IlanException.class)
    ResponseEntity<RestResponse<BaseDto>> handleDefaultException(IlanException e, WebRequest webRequest) {

        RestResponse<BaseDto> restResponse = new RestResponse<>();
        if (Objects.nonNull(e.getMesajlarEnum())) {
            restResponse = restResponse.createWithMesajEnum(e.getMesajlarEnum());
        } else {
            restResponse = restResponse.createWithMesajEnum(Mesajlar.GNL_BEKLENMEYEN_HATA_OLUSTU);
        }
        return ResponseHelper.responseEntityFromResponse(restResponse);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse<BaseDto>> handleException(Exception e, WebRequest webRequest) {
        RestResponse<BaseDto> restResponse = new RestResponse<>();
        restResponse = restResponse.createWithMesajEnum(Mesajlar.GNL_BEKLENMEYEN_HATA_OLUSTU);
        return ResponseHelper.responseEntityFromResponse(restResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestResponse<BaseDto>> handleException(HttpMessageNotReadableException e, WebRequest webRequest) {

        RestResponse<BaseDto> restResponse = new RestResponse<>();
        restResponse = restResponse.createWithMesajEnum(Mesajlar.VLD_GECERSIZ_PAYLOAD);
        return ResponseHelper.responseEntityFromResponse(restResponse);
    }

    @ExceptionHandler(MissingRequestValueException.class)
    public ResponseEntity<RestResponse<BaseDto>> handleException(MissingRequestValueException e, WebRequest webRequest) {

        RestResponse<BaseDto> restResponse = new RestResponse<>();
        restResponse = restResponse.createWithMesajEnum(Mesajlar.VLD_GECERSIZ_PAYLOAD);
        return ResponseHelper.responseEntityFromResponse(restResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<BaseDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest webRequest) {
        RestResponse<BaseDto> restResponse = new RestResponse<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            String fieldName = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            log.error("Field error in object '{}', field '{}': {}", fieldError.getObjectName(), fieldName, defaultMessage);
            HashMap<String, String> mesajArgs = new HashMap<>();
            mesajArgs.put(fieldName, defaultMessage);
            restResponse.addMesajWithArgs(Mesajlar.VLD_GECERSIZ_KEY, mesajArgs);
        }
        return ResponseHelper.responseEntityFromResponse(restResponse);
    }


}

