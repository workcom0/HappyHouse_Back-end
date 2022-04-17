<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var locations;

	$(document).ready(function(){
			$.ajax({
				url: "../../../main/apt",
				type:"post",
				dataType:'json',
				data:{action:"sido"},
				contentType:'application/x-www-form-urlencoded; charset=UTF-8',
				success:function(data){
					$.each(data, function(index, vo) {
						$("#sido").append("<option value='"+vo.sido_code+"'>"+vo.sido_name+"</option>");
					});//each			
				}, error:function(){
					alert("에러입니다.");
				}
			});	
	});//ready
	
	$(document).ready(function(){
		$("#sido").change(function() {
			$.get("../../../main/apt"
					,{action:"gugun", sido:$("#sido").val()}
					,function(data, status){
						$("#gugun").empty();
						$("#gugun").append('<option value="">구군선택</option>');
						$.each(data, function(index, vo) {
							$("#gugun").append("<option value='"+vo.gugun_code+"'>"+vo.gugun_name+"</option>");
						});//each
					}//function
					, "json"
			);//get
		});//change
		
		$("#gugun").change(function() {
			$.get("../../../main/apt"
					,{action:"dong", gugun:$("#gugun").val()}
					,function(data, status){
						$("#dong").empty();
						$("#dong").append('<option value="">동선택</option>');
						$.each(data, function(index, vo) {
							$("#dong").append("<option value='"+vo.dong_code+"'>"+vo.dong_name+"</option>");
						});//each
					}//function
					, "json"
			);//get
		});//change
		
		$("#dong").change(function() {
            $("#sido_name").attr("value",$("#sido option:checked").text());  
            $("#gugun_name").attr("value",$("#gugun option:checked").text());
            $("#dong_name").attr("value",$("#dong option:checked").text()).submit();
            
            $("#writeform").submit();
		});//change
		
	});//ready	
</script>

<div class="mb-5">
	<div class="alert alert-primary ">
		<strong id="tradeInfo">아파트 실거래가 조회</strong> 지역별 매매정보
	</div>
	<div align="center">
		<form id="writeform" class="text-left mb-3" method="post" action="/main/apt">
		<input type="hidden" name="action" value="aptByDong" />
		<input id="sido_name" type="hidden" name="sido_name" value=""/>
		<input id="gugun_name" type="hidden" name="gugun_name" value=""/>
		<input id="dong_name" type="hidden" name="dong_name" value=""/>
			<select class="" id="sido" name="sido" required="required" style="width:150px">
				<c:if test="${!empty selectDto}">
					<option value="${selectDto.sido_code}">${selectDto.sido_name }</option>
				</c:if>
				<c:if test="${empty selectDto}">
					<option value="">시도선택</option>
				</c:if>				
			</select> 
			<select class="" id="gugun" name="gugun" required="required" style="width:150px">
				<c:if test="${!empty selectDto}">
					<option value="">${selectDto.gugun_name }</option>
				</c:if>
				<c:if test="${empty selectDto}">
					<option value="">구군선택</option>
				</c:if>						
			</select> 
			<select class="" id="dong" name="dong" required="required" style="width:150px">
				<c:if test="${!empty selectDto}">
					<option value="">${selectDto.dong_name }</option>
				</c:if>
				<c:if test="${empty selectDto}">
					<option value="">동선택</option>
				</c:if>			
			</select>
		</form>
		<form id="writeform" class="text-left mb-3" method="post" action="/main/apt">
		<input type="hidden" name="action" value="aptByName" />
		<input id="sido_name" type="hidden" name="sido_name" value="${selectDto.sido_name }"/>
		<input id="gugun_name" type="hidden" name="gugun_name" value="${selectDto.gugun_name }"/>
		<input id="dong_name" type="hidden" name="dong_name" value="${selectDto.dong_name }"/>
		<input id="dong" name="dong" type="hidden" value="${selectDto.dong_code}"/>
			<span class="mx-5">
				<input id="aptName" name="aptName" type="text" placeholder="아파트명으로 검색"/>
				<input type="submit" value="검색"/>
			</span>	
		</form>
	</div>
</div>

