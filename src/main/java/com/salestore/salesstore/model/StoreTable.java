package com.salestore.salesstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "storeTable")
@Table(name = "storeTable")
@Schema(name = "Store")
public class StoreTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeId")
    @JsonIgnore
    private Long storeId;

    @Column(name = "fantasyName")
    private String fantasyName;

    @Column(name = "socialReason")
    private String socialReason;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "ie")
    private String ie;

    @Column(name = "cep")
    private String cep;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "number")
    private String number;

    @Column(name = "address")
    private String address;

    @Column(name = "ibgeCode")
    private String ibgeCode;

    @Column(name = "city")
    private String city;

    @Column(name = "uf")
    private String uf;

    @Column(name = "createdAt")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updatedAt")
    @UpdateTimestamp
    private Date updatedAt;

}
