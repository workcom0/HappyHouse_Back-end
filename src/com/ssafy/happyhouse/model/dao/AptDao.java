package com.ssafy.happyhouse.model.dao;

import java.util.List;

import com.ssafy.happyhouse.model.AptDto;

public interface AptDao {
	List<String[]> selectSido();
	List<String[]> selectGugun(String sido);
	List<String[]> selectDong(String gugun);
	List<AptDto> selectAptByDong(String dong);
	List<AptDto> selectAptByName(String dongcode, String aptName);
}
