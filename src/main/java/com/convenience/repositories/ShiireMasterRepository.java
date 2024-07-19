package com.convenience.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.convenience.models.entities.ShiireMaster;
import com.convenience.models.entities.ShiireMasterId;

@Repository
public interface ShiireMasterRepository extends JpaRepository<ShiireMaster, ShiireMasterId> {
	 List<ShiireMaster> findByShiireSakiIdOrderByShohinId(String shiireSakiId);
}
