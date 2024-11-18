package com.cengiz.ilanapiproject.service;

import com.cengiz.ilanapiproject.data.dto.MesajDto;
import com.cengiz.ilanapiproject.data.enums.Mesajlar;


/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */
public interface MesajService {

    MesajDto getMesajByMesajEnum(Mesajlar mesajlarEnum);
}
