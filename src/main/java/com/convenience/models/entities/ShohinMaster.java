package com.convenience.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "shohin_master")
public class ShohinMaster {

    @Id
    @Column(name = "shohin_code", length = 10, nullable = false)
    private String shohinId;

    @Column(name = "shohin_name", length = 50, nullable = false)
    @NotBlank
    @Size(max = 50)
    private String shohinName;

    @Column(name = "shohi_zeiritsu", nullable = false, precision = 15, scale = 2)
    @NotNull
    private BigDecimal shohiZeiritsu;

    @Column(name = "shohi_zeiritsu_gaisyoku", nullable = false, precision = 15, scale = 2)
    @NotNull
    private BigDecimal shohiZeiritsuGaishoku;

    @OneToMany(mappedBy = "shohinMaster")
    private Set<ShiireMaster> shiireMasters;

    //@OneToOne(mappedBy = "shohinMaster")
    //private TentoZaiko tentoZaikos;
}
