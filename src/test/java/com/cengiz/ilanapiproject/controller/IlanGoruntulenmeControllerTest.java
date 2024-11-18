package com.cengiz.ilanapiproject.controller;

import com.cengiz.ilanapiproject.data.dto.IlanGoruntulenmeDto;
import com.cengiz.ilanapiproject.service.IlanGoruntulenmeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class IlanGoruntulenmeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IlanGoruntulenmeService ilanGoruntulenmeService;

    private IlanGoruntulenmeDto ilanGoruntulenmeDto;

    @BeforeEach
    void setUp() {
        ilanGoruntulenmeDto = new IlanGoruntulenmeDto();
        ilanGoruntulenmeDto.setFkIlanId(1L);
        ilanGoruntulenmeDto.setGoruntulenmeSayisi(10L);
    }

    @Test
    void testGetIlanGoruntulenme() throws Exception {
        when(ilanGoruntulenmeService.findByIlanId(1L)).thenReturn(ilanGoruntulenmeDto);

        mockMvc.perform(get("/api/ilan/goruntulenme/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.fkIlanId").value(1L))
                .andExpect(jsonPath("$.data.goruntulenmeSayisi").value(10L));

        verify(ilanGoruntulenmeService, times(1)).findByIlanId(1L);
    }

    @Test
    void testSaveIlanGoruntulenme() throws Exception {
        when(ilanGoruntulenmeService.save(any(IlanGoruntulenmeDto.class))).thenReturn(ilanGoruntulenmeDto);

        mockMvc.perform(post("/api/ilan/goruntulenme")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(ilanGoruntulenmeDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.fkIlanId").value(1L))
                .andExpect(jsonPath("$.data.goruntulenmeSayisi").value(10L));

        verify(ilanGoruntulenmeService, times(1)).save(any(IlanGoruntulenmeDto.class));
    }
}