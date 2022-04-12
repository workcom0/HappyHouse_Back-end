package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.ListParameterDto;
import com.ssafy.happyhouse.model.NoticeDto;

public interface NoticeDao {
	
	void registerNotice(NoticeDto noticeDto) throws SQLException;
	List<NoticeDto> listNotice(ListParameterDto listParameterDto) throws SQLException;
	int getTotalCount(ListParameterDto listParameterDto) throws SQLException;
	
	NoticeDto getNotice(int noticeNo) throws SQLException;
	void updateNotice(NoticeDto noticeDto) throws SQLException;
	void deleteNotice(int noticeNo) throws SQLException;
}
