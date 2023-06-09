<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="body-top container">
	<%-- Message List --%>
	<h3 class="text-center">수신된 메세지 목록</h3>
		<form id="form" action="${pageContext.request.contextPath}/delete.msg">
			<div id="result">
				<table class="table table-striped">
					<colgroup>
						<col style="width:5%">
					<col style="width:15%">
					<col style="width:39%">
					<col style="width:17%">
					<col style="width:17%">
					<col style="width:7%">
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><input type="checkbox" id="check_all"/></th>
							<th scope="col">작성자</th>
							<th scope="col">제목</th>
							<th scope="col">수신 시간</th>
							<th scope="col">읽은 시간</th>
							<th scope="col">읽음</th>
						</tr>
					</thead>
					<tbody>
					<%-- 정보 들어올 자리 --%>
					
					</tbody>
				</table>
				<%-- Message List --%>
				<%-- Paging --%>
				<div class="text-center">
					<ul class="pagination" id="page_number">
					</ul>
				</div> 
				<%-- Paging --%>
				<div class="form-group text-right">
					<input type="submit" value="삭제" id="deletebtn" class="btn btn-danger" title="선택한 글을 삭제합니다."/>
					<a href="${pageContext.request.contextPath}/write.msg" title="글을 작성하러 갑니다." class="btn btn-default">작성</a>
				</div>
			</div>
		</form>
	<script>
	$(document).ready(function(){
		////전체선택
		$("#check_all").on("click",function(){
			if ($("#check_all").is(":checked")==true){
				$(":checkbox[name=check]").prop("checked",true);
			} else {
				$(":checkbox[name=check]").prop("checked",false);
			}
		});
	//페이징 눌렸을 때 작동할 함수
		var msgbtnClick = function(){
			$("#result tbody").empty();
			$("#page_number").empty();
			$.ajax({
				url:"${pageContext.request.contextPath}/list_ajax.msg",
				type:"get",
				dataType:"json",
				data:{"pageStartNum":$(this).attr("data-pageStartNum")},
				success:function(json){
					json.list.forEach((i)=>{
						// 메시지 리스트 작성
						var tr = $("<tr>");
						var checkbox = $("<input>").attr("type","checkbox");
							checkbox.attr("class","check");
							checkbox.attr("name","check");
							checkbox.val(i.msg_recieve_no);
							checkbox = $("<td>").append(checkbox);
						var sender =  $("<a>").attr("href","${pageContext.request.contextPath}/user.acc?id="+i.msg_sender);
							sender.append(i.msg_sender);
							sender = $("<td>").append(sender);
						var title = $("<a>").attr("href","${pageContext.request.contextPath}/read.msg?msgno="+i.msg_recieve_no);
							title.append(i.msg_title);
							title = $("<td>").append(title);
						var sendtime = $("<td>").append(i.msg_send_time);
						var rectime = $("<td>").append(i.msg_read_time);
						var read = (i.msg_read==true)?"읽음":"안읽음";
							read = $("<td>").append(read);
						tr.append(checkbox).append(sender).append(title).append(sendtime).append(rectime).append(read);
						$("#result tbody").append(tr);
					});
						//하단 페이지 번호 작성
						//이전 페이지 버튼
					if (json.pagesStart!=1){
						var prev = $("<a>").attr("data-pageStartNum",((json.pagesStart-2)*json.onePageListCount) );
							prev.attr("class","msgbtn btn btn-default");
							prev.attr("title","이전의 페이지들을 보여줍니다.");
							prev.append("이전");
							prev = $("<li>").append(prev);
							$("#page_number").append(prev);
					}
						//일반 페이지 번호
					for (var i=json.pagesStart; i<=json.pagesEnd; i++){
						var page = $("<a>").attr("data-pageStartNum",((i-1)*json.onePageListCount) );
							page.attr("class","msgbtn btn btn-default");
							page.attr("title",i + "페이지로 이동합니다.");
							page.append(i);
							page = $("<li>").append(page);
						if (i==json.currentPages){page.attr("class","active")}
							$("#page_number").append(page);
					}
						//다음 페이지
					if (json.pageCount!=json.pagesEnd){
						var next = $("<a>").attr("data-pageStartNum",(json.pagesEnd*json.onePageListCount) );
							next.attr("class","msgbtn btn btn-default");
							next.attr("title","다음의 페이지들을 보여줍니다.");
							next.append("다음");
							next = $("<li>").append(next);
							$("#page_number").append(next);
					}
					$(".msgbtn").on("click",msgbtnClick);
				},
				error:function(xhr,textStatus,errorThrown){
					$("#result tbody").html(textStatus+"HTTP-"+xhr.status+"/"+errorThrown);
				}
			});
		} //msgbtnClick
		////paging ajax
		msgbtnClick();
		$(".msgbtn").on("click",msgbtnClick);
		
		//빈칸체크
		$("#form").on("submit",function(){
			if ($(":checkbox[name=check]:checked").length==0){
				alert('선택된 회원이 없습니다!');
				return false;
			}
		});
	});
	</script>
</div>
<%@ include file="/inc/footer.jsp" %>
