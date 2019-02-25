<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<c:if test="${sessionScope.isLogin == 'false' || sessionScope.isLogin == ''}">
				尚未登陆
			</c:if>
			
			<c:if test="${sessionScope.isLogin == 'true'}">
				welcome back to myBookstore: ${name}
			</c:if>
		</div>
		<div class="row">
			<button class="btn btn-success" id="FirstPage">首页</button>
		</div>
		<div class="row">
		  <table id="shoppingItems_table" class="table table-hover">
				<thead>
					<tr>
						<th><input type="checkbox" id="allShoppingItem"></th>
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
				<div class="col-lg-6" id="totalPriceForCar">
					
				</div>
				<div class="col-lg-2" id="purchase">
					<button id="purchaseBtn" class="btn btn-primary btn-sm" >支付</button>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			console.log("showShoppingCar");
			showShoppingCar();
		})
		
		function showShoppingCar()
		{
			var isLogin = '${sessionScope.isLogin}';
			console.log(isLogin);
			if(('${sessionScope.isLogin}' == '') || ('${sessionScope.isLogin}' == 'false'))
			{
				var param = "id=0";
			}
			else
			{
				var param = "id=" + '${id}';	
			}
			console.log("getShoppingCarItemByVipId param: " + param);
			$.ajax({
				url:"getShoppingCarItemByVipId",
				type:"GET",
				data:param,
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
			console.log(items);
			$.each(items, function(index, item){
				if((item.number > 0) && (item.status == false))
				{
					console.log("购物车：itemId " + item.itemId + " status: " + item.status);
					var bookId = item.bookId;console.log(bookId);
					
					var checkBoxId = $("<td><input type='checkbox' class='shoppingItem'></td>");
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
					
					var tr = $("<tr></tr>").append(checkBoxId)
					.append(indexId)
					.append(bookNameId)
					.append(bookAuthorId)
					.append(priceId)
					.append(numberId)
					.append(totalPriceForItemTd)
					.append(buttonTd)
					.appendTo("#shoppingItems_table tbody");
					
					var id_index = index + 1;
					tr.attr("id", "item" + id_index);
					tr.attr("itemId", item.itemId);
					addBookBtn.click(function(){
						var idindex = $("#item" + id_index).attr("id");
						var book_name = item.bookName;
						changeItemNumber(true, "#item" + id_index, item.itemId);
					})
					
					descBookBtn.click(function(){
						var idindex = $("#item" + id_index).attr("id");
						changeItemNumber(false, "#item" + id_index, item.itemId)
					})
				}
			})
			
			var totalPriceText = $("<td></td>").append("总价:");
			var totalPriceNullSpace = $("<td></td>").append("  ");
			var totalPriceNumber = $("<td></td>").append(totalPriceForCar);
			$("#totalPriceForCar").append(totalPriceText)
			.append(totalPriceNullSpace)
			.append(totalPriceNumber);
		}
		
		$("#allShoppingItem").click(function(){
			//alert($(this).prop("checked"));
			$(".shoppingItem").prop("checked" ,$(this).prop("checked"));
		})
		
		$(document).on("click", ".shoppingItem", function(){
			//alert($(this).prop("checked"));
			if($(".shoppingItem:checked").length == $(".shoppingItem").length)
			{
				$("#allShoppingItem").prop("checked", $(this).prop("checked"));
			}
			else
			{
				$("#allShoppingItem").prop("checked", false);
			}
		});
		
		$("#FirstPage").click(function(){
			window.location.href = "bookPage";
		})
		
		$("#purchaseBtn").click(function(){
			var vipId = '${id}';
			console.log(vipId);
			var param;
			if(vipId == '')
			{
				param = "vipId=0";
			}
			else
			{
				param = "vipId=" + '${id}';
			}
			
			var paramdata = '';
			$.each($(".shoppingItem:checked"), function(){
				
				paramdata += $(this).parents("tr").attr("itemid") + "-";
				
			});
			paramdata = paramdata.substring(0, paramdata.length-1);
			
			//console.log(paramdata);
			param += "&items=" + paramdata;
			console.log(param);
			$.ajax({
				url:"purchase",
				type:"GET",
				data:param,
				success:function(result){
					if(result.opCode == 100)
					{
						alert("支付成功!!");	
						window.location.href = "ShoppingCar";
					}
					else if(result.opCode == 300)
					{
						alert("没有登录，需要跳转");
						console.log("meiyou denglu");
						var url = result.list.url;
						console.log(url);
						window.location.href = url;
					}
					else
					{
						alert("支付失败！！");
					}
					
				}
			}) 
			
		})
		
		function changeItemNumber(isAdd, id, itemId)
		{
			if(isAdd == true)
			{
				var cnt = parseInt($(id).find("td").eq(5).text()) + 1;
			}
			else
			{
				var cnt = parseInt($(id).find("td").eq(5).text()) - 1;
			}
			$(id).find("td").eq(5).text(cnt);
			$(id).find("td").eq(6).text(cnt * ($(id).find("td").eq(4).text()));
			
			var totalCnt = 0;
			if(isAdd == true)
			{
				totalCnt = parseInt($("#totalPriceForCar").find("td").eq(2).text()) + parseInt($(id).find("td").eq(4).text());	
			}
			else
			{
				totalCnt = parseInt($("#totalPriceForCar").find("td").eq(2).text()) - parseInt($(id).find("td").eq(4).text());
			}
			
			$("#totalPriceForCar").find("td").eq(2).text(totalCnt);
			
			
			paramdata = "ItemId=" + itemId;
			
			console.log($(id).attr("itemId"));
			$.ajax({
				url:"updateShoppingCarByItemId",
				type:"GET",
				data:paramdata + "&number=" + cnt,
				success:function(){
					console.log("add book success");
				}
			}) 
			
			if(cnt == 0)
			{
				window.location.href = "ShoppingCar";
			}
		}
		
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>