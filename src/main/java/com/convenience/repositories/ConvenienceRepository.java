package com.convenience.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.convenience.models.ChumonJisseki;

@Repository
public interface ConvenienceRepository extends JpaRepository<ChumonJisseki, String> {
	List<ChumonJisseki> findByChumonIdAndShiireSakiId(String keyword1,String keyword2);
}