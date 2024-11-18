package com.cengiz.ilanapiproject.controller;


import com.cengiz.ilanapiproject.config.domain.ResponseHelper;
import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanAlisverisDto;
import com.cengiz.ilanapiproject.data.dto.IlanAlisverisSorguDto;
import com.cengiz.ilanapiproject.data.dto.IlanAlisverisTalepDto;
import com.cengiz.ilanapiproject.service.IlanAlisverisService;
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
@RequestMapping("/api/ilan/alisveris")
@RequiredArgsConstructor
@Tag(name = "ilan alisveriş", description = "ilan alisveriş ile ilgili işlemler")
public class IlanAlisverisController {

    private final IlanAlisverisService ilanAlisverisService;

    @PostMapping("/save")
    @Operation(summary = "ilan Alisveris kayit islemi")
    public ResponseEntity<RestResponse<IlanAlisverisDto>> saveIlan(@Valid @RequestBody IlanAlisverisTalepDto dto) {
        return ilanAlisverisService.save(dto);
    }

    @GetMapping("/aktif")
    @Operation(summary = "Aktif Alisveris Listesini getirmektedir.")
    public ResponseEntity<RestResponse<IlanAlisverisDto>> findAllByAktifAlisverisIlanDurum() {
        return ResponseHelper.responseEntityOkFromListData(ilanAlisverisService.findAllByAktifAlisverisIlanDurum());
    }

    @PostMapping("/sorgulama")
    @Operation(summary = "Sorgulama filitrelerine gore Alisveris Listesini getirmektedir. (Hepsini sorgulamak icin alanlar bos gonderilebilir)")
    public ResponseEntity<RestResponse<IlanAlisverisDto>> findAllByAktifAlisverisIlanDurum(@Valid @RequestBody IlanAlisverisSorguDto dto) {
        return ResponseHelper.responseEntityOkFromListData(ilanAlisverisService.searchIlanAlisveris(dto));
    }
}
