<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
 <!--引入jQuery  -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

<!-- 引入bootstrap --> 
<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- <link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"> -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<!-- <script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> -->
<meta charset="utf-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("historyItems", pageContext.getAttribute("historyItems"));
%>
</head>
<body>
	
	<h4>History Page</h4>
	<h4>${abc }</h4>
	
	<script type="text/javascript">
	
	var historyItems = '${historyItems}';
	console.log(historyItems);
	
	$(function(){
		$.ajax({
			url:"checkHistoryItems",
			type:"GET",
			success:function(resultData){
				console.log(resultData);
			}
		})	
	})
	
	</script>
</body>
</html>