<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>

<div class="body-top">
	<form action="" method="get" id="form">
		<fieldset>
			<legend></legend>
				<!-- UserInfo Table -->
				<div class="container">
					<h3 class="text-center">사용자 계정 정보</h3>
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col"><input type="checkbox" id="check_all"/></th>
								<th scope="col">계정유형</th>
								<th scope="col">아이디</th>
								<th scope="col">이름</th>
								<th scope="col">이메일</th>
								<th scope="col">휴대전화번호</th>
								<th scope="col">사업자등록번호</th>
								<th scope="col">가입일자</th>
								<th scope="col">계정상태</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="dto" items="${page_count.list}">
								<tr>
									
									<td>
										<c:if test="${dto.role_name ne '관리자'}">
											<input type="checkbox"  name="check" value="${dto.id}"/>
										</c:if>
									</td>
									<td>${dto.role_name}</td>
									<td>
										<a href="${pageContext.request.contextPath}/user.acc?id=${dto.id}" title="${dto.id}의 상세페이지로 이동합니다.">${dto.id}</a>
									</td>
									<td>${dto.name}</td>
									<td>${dto.email}</td>
									<td>${dto.phonenum}</td>
									<td>${dto.company_num}</td>
									<td>${dto.create_date}</td>
									<td>${dto.status_name}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- UserInfo Table -->
				<!-- UserInfo pageSelect -->
				<div class="text-center">
					<ul class="pagination">
						<c:if test="${page_count.pagesStart!=1}">
							<li>
								<a href="${pageContext.request.contextPath}/user_list.acc?pageStartNum=${(page_count.pagesStart-2)*page_count.onePageListCount}<c:if test="${param.where ne null}">&amp;where=${param.where}&amp;searchkey=${param.searchkey}</c:if>"
								class="btn btn-default" title="이전의 페이지들을 보여줍니다.">이전</a>
							</li>
						</c:if>
						<c:forEach var="dto" begin="${page_count.pagesStart}" end="${page_count.pagesEnd}" step="1" varStatus="stat">
							<li <c:if test="${stat.index eq page_count.currentPages}">class="active"</c:if> >
								<a href="${pageContext.request.contextPath}/user_list.acc?pageStartNum=${(stat.index-1)*page_count.onePageListCount}<c:if test="${param.where ne null}">&amp;where=${param.where}&amp;searchkey=${param.searchkey}</c:if>"
								 title="${stat.index} 페이지로 이동합니다.">${stat.index}</a>
							</li>
						</c:forEach>
						<c:if test="${page_count.pageCount!=page_count.pagesEnd}">
							<li>
								<a href="${pageContext.request.contextPath}/user_list.acc?pageStartNum=${(page_count.pagesEnd)*page_count.onePageListCount}<c:if test="${param.where ne null}">&amp;where=${param.where}&amp;searchkey=${param.searchkey}</c:if>"
								class="btn btn-default" title="다음의 페이지들을 보여줍니다.">다음</a>
							</li>
						</c:if>
					</ul>
				</div>
				<!-- UserInfo pageSelect -->
				<!-- Search -->
				<div class="container">
		
					<div class="form-group text-center">
						<div>
							<div class="col-sm-2">
								<select name="where" class="form-control" id="where">
									<option value="default">검색범위 선택</option>						
									<option value="role_name">계정유형</option>
									<option value="id">아이디</option>
									<option value="name">이름</option>
									<option value="email">이메일</option>
									<option value="phonenum">휴대전화번호</option>
									<option value="company_num">사업자등록번호</option>
									<option value="create_date">가입일자</option>
									<option value="status_name">계정상태</option>
								</select>
							</div>
							<div class="col-sm-6">
								<input type="text" name="searchkey" class="form-control" id="searchkey"/>
							</div>
							<div class="col-sm-2">
								<input type="button" value="검색" id="searchbtn" class="btn btn-default btn-block" title="검색합니다."/>
							</div>
							<div class="col-sm-2">
								<div class="dropdown">
									<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">계정상태 조정 
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li class="dropdown-header">계정 권한 조정</li>
										<li><a id="activebtn" title="선택한 계정들을 활성화합니다.">활성화</a></li>
										<li><a id="outbtn" title="선택한 계정을 강제 비활성화합니다.">강제탈퇴</a></li>
										<li><a id="deletebtn" title="선택한 계정들의 정보를 삭제합니다.">삭제</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
		</form>
		<script>
			$("document").ready(function(){
				////빈칸 체크
				
				
				////전체선택
				$("#check_all").on("click",function(){
					if ($("#check_all").is(":checked")==true){
						$(":checkbox[name=check]").prop("checked",true);
					} else {
						$(":checkbox[name=check]").prop("checked",false);
					}
				});
				
				////버튼 누르면 submit되게 하기.
				//검색
				$("#searchbtn").on("click",function(){
					if ($("#where").val()=="default"){
						alert('검색 범위를 선택해주세요!');
					} else if ($("#searchkey").val()==""){
						alert('검색어를 입력해주세요!');
					} else {
						$("#form").attr("action","${pageContext.request.contextPath}/user_list.acc");
						$("#form").submit();					
					}
				});
				//활성화
				$("#activebtn").on("click",function(){
					if ($(":checkbox[name=check]:checked").length==0){
						alert('선택된 회원이 없습니다.');
					} else {
						$("#form").attr("action","${pageContext.request.contextPath}/user_active.acc");
						$("#form").attr("method","post");
						$("#form").submit();
					}
				});
				//탈퇴
				$("#outbtn").on("click",function(){
					if ($(":checkbox[name=check]:checked").length==0){
						alert('선택된 회원이 없습니다.');
					} else {
						$("#form").attr("action","${pageContext.request.contextPath}/user_forced_out.acc");
						$("#form").attr("method","post");
						$("#form").submit();
					}
				});
				//삭제
				$("#deletebtn").on("click",function(){
					if ($(":checkbox[name=check]:checked").length==0){
						alert('선택된 회원이 없습니다.');
					} else {
						$("#form").attr("action","${pageContext.request.contextPath}/user_del.acc");
						$("#form").attr("method","post");
						$("#form").submit();
					}
				});
			});
		</script>
	</div>
	<!-- Search -->

<%@include file="../inc/footer.jsp" %>