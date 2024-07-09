package com.convenience.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "chumon_jisseki")
public class ChumonJisseki {

    @Id
    @Column(name = "chumon_code", length = 20, nullable = false)
    @NotNull
    @Size(min = 1, max = 20)
    private String chumonId;

    @Column(name = "shiire_saki_code", length = 10, nullable = false)
    private String shiireSakiId;

    @Column(name = "chumon_date", nullable = false)
    private LocalDate chumonDate;

    //@ManyToOne
    //@JoinColumn(name = "shiire_saki_code", insertable = false, updatable = false)
    //private ShiireSakiMaster shiireSakiMaster;

    @OneToMany(mappedBy = "chumonJisseki", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChumonJissekiMeisai> chumonJissekiMeisais;

    //@Version
    //private Integer version;

    // Getters and Setters
    public String getChumonId() {
        return chumonId;
    }

    public void setChumonId(String chumonId) {
        this.chumonId = chumonId;
    }

    public String getShiireSakiId() {
        return shiireSakiId;
    }

    public void setShiireSakiId(String shiireSakiId) {
        this.shiireSakiId = shiireSakiId;
    }

    public LocalDate getChumonDate() {
        return chumonDate;
    }

    public void setChumonDate(LocalDate chumonDate) {
        this.chumonDate = chumonDate;
    }

    //public ShiireSakiMaster getShiireSakiMaster() {
    //    return shiireSakiMaster;
    //}

    //public void setShiireSakiMaster(ShiireSakiMaster shiireSakiMaster) {
    //    this.shiireSakiMaster = shiireSakiMaster;
    //}

    //public List<ChumonJissekiMeisai> getChumonJissekiMeisais() {
    //    return chumonJissekiMeisais;
    //}

    //public void setChumonJissekiMeisais(List<ChumonJissekiMeisai> chumonJissekiMeisais) {
    //    this.chumonJissekiMeisais = chumonJissekiMeisais;
    //}

    //public Integer getVersion() {
    //    return version;
    //}

    //public void setVersion(Integer version) {
    //    this.version = version;
    //}
}
