package com.salestore.salesstore.repository;

import com.salestore.salesstore.model.StoreTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreTable, Long> {

   Optional<StoreTable> findByCnpj(String cnpj);

}
