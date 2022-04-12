package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {

	int idCheck(String id);
	void registerMember(MemberDto memberDto) throws Exception;
	MemberDto login(String id, String pw) throws Exception;
	MemberDto infoMember(String id) throws Exception;
	void updateMember(MemberDto memberDto) throws Exception;
	void deleteMember(String id) throws Exception;
}
