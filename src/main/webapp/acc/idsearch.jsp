<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp" %>
<div class="body-top container">
	
	<div style="margin-top:20px;">
		<!-- 아이디 찾기 -->
		<div class="col-sm-6">
			<h3 class="text-center">아이디 찾기</h3>
			<form action="${pageContext.request.contextPath}/id_search_act.acc" id="idsearch">
				<fieldset>
					<legend>정보 입력</legend>
					<div class="form-group">
						<label for="idsearchkey">아이디를 검색하기 위한 정보를 입력해주세요.</label>
						<input type="text" class="form-control" name="searchkey" id="idsearchkey"/>
					</div>
					<div class="form-group">
						<input type="radio" id="idphonenum" name="type" value="phonenum" checked/>
						<label for="idphonenum">휴대전화번호로 찾기</label>
						<input type="radio" id="idemail" name="type" value="email"/>
						<label for="idemail">이메일로 찾기</label>
					</div>
					<div class="form-group text-center">
						<input type="submit" class="btn btn-primary" value="찾기" title="입력한 정보로 검색합니다."/>
					</div>
				</fieldset>
			</form>
			<script>
				$(document).ready(function(){
					$("#idsearch").on("submit",function(){
						if ($("#idsearchkey").val().trim()==""){
							alert('값을 입력해주세요!');
							return false;
						}
					});
				});
			</script>
		</div>
		<!-- 아이디 찾기 -->
		<!-- 비밀번호 찾기 -->
		<div class="col-sm-6 leftline">
			<h3 class="text-center">비밀번호 찾기</h3>
			<form action="${pageContext.request.contextPath}/pass_search_act.acc" id="passsearch">
				<fieldset>
					<legend>정보 입력</legend>
					<div class="form-group">
						<label for="passsearchid">아이디</label>
						<input type="text" class="form-control" name="passsearchid" id="passsearchid"/>
					</div>
					<div class="form-group">
						<label for="passsearchkey">비밀번호를 검색하기 위한 정보를 입력해주세요.</label>
						<input type="text" class="form-control" name="searchkey" id="passsearchkey"/>
					</div>
					<div class="form-group">
						<input type="radio" id="passphonenum" name="type" value="phonenum" checked/>
						<label for="passphonenum">휴대전화번호로 찾기</label>
						<input type="radio" id="passemail" name="type" value="email"/>
						<label for="passemail">이메일로 찾기</label>
					</div>
					<div class="form-group text-center">
						<input type="submit" class="btn btn-primary" value="찾기" title="입력한 정보로 검색합니다."/>
					</div>
				</fieldset>
			</form>
			<script>
				$(document).ready(function(){
					$("#passsearch").on("submit",function(){
						if ($("#passsearchkey").val().trim()==""){
							alert('값을 입력해주세요!');
							return false;
						}
					});
				});
			</script>
		</div>
		<!-- 비밀번호 찾기 -->
	</div>
</div>
<%@include file="/inc/footer.jsp" %>