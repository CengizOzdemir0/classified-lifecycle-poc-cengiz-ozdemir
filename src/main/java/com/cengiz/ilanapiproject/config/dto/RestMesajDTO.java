package com.cengiz.ilanapiproject.config.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestMesajDTO implements Serializable {

    private String kodu;
    private String mesaj;

}
