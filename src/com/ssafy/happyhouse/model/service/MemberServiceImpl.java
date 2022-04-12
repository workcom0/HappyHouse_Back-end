package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.dao.MemberDao;
import com.ssafy.happyhouse.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao memberDao = MemberDaoImpl.getMemberDao();
	
	private static MemberService memberService = new MemberServiceImpl();
	
	private MemberServiceImpl() {}

	public static MemberService getMemberService() {
		return memberService;
	}

	@Override
	public int idCheck(String id) {
		return memberDao.idCheck(id);
	}
	
	@Override
	public void registerMember(MemberDto memberDto) throws Exception {
		memberDao.registerMember(memberDto);
	}

	@Override
	public MemberDto login(String id, String pw) throws Exception {
		return memberDao.login(id, pw);
	}

	@Override
	public MemberDto infoMember(String id) throws Exception {
		return MemberDaoImpl.getMemberDao().infoMember(id);
	}

	@Override
	public void updateMember(MemberDto memberDto) throws Exception {
		MemberDaoImpl.getMemberDao().updateMember(memberDto);
	}

	@Override
	public void deleteMember(String id) throws Exception {
		MemberDaoImpl.getMemberDao().deleteMember(id);
	}
	
}