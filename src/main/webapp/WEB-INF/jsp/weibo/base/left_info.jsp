<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<!-- 左侧导航栏 -->
		<div style="width: 12%; float: left; margin-right: 10px; background-color: #fcfcf9;">
			<ul class="nav navbar-nav navbar-left">
				<li style="width: 100%"><a class = "btn btn-lg " href="queryAllWeiboFollow.action?pageNo=1"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;首页&nbsp;&nbsp;</a></li>
				<li style="width: 100%"><a class = "btn btn-lg " href="queryAllWeiboFriends.action?pageNo=1"><span class="glyphicon glyphicon-globe"></span>&nbsp;&nbsp;好友圈</a></li>
				<li style="width: 100%"><a class = "btn btn-lg " href="toMyLikes.action?pageNo=1"><span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;我的赞</a></li>
				<li style="width: 100%"><a class = "btn btn-lg " href="toMyCollection.action?pageNo=1"><span class="glyphicon glyphicon-paperclip"></span>&nbsp;&nbsp;我的收藏</a></li>
			</ul>
		</div>