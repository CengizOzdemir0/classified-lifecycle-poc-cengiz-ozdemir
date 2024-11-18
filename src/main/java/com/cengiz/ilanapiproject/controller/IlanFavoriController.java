package com.cengiz.ilanapiproject.controller;


import com.cengiz.ilanapiproject.config.domain.ResponseHelper;
import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanFavoriDto;
import com.cengiz.ilanapiproject.service.IlanFavoriService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Cengiz ÖZDEMİR
 * @created 16/11/2024
 */

@RestController
@RequestMapping("/api/ilan/favori")
@RequiredArgsConstructor
@Tag(name = "ilan Favori", description = "ilan favori ile ilgili işlemler")
public class IlanFavoriController {
    private final IlanFavoriService ilanFavoriService;

    @PostMapping("/save")
    @Operation(summary = "ilan Favori kayit islemi")
    public ResponseEntity<RestResponse<IlanFavoriDto>> save(@Valid @RequestBody IlanFavoriDto dto) {
        return ResponseHelper.responseEntityOkFromData(ilanFavoriService.save(dto));
    }

    @GetMapping("/ilan/{id}")
    @Operation(summary = "ilan Favori sorgulama listesi")
    public ResponseEntity<RestResponse<IlanFavoriDto>> findIlanFavari(@Valid @NotNull @PathVariable Long id) {
        return ResponseHelper.responseEntityOkFromListData(ilanFavoriService.findAllByIlanId(id));
    }

    @GetMapping("/kullanici/{id}")
    @Operation(summary = "ilan kullanici bazlı Favori sorgulama listesi")
    public ResponseEntity<RestResponse<IlanFavoriDto>> findIlanKullanici(@Valid @NotNull @PathVariable Long id) {
        return ResponseHelper.responseEntityOkFromListData(ilanFavoriService.findAllByKullaniciId(id));
    }

}
