package com.convenience.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.convenience.models.entities.ShiireSakiMaster;

/**
 *	仕入先マスタ用Jpaレポジトリ 
 */
@Repository
public interface ShiireSakiMasterRepository extends JpaRepository<ShiireSakiMaster, String> {
	//仕入先コードでソートしたリスト
	List<ShiireSakiMaster> findAllByOrderByShiireSakiId();
	//指定した仕入先コードが存在しているかどうか
	boolean existsById(String shiireSakiId);
}