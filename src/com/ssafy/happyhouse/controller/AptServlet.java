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
import com.ssafy.happyhouse.model.AptSelectBoxDto;
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
		System.out.println(action);
		
		if(action.equals("sido")) {
			selectSido(request, response);
		}else if(action.equals("gugun")) {
			selectGugun(request, response);
		}else if(action.equals("dong")) {
			selectDong(request, response);
		}else if(action.equals("aptByDong")) {
			selectAptByDong(request, response);
		}else if(action.equals("aptByName")) {
			selectAptByName(request, response);
		}
	}

	//------------------------------------------------------------ 아파트명으로 검색했을때, 아파트 리스트 출력
	private void selectAptByName(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	

	//------------------------------------------------------------ 동 선택했을 때, 아파트 리스트 출력
	private void selectAptByDong(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		String dong = request.getParameter("dong");
		
		String sido_name = request.getParameter("sido_name");
		String gugun_name = request.getParameter("gugun_name");
		String dong_name = request.getParameter("dong_name");
		
		AptSelectBoxDto selectDto = new AptSelectBoxDto(sido, sido_name, gugun, gugun_name, dong, dong_name);
		
		List<AptDto> list = null;
		JSONArray arr = new JSONArray();
		
		
		try {		
			list = aptService.selectAptByDong(dong);
			request.setAttribute("AptDto", list);
			request.setAttribute("selectDto", selectDto);
			
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
				obj.put("dealAmount", dto.getDealAmount());
				obj.put("dealYear", dto.getDealYear());
				obj.put("dealMonth", dto.getDealMonth());
				obj.put("dealDay", dto.getDealDay());
				
				arr.add(obj);
			}
			
			String jsonString = JSONValue.toJSONString(arr); 
			response.setCharacterEncoding("UTF-8"); 
			request.setAttribute("jsonData", jsonString);

			request.getRequestDispatcher("./jsp/apt/apt.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	
	//------------------------------------------------------------ 동 선택
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

	
	//------------------------------------------------------------ 구군 선택
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

	
	
	//------------------------------------------------------------ 시도 선택
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
