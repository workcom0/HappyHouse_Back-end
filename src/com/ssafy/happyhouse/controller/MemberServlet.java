package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.MemberService;
import com.ssafy.happyhouse.model.service.MemberServiceImpl;

@WebServlet("/user")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = MemberServiceImpl.getMemberService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				process(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	
			try {
				process(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String act = request.getParameter("act");
		String path = "/index.jsp";
		
		if("register".equals(act)) {
			registerMember(request, response);
			response.sendRedirect("/main/index.jsp");
			
			
		} else if("idcheck".equals(act)) {
			int cnt = idCheck(request, response);
			response.getWriter().append(cnt + "");
			
			
		} else if("login".equals(act)) {
			loginMember(request, response);
			
			
		} else if("logout".equals(act)) {
			path = loginOutMember(request, response);
			response.sendRedirect(request.getContextPath() + path);
			
			
		} else if("info".equals(act)) {
			info(request, response);
			
			
		} else if("modify".equals(act)) {
			path = modify(request, response);
			response.sendRedirect(request.getContextPath() + path);
			
			
		} else if("delete".equals(act)) {
			delete(request, response);
			response.sendRedirect(request.getContextPath() + path);
			
			
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		System.out.println("삭제할 아이디  " + id);
		try {
			MemberServiceImpl.getMemberService().deleteMember(id);
			HttpSession session = request.getSession();
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String modify(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = new MemberDto();		
		memberDto.setId(request.getParameter("id"));
		memberDto.setPw(request.getParameter("pw"));
		memberDto.setName(request.getParameter("name"));
		memberDto.setEmail(request.getParameter("email"));
		memberDto.setPhone(request.getParameter("phone"));
		System.out.println(memberDto.toString());
		try {
			memberService.updateMember(memberDto);
			return "/user?act=info&id=" + memberDto.getId();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원정보 수정 중 문제가 발생했습니다.");
			return null;
		}	
	}

	private void info(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		MemberDto dto = (MemberDto) session.getAttribute("userInfo");
		if(dto==null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 후에 이용하세요.'); location.href='/main/index.jsp';</script>");
			out.flush();
		}
		
		try {
			dto = MemberServiceImpl.getMemberService().infoMember(dto.getId());
			request.setAttribute("dto", dto);
			request.getRequestDispatcher("./jsp/user/mypage.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", "회원 정보 읽기 처리중 문제 발생!!");
		}
		System.out.println("");
	}

	private int idCheck(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 1;
		String id = request.getParameter("ckid");
		cnt = memberService.idCheck(id);
		return cnt;
	}

	private void registerMember(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = new MemberDto();		
		memberDto.setId(request.getParameter("id"));
		memberDto.setPw(request.getParameter("pw"));
		memberDto.setName(request.getParameter("name"));
		memberDto.setEmail(request.getParameter("email"));
		memberDto.setPhone(request.getParameter("phone"));
		
		System.out.println(memberDto.toString());
		
		try {
			memberService.registerMember(memberDto);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원 가입 중 문제가 발생했습니다.");
		}
	}

	private void loginMember(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = null;
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println(id);
		System.out.println(pw);
		HttpSession session = request.getSession();
		
		try {
			memberDto = memberService.login(id, pw);
			if(memberDto != null) { // 로그인 성공
					session.setAttribute("userInfo", memberDto);
					System.out.println("로그인완료");
					
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('로그인 성공'); location.href='/main/index.jsp';</script>");
					out.flush();
					
			} else { // 로그인 실패
				request.setAttribute("msg", "f");
				request.getRequestDispatcher("./jsp/user/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 처리중 문제 발생!!");
		}
	}

	private String loginOutMember(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그아웃");
		HttpSession session = request.getSession();
		session.invalidate();
		return "/index.jsp";
	}
}