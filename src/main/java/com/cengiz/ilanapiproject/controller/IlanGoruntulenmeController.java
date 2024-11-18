package com.cengiz.ilanapiproject.controller;

import com.cengiz.ilanapiproject.config.domain.ResponseHelper;
import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanGoruntulenmeDto;
import com.cengiz.ilanapiproject.service.IlanGoruntulenmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Cengiz ÖZDEMİR
 * @created 14/11/2024
 */

@RestController
@RequestMapping("/api/ilan/goruntulenme")
@RequiredArgsConstructor
@Tag(name = "ilan", description = "ilan goruntulenme ile ilgili işlemler")
public class IlanGoruntulenmeController {

    private final IlanGoruntulenmeService ilanGoruntulenmeService;

    @GetMapping("/{id}")
    @Operation(summary = "İlan görüntülenmesi listelemektedir")
    public ResponseEntity<RestResponse<IlanGoruntulenmeDto>> getIlanRaporByKategori(@Valid @PathVariable Long id) {
        return ResponseHelper.responseEntityOkFromData(ilanGoruntulenmeService.findByIlanId(id));
    }

    @PostMapping()
    @Operation(summary = "İlan görüntülenmesi kayıt işlemi")
    public ResponseEntity<RestResponse<IlanGoruntulenmeDto>> save(@Valid @RequestBody IlanGoruntulenmeDto dto) {
        return ResponseHelper.responseEntityOkFromData(ilanGoruntulenmeService.save(dto));
    }

}
