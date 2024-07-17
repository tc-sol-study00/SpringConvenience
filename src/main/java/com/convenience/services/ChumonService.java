package com.convenience.services;

import com.convenience.models.Chumon;
import com.convenience.models.SelectListItem;
import com.convenience.models.entities.ChumonJisseki;
import com.convenience.models.entities.ChumonJissekiMeisai;
import com.convenience.models.entities.ShiireSakiMaster;
import com.convenience.models.viewmodels.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.convenience.repositories.ChumonJissekiRepository;
import com.convenience.repositories.ShiireSakiMasterRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ChumonService {

	@Autowired
	private ChumonJissekiRepository chumonJissekiRepository;
	@Autowired
	private ShiireSakiMasterRepository ShiireSakiMasterRepository;
	@Autowired
	private Chumon chumon;

	@PersistenceContext
	private EntityManager entityManager;

//	public List<ChumonJisseki> getAllChumonJissekis() {
//		// List<ChumonJisseki> result = ConvenienceRepository.findAll();
//		List<ChumonJisseki> result = chumonJissekiRepository.findByChumonIdAndShiireSakiId("20240529-001",
//				"A000000001");
//
//		return result;
//	}

	public ChumonKeysViewModel SetChumonKeysViewModel() {

		List<ShiireSakiMaster> list = ShiireSakiMasterRepository.findAllByOrderByShiireSakiId();
		List<SelectListItem> selectListItemList = new ArrayList<SelectListItem>();

		for (ShiireSakiMaster item : list) {
			SelectListItem selectlistitem = new SelectListItem();
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

	public ChumonViewModel ChumonReception(String inShiireSakiId, LocalDate inChumondate) {

		ChumonJisseki setChumonJisseki, queriedChumonJisseki;

		entityManager.clear();

		queriedChumonJisseki = chumon.ChumonToiawase(inShiireSakiId, inChumondate);

		if (queriedChumonJisseki == null) {
			setChumonJisseki = chumon.ChumonSakusei(inShiireSakiId, inChumondate);
		} else {
			setChumonJisseki = queriedChumonJisseki;
		}

		ChumonViewModel chumonViewModel = new ChumonViewModel();
		chumonViewModel.setChumonJisseki(setChumonJisseki);

		return chumonViewModel;

	}

	@Transactional
	public boolean AdjustChumonZanToUpdate(ChumonJisseki inchumonJisseki) {
		Boolean isResultNormal;

		// 注文残調整
		for (ChumonJissekiMeisai chumonJissekiMeisai : inchumonJisseki.getChumonJissekiMeisais()) {
			BigDecimal chumonSu = chumonJissekiMeisai.getChumonSu();
			BigDecimal chumonZan = chumonJissekiMeisai.getChumonZan();
			BigDecimal lastChumonSu = chumonJissekiMeisai.getLastChumonSu();
			chumonZan = chumonZan.add(chumonSu.subtract(lastChumonSu));
			
			if (chumonZan.compareTo(BigDecimal.ZERO) < 0) {
				chumonZan = BigDecimal.ZERO;
			}
			chumonJissekiMeisai.setChumonZan(chumonZan);
		}

		try {
			chumon.ChumonUpdate(inchumonJisseki);
			isResultNormal = true;
		} catch (Exception e) {
			isResultNormal = false;
		}
		return isResultNormal;
	}

}
