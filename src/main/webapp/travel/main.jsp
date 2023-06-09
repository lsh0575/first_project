<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 날씨 api - fontawesome 아이콘 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<style>
#travelCarousel {
	width: 550px;
	height: 550px;
	margin: 0 auto;
	padding: 10px;
	overflow: hidden;
}

#travelCarousel img {
	width: 100%;
	height: 1000px;
	background-position: center;
	object-fit: cover;
}

.weatherTitle {
	background-color: rgb(101, 178, 255);
	padding: 30px;
	color: #fff;
	height: 220px;
	width: 500px;
}
</style>


<div class="container-fluid body-top">
	<div class="row">
		<div class="col-sm-7">
			<div class="text-center">
				<a href="${pageContext.request.contextPath}/paging.travel"
					title="게시글 리스트로 이동합니다." class="btn btn-lg"> <span
					class="glyphicon glyphicon-list"></span>
				</a> <a href="${pageContext.request.contextPath}/paging.travel"
					title="게시글 리스트로 이동합니다." class="btn btn-danger">글 목록 보기</a>
			</div>
			<div id="travelCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<c:forEach var="dto" items="${main}" varStatus="status">
						<li data-target="#travelCarousel"
							data-slide-to="${status.index-1}"
							<c:if test="${status.first}"> class="active"</c:if>></li>
					</c:forEach>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<c:forEach var="dto" items="${main}" varStatus="status">
						<div class="item <c:if test="${status.first}"> active</c:if>">
							<a class="imgList"
								href="${pageContext.request.contextPath}/detail.travel?tno=${dto.tno}">
								<img src="${pageContext.request.contextPath}/upload/${dto.timages_1}" alt="${dto.ttitle}">
							</a>
							<div class="carousel-caption">
								<h3>${dto.tname}</h3>
								<p>${dto.ttitle}</p>
							</div>
						</div>
					</c:forEach>
				</div>

				<!-- Left and right controls -->
				<a class="left carousel-control" href="#travelCarousel"
					role="button" data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#travelCarousel"
					role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
		<!--			  -->
		<!--			  -->

		<!-- Exchange Rate API 사용 -->
		<div class="col-sm-5">
			<div class="row well">
				<div class="currency-container">
					<p>환율 계산기</p>
					<form class="currency-form">
						<label for="base-currency">기준 통화:</label> 
						<select id="base-currency">
							<option value="USD">미국 달러 (USD)</option>
							<option value="VND">베트남 동 (VND)</option>
							<option value="JPY">일본 엔 (JPY)</option>
							<option value="CNY">중국 위안 (CNY)</option>
							<option value="THB">태국 바트 (THB)</option>
							<option value="KRW">한국 원화 (KRW)</option>
							<option value="AUD">호주 달러 (AUD)</option>
							<option value="HKD">홍콩 달러 (HKD)</option>
							<!-- 다른 통화 옵션 추가 -->
						</select>&nbsp;&nbsp;&nbsp;&nbsp;
						<span class="glyphicon glyphicon-circle-arrow-right">&nbsp;</span>
						<label for="target-currency">변환할 통화:</label>
							<select id="target-currency">
							<option value="USD">미국 달러 (USD)</option>
							<option value="VND">베트남 동 (VND)</option>
							<option value="JPY">일본 엔 (JPY)</option>
							<option value="CNY">중국 위안 (CNY)</option>
							<option value="THB">태국 바트 (THB)</option>
							<option value="KRW">한국 원화 (KRW)</option>
							<option value="AUD">호주 달러 (AUD)</option>
							<option value="HKD">홍콩 달러 (HKD)</option>
							<!-- 다른 통화 옵션 추가 -->
						</select> <br /> <label for="base-amount">기준 통화량:</label> <input
							type="number" id="base-amount">

						<button type="submit">계산하기</button>
					</form>
					<label>계산 결과:</label>
					<div id="result"></div>
				</div>
			</div>

			<!-- OpenWeatherMap API 사용 -->
			<div class="row well">
				<!--	오늘 날짜 출력  -->
				<span class="nowtime"></span>
				<!--	날씨 출력	    -->
				<div class="weatherTitle" style="">
					<div style="float: left;">
						<div class="weather_icon"></div>
					</div>
					<br>

					<div style="float: right; margin: -5px 0px 0px 60px; font-size: 11pt">
						<div class="temp_min"></div>
						<div class="temp_max"></div>
						<div class="humidity"></div>
						<div class="wind"></div>
						<div class="cloud"></div>
					</div>
					<div style="float: right; margin-top: -45px;">
						<div class="current_temp" style="font-size: 50pt"></div>
						<div class="weather_description" style="font-size: 20pt"></div>
						<div class="city" style="font-size: 13pt"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 			 -->
