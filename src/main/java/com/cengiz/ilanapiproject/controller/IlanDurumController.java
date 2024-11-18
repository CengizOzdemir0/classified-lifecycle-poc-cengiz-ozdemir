package com.cengiz.ilanapiproject.controller;


import com.cengiz.ilanapiproject.config.domain.ResponseHelper;
import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanDurumDto;
import com.cengiz.ilanapiproject.service.IlanDurumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Cengiz ÖZDEMİR
 * @created 13/11/2024
 */

@RestController
@RequestMapping("/api/ilan/durum")
@RequiredArgsConstructor
@Tag(name = "ilan-durum", description = "ilan durum ile ilgili işlemler")
public class IlanDurumController {

    private final IlanDurumService ilanDurumService;

    @GetMapping("/{id}")
    @Operation(summary = "ilan durum listesini getirir")
    public ResponseEntity<RestResponse<IlanDurumDto>> findAll(@Valid @NotNull @PathVariable Long id) {
        return ResponseHelper.responseEntityOkFromListData(ilanDurumService.findAll(id));
    }
}
