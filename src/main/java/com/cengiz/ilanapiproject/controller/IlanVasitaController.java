package com.cengiz.ilanapiproject.controller;

import com.cengiz.ilanapiproject.config.domain.ResponseHelper;
import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanVasitaDto;
import com.cengiz.ilanapiproject.data.dto.IlanVasitaSorguDto;
import com.cengiz.ilanapiproject.data.dto.IlanVasitaTalepDto;
import com.cengiz.ilanapiproject.service.IlanVasitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@RestController
@RequestMapping("/api/ilan/vasita")
@RequiredArgsConstructor
@Tag(name = "ilan vasıta", description = "ilan Vasita ile ilgili işlemler")
public class IlanVasitaController {

    private final IlanVasitaService ilanVasitaService;

    @PostMapping("/save")
    @Operation(summary = "ilan vasita kayit islemi")
    public ResponseEntity<RestResponse<IlanVasitaDto>> saveIlan(@Valid @RequestBody IlanVasitaTalepDto dto) {
        return ilanVasitaService.save(dto);
    }

    @GetMapping("/aktif")
    @Operation(summary = "Aktif Vasita Listesini getirmektedir.")
    public ResponseEntity<RestResponse<IlanVasitaDto>> findAllByAktifIlanDurum() {
        return ResponseHelper.responseEntityOkFromListData(ilanVasitaService.findAllByAktifIlanDurum());
    }

    @PostMapping("/sorgulama")
    @Operation(summary = "Sorgulama kriterlerine gore Aktif Vasita Listesini getirmektedir. (Hepsini sorgulamak icin alanlar bos gonderilebilir)")
    public ResponseEntity<RestResponse<IlanVasitaDto>> findAllByAktifIlanDurum(@Valid @RequestBody IlanVasitaSorguDto dto) {
        return ResponseHelper.responseEntityOkFromListData(ilanVasitaService.searchIlanVasita(dto));
    }

}