<!-- 			 -->
<!-- 			 -->


<!-- 날씨 출력 script -->
<!-- OpenWeatherMap API -->
<script>
	//오늘 날짜출력
	$(document).ready(function() {

		function convertTime() {
			var now = new Date();

			var month = now.getMonth() + 1;
			var date = now.getDate();

			return month + '월' + date + '일';
		}

		var currentTime = convertTime();
		$('.nowtime').append(currentTime);
	});

	//날씨 api - fontawesome 아이콘

	var weatherIcon = {
		'01' : 'fas fa-sun',
		'02' : 'fas fa-cloud-sun',
		'03' : 'fas fa-cloud',
		'04' : 'fas fa-cloud-meatball',
		'09' : 'fas fa-cloud-sun-rain',
		'10' : 'fas fa-cloud-showers-heavy',
		'11' : 'fas fa-poo-storm',
		'13' : 'far fa-snowflake',
		'50' : 'fas fa-smog'
	};

	// 날씨 api - 서울
	var apiURI = "https://api.openweathermap.org/data/2.5/weather?q=" + 'seoul'
			+ "&appid=" + "b957a41621c2701ec8178c588df27dea";
	$
			.ajax({
				url : apiURI,
				dataType : "json",
				type : "GET",
				async : "false",
				success : function(resp) {

					var $Icon = (resp.weather[0].icon).substr(0, 2);
					var $weather_description = resp.weather[0].main;
					var $Temp = Math.floor(resp.main.temp - 273.15) + 'º';
					var $humidity = '습도&nbsp;&nbsp;&nbsp;&nbsp;'
							+ resp.main.humidity + ' %';
					var $wind = '바람&nbsp;&nbsp;&nbsp;&nbsp;' + resp.wind.speed + ' m/s';
					var $city = '서울';
					var $cloud = '구름&nbsp;&nbsp;&nbsp;&nbsp;' + resp.clouds.all + "%";
					var $temp_min = '최저 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(resp.main.temp_min - 273.15) + 'º';
					var $temp_max = '최고 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(resp.main.temp_max - 273.15) + 'º';

					$('.weather_icon').append(
									'<i class="' + weatherIcon[$Icon] +' fa-5x" style="height : 150px; width : 150px;"></i>');
					$('.weather_description').prepend($weather_description);
					$('.current_temp').prepend($Temp);
					$('.humidity').prepend($humidity);
					$('.wind').prepend($wind);
					$('.city').append($city);
					$('.cloud').append($cloud);
					$('.temp_min').append($temp_min);
					$('.temp_max').append($temp_max);
				}
			})
</script>

<!-- 환율 계산기 script -->
<!-- Exchange Rate API -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready( function() {
		// 폼 제출 시 실행되는 함수
		$(".currency-form").submit( function(event) { event.preventDefault(); // 기본 제출 동작 방지
			// 선택한 통화
			var baseCurrency = $( "#base-currency").val();
			var targetCurrency = $( "#target-currency").val();

			// 선택한 통화량
			var baseAmount = parseFloat($( "#base-amount").val());

			// API 요청 URL
			var apiUrl = "https://v6.exchangerate-api.com/v6/b7894926a68099a5d417b5b1/latest/" + baseCurrency;

			// API 요청
			$.ajax({
					url : apiUrl,
					dataType : "json",
					success : function(data) {
						// 환율 데이터에서 변환율 가져오기
						var conversionRates = data.conversion_rates;
						var exchangeRate = conversionRates[targetCurrency];

						// 계산된 결과
						var convertedAmount = baseAmount * exchangeRate;

						// 결과 출력
						$("#result") .text( convertedAmount .toFixed(2)); },
					error : function() {
						// API 요청 실패 시 오류 메시지 출력
						$("#result") .text( "환율 계산에 실패했습니다."); }
				});
		});
});
</script>