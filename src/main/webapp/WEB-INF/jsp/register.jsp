<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>微博</title>
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/login.css">
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="site-wrapper" >
		<div class="site-wrapper-inner" >
			<div class="cover-container" >
				<div class="inner cover backImage">
					<h1 class="text-info">注册新用户！</h1>
					<form class="form-signin"  method="post" id ="form" action="${pageContext.request.contextPath }/register.action">
						<div class="from-group">
							<span id="error_username" style="color: #ff5b5b; font-size: 17px" >${error_username}</span> 
							<span id="isExistUser" style="color: #ff5b5b; font-size: 17px" ></span> 
							<input oninput="javascript:check_username();" tyregister.actionpe="text" id="username" value="${username }" name="username" class="form-control" placeholder="请输入用户名" required="required" autofocus="autofocus">
						</div>
						<div class="from-group">
							<span style="color: #ff5b5b; font-size: 17px" id="error_pw"></span>
							<input oninput="check_password()" type="password" id="password"
								name="password" class="form-control" placeholder="请输入密码"
								required="required">
						</div>
						<div class="from-group">
							<span style="color: #ff5b5b; font-size: 17px" id="error_rPw"></span>
							<input type="password" id="rePassword" name="rePassword"
								class="form-control" placeholder="再次输入密码" required="required">
						</div>
						<div class="from-group">
							<span style="color: #ff5b5b; font-size: 17px" name="error_vc">${error_vc }</span>
							<table>
								<tr>
									<td><input name="vCode" type="text" name="verify"
										class="form-control" placeholder="输入验证码" required="required">
									</td>
									<td><img onclick="changeVerifyCode()"
										style="height: 41px; margin-left: 10px; border-radius: 5px;"
										id="vCode"
										src="${pageContext.request.contextPath }/getImageCheckCode.action">
									</td>
								</tr>
							</table>
						</div>

			
						<br>
						<button  type="button" onclick="submit_form()" class="btn btn-lg btn-primary btn-block">注册</button>
						
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//判断两次密码是否相等
		var diff = true;
		//	jQuery
		$(document).ready(function() {
	
	
			$("#rePassword").focusout(function() {
				var rPw = $("#password").val();
				var pw = $("#rePassword").val();
				if (rPw != pw) {
					$("#error_rPw").html("密码不一致")
					diff = false;
				} else {
					$("#error_rPw").html("")
					diff =  true;
				}
			})
			
			//ajax 判断用户名是否存在
		/* 	$("#username").focusout(function() {
				var username = $("#username").val();
				var url = "http://localhost/weibo/isExistUsername.action?username="+username;
				$.get(url,function(data){
      				 if(data.message == "no"){
       					   $("#isExistUser").html("");
      				 }else{
       					   $("#isExistUser").html("用户名已存在");
      					}
    			});					
			}) */
			
			//ajax 判断用户名是否存在
			$("#username").focusout(function(){
				var params={}; 
				params.username=$("#username").val();; 
				$.ajax({ 
				  type:"post", 
				  url:"http://localhost/weibo/isExistUsername.action", 
				  data:params, 
				  dataType:"json", 
				  success:function(data){ 
				    if(data.message=='yes'){ 
				      $("#isExistUser").html("用户名已存在");; 
				    }else{ 
				      $("#isExistUser").html("");
				    } 
				  }
				})
			})	
			
			
	
		})
	
		//	提交
		function submit_form(){
		    var form = document.getElementById("form");
			if(check_username() && check_password()&& diff){
				
				form.submit();
			}else{
			
				return;
			} 
		}
	
		//原生js
		function check_username() {
			var input_username = document.getElementById("username");
			var error_username = document.getElementById("error_username");
			//正则判断数字+字母
			var reg = /^[A-Za-z0-9]+$/;
			if (input_username.value.length < 5) {
				error_username.innerHTML = "用户名长度必须大于4";
				return false;
			} else if (!reg.test(input_username.value)) {
				error_username.innerHTML = "用户名必须由数字和字母组成";
				return false;
			} else {
				error_username.innerHTML = "";
				return true;
			}
		}
		//校验密码
		function check_password() {
			var password = document.getElementById("password");
			var error_pw = document.getElementById("error_pw");
			if (password.value.length < 7) {
				error_pw.innerHTML = "密码长度必须大于6"
				return false;
			} else if (password.value.length >= 19) {
				error_pw.innerHTML = "密码长度必须小于19"
				return false;
			} else {
				error_pw.innerHTML = ""
				return true;
			}
		}
		function changeVerifyCode() {
			var img = document.getElementById("vCode");
			img.src = "${pageContext.request.contextPath }/getImageCheckCode.action?" + new Date().getTime();
		}
	</script>


</body>

</html>