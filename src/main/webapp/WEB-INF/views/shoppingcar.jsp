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
		  <div class="col-md-12">welcome back to myBookstore: ${name}</div>
		</div>
		
		<div class="row">
		  <table id="shoppingItems_table" class="table table-hover">
				<thead>
					<tr>
						<th>编号</th>
						<th>书名</th>
						<th>作者</th>
						<th>单价</th>
						<th>数量</th>
						<th>总价</th>
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
				<div class="col-lg-6" id="totalPriceForCar">
					
				</div>
				<!--分页条信息  -->
				<div class="col-lg-6" id="pagenav">
					
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			showShoppingCar();
		})
		
		function showShoppingCar()
		{
			$.ajax({
				url:"getShoppingCarItemByVipId",
				type:"GET",
				data:"id=" + '${id}',
				success:function(returnData){
					console.log(returnData);
					build_shoppingItemTable(returnData);
				}
			})
		}

		function build_shoppingItemTable(result)
		{
			$("#shoppingItems_table tbody").empty();
			var totalPriceForCar = 0;
			var items = result.list.shoppingitems;
			$.each(items, function(index, item){
				var indexId = $("<td></td>").append(index+1);
				var bookNameId = $("<td></td>").append(item.bookName);
				var bookAuthorId = $("<td></td>").append(item.bookAuthor);
				var priceId = $("<td></td>").append(item.price);
				var numberId = $("<td></td>").append(item.number);
				var totalPriceForItemTd = $("<td></td>").append(item.number * item.price);
				totalPriceForCar += item.number * item.price;
				
				var addBookBtn = $("<button></button>").addClass("btn btn-primary btn-sm").append("+"); 
				var descBookBtn = $("<button></button>").addClass("btn btn-primary btn-sm").append("-"); 
				
				var buttonTd = $("<td></td>").append(addBookBtn).append("  ").append(descBookBtn);
				
				var tr = $("<tr></tr>").append(indexId)
				.append(bookNameId)
				.append(bookAuthorId)
				.append(priceId)
				.append(numberId)
				.append(totalPriceForItemTd)
				.append(buttonTd)
				.appendTo("#shoppingItems_table tbody");
				
				var id_index = index + 1;
				tr.attr("id", "item" + id_index);
				
				addBookBtn.click(function(){
					var idindex = $("#item" + id_index).attr("id");;
					alert("item number: " + idindex + " click");
				})
			})
			
			$("#totalPriceForCar").append("总价：" + totalPriceForCar);
		}
		
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>