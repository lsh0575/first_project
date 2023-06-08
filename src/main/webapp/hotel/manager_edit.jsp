<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@include file="/inc/header.jsp" %>
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

	<div class="container body-top ">
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
							<img src="${pageContext.request.contextPath}/upload/hotel/${imgs}" alt="${dto.hprod.hcontent}"
								class="hotelimg">
							<div class="carousel-caption">
								<h3>${dto.hprod.hname}</h3>
								<p>${dto.hprod.hcontent}</p>
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
				action="${pageContext.request.contextPath}/manager_edit.hotel?hno=${dto.hprod.hno}"
				method="post" id="writeform" enctype="multipart/form-data">
				<div>
					<div class="col-sm-9">
						<div class="form-group">
							<input type="text" id="hname" name="hname" class="form-control"
								value="${dto.hprod.hname}"/>
						</div>
						<!-- col-sm-9 -->
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<input type="text" id="hnation" name="hnation"
								class="form-control" value="${dto.hprod.hnation}" />
						</div>
					</div>
				</div>
				<!-- 첫줄 -->
				<div>
					<div class="col-sm-3">
						<select id="htype" name="htype" class="form-control">
						<option value="default">${dto.hprod.htype}</option>
						<option value="호텔">호텔</option>
						<option value="게스트하우스">게스트하우스</option>
						<option value="리조트">리조트</option>						
						</select>
					</div>
					<div class="col-sm-3">
						<input type="text" id="hprice" name="hprice" class="form-control"
							value="${dto.hprod.hprice}"  />
					</div>
					<div class="form-group col-sm-2">
						<select id="hgrade" name="hgrade" class="form-control">
						<option value="default">${dto.hprod.hgrade}</option> 
						<option value="5">★★★★★</option>
						<option value="4">★★★★</option>
						<option value="3">★★★</option>			
						<option value="2">★★</option>			
						<option value="1">★</option>			
						</select>
					</div>
					<div class="form-group col-sm-2">
						<select id="hbed" name="hbed" class="form-control">
						<option value="default">${dto.hprod.hbed}</option> 
						<option value="싱글배드">싱글배드</option>
						<option value="더블배드">더블배드</option>
						<option value="트윈배드">트윈배드</option>				
						</select>
					</div>
					<div class="form-group col-sm-2">
						<input type="text" id="hcnt" name="hcnt" class="form-control"
							value="${dto.hprod.hcnt}"  />
					</div>
				</div>
				<!-- 두번째줄 -->

				<br />

				<div>
					<div class="col-sm-2">
					<input type="date" id="checkin" name="checkin" class="form control" value="${dto.hprod.checkin}" />
					</div>
					<div class="col-sm-2">
					<input type="date" id="checkout" name="checkout" class="form control" value="${dto.hprod.checkout}" />
					</div>
					<div class="col-sm-8">
						<p></p>
					</div>
				</div>
				<!-- 세번째줄 -->

				<br />

				<div class="col-sm-12">
					<div class="col-sm-6 text-center" style=" margin-top: 20px; margin-bottom: 10px">
						<textarea id="hcontent" name="hcontent" rows="10" cols="70"
							>${dto.hprod.hcontent}</textarea>
					</div>
					<div class="col-sm-6" style=" margin-top: 20px; margin-bottom: 10px">
						<input type="file" id="img1" name="img1" class="btn btn-primary" />
						<input type="file" id="img2" name="img2" class="btn btn-primary" />
						<input type="file" id="img3" name="img3" class="btn btn-primary" />
						<input type="file" id="img4" name="img4" class="btn btn-primary" />
					</div>
				</div>
				<!-- 네번째줄 -->

				<div class="form-group col-sm-12">
			<label for="smoke">흡연여부</label>
			<input type="checkbox" id="smoke" name="smoke"  <c:if test="${dto.hoption.smoke}"> checked  </c:if> />
			<label for="ref">냉장고</label>
			<input type="checkbox" id="ref" name="ref" <c:if test="${dto.hoption.ref}"> checked  </c:if> />
			<label for="wifi">WI-FI</label>
			<input type="checkbox" id="wifi" name="wifi" <c:if test="${dto.hoption.wifi}"> checked  </c:if> />
			<label for="tv">TV</label>
			<input type="checkbox" id="tv" name="tv" <c:if test="${dto.hoption.tv}"> checked  </c:if> />
			<label for="tub">욕조</label>
			<input type="checkbox" id="tub" name="tub" <c:if test="${dto.hoption.tub}"> checked  </c:if> />
			<label for="airc">에어컨</label>
			<input type="checkbox" id="airc" name="airc" <c:if test="${dto.hoption.airc}"> checked  </c:if> />
			<label for="wifi">세탁기</label>
			<input type="checkbox" id="wash" name="wash" <c:if test="${dto.hoption.wash}"> checked  </c:if> />
				</div>
			<div class="col-sm-12 text-right">
				<input type="submit" class="btn btn-danger" title="수정하기" value="수정하기" />
					<a href="${pageContext.request.contextPath}/manager_delete.hotel?hno=${dto.hprod.hno}" class="btn btn-danger" title="삭제하기">삭제하기</a>
					 <a href="${pageContext.request.contextPath}/manager_list.hotel" class="btn btn-info" title="목록보기 링크입니다.">목록보기</a>
			</div>

			</form>
		</div>
	</div>
<%@include file="/inc/footer.jsp" %>