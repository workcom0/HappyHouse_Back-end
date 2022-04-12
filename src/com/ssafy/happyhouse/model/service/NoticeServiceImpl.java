package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.ListParameterDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.dao.NoticeDao;
import com.ssafy.happyhouse.model.dao.NoticeDaoImpl;
import com.ssafy.util.PageNavigation;

public class NoticeServiceImpl implements NoticeService{
	
	private NoticeDao noticeDao = NoticeDaoImpl.getNoticeDao();
	
	private static NoticeService noticeService = new NoticeServiceImpl();
	
	private NoticeServiceImpl() {}

	public static NoticeService getNoticeService() {
		return noticeService;
	}

	@Override
	public void registerNotice(NoticeDto noticeDto) throws Exception {
		noticeDao.registerNotice(noticeDto);
	}

	@Override
	public List<NoticeDto> listNotice(String pg, String key, String word) throws Exception {
		int pgno = pg != null ? Integer.parseInt(pg) : 1;
		int countPerPage = 10;
		int start= (pgno - 1) * countPerPage;
		
		ListParameterDto listParameterDto = new ListParameterDto();
		listParameterDto.setKey(key == null ? "" : key.trim());
		listParameterDto.setWord(word == null ? "" : word.trim());
		listParameterDto.setStart(start);
		listParameterDto.setCurrentPerPage(countPerPage);
		return noticeDao.listNotice(listParameterDto);
	}

	@Override
	public PageNavigation makePageNavigation(String pg, String key, String word) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int currentPage = pg != null ? Integer.parseInt(pg) : 1;
		int naviSize = 5;
		int countPerPage = 10;
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setCountPerPage(countPerPage);
		pageNavigation.setNaviSize(naviSize);
		
		ListParameterDto listParameterDto = new ListParameterDto();
		listParameterDto.setKey(key == null ? "" : key.trim());
		listParameterDto.setWord(word == null ? "" : word.trim());
		
		int totalCount = noticeDao.getTotalCount(listParameterDto);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / countPerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		pageNavigation.setStartRange(currentPage <= naviSize);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public NoticeDto getNotice(int noticeNo) throws Exception {
		return noticeDao.getNotice(noticeNo);
	}

	@Override
	public void updateNotice(NoticeDto noticeDto) throws Exception {
		noticeDao.updateNotice(noticeDto);
	}

	@Override
	public void deleteNotice(int noticeNo) throws Exception {
		noticeDao.deleteNotice(noticeNo);
	}

}
