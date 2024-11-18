package com.cengiz.ilanapiproject.controller;

import com.cengiz.ilanapiproject.data.dto.IlanDurumDto;
import com.cengiz.ilanapiproject.service.IlanDurumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IlanDurumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IlanDurumService ilanDurumService;

    private List<IlanDurumDto> ilanDurumDtoList;

    @BeforeEach
    void setUp() {

        IlanDurumDto ilanDurumDto1 = new IlanDurumDto(1L, 101, LocalDateTime.now(), 1L, LocalDateTime.now(), 2L);
        IlanDurumDto ilanDurumDto2 = new IlanDurumDto(2L, 102, LocalDateTime.now(), 3L, LocalDateTime.now(), 4L);

        ilanDurumDtoList = List.of(ilanDurumDto1, ilanDurumDto2);

        Mockito.when(ilanDurumService.findAll(anyLong())).thenReturn(ilanDurumDtoList);
    }

    @Test
    void findAll_ReturnsIlanDurumDtoList() throws Exception {

        ResultActions response = mockMvc.perform(get("/api/ilan/durum/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.listData", hasSize(2)))
                .andExpect(jsonPath("$.listData[0].fkIlanId").value(1L))
                .andExpect(jsonPath("$.listData[0].durumId").value(101))
                .andExpect(jsonPath("$.listData[0].kaydedenKullaniciId").value(1L))
                .andExpect(jsonPath("$.listData[0].guncelleyenKullaniciId").value(2L))
                .andExpect(jsonPath("$.listData[1].fkIlanId").value(2L))
                .andExpect(jsonPath("$.listData[1].durumId").value(102))
                .andExpect(jsonPath("$.listData[1].kaydedenKullaniciId").value(3L))
                .andExpect(jsonPath("$.listData[1].guncelleyenKullaniciId").value(4L));
    }

}
