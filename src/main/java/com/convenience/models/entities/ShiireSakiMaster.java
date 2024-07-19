package com.convenience.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "shiire_saki_master")
public class ShiireSakiMaster {

    @Id
    @Column(name = "shiire_saki_code", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String shiireSakiId;

    @Column(name = "shiire_saki_kaisya", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String shiireSakiKaisya;

    @Column(name = "shiire_saki_busho", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String shiireSakiBusho;

    @Column(name = "yubin_bango", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String yubinBango;

    @Column(name = "todoufuken", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String todoufuken;

    @Column(name = "shikuchoson", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String shikuchoson;

    @Column(name = "banchi", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String banchi;

    @Column(name = "tatemonomei", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String tatemonomei;

    @OneToMany(mappedBy = "shiireSakiMaster")
    private Set<ShiireMaster> shireMasters;

    @OneToMany(mappedBy = "shiireSakiMaster")
    private Set<ChumonJisseki> chumonJissekis;
}

