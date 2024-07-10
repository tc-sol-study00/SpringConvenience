package com.convenience.services;

import com.convenience.models.Chumon;
import com.convenience.models.SelectListItem;
import com.convenience.models.entities.ChumonJisseki;
import com.convenience.models.entities.ShiireSakiMaster;
import com.convenience.models.viewmodels.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.convenience.repositories.ChumonJissekiRepository;
import com.convenience.repositories.ShiireSakiMasterRepository;

@Service
public class ChumonService {
	
    @Autowired
    private ChumonJissekiRepository chumonJissekiRepository;
    @Autowired
    private ShiireSakiMasterRepository ShiireSakiMasterRepository;
    @Autowired
    private Chumon chumon;
    
   // ChumonService(){
   // 	chumon=new Chumon();
   // }

    public List<ChumonJisseki> getAllChumonJissekis() {
    	//List<ChumonJisseki> result = ConvenienceRepository.findAll();
    	List<ChumonJisseki> result = chumonJissekiRepository.findByChumonIdAndShiireSakiId("20240529-001","A000000001");

        return result;
    }
    
    public ChumonKeysViewModel SetChumonKeysViewModel() {
   	
    	List<ShiireSakiMaster> list = ShiireSakiMasterRepository.findAllByOrderByShiireSakiId();
    	List<SelectListItem> selectListItemList = new ArrayList<SelectListItem>();
    	
    	for( ShiireSakiMaster item : list){
    		SelectListItem selectlistitem=new SelectListItem();
    		selectlistitem.setText(item.getShiireSakiKaisya());
    		selectlistitem.setValue(item.getShiireSakiId());
    		selectListItemList.add(selectlistitem);
    	}

        ChumonKeysViewModel viewModel = new ChumonKeysViewModel();
        	viewModel.setShiireSakiId(null);
        	viewModel.setChumonDate(LocalDate.now());
        	viewModel.setShiireSakiList(selectListItemList);
        return viewModel;
    }
    
    public ChumonViewModel ChumonReception(String inShiireSakiId, LocalDate inChumondate ) {
    	
    	ChumonJisseki setChumonJisseki,queriedChumonJisseki;
    	
    	queriedChumonJisseki=chumon.ChumonToiawase(inShiireSakiId, inChumondate);
    	
    	if(queriedChumonJisseki == null) {
    		setChumonJisseki=chumon.ChumonSakusei(inShiireSakiId, inChumondate);
    	}else {
    		setChumonJisseki=queriedChumonJisseki;
    	}
        	
    	ChumonViewModel chumonViewModel = new ChumonViewModel();
    	chumonViewModel.setChumonJisseki(setChumonJisseki);
    	
    	return chumonViewModel;
    	
    }
    
    public void Update(ChumonJisseki inchumonJisseki) {
    	chumonJissekiRepository.save(inchumonJisseki);
    }

}
