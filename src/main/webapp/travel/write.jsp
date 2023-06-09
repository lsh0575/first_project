<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../inc/header.jsp"%>

<style>
	.container-field { padding:5% }
</style>

<div class="container-field  panel-info body-top">
	<h3 class="text-center" >Travel Review</h3>
	<form action="${pageContext.request.contextPath}/write.travel"
		  method="post"
		  enctype="multipart/form-data"
		  id="write_form">
	<fieldset>
	<legend>Travel Review(CREATE)</legend>
	
<div class="from-group">
	<div>
		<div>
		<label for="tcategory">여행 카테고리</label> <br/>
			<input type="radio" name="tcategory" value="1" id="healing" checked> 
			<label for="healing">healing</label>
			<input type="radio" name="tcategory" value="2" id="active"> 
			<label for="active">active</label>
			<input type="radio" name="tcategory" value="3" id="food"> 
			<label for="food">food</label>
			<input type="radio" name="tcategory" value="4" id="site"> 
			<label for="site">site</label>
		</div>
		<div>
			<label for="tcost">여행 경비</label> 
			<input type="number" name="tcost" id="tcost" placeholder="여행경비입력" min="0" step="5000" class="form-control">
		</div>
	</div>
	<div>	
		<div>
			<label for="tstart_date">여행 시작일</label> 
			<input type="date" name="tstart_date" id="tstart_date" min="1980-01-01" max="2999-12-31" class="form-control">
		</div>
		<div>
			<label for="tend_date">여행 종료일</label> 
			<input type="date" name="tend_date" id="tend_date" min="1980-01-01" max="2999-12-31" class="form-control" onchange="validateEndDate()">
		</div>
	</div>
</div>

	<div>	
		<div>
			<label for="tname">작성자</label>
			<input type="text" id="tname" name="tname" value="${sessionScope.account.id}" class="form-control" readonly />
		</div>
		<div>
			<label for="tpass">비밀번호</label>
			<input type="password" id="tpass" name="tpass" class="form-control" placeholder="패스워드를 입력하세요.">
		
		</div>
	</div>
	
	<div>	
	<div>
		<label for="ttitle">제목</label>
		<input type="text" name="ttitle" id="ttitle" class="form-control" placeholder="제목을 입력하세요." />
	</div>
	<div>
		<label for="tcontent">내용</label>
		<textarea name="tcontent" id="tcontent" class="form-control" style="resize: none;" rows="10" placeholder="내용을 입력하세요."></textarea>
	</div>
	</div>
	
	<div>
		<div class="form-group">
			<label for="timages">이미지 업로드</label>
			<input type="file" id="timages" name="timages_1">
		</div>
		<div class="form-group">
			<input type="file" id="timages_2" name="timages_2">
		</div>
		<div class="form-group">
			<input type="file" id="timages_3" name="timages_3">
		</div>
	</div>
	
		<div>
			<div>
				<label for="score">추천점수</label><br> 
					<input type="radio" name="tscore" id="score1" value="1" checked>
					<label for="score1">1</label>
					<input type="radio" name="tscore" id="score2" value="2">
					<label for="score2">2</label> 
					<input type="radio" name="tscore" id="score3" value="3"> 
					<label for="score3">3</label>
					<input type="radio" name="tscore" id="score4" value="4">
					<label for="score4">4</label> 
					<input type="radio" name="tscore" id="score5" value="5">
					<label for="score5">5</label>
			</div>
		</div>
	<div>
		<div class="form-group">
			<input type="submit" id="submit" value="등록" class="form-control btn btn-success">
		</div>
		<div class="form-group">
			<input type="reset" id="reset" value="취소" class="form-control btn btn-danger">
		</div>
		<div class="form-group">
			<a href="${pageContext.request.contextPath}/paging.travel" class="form-control btn btn-default">목록</a>
		</div>
	</div>	 	
</fieldset>
</form>
	
	<script>
	$(function(){
		  $("#write_form").on("submit", function() {
		    if ($("#tcost").val() == "") {
			      $("#tcost").focus();
			      alert("여행경비를 입력 해주세요.");
			      return false;
			}
		    if ($("#tstart_date").val() == "") {
		      $("#tstart_date").focus();
		      alert("여행 날짜를 입력 해주세요.");
		      return false;
		    }
		    if ($("#tend_date").val() == "") {
		      $("#tend_date").focus();
		      alert("여행 날짜를 입력 해주세요.");
		      return false;
		    }
		    if ($("#tname").val() == "") {
		      $("#tname").focus();
		      alert("작성자를 입력해주세요.");
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
			var currentDate = new Date();
			var startDate = new Date(document.getElementById("tstart_date").value);
		    var endDate = new Date(document.getElementById("tend_date").value);
		    if (startDate > currentDate) {
		        alert("여행 시작일은 현재 날짜보다 앞일 수 없습니다.");
		        $("#tstart_date").val("");
		    }
		    if (endDate < startDate) {
		      alert("여행 종료일은 여행 시작일 이후의 날짜여야 합니다.");
		      document.getElementById("tend_date").value = "";
		    }
		  }
		});
	</script>
	
</div>


<%@include file="../inc/footer.jsp"%>