package com.convenience.models.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
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

	 @ManyToOne
	 @JoinColumn(name = "shiire_saki_code", insertable = false, updatable = false)
	 private ShiireSakiMaster shiireSakiMaster;

	@OneToMany(mappedBy = "chumonJisseki", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("shiirePrdId ASC")  // ここで順序を指定
	private List<ChumonJissekiMeisai> chumonJissekiMeisais;

    @Version
    @Column(name = "version")
    private long version = 0;
}
