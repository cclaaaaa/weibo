<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>发现新鲜事</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/login.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/comment.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/webuploader.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/upload.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/zoomify.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/base.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.12.0.min.js "></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/upload.js "></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/longPolling.js "></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base.js "></script>
<jsp:include page="../nav.jsp"></jsp:include>
</head>
<body style="padding: 80px">
	<!-- 正文 -->
	<div class="container" >
		<jsp:include page="base/right_info.jsp"></jsp:include>

		<!-- 左侧导航栏 -->
		<jsp:include page="base/left_info.jsp"></jsp:include>

		<!-- 微博内容 -->
		<div style="width: 62%; float: left;">
			<!-- 发送新微博 -->
			<div class="container" style="width: auto; background-color: #fcfcf9;">
				<h3>Share your fun</h3>
				<img src="${pageContext.request.contextPath}/images/msg.jpg" align="left">
				<form method="post" action="post.action">
					<textarea class="form-control" rows="7"
						style="border: 1px; resize: none; width: 100%; background-color: #eee" placeholder="分享你的心情"
						name="weiboCustom.content"></textarea>
					<div id="uploader-demo" style="margin-top:-20px;">
						<div class="webuploader-container">
							<!--用来存放item-->
							<div id="fileList" class="uploader-list"></div>
							<div id="sendButtom">
								<div id="one" style="float: left;margin-left:10px;">
									<div id="filePicker" class="webuploader-pick form-control"> <span class="glyphicon glyphicon-picture"></span> 选择图片</div>
									<input type="file" name="file"
										class="webuploader-element-invisible" multiple="multiple"
										accept="image/*"> <label
										style="cursor: pointer; background: rgb(255, 255, 255);"></label>
								</div>
								<div id="two" style="float: left; margin-left:75%;">
									<button id="postWeibo" type="submit"
										class="btn btn-md"style="color: #fff;background-color: #e99b16cc;border-color: #f9fbf9;">发微博</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>

			<!--用于展示微博 及收藏点赞转发评论-->
			<jsp:include page="base/weiboList.jsp"/>
			
			<div class="container" style="width: auto; background-color: #fff;">
				<!-- 分页 -->
				<ul class="pagination pagination-lg">
					<!-- 上一页 -->
					<li><a href="queryAllWeiboNow.action?pageNo=${page.pageNo-1 }">&laquo;</a></li>
					<c:choose>
						<%-- 第一条：如果总页数<=5，那么页码列表为1 ~ tp --%>
						<c:when test="${page.totalPage <= 5 }">
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
							<li><a href="queryAllWeiboNow.action?pageNo=${i }">${i}</a></li>
						</c:if>
					</c:forEach>
					<!-- 下一页 -->
					<c:if test="${page.pageNo>=page.totalPage }">
					<li><a href="#">&raquo;</a></li>
					</c:if>
					<c:if test="${page.pageNo<page.totalPage }">
					<li><a href="queryAllWeiboNow.action?pageNo=${page.pageNo+1 }">&raquo;</a></li>
					</c:if>
				</ul>
			</div>

		</div>
	</div>
	<script type="text/javascript">
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
	</script>
</body>

</html>