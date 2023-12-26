package com.salestore.salesstore.service;

import com.salestore.salesstore.dto.GetCepDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Setter
@Getter
@Service
public class GetCepService {

    public GetCepDto searchCep (String cep) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GetCepDto> getCep = restTemplate.getForEntity(("http://viacep.com.br/ws/"+cep+"/json/"), GetCepDto.class);
        return getCep.getBody();
    }

}
