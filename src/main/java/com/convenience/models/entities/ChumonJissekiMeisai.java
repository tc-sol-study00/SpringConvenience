package com.convenience.models.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "chumon_jisseki_meisai")
@IdClass(ChumonJissekiMeisaiId.class)
public class ChumonJissekiMeisai {

    @Id
    @Column(name = "chumon_code")
    @NotEmpty
	@Size(min = 1, max = 20)
    private String chumonId;

    @Id
    @Column(name = "shiire_saki_code")
    @NotEmpty
	@Size(min = 1, max = 10)
    private String shiireSakiId;

    @Id
    @Column(name = "shiire_prd_code")
    @NotEmpty
	@Size(min = 1, max = 10)
    private String shiirePrdId;

    @Id
    @Column(name = "shohin_code")
    @NotEmpty
	@Size(min = 1, max = 10)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "shiire_saki_code", referencedColumnName = "shiire_saki_code", insertable = false, updatable = false),
            @JoinColumn(name = "shiire_prd_code", referencedColumnName = "shiire_prd_code", insertable = false, updatable = false),
            @JoinColumn(name = "shohin_code", referencedColumnName = "shohin_code", insertable = false, updatable = false)
    })
    private ShiireMaster shiireMaster;
}
