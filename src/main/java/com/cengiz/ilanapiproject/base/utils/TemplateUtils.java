package com.cengiz.ilanapiproject.base.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author Cengiz ÖZDEMİR
 * @created 14/11/2024
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplateUtils {

    public static String getReturnString(String formatString, Map<String, String> keyMap) {
        StringBuilder result = new StringBuilder(formatString);

        for (Map.Entry<String, String> entry : keyMap.entrySet()) {
            result.append(" ").append(entry.getKey()).append(": ").append(entry.getValue());
        }

        return result.toString();
    }

}