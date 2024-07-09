package com.convenience.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "chumon_jisseki_meisai")
public class ChumonJissekiMeisai {

    @Id
    @Column(name = "chumon_code")
    @NotEmpty
    @Max(20)
    private String chumonId;

    @Column(name = "shiire_saki_code")
    @NotEmpty
    @Max(10)
    private String shiireSakiId;

    @Column(name = "shiire_prd_code")
    @NotEmpty
    @Max(10)
    private String shiirePrdId;

    @Column(name = "shohin_code")
    @NotEmpty
    @Max(10)
    private String shohinId;

    @Column(name = "chumon_su")
    @NotNull
    @PositiveOrZero
    private BigDecimal chumonSu;

    @Column(name = "chumon_zan")
    private BigDecimal chumonZan;

    // This field is not mapped to the database
    @Transient
    private BigDecimal lastChumonSu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chumon_code", referencedColumnName = "chumon_code", insertable = false, updatable = false)
    private ChumonJisseki chumonJisseki;

    /*

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "shiire_saki_code", referencedColumnName = "shiire_saki_code", insertable = false, updatable = false),
        @JoinColumn(name = "shiire_prd_code", referencedColumnName = "shiire_prd_code", insertable = false, updatable = false),
        @JoinColumn(name = "shohin_code", referencedColumnName = "shohin_code", insertable = false, updatable = false)
    })
    private ShiireMaster shiireMaster;
    */

    // Assuming ShiireJisseki is another entity with @OneToMany mapping
    //private Collection<ShiireJisseki> shiireJisseki;

    //@Version
    //private Long version;

    // Getters and setters
}