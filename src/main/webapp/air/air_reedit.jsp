<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!---------- JSP AND IMPORT AND LIB ---------->
<%@include file="../inc/header.jsp"%>
<!---------- HEADER ---------->
<!---------- HEADER ---------->
<!---------- HEADER ---------->

<!---------- START CONTAINER ---------->
<div id="airMain" class="body-top container-field">
	<div class="row">
		<div class="col-sm-12">
			<div class="airP1">
				<h3 class="heading-panel">예약자 수정</h3>
				<form
					action="${pageContext.request.contextPath}/air_reedit.air?reservation_number=${list[0].reservation_number}"
					method="post" id="form">
					<table class="table table-striped table-bordered">
						<tbody>
							<tr>
								<th scope="row">예약자명</th>
								<td><input type="text" class="form-control"
									id="reservation_name" name="reservation_name"
									value="${list[0].reservation_name}" /></td>
							</tr>
							<tr>
								<th scope="row">ID</th>
								<td><input type="text" class="form-control" id="user_ID"
									name="user_ID" value="${list[0].user_ID}" readonly /></td>
							</tr>
							<tr>
								<th scope="row">전화번호</th>
								<td><input type="tel" class="form-control"
									id="telephone_number" name="telephone_number"
									value="${list[0].telephone_number}"
									pattern="^01[016789]-[0-9]{4}-[0-9]{4}$" required
									oninput="validateTelephoneNumber(this)" /></td>
							</tr>
							<tr>
								<th scope="row">이메일</th>
								<td><input type="email" class="form-control" id="email"
									name="email" value="${list[0].email}" /></td>
							</tr>
							<tr>
								<th scope="row">티켓 종류</th>
								<td><input type="text" class="form-control"
									id="ticket_type" name="ticket_type"
									value="${list[0].ticket_type}" readonly /></td>
							</tr>
							<tr>
								<th scope="row">성인 예약자 수</th>
								<td><input type="text" class="form-control" id="adults"
									name="adults" value="${list[0].adults}" readonly /></td>
							</tr>
							<tr>
								<th scope="row">청소년 예약자 수</th>
								<td><input type="text" class="form-control" id="teenagers"
									name="teenagers" value="${list[0].teenagers}" readonly /></td>
							</tr>
							<tr>
								<th scope="row">총 결제 금액</th>
								<td><input type="text" class="form-control"
									id="total_payment" name="total_payment"
									value="${list[0].total_payment}" readonly /></td>
							</tr>
							<tr>
								<th scope="row">예약 일자</th>
								<td><input type="text" class="form-control" id="email"
									name="email" value="${list[0].email}" readonly /></td>
							</tr>
						</tbody>
					</table>
					<div class='form-group'>
						<input type="submit" class="btn btn-primary btn-block" title="입력"
							value="등록" />
					</div>
					<div class="form-group">
						<input type="reset" class="btn btn-warning btn-block" title="취소"
							value="취소" />
					</div>
					<div class="form-group">
						<a
							href="${pageContext.request.contextPath}/air_redetail.air?reservation_number=${list[0].reservation_number}"
							class="btn btn-default btn-block" title="목록보기">목록보기</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!---------- END CONTAINER ---------->

<!---------- START JAVASCRIPT ---------->
<script>
function validateForm() {
    if ($("#reservation_name").val() === "") {
        alert("예약자명을 입력해주세요.");
        $("#reservation_name").focus();
        return false;
    }
    if ($("#telephone_number").val() === "") {
        alert("전화번호를 입력해주세요.");
        $("#telephone_number").focus();
        return false;
    }
    if ($("#email").val() === "") {
        alert("이메일을 입력해주세요.");
        $("#email").focus();
        return false;
    }
    
    var telephoneRegex = /^01[016789]-[0-9]{4}-[0-9]{4}$/;
    if (!telephoneRegex.air($("#telephone_number").val())) {
        alert("010-xxxx-xxxx 형식으로 입력해주세요.");
        $("#telephone_number").focus();
        return false;
    }
    
    return true;
}

$(document).ready(function() {
    $("#form").submit(function() {
        return validateForm();
    });
});
</script>
<!---------- END JAVASCRIPT ---------->

<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<!---------- FOOTER ---------->
<%@include file="../inc/footer.jsp"%>