package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.util.DBUtil;

public class MemberDaoImpl implements MemberDao {
	
	private DBUtil dbUtil = DBUtil.getInstance();

	private static MemberDao memberDao = new MemberDaoImpl();
	
	private MemberDaoImpl() {}

	public static MemberDao getMemberDao() {
		return memberDao;
	}

	@Override
	public int idCheck(String id) {
		int cnt = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select count(id) \n");
			loginMember.append("from userinfo \n");
			loginMember.append("where id = ?");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			cnt = 1;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}
	
	@Override
	public void registerMember(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder registerMember = new StringBuilder();
			registerMember.append("insert into userinfo (id, pw, name, email, phone) \n");
			registerMember.append("values (?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(registerMember.toString());
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPw());
			pstmt.setString(3, memberDto.getName());		
			pstmt.setString(4, memberDto.getEmail());
			pstmt.setString(5, memberDto.getPhone());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public MemberDto login(String id, String pw) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select id, name \n");
			loginMember.append("from userinfo \n");
			loginMember.append("where id = ? and pw = ?");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberDto = new MemberDto();
				memberDto.setId(rs.getString("id"));
				memberDto.setName(rs.getString("name"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return memberDto;
	}

	@Override
	public MemberDto infoMember(String id) {
		String sql = "select * from userinfo where id=?;";
		
		MemberDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new MemberDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
			
		return dto;
	}

	@Override
	public void updateMember(MemberDto memberDto) throws Exception {
		String sql = "update userinfo set pw=?, name=?, email=?, phone=? where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getPw());		
			pstmt.setString(2, memberDto.getName());	
			pstmt.setString(3, memberDto.getEmail());
			pstmt.setString(4, memberDto.getPhone());
			pstmt.setString(5, memberDto.getId());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteMember(String id) throws Exception {
		String sql = "delete from userinfo where id=?";
		System.out.println("삭제전");
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			System.out.println("삭제완료");
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

}