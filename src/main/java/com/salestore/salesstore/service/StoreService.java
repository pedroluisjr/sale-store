package com.salestore.salesstore.service;

import com.salestore.salesstore.dto.GetCepDto;
import com.salestore.salesstore.dto.StoreDto;
import com.salestore.salesstore.model.StoreTable;
import com.salestore.salesstore.repository.StoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    private final GetCepService getCepService;

    public StoreService(StoreRepository storeRepository,
                        GetCepService getCepService) {
        this.storeRepository = storeRepository;
        this.getCepService = getCepService;
    }


    public void createStore (StoreDto storeDto) {
        Optional<StoreTable> findCnpj = storeRepository.findByCnpj(storeDto.getCnpj());
        if (findCnpj.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cnpj already exist in database");
        }
        GetCepDto cep = getCepService.searchCep(storeDto.getCep()); //Pego o CEP enviado na request e chamo o getCepService que busca o cep pelo ViaCep.
        assert cep != null;
        storeRepository.save(storeDto.getCep(cep));
    }

}
