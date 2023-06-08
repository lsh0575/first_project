<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
	<div class="container panel panel-info">
		<h3></h3>
		<label for="vol">Volume (between 0 and 50):</label>
  		<input type="range" id="vol" name="vol" min="0" max="50">
  		<hr/>
  		<div  class="result"></div>
  		<script>
  			$(function(){
  				$("#vol").on("change" , function(){
  					$(".result").html( $(this).val() );
  				});
  			});
  		</script>
	</div>
</body>
</html>