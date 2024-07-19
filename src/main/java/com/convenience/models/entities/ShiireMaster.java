package com.convenience.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "shiire_master")
@IdClass(ShiireMasterId.class)
public class ShiireMaster {

    @Id
    @Column(name = "shiire_saki_code", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String shiireSakiId;

    @Id
    @Column(name = "shiire_prd_code", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String shiirePrdId;

    @Id
    @Column(name = "shohin_code", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String shohinId;

    @Column(name = "shiire_prd_name", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String shiirePrdName;

    @Column(name = "shiire_pcs_unit", nullable = false, precision = 7, scale = 2)
    @NotNull
    private BigDecimal shiirePcsPerUnit;

    @Column(name = "shiire_unit", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String shiireUnit;

    @Column(name = "shiire_tanka", nullable = false, precision = 7, scale = 2)
    @NotNull
    private BigDecimal shireTanka;

    @ManyToOne
    @JoinColumn(name = "shohin_code", insertable = false, updatable = false)
    private ShohinMaster shohinMaster;

    @ManyToOne
    @JoinColumn(name = "shiire_saki_code", insertable = false, updatable = false)
    private ShiireSakiMaster shiireSakiMaster;

    @OneToMany(mappedBy = "shiireMaster")
    private Set<ChumonJissekiMeisai> chumonJissekiMeisaiis;

    //@OneToOne(mappedBy = "shiireMaster")
    //private SokoZaiko sokoZaikos;

    //@OneToMany(mappedBy = "shiireMaster")
    //private Set<TentoHaraidashiJisseki> tentoHaraidashiJissekis;

 
}
