package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.ListParameterDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.util.DBUtil;

public class NoticeDaoImpl implements NoticeDao{
	private DBUtil dbUtil = DBUtil.getInstance();

	private static NoticeDao noticeDao = new NoticeDaoImpl();

	private NoticeDaoImpl() {}

	public static NoticeDao getNoticeDao() {
		return noticeDao;
	}

	@Override
	public void registerNotice(NoticeDto noticeDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder registerNotice = new StringBuilder();
			registerNotice.append("insert into notice (userid, subject, content, regtime) \n");
			registerNotice.append("values (?, ?, ?, now())");
			pstmt = conn.prepareStatement(registerNotice.toString());
			pstmt.setString(1, noticeDto.getUserId());
			pstmt.setString(2, noticeDto.getSubject());
			pstmt.setString(3, noticeDto.getContent());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public List<NoticeDto> listNotice(ListParameterDto listParameterDto) throws SQLException {
		List<NoticeDto> list = new ArrayList<NoticeDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder listNotice = new StringBuilder();
			listNotice.append("select g.articleno, g.userid, g.subject, g.content, \n");
			listNotice.append(" 		case when date_format(g.regtime, '%y%m%d') = date_format(now(), '%y%m%d') \n");
			listNotice.append("			then date_format(g.regtime, '%H:%i:%d') \n");
			listNotice.append("			else date_format(g.regtime, '%y.%m.%d') \n");
			listNotice.append("		end regtime \n");
			listNotice.append("from notice g \n");			
			String key = listParameterDto.getKey();
			String word = listParameterDto.getWord();
			if(!word.isEmpty()) {
				listNotice.append("where \n");
				if(key.equals("subject")) {
					listNotice.append(" g.subject like concat('%', ?, '%') \n");
				} else if(key.equals("content")){
					listNotice.append(" g.content like concat('%', ?, '%') \n");
				}
			}
			listNotice.append("order by g.articleno desc limit ?, ? \n");
			pstmt = conn.prepareStatement(listNotice.toString());
			int idx = 0;
			if(!word.isEmpty()) {
				pstmt.setString(++idx, word);
			}
			pstmt.setInt(++idx, listParameterDto.getStart());
			pstmt.setInt(++idx, listParameterDto.getCurrentPerPage());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NoticeDto noticeDto = new NoticeDto();
				noticeDto.setArticleNo(rs.getInt("articleno"));
				noticeDto.setUserId(rs.getString("userid"));
				noticeDto.setSubject(rs.getString("subject").replace("<", "&lt;"));
				noticeDto.setContent(rs.getString("content"));
				noticeDto.setRegTime(rs.getString("regtime"));
				
				list.add(noticeDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public int getTotalCount(ListParameterDto listParameterDto) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder listArticle = new StringBuilder();
			listArticle.append("select count(articleno) \n");
			listArticle.append("from notice \n");
			String key = listParameterDto.getKey();
			String word = listParameterDto.getWord();
			if(!word.isEmpty()) {
				if(key.equals("subject")) {
					listArticle.append("where subject like concat('%', ?, '%') \n");
				} else {
					listArticle.append("where " + key + " = ? \n");
				}
			}

			pstmt = conn.prepareStatement(listArticle.toString());
			if(!word.isEmpty())
				pstmt.setString(1, word);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}

	@Override
	public NoticeDto getNotice(int noticeNo) throws SQLException {
		NoticeDto noticeDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder listNotice = new StringBuilder();
			listNotice.append("select g.articleno, g.userid, g.subject, g.content, g.regtime \n");
			listNotice.append("from notice g \n");
			listNotice.append("where g.articleno = ? \\n");
			System.out.println(listNotice.toString());
			pstmt = conn.prepareStatement(listNotice.toString());
			pstmt.setInt(1, noticeNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				noticeDto = new NoticeDto();
				noticeDto.setArticleNo(rs.getInt("articleno"));
				noticeDto.setUserId(rs.getString("userid"));
				noticeDto.setSubject(rs.getString("subject"));
				noticeDto.setContent(rs.getString("content"));
				noticeDto.setRegTime(rs.getString("regtime"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return noticeDto;
	}

	@Override
	public void updateNotice(NoticeDto noticeDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder updateNotice = new StringBuilder();
			updateNotice.append("update notice \n");
			updateNotice.append("set subject = ?, content = ? \n");
			updateNotice.append("where articleno = ?");
			pstmt = conn.prepareStatement(updateNotice.toString());
			pstmt.setString(1, noticeDto.getSubject());
			pstmt.setString(2, noticeDto.getContent());
			pstmt.setInt(3, noticeDto.getArticleNo());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}		
	}

	@Override
	public void deleteNotice(int noticeNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder updateNotice = new StringBuilder();
			updateNotice.append("delete from notice \n");
			updateNotice.append("where articleno = ?");
			pstmt = conn.prepareStatement(updateNotice.toString());
			pstmt.setInt(1, noticeNo);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

}
