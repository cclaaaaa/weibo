<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${user.nickname}的主页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/mine.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/zoomify.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/comment.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.0.min.js "></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/longPolling.js "></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base.js "></script>

<!-- 导航栏 -->
<jsp:include page="../nav.jsp"></jsp:include>
</head>
<body style="padding: 80px">
	<div class="container">
		<div class="leftbox">
			<!-- 头像 -->
			<img src="/imgUpload/${user.face }" height="150px" width="150px"
				class="img-circle" align="center">
		</div>
		<div class="rightbox">
			<table width="100%">
				<tr style="height: 60px; margin-left: 100px">
					<td style="font-size: 40px"><span>${user.nickname }&nbsp;&nbsp;</span></td>
					<td></td>
				</tr>
			</table>
			<br>
			<table width="100%" style="table-layout: fixed; font-size: 20px">
				<tr>
					<td><a href="queryUserPage.action?pageNo=1&userId=${user.userId}">${user.weiboCount }微博</a></td>
					<td><a href="listFollow.action?userId=${user.userId}">${user.followCount }关注</a></td>
					<td><a href="listFans.action?userId=${user.userId}">${user.fansCount }粉丝</a></td>
				</tr>
			</table>
			<br>
			<table style="font-size: 15px" align="center">
				<tr>
					<td> 
					<c:if test="${user.sex==1 }">
						<span>♂</span>
					</c:if>
					<c:if test="${user.sex==0 }">
						<span>♀</span>
					</c:if>
					<span>${user.age}岁  ${user.p } ${user.c }</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
				<div style="width:780px; margin:0 auto;">
					<jsp:include page="../weibo/base/weiboList.jsp"/>
				</div>
				<!-- 分页 -->
				<ul class="pagination pagination-lg">
					<!-- 上一页 -->
					<li><a href="queryMinePage.action?pageNo=${page.pageNo-1 }">&laquo;</a></li>
					<c:choose>
						<%-- 第一条：如果总页数<=5，那么页码列表为1 ~ tp --%>
						<c:when test="${page.totalPage <= page.pageSize }">
							<c:set var="begin" value="1" />
							<c:set var="end" value="${page.totalPage }" />
						</c:when>
						<c:otherwise>
							<%-- 第二条：按公式计算，让列表的头为当前页+2；列表的尾为当前页+2 --%>
							<c:set var="begin" value="${page.pageNo-2 }" />
							<c:set var="end" value="${page.pageNo+2 }" />

							<%-- 第三条：第二条只适合在中间，而两端会出问题。这里处理begin出界！ --%>
							<%-- 如果begin<1，那么让begin=1，相应end=10 --%>
							<c:if test="${begin<1 }">
								<c:set var="begin" value="1" />
								<c:set var="end" value="5" />
							</c:if>
							<%-- 第四条：处理end出界。如果end>tp，那么让end=tp，相应begin=tp-4 --%>
							<c:if test="${end>page.totalPage }">
								<c:set var="begin" value="${page.totalPage-4 }" />
								<c:set var="end" value="${page.totalPage }" />
							</c:if>
						</c:otherwise>
					</c:choose>
					<c:forEach begin="${begin}" end="${end}" var="i">
						<c:if test="${i==page.pageNo }">
							<li class="active"><a href="#">${i}</a></li>
						</c:if>
						<c:if test="${i!=page.pageNo }">
							<li><a href="queryMinePage.action?pageNo=${i }">${i}</a></li>
						</c:if>
					</c:forEach>
					<!-- 下一页 -->
					<li><a href="queryMinePage.action?pageNo=${page.pageNo+1 }">&raquo;</a></li>
				</ul>
	<script type="text/javascript">
	
	//删除微博
	function delWeibo(weiboId) {
		if(confirm("您确定要删除这条微博吗？")){
			$.get("${pageContext.request.contextPath }/deleteWeibo.action?weiboId=" + weiboId,null,function(data){
				$("#weibo" + weiboId).remove();
			});
		}
	}
	
	//已关注——关注
	function follow1(userId) {
		var text = $("#follow").text();
		if(text=="关注") { //未关注——— 关注 ————>已关注
		$.get("${pageContext.request.contextPath }/follow.action?flag=1&userId=" + userId,null,function(data){
			$("#follow").attr("class","btn btn-lg btn-success");
			$("#follow").text("已关注");
		});
		} else if(text=="已关注"){ //已关注—— 取关 ——>关注
		$.get("${pageContext.request.contextPath }/unfollow.action?flag=1&userId=" + userId,null,function(data){
			$("#follow").attr("class","btn btn-lg btn-primary");
			$("#follow").text("关注");
		});
		}
	}
	//转发微博
	function repost(weiboId) {
		$('#Modal'+weiboId).modal('toggle');
	}
	
	//跳至自己的主页
	function clickme() {
		window.location="queryMinePage.action?pageNo=1";
	}

	//跳至userId的用户主页
	function clickother(userId) {
		var url = "queryUserPage.action?pageNo=1&userId=" + userId;
		window.location=url;
	}
	
	//跳至所选微博页
	function clickWeibo(weiboId) {
		var url = "singleWeibo.action?weiboId=" + weiboId;
		window.open(url);
	}
	
	function likes(weiboId) {
		//未赞——>已赞
		var likeCount = $("#likeCount" + weiboId).text();
		if($("#likespan"+weiboId).hasClass("glyphicon-heart-empty")){
			$.get("${pageContext.request.contextPath }/like.action?weiboId=" + weiboId,null,function(data){
				$("#likespan"+weiboId).attr("class","glyphicon glyphicon-heart");
				likeCount++;
				$("#likeCount" + weiboId).text(likeCount);
			});
		}
		//已赞——>取消赞
		else {
			$.get("${pageContext.request.contextPath }/unlike.action?weiboId=" + weiboId,null,function(data){
				$("#likespan"+weiboId).attr("class","glyphicon glyphicon-heart-empty");
				likeCount--;
				$("#likeCount" + weiboId).text(likeCount);
			});
		}
	}

	function collect(weiboId) {
		var text = $("#collect" + weiboId).text();
		if(text == "收藏") {
			$.get("${pageContext.request.contextPath }/collect.action?weiboId=" + weiboId,null,function(data){
				$("#collect" + weiboId).text("已收藏");
			});
		} 
		if(text == "已收藏"){
			$.get("${pageContext.request.contextPath }/uncollect.action?weiboId=" + weiboId,null,function(data){
				$("#collect" + weiboId).text("收藏");
			});
		}
		
	}

	//相互关注—关注
	function follow2(userId) {
		var text = $("#follow").text();
		if(text=="相互关注"){ //相互关注——— 取关 ————>关注
		$.get("${pageContext.request.contextPath }/unfollow.action?flag=2&userId=" + userId,null,function(data){
			$("#follow").attr("class","btn btn-lg btn-primary");
			$("#follow").text("关注");
		});
		} else if(text=="关注") {
		$.get("${pageContext.request.contextPath }/follow.action?flag=2&userId=" + userId,null,function(data){
			$("#follow").attr("class","btn btn-lg btn-warning");
			$("#follow").text("相互关注");
		});
		}
	}
	</script>


	<script type="text/javascript "
		src="${pageContext.request.contextPath }/js/jquery-3.3.1.js "></script>
	<script type="text/javascript "
		src="${pageContext.request.contextPath }/js/bootstrap.js "></script>
	<script type="text/javascript "
		src="${pageContext.request.contextPath }/js/bootstrap-datetimepicker.js "></script>
	<script type="text/javascript "
		src="${pageContext.request.contextPath }/js/bootstrap-datetimepicker.zh-CN.js "></script>
	<script type="text/javascript "
		src="${pageContext.request.contextPath }/js/date.js "></script>
	<script type="text/javascript "
		src="${pageContext.request.contextPath }/js/jquery.flexText.js "></script>
	<script type="text/javascript "
		src="${pageContext.request.contextPath }/js/webuploader.js"></script>
	<script type="text/javascript "
		src="${pageContext.request.contextPath }/js/comment.js"></script>
	<script type="text/javascript "
		src="${pageContext.request.contextPath }/js/zoomify.js"></script>
	<script type="text/javascript">
		$('.example img').zoomify();
		
		// 长轮询
		$.ajax(getNotice);
		function hi(weiboId){
			var id = weiboId;
			alert(id);
		}
	</script>

</body>

</html>