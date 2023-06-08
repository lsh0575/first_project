<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
	.boximg img{
	    width:100%;
	}
	.imgbox img {
    width: 100%;
    height: 150px;
    overflow: hidden;
	}
	.col-sm-4.modalimg {
	   margin-bottom: 5%;
	}
	.topgap{
		margin-top: 20px;
	}
	.hotel_title {
		font-size: 20px;
	}
	
	.col-sm-4.sidesize {
    width: 250px;
    height: 120px;
	}
	.gradesize h3 {
    margin-top: 10px;
    margin-bottom: 10px;
	}
	
	.row.modalimg.heightgoods {
    margin-top: 20px;
    margin-bottom: 50px;
	}
	
.hoteltext {
    text-align: center;
    margin-top: 300px;
    font-size: 30px;
    color: gray;
}
</style>
</head>
<body>	
	<div class="topgap  container">	
		<div class="panel panel-info ">
			<div class="col-sm-4 sidesize">
			<h3> 상품 등급 </h3>
			<div class="form-group ">
			<select id="hgrade" name="hgrade" class="form-control ajaxoption" >
			<option value="default">등급설정</option> 
			<option value="5">★★★★★</option>
			<option value="4">★★★★</option>
			<option value="3">★★★</option>			
			<option value="2">★★</option>			
			<option value="1">★</option>			
			</select>
		</div>
			
			<hr/>
			<h3> 상품 가격 </h3>
			<div class="form-group">
			<label for="vol">가격 범위를 설정하세요.</label>
	  		<input type="range" id="hprice" name="hprice" min="10000" max="500000" step="1000" value="500000" class="ajaxoption"/>
	  		<hr/>
	  		<div> <span class="result">500000</span>원 이하</div>
	  		<script>
	  			$(function(){
	  				$("#hprice").on("change" , function(){
	  					$(".result").html( $(this).val() );
	  				});
	  			});
	  		</script>
			</div>
			<hr/>
			<h3> 침대 구성 </h3>
			<div class="form-group">
			<select id="hbed" name="hbed" class="form-control ajaxoption">
			<option value="default">침대구성</option> 
			<option value="single">싱글배드</option>
			<option value="double">더블배드</option>
			<option value="twin">트윈배드</option>					
			</select>
			</div>
			<hr/>
			
			
			
			
			<h4> 편의 시설</h4>
			<div class="form-group">
			<label for="smoke">흡연여부</label>
			<input type="checkbox" id="smoke" name="smoke" />
			<label for="ref">냉장고</label>
			<input type="checkbox" id="ref" name="ref" />
			<label for="wifi">WI-FI</label>
			<input type="checkbox" id="smoke" name="smoke" />
			</div>
			<div class="form-group">
			<label for="tv">TV</label>
			<input type="checkbox" id="tv" name="tv" />
			<label for="tub">욕조</label>
			<input type="checkbox" id="tub" name="tub" />
			<label for="airc">에어컨</label>
			<input type="checkbox" id="airc" name="airc" />
			</div>
			
			<script>
					$(function(){
						$(".ajaxoption").on("change",function(){
							$.ajax({
								data:{"grade":$("#hgrade").val(),
									  "price":$("#hprice").val(),
									  "bed":$("#hbed").val()} ,
								url:"${pageContext.request.contextPath}/Ajax_list.hotel",
								type:"get",
								dataType:"json", 
									success:function(json){
										console.log(json);

										$("#ajaxresult").html($("<p>").attr("class","hoteltext").html("조건에 맞는 상품이 존재하지 않습니다."));
										for (var i = 0; i < json.length; i++){
											/* 	<div class="col-sm-6 imgbox" >
												<img src="${pageContext.request.contextPath}/hotel/upload${dto.himg.img1}" alt="${dto.hprod.hname}" data-toggle="modal" data-target="#myModal${status.count}">
												</div>  이미지박스        */		
											var mainimg = $("<img>").attr("src","${pageContext.request.contextPath}/hotel/upload/"+json[i].himg.img1)//혹시 모르니 이미지앞 / 제거버전 확인
															.attr("alt",json[i].hprod.hname).attr("data-toggle","modal").attr("data-target","#myModal"+i);																									
											var divimgbox = $("<div>").attr("class","col-sm-6 imgbox");
												divimgbox.append(mainimg);
											/* 	<div class="col-sm-6" >
									   		<p class="hotel_title">${dto.hprod.hname}</p>								
											<p>체크인 : ${dto.hprod.checkin}</p>
											<p>체크아웃 : ${dto.hprod.checkout}</p>
											<p>소개글 : ${dto.hprod.hcontent}</p>
											</div> */
											var hotel_title = $("<p>").append(json[i].hprod.hname);
												hotel_title.attr("class","hotel_title");
											var checkin = $("<p>").append("체크인 : "+json[i].hprod.checkin);
											var checkout = $("<p>").append("체크아웃 : " + json[i].hprod.checkout);
											var content = $("<p>").append("소개글 : " + json[i].hprod.hcontent);			
											var divcontext = $("<div>").attr("class","col-sm-6");
												divcontext.append(hotel_title).append(checkin).append(checkout).append(content);		
												
											/*  <div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">${dto.hprod.hname}</h4>
												</div>	 */										
											var button = $("<button>").attr("type","button").attr("class","close").attr("data-dismiss","modal");
												button.append("&times;");
											var h4title = $("<h4>").append(json[i].hprod.hname);
												h4title.attr("class","modal-title");
											var divmodalheader = $("<div>").attr("class","modal-header");
												divmodalheader.append(button).append(h4title);
												
	/* 										<div class="modal-body modalimg">
											<p>지역 : ${dto.hprod.hnation}</p>								
											<p>유형 : ${dto.hprod.htype}</p>
											<p>가격 : ${dto.hprod.hprice}원</p>
											<p class="boximg"><img src="${pageContext.request.contextPath}/hotel/upload/${dto.himg.img1}" alt="${dto.hprod.hname}"></p>
											</div> */
											var nation = $("<p>").append("지역 : " + json[i].hprod.hnation);
											var type = $("<p>").append("유형 : " + json[i].hprod.htype);
											var price = $("<p>").append("가격 : " + json[i].hprod.hprice);
											var pimg = $("<img>").attr("src","${pageContext.request.contextPath}/hotel/upload/"+json[i].himg.img1);
												pimg.attr("alt", json[i].hprod.hname);
												pimg = $("<p>").append(pimg);
												pimg.attr("class","boximg");
											var divmodalbody = $("<div>").attr("class","modal-body modalimg");
												divmodalbody.append(nation).append(type).append(price).append(pimg);
												
									/* 		<div class="modal-footer">
												<a href = "${pageContext.request.contextPath}/user_detail.hotel?hno=${dto.hprod.hno}" class ="btn btn-default" title = "예약하러가기" > 예약하러가기 </a>
												<button type="button" class="btn btn-default"
														data-dismiss="modal">Close</button>
											</div> <!-- class="modal-footer" --> */
											var ahref = $("<a>").append("예약하러가기");
												ahref.attr("href" , "${pageContext.request.contextPath}/user_detail.hotel?hno="+json[i].hprod.hno);
												ahref.attr("class" , "btn btn-default");
												ahref.attr("title" , "예약하러가기");
											var closebutton = $("<button>").append("Close");
												closebutton.attr("type" , "button").attr("class","btn btn-default").attr("data-dismiss" , "modal");
											var divmodalfooter = $("<div>").attr("class","modal-footer");
												divmodalfooter.append(ahref).append(closebutton);
											
												
												/* <div class="modal-content">
												</div>   모달헤더 / 모달바디 / 모달풋터를 감쌈 */
											var divmodalcontent= $("<div>").attr("class","modal-content");
												divmodalcontent.append(divmodalheader).append(divmodalbody).append(divmodalfooter);
											
												/* <div class="modal-dialog">
												</div> */
											var divmodaldialog = $("<div>").attr("class","modal-dialog");
												divmodaldialog.append(divmodalcontent);
											
												/* <div id="myModal${status.count}" class="modal fade" role="dialog">
												</div> */
											var divmodalfade = $("<div>").attr("id" , "myModal"+i).attr("class","modal fade").attr("role","dialog");
												divmodalfade.append(divmodaldialog);
																
												//모든걸 감싼친구
											var divmodalgoods = $("<div>").attr("class","row modalimg heightgoods");
												divmodalgoods.append(divimgbox).append(divcontext).append(divmodalfade);
											
												if (i==0){
													$("#ajaxresult").html(divmodalgoods);
												} else {
													$("#ajaxresult").append(divmodalgoods);
												}
										}
									}, error:function(xhr,textStatus,errorThrown){
									$("#ajaxresult").html(textStatus + "(http-" + xhr.status + "/" + errorThrown + ")");
								}
							});
						});
					});
			</script>

			<a href = "${pageContext.request.contextPath}/user_reservelist.hotel" class ="btn btn-info" title = "예약목록확인" > 예약현황보기 </a>
			</div> <!-- end side menu -->
							<div class="col-sm-8 " id="ajaxresult"> 
								<p class="hoteltext">조건을 선택해주세요.</p>			
							</div><!--end class="col-sm-8"-->
							<div>
								<a href = "${pageContext.request.contextPath}/user_hotellist.hotel" class ="btn btn-info" title = "모든상품 보기" > 더많은 상품 보기! </a>
							</div>
		</div> <!-- class="panel-heading" -->
		</div> <!-- class="topgap  container" -->
</body>
</html>