<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../inc/header.jsp" %>

<style>
	.container-field { padding:5% }
	.img_detail img { width:40% }
</style>

	<div class="container-field form-group form-group-info body-top">
	<form action="#">
	<fieldset>
		<h3 class="form-group">Travel Detail</h3>
		
	<div class="form-group">
		<div class="form-group">
			<span class="glyphicon glyphicon-plus">No.</span>
			<input type="text" id="tno" name="tno" value="${dto.tno}" readonly class="form-control">
		</div>
		<div class="form-group">
			<span class="glyphicon glyphicon-plus">조회수</span>
			<input type="text" id="thit" name="thit" value="${dto.thit}" readonly class="form-control">
		</div>
	</div>
	
	<div class="form-group">
		<div class="form-group">
			<span class="glyphicon glyphicon-plus">카테고리</span>
			<input type="text" id="tcategory" name="tcategory" value="${dto.tcategory_name}" readonly class="form-control">
		</div>
		<div class="form-group">
			<span class="glyphicon glyphicon-plus">여행경비</span>
			<input type="text" id="tcost" name="tcost" value="${dto.tcost}" readonly class="form-control">
		</div>
	</div>
	
	<div class="form-group">
		<div class="form-group">
			<span class="glyphicon glyphicon-plus">여행시작일</span>
			<input type="text" id="tstart_date" name="tstart_date" value="${dto.tstart_date}" readonly class="form-control">
		</div>
		<div class="form-group">
			<span class="glyphicon glyphicon-plus">여행종료일</span>
			<input type="text" id="tend_date" name="tend_date" value="${dto.tend_date}" readonly class="form-control">
		</div>
	</div>
	
	<div class="form-group">
		<div class="form-group">
			<span class="glyphicon glyphicon-plus">작성자</span>
			<input type="text" id="tname" name="tname" value="${dto.tname}" readonly class="form-control">
		</div>		
	</div>
	
	<div class="form-group">
		<div class="form-group">
			<span class="glyphicon glyphicon-plus">제목</span>
			<input type="text" id="ttitle" name="ttitle" value="${dto.ttitle}" readonly class="form-control">
		</div>
		<div class="form-group">
			<span class="glyphicon glyphicon-plus">내용</span>
			<input type="text" id="tcontent" name="tcontent" value="${dto.tcontent}" readonly class="form-control">
		</div>	
	</div>
	<div class="form-group">
		<div class="form-group">
			<span class="glyphicon glyphicon-plus">추천점수</span>
			<input type="text" id="tscore" name="tscore" value="${dto.tscore}" readonly class="form-control">
		</div>
	</div>
	<div class="form-group">
	  <div class="form-group img_detail">
	    <span class="glyphicon glyphicon-plus">이미지</span>
	    <p><img src="${pageContext.request.contextPath}/upload/${dto.timages_1}" alt="${dto.timages_1}"></p>
	    
	    <c:if test="${not empty dto.timages_2}">
	      <p><img src="${pageContext.request.contextPath}/upload/${dto.timages_2}" alt="${dto.timages_2}"></p>
	    </c:if>
	    
	    <c:if test="${not empty dto.timages_3}">
	      <p><img src="${pageContext.request.contextPath}/upload/${dto.timages_3}" alt="${dto.timages_3}"></p>
	    </c:if>
	  </div>
	</div>
		<div class="form-group">
		<div class="form-group">
			<a href="<%=request.getContextPath()%>/edit_view.travel?tno=${dto.tno}" class="form-control btn btn-info">수정</a>
		</div>
		<div class="form-group">
			<a href="<%=request.getContextPath()%>/delete_view.travel?tno=${dto.tno}" class="form-control btn btn-danger">삭제</a>	
		</div>
		<div class="form-group">
			<a href="<%=request.getContextPath()%>/paging.travel" class="form-control btn btn-default">목록</a>
		</div>
	</div>
	
	</fieldset>
	</form>		
</div>		

<%@include file="../inc/footer.jsp"%>