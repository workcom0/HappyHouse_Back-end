package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.AptDto;
import com.ssafy.util.DBUtil;

//DAO : DataBase Access Object
public class AptDaoImpl implements AptDao {
	private DBUtil dbUtil = DBUtil.getInstance();
	private static AptDao aptDao;

	private AptDaoImpl() {
	}

	public static AptDao getAptDao() {
		if (aptDao == null)
			aptDao = new AptDaoImpl();
		return aptDao;
	}

	

	@Override
	public List<String[]> selectSido() {
		List<String[]> list = new ArrayList<>();
		String sql = "select left(sidocode,2) sidocode, sidoname \n";
		sql += "from sidocode \n";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String str1 = rs.getString("sidocode");
				String str2 = rs.getString("sidoname");

				String[] str = new String[2];
				str[0] = str1;
				str[1] = str2;

				list.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
		return list;
	}

	@Override
	public List<String[]> selectGugun(String sido) {
		List<String[]> list = new ArrayList<>();

		String sql = "select left(guguncode,5) guguncode, gugunname \n";
		sql += "from guguncode \n";
		sql += "where left(guguncode,2)=? \n";
		sql += "order by guguncode \n";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sido);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String str1 = rs.getString("guguncode");
				String str2 = rs.getString("gugunname");

				String[] str = new String[2];
				str[0] = str1;
				str[1] = str2;

				list.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public List<String[]> selectDong(String gugun) {
		List<String[]> list = new ArrayList<>();

		String sql = "select distinct dongcode, dongname \n";
		sql += "from houseinfo \n";
		sql += "where left(dongcode,5)=? \n";
		sql += "order by dongcode \n";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gugun);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String str1 = rs.getString("dongcode");
				String str2 = rs.getString("dongname");
				System.out.println(str2);
				String[] str = new String[2];
				str[0] = str1;
				str[1] = str2;

				list.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public List<AptDto> selectAptByDong(String dong) {
		List<AptDto> list = new ArrayList<>();

		String sql = "select i.aptCode, i.aptName, i.dongCode, i.dongName, i.buildYear, i.jibun, i.lat, i.lng, d.dealAmount, d.dealYear, d.dealMonth, d.dealDay, d.area \n";
		sql += "from houseinfo i, housedeal d \n";
		sql += "where dongCode=? and i.aptCode=d.aptCode \n";
		sql += "order by d.dealYear desc , dealMonth desc, dealDay desc \n";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dong);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AptDto dto = new AptDto(rs.getInt("i.aptCode"), rs.getString("i.aptName"), rs.getString("i.dongcode"), 
											rs.getString("i.dongName"), rs.getInt("i.buildYear"), rs.getString("i.jibun"), rs.getString("i.lat"), rs.getString("i.lng"),
											rs.getString("d.dealAmount"), rs.getInt("d.dealYear"), rs.getInt("d.dealMonth"), rs.getInt("d.dealDay"),  rs.getString("d.area"));

				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public List<AptDto> selectAptByName(String dongcode, String aptName) {
		List<AptDto> list = new ArrayList<>();
		String tmp = "%" + aptName + "%";
		
		String sql = "select i.aptCode, i.aptName, i.dongCode, i.dongName, i.buildYear, i.jibun, i.lat, i.lng, d.dealAmount, d.dealYear, d.dealMonth, d.dealDay, d.area \n";
		sql += "from houseinfo i, housedeal d \n";
		sql += "where i.dongcode=? and i.aptName like ? and d.aptCode=i.aptCode \n";
		sql += "order by d.dealYear desc , d.dealMonth desc, d.dealDay desc \n";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dongcode);
			pstmt.setString(2, tmp);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AptDto dto = new AptDto(rs.getInt("i.aptCode"), rs.getString("i.aptName"), rs.getString("i.dongcode"), 
											rs.getString("i.dongName"), rs.getInt("i.buildYear"), rs.getString("i.jibun"), rs.getString("i.lat"), rs.getString("i.lng"),
											rs.getString("d.dealAmount"), rs.getInt("d.dealYear"), rs.getInt("d.dealMonth"), rs.getInt("d.dealDay"),  rs.getString("d.area"));

				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

}
