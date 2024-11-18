package com.cengiz.ilanapiproject.config.domain;

import com.cengiz.ilanapiproject.base.dto.BaseDto;
import com.cengiz.ilanapiproject.base.utils.TemplateUtils;
import com.cengiz.ilanapiproject.config.dto.RestMesajDTO;
import com.cengiz.ilanapiproject.data.enums.Dil;
import com.cengiz.ilanapiproject.data.enums.HttpStatus;
import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import com.cengiz.ilanapiproject.service.MesajService;
import com.cengiz.ilanapiproject.service.impl.MesajServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@Data
@Slf4j
public class RestResponse<T extends BaseDto> {


    @JsonProperty("lang")
    private String dilEnum = Dil.TR.getUlkeKodu();

    @JsonProperty("infos")
    private List<RestMesajDTO> infoList = new ArrayList<>();

    @JsonProperty("warnings")
    private List<RestMesajDTO> warningList = new ArrayList<>();

    @JsonProperty("errors")
    private List<RestMesajDTO> errorList = new ArrayList<>();
    @JsonIgnore
    private HttpStatus httpStatus;

    @JsonProperty("data")
    private T data;

    @JsonProperty("listData")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> listData;

    private boolean success = true;


    public RestResponse<T> createWithMesajEnum(Mesajlar mesajlar) {
        this.addMesaj(mesajlar);
        return this;
    }

    public void addMesajWithArgs(Mesajlar mesajlar, Map<String, String> args) {
        MesajService mesajService = BeanUtil.getBean(MesajService.class);
        this.httpStatus = mesajlar.getHttpStatus();
        this.success = false;
        this.errorList.add(new RestMesajDTO(
                mesajlar.getKodu(),
                TemplateUtils.getReturnString(mesajService.getMesajByMesajEnum(mesajlar).getMesaj(), args)
        ));

    }


    public void addMesaj(Mesajlar mesajlar) {
        MesajService mesajService = BeanUtil.getBean(MesajService.class);
        this.httpStatus = mesajlar.getHttpStatus();
        switch (mesajlar.getSeviye()) {
            case WARN:
                this.warningList.add(new RestMesajDTO(mesajlar.getKodu(), mesajService.getMesajByMesajEnum(mesajlar).getMesaj()));
                break;
            case ERROR:
                this.success = false;
                this.errorList.add(new RestMesajDTO(mesajlar.getKodu(), mesajService.getMesajByMesajEnum(mesajlar).getMesaj()));
                break;
            default:
                this.infoList.add(new RestMesajDTO(mesajlar.getKodu(), mesajService.getMesajByMesajEnum(mesajlar).getMesaj()));
                break;
        }
    }


    public void setData(T data) {
        this.data = data;
    }

    public void setListData(List<T> data) {
        listData = data;
    }


}
