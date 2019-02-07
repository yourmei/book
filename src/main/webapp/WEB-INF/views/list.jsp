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
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<div class="container">
		<div class="row">
		  <div class="col-md-12">welcome back to myBookstore </div>
		</div>
		<div class="row">
		  <div class="col-md-12"> 
		  	<button id="checkCar" class="btn btn-success">查看购物车</button>
		  </div>
		</div>
		<div class="row">
		  <table id="books_table" class="table table-hover">
				<thead>
					<tr>
						<th>编号</th>
						<th>书名</th>
						<th>作者</th>
						<th>出版日期</th>
						<th>价格</th>
						<th>库存</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>					
		   </table>
		</div>
		<div class="row">
			<!--页面信息  -->
			<div class="row">
				<!--分页文字信息  -->
				<div class="col-lg-6" id="pageinfo">
				</div>
				<!--分页条信息  -->
				<div class="col-lg-6" id="pagenav">
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	<script type="text/javascript">
		$(function(){
			listBookPage(1);
		})
	
		function listBookPage(pageNumber){
			$.ajax({
				url:"listBook",
				type:"GET",
				data:"pn="+pageNumber,
				success:function(returnPageInfo){
					console.log(returnPageInfo);
					build_page_table(returnPageInfo);
					build_page_info(returnPageInfo);
					buitd_page_nav(returnPageInfo);
				}
			})  
		}
		
		function build_page_table(result)
		{
			$("#books_table tbody").empty();
			var books = result.list.book.list;
			$.each(books, function(index, item){
				var bookIdTd = $("<td></td>").append(item.bookId);
				var bookNameTd = $("<td></td>").append(item.bookName);
				var bookAuthorTd = $("<td></td>").append(item.bookAuthor);
				var bookDateTd = $("<td></td>").append(item.date);
				var bookPriceTd = $("<td></td>").append(item.price);
				var bookStockTd = $("<td></td>").append(item.stock);
				
				var addCarBtn = $("<button></button>").addClass("btn btn-primary btn-sm").append("加入购物车");
				var buttonTd = $("<td></td>").append(addCarBtn);
				$("<tr></tr>").append(bookIdTd)
				.append(bookNameTd)
				.append(bookAuthorTd)
				.append(bookDateTd)
				.append(bookPriceTd)
				.append(bookStockTd)
				.append(buttonTd)
				.appendTo("#books_table tbody");
			})
		}
		
		function build_page_info(result)
		{
			$("#pageinfo").empty();
			$("#pageinfo").append("当前第 " + result.list.book.pageNum +
					" 页  总共 " + result.list.book.pages +
					" 页 总共 " + result.list.book.total + " 条记录");
		}
		
		function buitd_page_nav(result)
		{
			$("#pagenav").empty();
			var ul = $("<ul></ul>").addClass("pagination");

			var firstPage = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
			firstPage.click(function(){
				listBookPage(1);
			});
			
			var lastPage = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
			lastPage.click(function(){
				console.log(result.list.book.pages);
				listBookPage(result.list.book.pages);
			})
			
			var prevPge = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href", "#"));
			if(result.list.book.prePage != 0)
			{
				prevPge.click(function(){
					listBookPage(result.list.book.prePage);
				})
			}
			
			var nextPge = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href", "#"));
			if(result.list.book.nextPage != 0)
			{
				nextPge.click(function(){
					listBookPage(result.list.book.nextPage);
				})
			}
			
			ul.append(firstPage);
			ul.append(prevPge);
			$.each(result.list.book.navigatepageNums, function(index, item){
				var pageli = $("<li></li>").append($("<a></a>").append(item).attr("href", "#"));
				pageli.click(function(){
					listBookPage(item);
				})
				ul.append(pageli);
			})
			ul.append(nextPge);
			ul.append(lastPage).appendTo("#pagenav"); 
		}
	</script>
</body>
</html>