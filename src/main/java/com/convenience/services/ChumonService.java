package com.convenience.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.convenience.models.ChumonJisseki;
import com.convenience.repositories.ConvenienceRepository;

@Service
public class ChumonService {
	
    @Autowired
    private ConvenienceRepository ConvenienceRepository;

    public List<ChumonJisseki> getAllChumonJissekis() {
    	//List<ChumonJisseki> result = ConvenienceRepository.findAll();
    	List<ChumonJisseki> result = ConvenienceRepository.findByChumonIdAndShiireSakiId("20240529-001","A000000001");

        return result;
    }

}
