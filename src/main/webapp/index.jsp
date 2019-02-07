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

<style type="text/css">
	/* 样式   */
	/* form-signin类表单的width */
	.form-signin{
		max-width: 330px;
		padding: 15px;
		margin: 0 auto;
	}
	/* input输入框的下间距 */
	input {
		margin-bottom: 3px;
	}
	#loginIn {
		height:40px;
		width:206px;
	}
</style>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h4>Welcome to my bookstore</h4>
	</div>
	
	<div class="container">
		<form class="form-signin" role="form">
			<h2 class="form-signin-heading">欢迎登陆</h2>
			<input type="text" id="input_name" class="form-control" placeholder="name" required autofocus>
			<input type="password" id="input_password" class="form-control" placeholder="password" required>
			
			<label class="radio-inline">
		      <input type="radio"  value="admin" name="login" checked="checked">管理员
		    </label>
		    <label class="radio-inline">
		      <input type="radio"  value="vip" name="login">会员
		    </label>

			<button id="loginBtn" class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
			<button id="loginIn" class="btn btn-success" type="submit" data-toggle="modal" data-target="#signInModal">注册</button>
		</form>
	</div>
	
	
	
	<!-- 注册模态框（Modal） -->
	<div class="modal fade" id="signInModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						注册会员
					</h4>
				</div>
				<div class="modal-body">
					<input type="text" id="signIn_name" class="form-control" placeholder="name" required autofocus>
					<input type="password" id="signIn_password" class="form-control" placeholder="password" required>
					<input type="text" id="signIn_email" class="form-control" placeholder="email" required>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button id="signInBtn" type="button" class="btn btn-primary">
						确定
					</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>	
	
	<script type="text/javascript">
		$("#loginBtn").click(function(){
			//console.log("loginBtn click");
			var name = $("#input_name").val();
			var password = $("#input_password").val();
			var isAdmin = false;
			if($("input[name='login']:checked").val() == "admin")
			{
				isAdmin = true;
			}
			var paramdata = "name=" + name + "&password=" + password + "&isAdmin=" + isAdmin;
			//console.log(paramdata);
			//console.log(name+" "+password);
			//console.log($("input[name='login']:checked").val());
			
			$.ajax({
				url:"login",
				type:"GET",
				data:paramdata,
				success:function(returnData){
					//console.log("ajax login success");
					console.log(returnData);
					if(returnData.opCode == 100)
					{
						var pageNumber = 1;
						window.location.href = "bookPage";
					}
					else
					{
						alert("用户名或者密码错误，请重新输入!");
						$("#input_name").val("");
						$("#input_password").val("");
					}
				}
			})
			return false;
		})
		
		
		$("#signInBtn").click(function(){
			console.log("signInBtn click");
			
			var name = $("#signIn_name").val();
			var password = $("#signIn_password").val();
			var email = $("#signIn_email").val();
			
			var paramData = "name=" + name + "&password=" + password + "&email=" + email;
			console.log(paramData);
			
			$.ajax({
				url:"signin",
				type:"POST",
				data:paramData,
				success:function(returnData){
					console.log(returnData);
				}
			})
			
			return false;
		})
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<!-- <button type="button" class="btn btn-primary">（首选项）Primary</button> -->
	<!-- <button type="button" class="btn btn-danger">（危险）Danger</button> -->
	<!-- <a href="AdmControllertest">AdmControllertest</a> -->
</body>
</html>