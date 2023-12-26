package com.salestore.salesstore.dto;

import com.salestore.salesstore.model.StoreTable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
public class StoreDto {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Max(value = 21)
    @Min(value = 14)
    private String cnpj;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Max(value = 12)
    private String ie;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Max(value = 35)
    private String fantasyName;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Max(value = 20)
    private String socialReason;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Max(value = 8)
    @Min(value = 8)
    private String cep;

    private String neighborhood;
    private String number;
    private String address;
    private String ibgeCode;
    private String city;
    private String uf;

    public StoreTable getCep(GetCepDto getCepDto) {
        StoreTable saveStore = new StoreTable();
        saveStore.setCnpj(this.getCnpj());
        saveStore.setIe(this.getIe());
        saveStore.setFantasyName(this.getFantasyName());
        saveStore.setSocialReason(this.getSocialReason());
        saveStore.setCep(getCepDto.getCep());
        saveStore.setNeighborhood(getCepDto.getBairro());
        saveStore.setNumber(this.getNumber());
        saveStore.setAddress(getCepDto.getLogradouro());
        saveStore.setIbgeCode(getCepDto.getIbge());
        saveStore.setCity(getCepDto.getLocalidade());
        saveStore.setUf(getCepDto.getUf());
        return saveStore;
    }

}
