package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberDao {

	int idCheck(String id);
	void registerMember(MemberDto memberDto) throws SQLException;
	MemberDto login(String id, String pw) throws SQLException;
	MemberDto infoMember(String id) throws Exception;
	void updateMember(MemberDto memberDto) throws Exception;
	void deleteMember(String id) throws Exception;
}