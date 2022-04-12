package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.util.PageNavigation;

public interface NoticeService {
	void registerNotice(NoticeDto noticeDto) throws Exception;
	List<NoticeDto> listNotice(String pg, String key, String word) throws Exception;
	PageNavigation makePageNavigation(String pg, String key, String word) throws Exception;
	
//	구현해 보세요!!!
	NoticeDto getNotice(int noticeNo) throws Exception;
	void updateNotice(NoticeDto noticeDto) throws Exception;
	void deleteNotice(int noticeNo) throws Exception;
}
