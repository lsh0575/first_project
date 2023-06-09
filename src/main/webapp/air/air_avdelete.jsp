<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!---------- JSP AND IMPORT AND LIB ---------->

<%@include file="../inc/header.jsp"%>
<!---------- HEADER ---------->
<!---------- HEADER ---------->
<!---------- HEADER ---------->

<!---------- START CONTAINER ---------->
<div id="airMain" class="container-field">
	<div class="row">
		<div class="col-sm-12">
			<div class="airP1">
				<h3 class="heading-panel">항공편 삭제</h3>
				<form
					action="${pageContext.request.contextPath}/air_avdelete.air?flight_code=${param.flight_code}"
					method="post" id="form">
					<fieldset>
						<div class="form-group">
							<label for="password">패스워드</label> <input type="password"
								id="pass" name="pass" class="form-control" />
							<p>(*) 패스워드를 입력하세요.</p>
						</div>
						<div class="form-group">
							<input type="submit" class="btn btn-primary" value="확인" /> <a
								href="javascript:history.go(-1)" title="원래대로"
								class="btn btn-default">취소</a>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>
<!---------- END CONTAINER ---------->

<!---------- START JAVASCRIPT ---------->
<script>
	$(document).ready(function() {
		$("#form").submit(function() {
			if ($("#password").val() == "") {
				alert("패스워드를 입력해주세요.");
				$("#password").focus();
				return false;
			}
		});
	});
</script>
<!---------- END JAVASCRIPT ---------->

<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<%@include file="../inc/footer.jsp"%>