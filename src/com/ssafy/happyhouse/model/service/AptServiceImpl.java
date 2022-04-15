package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.AptDto;
import com.ssafy.happyhouse.model.dao.AptDao;
import com.ssafy.happyhouse.model.dao.AptDaoImpl;

public class AptServiceImpl implements AptService {
	
	private AptDao aptdao = AptDaoImpl.getAptDao();
	private static AptService aptService = new AptServiceImpl();
	
	private AptServiceImpl() {}
	
	public static AptService getAptService() {
		return aptService;
	}


	@Override
	public List<String[]> selectSido() {
		return aptdao.selectSido();
	}

	@Override
	public List<String[]> selectGugun(String sido) {
		return aptdao.selectGugun(sido);
	}

	@Override
	public List<String[]> selectDong(String gugun) {
		return aptdao.selectDong(gugun);
	}

	@Override
	public List<AptDto> selectAptByDong(String dong) {
		return aptdao.selectAptByDong(dong);
	}

}
