package com.cengiz.ilanapiproject.controller;


import com.cengiz.ilanapiproject.config.domain.ResponseHelper;
import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanDto;
import com.cengiz.ilanapiproject.data.dto.IlanOnayDto;
import com.cengiz.ilanapiproject.data.dto.IlanRaporDto;
import com.cengiz.ilanapiproject.service.IlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author Cengiz ÖZDEMİR
 * @created 13/11/2024
 */

@RestController
@RequestMapping("/api/ilan")
@RequiredArgsConstructor
@Tag(name = "ilan", description = "ilan ile ilgili işlemler")
public class IlanController {

    private final IlanService ilanService;

    @PostMapping("/onayla")
    @Operation(summary = "ilan onay islemi")
    public ResponseEntity<RestResponse<IlanOnayDto>> onayla(@Valid @RequestBody IlanOnayDto dto) {
        return ilanService.onayla(dto);
    }

    @PostMapping("/pasife-al")
    @Operation(summary = "ilan pasife alma islemi")
    public ResponseEntity<RestResponse<IlanOnayDto>> pasifeAl(@Valid @RequestBody IlanOnayDto dto) {
        return ilanService.pasifeAl(dto);
    }

    @GetMapping("/rapor")
    @Operation(summary = "kategori bazlı rapor listelemektedir (GET/dashboard/classifieds/statistics)")
    public ResponseEntity<RestResponse<IlanRaporDto>> getIlanRaporByKategori() {
        return ResponseHelper.responseEntityOkFromListData(ilanService.getIlanRaporByKategori());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Ilan bilgilerini dönmektedir.")
    public ResponseEntity<RestResponse<IlanDto>> findIlanId(@Valid @PathVariable Long id) {
        return ResponseHelper.responseEntityOkFromData(ilanService.findByIdWDto(id));
    }

    @GetMapping("/kategori-aktif/{fkKategoriId}")
    @Operation(summary = "kategori bazlı aktif ilanlar listelemektedir")
    public ResponseEntity<RestResponse<IlanDto>> getIlanByKategori(@Valid @PathVariable Integer fkKategoriId) {
        return ResponseHelper.responseEntityOkFromListData(ilanService.findAllByKategoriWithAktif(fkKategoriId));
    }

    @GetMapping("/kategori-aktif/{fkKategoriId}/{fkKullaniciId}")
    @Operation(summary = "Kullanici - kategori bazlı aktif ilanlar listelemektedir")
    public ResponseEntity<RestResponse<IlanDto>> getIlanByKategoriWUser(@Valid @PathVariable Integer fkKategoriId, @Valid @PathVariable Long fkKullaniciId) {
        return ResponseHelper.responseEntityOkFromListData(ilanService.findAllByKategoriWithAktifKullanici(fkKategoriId, fkKullaniciId));
    }


}
