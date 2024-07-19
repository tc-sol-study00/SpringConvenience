package com.convenience.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.convenience.models.entities.ShiireMaster;
import com.convenience.models.entities.ShiireMasterId;

/**
 *	仕入マスタ用Jpaレポジトリ 
 */
@Repository
public interface ShiireMasterRepository extends JpaRepository<ShiireMaster, ShiireMasterId> {
	//検索（仕入先コード）＆並び替え（商品コード）
	 List<ShiireMaster> findByShiireSakiIdOrderByShohinId(String shiireSakiId);
}
