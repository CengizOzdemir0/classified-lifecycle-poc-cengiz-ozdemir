package com.cengiz.ilanapiproject.controller;


import com.cengiz.ilanapiproject.config.domain.ResponseHelper;
import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanEmlakDto;
import com.cengiz.ilanapiproject.data.dto.IlanEmlakSorguDto;
import com.cengiz.ilanapiproject.data.dto.IlanEmlakTalepDto;
import com.cengiz.ilanapiproject.service.IlanEmlakService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

@RestController
@RequestMapping("/api/ilan/emlak")
@RequiredArgsConstructor
@Tag(name = "ilan Emlak", description = "ilan Emlak ile ilgili işlemler")
public class IlanEmlakController {


    private final IlanEmlakService ilanEmlakService;

    @PostMapping("/save")
    @Operation(summary = "ilan Emlak kayit islemi")
    public ResponseEntity<RestResponse<IlanEmlakDto>> saveIlan(@Valid @RequestBody IlanEmlakTalepDto dto) {
        return ilanEmlakService.save(dto);
    }

    @GetMapping("/aktif")
    @Operation(summary = "Aktif Emlak Listesini getirmektedir.")
    public ResponseEntity<RestResponse<IlanEmlakDto>> findAllByAktifIlanDurum() {
        return ResponseHelper.responseEntityOkFromListData(ilanEmlakService.findAllByAktifEmlakIlanDurum());
    }

    @PostMapping("/sorgulama")
    @Operation(summary = "Sorgulanan kriterlere gore aktif Emlak Listesini getirmektedir. (Hepsini sorgulamak icin alanlar bos gonderilebilir)")
    public ResponseEntity<RestResponse<IlanEmlakDto>> findAllByAktifIlanDurum(@Valid @RequestBody IlanEmlakSorguDto dto) {
        return ResponseHelper.responseEntityOkFromListData(ilanEmlakService.getFilteredIlanEmlak(dto));
    }
}
