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


/**
 *	注文実績Jpaレポジトリ 
 */
@Repository
public interface ChumonJissekiRepository extends JpaRepository<ChumonJisseki, String> {
	//検索用（注文コード＆仕入先コード）→リスト
	List<ChumonJisseki> findByChumonIdAndShiireSakiId(String keyword1, String keyword2);
	//検索用（仕入先コード＆注文日）→１行 
	ChumonJisseki findByShiireSakiIdAndChumonDate(String shiireSakiId, LocalDate chumonDate);
	//注文日当日の最大注文コードを求める用（注文コード発番で利用
	@Query(value = "SELECT MAX(c.chumon_code) FROM chumon_jisseki c WHERE c.chumon_code LIKE CONCAT(:prefix, '%')", nativeQuery = true)
	 String findLatestChumonId(@Param("prefix") String prefix);
}
