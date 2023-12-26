package com.salestore.salesstore.dto;

import com.salestore.salesstore.model.StoreTable;
import lombok.Data;

@Data
public class GetCepDto {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;

    public StoreTable getStore() {
        StoreTable saveStore = new StoreTable();
        saveStore.setCep(this.getCep());
        saveStore.setAddress(this.getLogradouro());
        saveStore.setUf(this.getUf());
        saveStore.setCity(this.getLocalidade());
        saveStore.setNeighborhood(this.getBairro());
        saveStore.setIbgeCode(this.getIbge());
        return saveStore;
    }

}
