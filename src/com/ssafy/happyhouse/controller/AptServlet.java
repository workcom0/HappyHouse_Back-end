package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.ssafy.happyhouse.model.AptDto;
import com.ssafy.happyhouse.model.service.AptService;
import com.ssafy.happyhouse.model.service.AptServiceImpl;

@WebServlet("/apt")
public class AptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AptService aptService = AptServiceImpl.getAptService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String path = "/index.jsp";
		
		if(action.equals("sido")) {
			selectSido(request, response);
		}else if(action.equals("gugun")) {
			selectGugun(request, response);
		}else if(action.equals("dong")) {
			selectDong(request, response);
		}else if(action.equals("apt")) {
			selectApt(request, response);
		}
	}

	private void selectApt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		String dong = request.getParameter("dong");
		PrintWriter out = response.getWriter();
		List<AptDto> list = null;
		JSONArray arr = new JSONArray();
		
		list = aptService.selectApt(dong);		
		for(AptDto dto : list) {
			JSONObject obj = new JSONObject();
			obj.put("aptCode", dto.getAptCode());
			obj.put("aptName", dto.getAptName());
			obj.put("dongCode", dto.getDongCode());
			obj.put("dongName", dto.getDongName());
			obj.put("buildYear", dto.getBuildYear());
			obj.put("jibun", dto.getJibun());
			obj.put("lat", dto.getLat());
			obj.put("lng", dto.getLng());
			arr.add(obj);
		}
		
		String jsonString = JSONValue.toJSONString(arr); 
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(jsonString);
	}

	private void selectDong(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		String gugun = request.getParameter("gugun");
		PrintWriter out = response.getWriter();
		List<String[]> list = null;
		JSONArray arr = new JSONArray();
		
		list = aptService.selectDong(gugun);		
		for(String[] dto : list) {
			JSONObject obj = new JSONObject();
			obj.put("dong_code", dto[0]);
			obj.put("dong_name", dto[1]);
			System.out.println(dto[1]);
			arr.add(obj);
		}
		
		String jsonString = JSONValue.toJSONString(arr); 
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(jsonString);
	}

	private void selectGugun(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		String sido = request.getParameter("sido");
		PrintWriter out = response.getWriter();
		List<String[]> list = null;
		JSONArray arr = new JSONArray();
		
		list = aptService.selectGugun(sido);		
		for(String[] dto : list) {
			JSONObject obj = new JSONObject();
			obj.put("gugun_code", dto[0]);
			obj.put("gugun_name", dto[1]);
			arr.add(obj);
		}
		
		String jsonString = JSONValue.toJSONString(arr); 
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(jsonString);
	}

	private void selectSido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		List<String[]> list = new ArrayList<>();
		JSONArray arr = new JSONArray();
		
		list = aptService.selectSido();		
		for(String[] dto : list) {
			JSONObject obj = new JSONObject();
			obj.put("sido_code", dto[0]);
			obj.put("sido_name", dto[1]);
			arr.add(obj);
		}
		
		String jsonString = JSONValue.toJSONString(arr); 
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(jsonString);
		
	}
	
	

}
