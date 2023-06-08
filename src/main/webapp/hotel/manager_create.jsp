<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp" %>
<div class="container body-top">
	<form action="${pageContext.request.contextPath}/manager_create.hotel" method="post" id="writeform" enctype="multipart/form-data">
		<div>
		<div class="col-sm-9">
		<div class="form-group">
			<label for="hname">이름</label>
			<input type = "text" id="hname" name="hname" class="form-control" placeholder="상품 이름을 입력하여 주세요."  required/>
		 </div><!-- col-sm-9 -->
		</div>
		<div class="col-sm-3">
		<div class="form-group">
			<label for="hnation">국가</label>
			<input type="text" id="hnation" name="hnation" class="form-control" required />
		</div> 
		</div> 
		</div> <!-- 첫줄 -->
		<div>
		<div class="col-sm-3">
		<div class="form-group ">
			<select id="htype" name="htype" class="form-control" required >
			<option value="default">등급설정</option>
			<option value="호텔">호텔</option>
			<option value="게스트하우스">게스트하우스</option>
			<option value="리조트">리조트</option>						
			</select>
		</div>
		</div>
		<div class="col-sm-3">
			<input type="text" id="hprice" name="hprice" class="form-control" placeholder="가격입력"  required />
		</div>
		<div class="form-group col-sm-2">
			<select id="hgrade" name="hgrade" class="form-control">
			<option value="default">등급설정</option> 
			<option value="5">★★★★★</option>
			<option value="4">★★★★</option>
			<option value="3">★★★</option>			
			<option value="2">★★</option>			
			<option value="1">★</option>			
			</select>
		</div>
		<div class="form-group col-sm-2">
			<select id="hbed" name="hbed" class="form-control" required >
			<option value="default">침대구성</option> 
			<option value="싱글배드">싱글배드</option>
			<option value="더블배드">더블배드</option>
			<option value="트윈배드">트윈배드</option>				
			</select>
		</div>
		<div class="form-group col-sm-2">
			<input type="text" id="hcnt" name="hcnt" class="form-control" placeholder="숙박인원" required />
		</div>
		</div><!-- 두번째줄 -->
		
		<br/>
		
		<div>
		<div class="col-sm-2">
		<input type="date" id="checkin" name="checkin" class="form control"   required/>
		</div>
		<div class="col-sm-2">
		<input type="date" id="checkout" name="checkout" class="form control"  required/>
		</div>
		<div class="col-sm-8">
		<p></p>
		</div>
		</div><!-- 세번째줄 -->
		
		<br/>
		
		<div class="col-sm-12">
		<div class="col-sm-6" style="margin-top: 20px; margin-bottom: 10px" >
		<textarea id="hcontent" name="hcontent" rows="10" cols="70"> </textarea>
		</div>
		<div class="col-sm-6" style="margin-top: 20px; margin-bottom: 10px" >
				<input type="file" id="img1" name="img1" class="btn btn-primary" />
				<input type="file" id="img2" name="img2" class="btn btn-primary" />
				<input type="file" id="img3" name="img3" class="btn btn-primary" />	
				<input type="file" id="img4" name="img4" class="btn btn-primary" />
		</div>
		</div> <!-- 네번째줄 -->

		<br/>
		<div class="form-group col-sm-12">
			<label for="smoke">흡연여부</label>
			<input type="checkbox" id="smoke" name="smoke" />
			<label for="ref">냉장고</label>
			<input type="checkbox" id="ref" name="ref" />
			<label for="wifi">WI-FI</label>
			<input type="checkbox" id="smoke" name="smoke" />
			<label for="tv">TV</label>
			<input type="checkbox" id="tv" name="tv" />
			<label for="tub">욕조</label>
			<input type="checkbox" id="tub" name="tub" />
			<label for="airc">에어컨</label>
			<input type="checkbox" id="airc" name="airc" />
		</div>

		 <div class="form-group text-right">
		 	<input type="submit" value="입력" class="btn btn-info" style="color:white; background-color:#f4511e" >
			<input type="reset" value="취소" class="btn btn-default" >
		 	<a href = "${pageContext.request.contextPath}/manager_list.hotel" class ="btn btn-default" title = "목록으로 돌아갑니다." > 목록보기 </a>
		 </div>
	</form>
</div>
	<script>
		$(function(){
			$("#writeform").submit(function(){
				if($("#hname").val() == ""){ alert("빈칸입니다. 확인해주세요"); $("hname").focus(); return false; }
				if($("#hnation").val() == ""){ alert("빈칸입니다. 확인해주세요"); $("#hnation").focus(); return false; }
				if($("#hcnt").val() == ""){ alert("빈칸입니다. 확인해주세요"); $("#hcnt").focus(); return false; }
			});
		});
	</script>
</body>
<%@ include file="/inc/footer.jsp" %>