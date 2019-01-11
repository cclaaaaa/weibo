<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 右侧个人简单信息 -->
		<div style="width: 24%; float: right; background-color: #fcfcf9;height: 550px;">
			<div class = "bg_photo" style="width: 100%; float: right; background-color: #fcfcf9;height: 90px;">
				<!-- 头像 -->
				<img src="/imgUpload/${user.face}" style="margin-top: 30px;"
					height="80px " width="80px" class="img-circle " align="center">
			</div>
			<div style="width: 100%; float: right; height: 130px;">
				<div style="width: 100%; float: right; height: 30px; margin-top:18px;">
					<!-- 昵称 -->
					<span style="font-size: 20px;">${user.nickname}&nbsp;&nbsp;</span>
				</div>
				<div style="width: 100%; float: right; height: 30px; margin-top:0px;">
					<table align="center" style="table-layout: fixed;">
						<tr>
							<td><a style="font-size:22px; color:black;" href="queryUserPage.action?userId=${user.userId}&pageNo=1"><strong>${user.weiboCount }</strong>&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
							<td><a style="font-size:22px; color:black;" href="listFollow.action?userId=${user.userId }"><strong>${user.followCount }&nbsp;&nbsp;&nbsp;&nbsp;</strong></a></td>
							<td><a style="font-size:22px; color:black;" href="listFans.action?userId=${user.userId }"><strong>${user.fansCount }</strong></td>
						</tr>
						<tr>
							<td><a>微博&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
							<td><a>关注&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
							<td><a>粉丝</a></td>
						</tr>
					</table>
				</div>
				<div style="width: 100%; float: right; height: 30px; margin-top:23px;">
					<c:if test="${user.sex==0 }">
						<span style="font-size: 15px">♀ </span>
					</c:if>
					<c:if test="${user.sex==1 }">
						<span style="font-size: 15px">♂ </span>
					</c:if>
					<span style="font-size: 15px"> ${user.age } 岁 ${user.p } ${user.c }</span>
				</div>
			</div> 
			<div style="width: 100%; float: right;height: 5px;background-color:aliceblue;margin-top:0px;">
				<a></a>
			</div>
			<div style="width: 100%; float: right;height: 5px;margin-top:0px;">
			
					<ul style="margin-top:40px;">
					  <li> <a><strong>Some Company, Inc.</strong></a></li>
					  <li><a>007 street</a></li>
					  <li><a>HangZhou City, State XXXXX</a></li>
					  <li><a><abbr title="Phone">P:</abbr>13106351219</a></li>
					  <li><a><strong>Full Name</strong></a></li>
					  <li><a href="mailto:#">841281090@qq.com</a>/li>
					</ul>
			</div>
		</div>