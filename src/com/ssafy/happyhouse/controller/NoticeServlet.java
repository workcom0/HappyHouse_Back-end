package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;
import com.ssafy.happyhouse.model.service.NoticeServiceImpl;
import com.ssafy.util.PageNavigation;

@WebServlet("/notice")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NoticeService noticeService = NoticeServiceImpl.getNoticeService();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "/index.jsp";
		
		if("mvregister".equals(act)) {
			response.sendRedirect(request.getContextPath() + "/jsp/notice/write.jsp");
			
			
		} else if ("register".equals(act)) {
			registerArticle(request, response);
			//request.getRequestDispatcher(path).forward(request, response);
			
			
		} else if ("list".equals(act)) {
			path = listArticle(request, response);
			request.getRequestDispatcher(path).forward(request, response);
			
			
		} else if ("get".equals(act)) {
			path = getArticle(request, response);
			request.getRequestDispatcher(path).forward(request, response);
			
			
		} else if ("modify".equals(act)) {
			path = modifyArticle(request, response);
			response.sendRedirect(request.getContextPath() + path);
			
		} else if ("delete".equals(act)) {
			path = deleteArticle(request, response);
			response.sendRedirect(request.getContextPath() + path);

			
		}
	}

	private String deleteArticle(HttpServletRequest request, HttpServletResponse response) {	
		int articleNo = Integer.parseInt(request.getParameter("articleno"));
			
		try {
			noticeService.deleteNotice(articleNo);
			return "/article?act=list";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글 얻기중 에러가 발생했습니다.");
			return "/error/error.jsp";
		}
	}

	private String modifyArticle(HttpServletRequest request, HttpServletResponse response) {		
		NoticeDto noticeDto = new NoticeDto();
		noticeDto.setArticleNo(Integer.parseInt(request.getParameter("articleno")));
		noticeDto.setSubject(request.getParameter("subject"));
		noticeDto.setContent(request.getParameter("content"));
		try {
			noticeService.updateNotice(noticeDto);
			return "/notice?act=get&articleno="+noticeDto.getArticleNo();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글 수정중 에러가 발생했습니다.");
			return "/error/error.jsp";
		}
	}

	private String getArticle(HttpServletRequest request, HttpServletResponse response) {
		int articleNo = Integer.parseInt(request.getParameter("articleno"));
			
		try {
			NoticeDto noticeDto = noticeService.getNotice(articleNo);
			request.setAttribute("article", noticeDto);
			return "/jsp/notice/detail.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글 얻기중 에러가 발생했습니다.");
			return "/error/error.jsp";
		}
		
	}

	private String listArticle(HttpServletRequest request, HttpServletResponse response) {
		String pg = request.getParameter("pg");
		String key = request.getParameter("key");
		String word = request.getParameter("word");
		try {
			List<NoticeDto> list = noticeService.listNotice(pg, key, word);
			PageNavigation navigation = noticeService.makePageNavigation(pg, key, word);
				
			request.setAttribute("noticeList", list);
			request.setAttribute("navi", navigation);
			request.setAttribute("key", key);
			request.setAttribute("word", word);
				
			return "/jsp/notice/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글목록 얻기중 에러가 발생했습니다.");
			return "/error/error.jsp";
		}		
	}

	private void registerArticle(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
			
		NoticeDto noticeDto = new NoticeDto();
		noticeDto.setUserId(memberDto.getId());
		noticeDto.setSubject(request.getParameter("subject"));
		noticeDto.setContent(request.getParameter("content"));
		try {
			noticeService.registerNotice(noticeDto);
			response.sendRedirect("/main/notice?act=list&pg=1&key=&word=&");
			/*request.setAttribute("articleno", noticeDto.getArticleNo());
			getArticle(request, response);*/
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글 작성중 에러가 발생했습니다.");
			try {
				request.getRequestDispatcher("/error/error.jsp").forward(request, response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} ;
		}	
	}
}
