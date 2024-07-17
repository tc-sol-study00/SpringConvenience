package com.convenience.repositories;

import java.time.LocalDate;
import org.springframework.data.domain.Sort;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.convenience.models.entities.ChumonJisseki;
import com.convenience.models.entities.ChumonJissekiMeisai;

@Repository
public interface ChumonJissekiRepository extends JpaRepository<ChumonJisseki, String> {
	List<ChumonJisseki> findByChumonIdAndShiireSakiId(String keyword1, String keyword2);
	 @Query(value = "SELECT MAX(c.chumon_code) FROM chumon_jisseki c WHERE c.chumon_code LIKE CONCAT(:prefix, '%')", nativeQuery = true)
	 String findLatestChumonId(@Param("prefix") String prefix);
	 ChumonJisseki findByShiireSakiIdAndChumonDate(String shiireSakiId, LocalDate chumonDate);
}
