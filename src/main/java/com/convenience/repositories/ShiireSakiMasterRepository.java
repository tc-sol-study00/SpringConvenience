package com.convenience.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.convenience.models.entities.ShiireSakiMaster;

@Repository
public interface ShiireSakiMasterRepository extends JpaRepository<ShiireSakiMaster, String> {
	List<ShiireSakiMaster> findAllByOrderByShiireSakiId();
	boolean existsById(String shiireSakiId);
}