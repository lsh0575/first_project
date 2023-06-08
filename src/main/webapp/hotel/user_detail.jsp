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
.hotelcarousel{
	height: 350px;
	overflow:hidden;
	margin-bottom: 50px;
}
.hotelimg img{
	width: 100%;
}
.hotelimg.item{
	align-content: center;
}
</style>
</head>
<body>
	<div class="container panel panel-info ">
		<br>
		<div class="hotelmain">
			<div class="col-sm-3"></div>
			<div id="myCarousel" class="carousel slide hotelcarousel col-sm-6"
				data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators ">
					<c:forEach var="lists" items="${imglist}" varStatus="status">
						<li data-target="#myCarousel" data-slide-to="${status.index}"
							<c:if test="${status.first}"> class="active" </c:if>></li>
					</c:forEach>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<c:forEach var="imgs" items="${imglist}" varStatus="status">
						<div
							class="hotelimg item <c:if test="${status.first}"> active </c:if> ">
							<img src="hotel/upload/${imgs}" alt="${dto.hprod.hcontent}"
								class="hotelimg">
							<div class="carousel-caption">
							</div>
						</div>
					</c:forEach>
				</div>
				<!-- Wrapper for slides -->

				<!-- Left and right controls -->
				<a class="left carousel-control" href="#myCarousel" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
			<div class="col-sm-3"></div>
		</div>
		<div class="container">
			<form
				action="${pageContext.request.contextPath}/user_reserve.hotel?hno=${dto.hprod.hno}"
				method="post" id="writeform">
				<div>
					<div class="col-sm-9">
						<div class="form-group">
							<input type="text" id="hname" name="hname" class="form-control"
								value="${dto.hprod.hname}" readonly />
						</div>
						<!-- col-sm-9 -->
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<input type="text" id="hnation" name="hnation"
								class="form-control" value="${dto.hprod.hnation}" readonly />
						</div>
					</div>
				</div>
				<!-- 첫줄 -->
				<div>
					<div class="col-sm-3">
						<div class="form-group ">
							<input type="text" id="htype" name="htype" class="form-control"
								value="${dto.hprod.htype}" readonly />
						</div>
					</div>
					<div class="col-sm-3">
						<input type="text" id="hprice" name="hprice" class="form-control"
							value="${dto.hprod.hprice}" readonly />
					</div>
					<div class="form-group col-sm-2">
						<input type="text" id="hgrade" name="hgrade" class="form-control"
							value="${dto.hprod.hgrade}" readonly />
					</div>
					<div class="form-group col-sm-2">
						<input type="text" id="hbed" name="hbed" class="form-control"
							value="${dto.hprod.hbed}" readonly />
					</div>
					<div class="form-group col-sm-2">
						<input type="text" id="hcnt" name="hcnt" class="form-control"
							value="${dto.hprod.hcnt}" readonly />
					</div>
				</div>
				<!-- 두번째줄 -->
				<div>
					<div class="col-sm-2">
						<input type="text" id="checkin" name="checkin"
							class="form-control" value="${dto.hprod.checkin}" readonly />
					</div>
					<div class="col-sm-2">
						<input type="text" id="checkout" name="checkout"
							class="form-control" value="${dto.hprod.checkout}" readonly />
					</div>
					<div class="col-sm-8">
						<p></p>
					</div>
				</div>
				<!-- 세번째줄 -->
				<div class="col-sm-12">
					<div class="col-sm-6 text-center" style=" margin-top: 20px; margin-bottom: 10px">
						<textarea id="hcontent" name="hcontent" rows="10" cols="70"
							readonly>${dto.hprod.hcontent}</textarea>
					</div>
					<div class="col-sm-6" style=" margin-top: 20px; margin-bottom: 10px">
									<p>흡연여부:${dto.hoption.smoke}</p>
									<p>냉장고:${dto.hoption.ref}</p>
									<p>와이파이:${dto.hoption.wifi}</p>
									<p>티비:${dto.hoption.tv}</p>
									<p>욕조:${dto.hoption.tub}</p>
									<p>에어컨:${dto.hoption.airc}</p>
									<p>세탁기:${dto.hoption.wash}</p>
					</div>
				</div>
				<!-- 네번째줄 -->
				<div class="col-sm-12">
				<label for="id">아이디를 입력해주세요</label>
				<input type="text" id="id" name="id" >
				</div>
			<div class="col-sm-12 text-right">
				<input type="submit" value="예약하기" class="btn btn-info" style="color:white; background-color:#f4511e" >
				<a href="${pageContext.request.contextPath}/user_view.hotel" class="btn btn-info" title="목록보기 링크입니다.">목록보기</a>
			</div>
			</form>
		</div>
	</div>
</body> 
</html>