<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp" %>

<style>
	.container-field { padding:5% }
	.img_edit img { width:40% }
</style>

	<div class="container-field panel panel-info body-top">
	<form action="${pageContext.request.contextPath}/edit.travel?tno=${dto.tno}" method="post" id="edit_form">
	<fieldset>
	<legend>Travel EDIT(UPDATE)</legend>
	
	<div class="from-group">
		<div class="panel">
			<div class="panel-body">
				<span class="glyphicon glyphicon-plus">카테고리</span>
				<input type="text" id="tcategory" name="tcategory" value="${dto.tcategory_name}" readonly class="form-control">
			</div>
			<div class="panel-body">
				<span class="glyphicon glyphicon-plus">여행경비</span>
				<input type="text" id="tcost" name="tcost" value="${dto.tcost}" class="form-control">
			</div>
		</div>
		<div class="panel">
			<div class="panel-body">
				<label for="tstart_date" class="glyphicon glyphicon-plus">여행시작일</label>
				<input type="date" id="tstart_date" name="tstart_date" value="${dto.tstart_date}" class="form-control">
			</div>
			<div class="panel-body">
				<label for="tend_date" class="glyphicon glyphicon-plus">여행종료일</label>
				<input type="date" id="tend_date" name="tend_date" value="${dto.tend_date}" class="form-control">
			</div>
		</div>
	</div>
	<div class="panel">
		<div class="panel-body">
			<span class="glyphicon glyphicon-plus">작성자</span>
			<input type="text" id="tname" name="tname" value="${dto.tname}" readonly class="form-control">
		</div>
		<div class="panel-body">
			<label class="glyphicon glyphicon-plus" for="tpass">비밀번호</label>
			<input type="password" id="tpass" name="tpass" class="form-control" placeholder="패스워드를 입력하세요.">
			<span>(*) 수정 및 삭제시 필수</span>
		</div>		
	</div>
	<div class="panel">
		<div class="panel-body">
			<span class="glyphicon glyphicon-plus">제목</span>
			<input type="text" id="ttitle" name="ttitle" value="${dto.ttitle}" class="form-control">
	</div>
		
		<div class="panel-body">
			<span class="glyphicon glyphicon-plus">내용</span>
			<textarea name="tcontent" id="tcontent" class="form-control" style="resize: none;" rows="10">${dto.tcontent}</textarea>
		</div>	
	</div>

	<div class="panel">
		<div class="panel-body img_edit">
			<span class="glyphicon glyphicon-plus">이미지</span>
			<p><img src="/upload/${dto.timages_1}" alt="${dto.timages_1}"></p>
		</div>
	</div>
	<div class="panel">
		<div class="panel-body">
			<span class="glyphicon glyphicon-plus">추천점수</span>
			<input type="text" id="tscore" name="tscore" value="${dto.tscore}" readonly class="form-control">
		</div>
	</div>
	<div class="panel">
		<div class="panel-body">
			<input type="submit" id="submit" value="확인" class="form-control btn btn-info">
		</div>
		<div class="panel-body">
			<input type="reset" id="reset" value="취소" class="form-control btn btn-danger">
		</div>
		<div class="panel-body">
			<a href="${pageContext.request.contextPath}/paging.travel" class="form-control btn btn-default">목록</a>
		</div>
	</div>	 	
</fieldset>
</form>
	
	<script>
		
	$(function() {
		  $("#edit_form").on("submit", function() {
		    if ($("#tcost").val() == "") {
		      $("#tcost").focus();
		      alert("여행경비를 입력 해주세요.");
		      return false;
		    }
		    if ($("#tstart_date").val() == "") {
		      $("#tstart_date").focus();
		      alert("여행 날짜를 확인 해주세요.");
		      return false;
		    }
		    if ($("#tend_date").val() == "") {
		      $("#tend_date").focus();
		      alert("여행 날짜를 확인 해주세요.");
		      return false;
		    }
		    if ($("#tpass").val() == "") {
		      $("#tpass").focus();
		      alert("패스워드를 입력해주세요.");
		      return false;
		    }
		    if ($("#ttitle").val() == "") {
		      $("#ttitle").focus();
		      alert("제목을 입력해주세요.");
		      return false;
		    }
		    if ($("#tcontent").val() == "") {
		      $("#tcontent").focus();
		      alert("내용을 입력해주세요.");
		      return false;
		    }
		  });

		  $("#tend_date").on("change", function() {
		    validateEndDate();
		  });

		  function validateEndDate() {
		    var startDate = new Date(document.getElementById("tstart_date").value);
		    var endDate = new Date(document.getElementById("tend_date").value);

		    if (endDate < startDate) {
		      alert("여행 종료일은 여행 시작일 이후의 날짜여야 합니다.");
		      document.getElementById("tend_date").value = "";
		    }
		  }
		});
	</script>
	</div>

<%@include file="../inc/footer.jsp" %>
	