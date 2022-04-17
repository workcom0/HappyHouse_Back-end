package com.ssafy.happyhouse.model;

public class AptSelectBoxDto {
	private String sido_code;
	private String sido_name;
	private String gugun_code;
	private String gugun_name;
	private String dong_code;
	private String dong_name;
	public AptSelectBoxDto(String sido_code, String sido_name, String gugun_code, String gugun_name, String dong_code,
			String dong_name) {
		super();
		this.sido_code = sido_code;
		this.sido_name = sido_name;
		this.gugun_code = gugun_code;
		this.gugun_name = gugun_name;
		this.dong_code = dong_code;
		this.dong_name = dong_name;
	}
	public String getSido_code() {
		return sido_code;
	}
	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}
	public String getSido_name() {
		return sido_name;
	}
	public void setSido_name(String sido_name) {
		this.sido_name = sido_name;
	}
	public String getGugun_code() {
		return gugun_code;
	}
	public void setGugun_code(String gugun_code) {
		this.gugun_code = gugun_code;
	}
	public String getGugun_name() {
		return gugun_name;
	}
	public void setGugun_name(String gugun_name) {
		this.gugun_name = gugun_name;
	}
	public String getDong_code() {
		return dong_code;
	}
	public void setDong_code(String dong_code) {
		this.dong_code = dong_code;
	}
	public String getDong_name() {
		return dong_name;
	}
	public void setDong_name(String dong_name) {
		this.dong_name = dong_name;
	}
	
}
