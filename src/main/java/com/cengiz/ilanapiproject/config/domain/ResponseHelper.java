package com.cengiz.ilanapiproject.config.domain;

import com.cengiz.ilanapiproject.base.dto.BaseDto;
import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;


/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseHelper {

    public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityFromResponse(RestResponse<T> restResponse) {
        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus().getSpringHttpStatus());
    }

    public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityDataWithMesaj(Mesajlar mesajlarEnum) {
        RestResponse<T> res = new RestResponse<>();
        res.addMesaj(mesajlarEnum);
        return responseEntityFromResponse(res);
    }


    public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityOkFromData(T data) {
        return responseEntityFromData(data, HttpStatus.OK, Mesajlar.GNL_ISLEM_BASARILI);
    }

    public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityOkFromListData(List<T> data) {
        return responseEntityFromListData(data, HttpStatus.OK, Mesajlar.GNL_ISLEM_BASARILI);
    }

    public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityFromData(T data, HttpStatus httpStatus, Mesajlar... mesajList) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setData(data);
        if (!ObjectUtils.isEmpty(mesajList)) {
            Arrays.stream(mesajList).forEach(restResponse::addMesaj);
        }
        return new ResponseEntity<>(restResponse, httpStatus);
    }

    public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityFromListData(List<T> data, HttpStatus httpStatus,
                                                                                                 Mesajlar... mesajList) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setListData(data);
        if (!ObjectUtils.isEmpty(mesajList)) {
            Arrays.stream(mesajList).forEach(restResponse::addMesaj);
        }
        return new ResponseEntity<>(restResponse, httpStatus);
    }

}
