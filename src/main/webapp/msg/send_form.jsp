<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="body-top container">
	<h3 class="text-center">메세지 보내기</h3>
	<!-- 메세지 폼 -->
	<form action="${pageContext.request.contextPath}/send.msg" method="post" id="messageform">
		<c:if test="${sessionScope.account.role_id eq 1}">
			<div class="form-group">
				<input type="checkbox" value="check" id="sendall"/>
				<label for="sendall">전체 발송</label>
				<input type="checkbox" value="check" id="sendcompany"/>
				<label for="sendcompany">사용자 전체 발송</label>
			</div>
			<div class="form-group">
				<input type="checkbox" name="role" value="0" id="role0"/>
				<label for="role0">일반사용자</label>
				<input type="checkbox" name="role" value="1" id="role1"/>
				<label for="role1">관리자</label>
				<input type="checkbox" class="company" name="role" value="2" id="role2"/>
				<label for="role2">항공사업자</label>
				<input type="checkbox" class="company" name="role" value="3" id="role3"/>
				<label for="role3">숙박사업자</label>
				<input type="checkbox" class="company" name="role" value="4" id="role4"/>
				<label for="role4">여행사업자</label>
			</div>
		</c:if>
		<div class="form-group" id="reciever_div">
			<label for="reciever" >수신자 아이디</label><span id="idcheck"></span>
			<input type="text" class="form-control" name="reciever" id="reciever" placeholder="수신자 아이디를 입력해주세요"/>
			<div id="result" class="dropdown">
			</div>
		</div>
		<div class="form-group">
			<label for="title">제목</label>
			<input type="text" class="form-control" name="title" id="title" placeholder="메시지 제목을 입력해주세요"/>
		</div>
		<div class="form-group">
			<label for="context">내용</label>
			<textarea id="context" class="form-control" rows="15" name="context" placeholder="메세지 내용을 입력하세요"></textarea>
		</div>
		<div class="form-group text-center">
			<div class="col-sm-3"></div>
			<div class="col-sm-3"><input type="submit" class="btn btn-primary btn-block" value="전송" title="전송하기"></div>
			<div class="col-sm-3"><a href="javascript:history.go(-1)" class="btn btn-danger btn-block" title="전송을 하지 않고 뒤로 갑니다.">뒤로가기</a></div>
			<div class="col-sm-3"></div>
		</div>
	</form>
	<script>

	
		$(document).ready(function(){
			//제목 입력할 때 탭 또는 엔터면 인식해서 다음으로
			$("#reciever").on("keydown",function(e){
				if (e.keyCode==9||e.keyCode==13||e.keycode==108){
					$("#context").focus();
					e.keyCode==null;
					return false;
				}
			});
			//아이디 입력할때 탭 또는 엔터면 인식해서 다음으로
			$("#reciever").on("keydown",function(e){
				if (e.keyCode==9||e.keyCode==13||e.keycode==108){
					$(".idselect").first().click();
					$("#title").focus();
					e.keyCode==null;
					return false;
				}
			});
			
			
			//체크박스 하나라도 체크되어있으면 수신자 아이디 잠그기
			var recievercontrol = function(){
				if ($(":checkbox[name='role']:checked").length!=0){
					$("#reciever").attr("readonly",true);
					$("#reciever").val(null);
					$("#reciever").attr("placeholder","그룹에 전송합니다");					
				} else {
					$("#reciever").attr("readonly",false);
					$("#reciever").attr("placeholder","수신자 아이디를 입력해주세요");					
				}
				$("#idcheck").empty();
			};
			//발송그룹 선택하면 수신자 아이디 잠그기
			$(":checkbox[name='role']").on("click",recievercontrol);
			//전체선택하면 체크박스 다 채우기
			$("#sendall").on("click",function(){
				if ($("#sendall").is(":checked")){
					$(":checkbox").prop("checked",true);
				} else {
					$(":checkbox").prop("checked",false);					
				}
				recievercontrol();
			});
			//사업자 선택하면 체크박스 채우기
			$("#sendcompany").on("click",function(){
				if ($("#sendcompany").is(":checked")){
					$(":checkbox[class='company']").prop("checked",true);
				} else if (!$("#sendall").is(":checked")){
					$(":checkbox[class='company']").prop("checked",false);					
				}
				recievercontrol();
			});
			//빈칸체크
			$("#messageform").on("submit",function(){				
				if ($(":checkbox[name='role']:checked").length==0 && $("#idcheck").attr("data-check")!="true" ){
					alert('수신자가 없습니다!');
					$("#reciever").focus();
					return false;
				} else if ($("#title").val().trim()==""){
					alert('제목을 입력해주세요!');
					$("#title").focus();
					return false;
				} else if ($("#context").val().trim()==""){
					alert('내용을 입력해주세요!');
					$("#context").focus();
					return false;
				}
			});
			
			//아이디 자동완성 ajax
			
			var idcheckTrue = function(){
				$("#idcheck").css("color","green");
				$("#idcheck").html(" 아이디 확인 성공");
				$("#idcheck").attr("data-check","true");
			}
			var idcheckFalse = function(){
				$("#idcheck").css("color","red");
				$("#idcheck").html(" 아이디가 존재하지 않습니다.");
				$("#idcheck").attr("data-check","false");
			}
			
			
			var focus_id=false;
			
			// 마우스 클릭/빼기
			$("#reciever").on("keyup focus",function(){ //아이디 입력창에서 키를 눌렀을 때
				if ($("#reciever").val().trim()!=""){ //만약 입력값이 있다면
					//자동완성
					$.ajax({
						data : { "id" : $("#reciever").val() },
						url : "${pageContext.request.contextPath}/idlist.msg",
						type : "get",
						dataType : "json",
						success : function(json){
							$("#result").attr("class", "dropdown open");
							var ul = $("<ul>").attr("class","dropdown-menu");
							if (json[0]!=null){ //검색결과가 있다면
								json.forEach((obj)=>{
									var id = $("<a>").attr("class","idlist idselect").html(obj.id);
									id = $("<li>").html(id);
									ul.append(id);
								});
								$("#result").html(ul);
								//자동완성된 아이디 클릭하면 값으로 들어가게 하기
								$(".idselect").on("click",function(){
									if (focus_id==true){
										$("#reciever").val($(".idselect").first().text());
										idcheckTrue();
									}
									$("#result").empty();
								});
								$("#result").on("mouseout",function(){
									focus_id=false;
								});
								$("#result").on("mouseover",function(){
									focus_id=true;
								});
								$("#reciever").on("focus",function(){
									focus_id=true;
								});
								$("#reciever").on("focusout",function(){
									if (focus_id==false){
										$("#result").empty();
									}
								})
									
							} else { //검색결과가 없는 경우 비우기
								$("#result").empty();
							}
						},
						error : function(xhr,textStatus,errorThrown){
							$("#idcheck").html(textStatus + " HTTP - "+xhr.status+errorThrown);
						}
					});
				//아이디 확인
				$.ajax({
					data : { "id" : $("#reciever").val() },
					url : "${pageContext.request.contextPath}/idcheck.msg",
					type : "get",
					dataType : "json",
					success : function(json){
						if (json.check){ //정확한 아이디가 맞다면
							idcheckTrue();
						} else {
							idcheckFalse();
						}
					},
					error : function(xhr,textStatus,errorThrown){
						$("#idcheck").html("idcheck error - "+ textStatus + " HTTP - "+xhr.status+errorThrown);
					}
				});
				} else { //입력값이 없다면
					$("#result").empty();
					idcheckFalse();
				}
			});
			
		});
	</script>
	<!-- 메세지 폼 -->
</div>
<%@ include file="/inc/footer.jsp"%>