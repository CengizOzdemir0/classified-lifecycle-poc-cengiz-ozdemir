package com.cengiz.ilanapiproject.exception;

import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import lombok.Getter;

import java.util.Map;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */
@Getter
public class IlanException extends RuntimeException {


    private final Mesajlar mesajlarEnum;
    private final transient Map<String, String> mesajArgsMap;

    public IlanException(Mesajlar mesajlarEnum) {
        this(mesajlarEnum, null);
    }

    public IlanException(Mesajlar mesajlarEnum, Map<String, String> mesajArgsMap) {
        this.mesajlarEnum = mesajlarEnum;
        this.mesajArgsMap = mesajArgsMap;
    }


}
