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
<title>我的赞</title>
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
<!-- 导航栏 -->
<jsp:include page="../nav.jsp"></jsp:include>
</head>
<body style="padding: 80px">


	<!-- 正文 -->
	<div class="container">
		<!-- 右侧个人简单信息 -->
		<jsp:include page="base/right_info.jsp"/>

		<!-- 左侧导航栏 -->
		<jsp:include page="base/left_info.jsp"/>
		<!-- 微博内容 -->
		<div style="width: 62%; float: left;">
			<!-- 标题 -->
			<div class="container" style="width: auto; background-color: #fff;">
				<p style="font-size: 40px; color: black">我的赞</p>
			</div>
			<br>
			<c:forEach items="${likesList }" var="like" varStatus="status">
				<div id="weiboItem" style="margin-top:10px;">
					<!-- 头部 -->
					<div class="container"
						style="width: auto; background-color: white;">
						<!-- 头像 -->
						<div style="cursor: pointer; height: 50px; width: 50px; margin: 10px; float: left;">
								<img onclick="javascript:clickother(${like.weibo.userId});"
									src="/imgUpload/${like.weibo_face}" width="50px" height="50px"
									class="img-circle">
						</div>
						<!-- 昵称+日期 -->
						<div
							style="text-align: left; margin: 10px; margin-left: 20px; float: left;">
							<a style="color: #333; font-size: 20px" href="javascrip:;">${like.weibo_nickname }</a><br>
							<span style="color: #333; font-size: 15px">${like.weibo.date }</span>
						</div>
						<!-- 点赞时间 -->
						<div style="float: right; margin: 20px;">
						<p style="color: #777">赞于${like.date }</p>
						</div>
					</div>
					<!-- 内容 -->
					<div class="container"
						style="width: auto; background-color: #fff;">
						<!-- 文字 -->
						<!-- onclick="javascript:clickWeibo(${weibo.weiboId});" -->
						<div style="text-align: left; margin-left: 85px">
							<p style="color: #333; font-size: 17px">${like.weibo.content }</p>
						</div>
						<!-- 原创微博 -->
						<c:if test="${like.weibo.original == 1 }">
							<!-- 图片 -->
							<div class="example"
								style="margin-left: 85px; margin-bottom: 20px;">
								<table>
									<tr>
										<c:if test="${like.weibo.pic1!=null }">
											<td><img src="/imgUpload/${like.weibo.pic1 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
										<c:if test="${like.weibo.pic2!=null }">
											<td><img src="/imgUpload/${like.weibo.pic2 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
										<c:if test="${like.weibo.pic3!=null }">
											<td><img src="/imgUpload/${like.weibo.pic3 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${like.weibo.pic4!=null }">
											<td><img src="/imgUpload/${like.weibo.pic4 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
										<c:if test="${like.weibo.pic5!=null }">
											<td><img src="/imgUpload/${like.weibo.pic5 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
										<c:if test="${like.weibo.pic6!=null }">
											<td><img src="/imgUpload/${like.weibo.pic6 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${like.weibo.pic7!=null }">
											<td><img src="/imgUpload/${like.weibo.pic7 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
										<c:if test="${like.weibo.pic8!=null }">
											<td><img src="/imgUpload/${like.weibo.pic8 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
										<c:if test="${like.weibo.pic9!=null }">
											<td><img src="/imgUpload/${like.weibo.pic9 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
									</tr>
								</table>
							</div>
						</c:if>
					</div>

					<!-- 非原创 转发微博 -->
					<c:if test="${like.weibo.original == 0 }">
						<!-- 头部 -->
						<div class="container"
							style="width: auto; background-color: #eaeaec;">
							<!-- 头像 -->
							<div
								style="cursor: pointer; height: 30px; width: 30px; margin: 10px; float: left; margin-left: 100px;">
									<img
										onclick="javascript:clickother(${like.weibo.repost.user.userId});"
										src="/imgUpload/${like.weibo.repost.user.face}" width="40px"
										height="40px" class="img-circle">
							</div>
							<!-- 昵称+日期 -->
							<div
								style="text-align: left; margin: 10px; margin-left: 20px; float: left;">
								<a style="color: #333; font-size: 14px" href="javascrip:;">${like.weibo.repost.user.nickname }</a><br>
								<span style="color: #333; font-size: 10px">${like.weibo.repost.date }</span>
							</div>
						</div>
						<div class="container"
							style="width: auto; background-color: #eaeaec;">
							<!-- 文字 -->
							<!-- onclick="javascript:clickWeibo(${weibo.weiboId});" -->
							<div style="text-align: left; margin-left: 160px">
								<p style="color: #333; font-size: 17px">${like.weibo.repost.content }</p>
							</div>
							<!-- 图片 -->
							<div class="example"
								style="margin-left: 160px; margin-bottom: 20px;">
								<table>
									<tr>
										<c:if test="${like.weibo.repost.pic1!=null }">
											<td><img src="/imgUpload/${like.weibo.repost.pic1 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
										<c:if test="${like.weibo.repost.pic2!=null }">
											<td><img src="/imgUpload/${like.weibo.repost.pic2 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
										<c:if test="${like.weibo.repost.pic3!=null }">
											<td><img src="/imgUpload/${like.weibo.repost.pic3 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${like.weibo.repost.pic4!=null }">
											<td><img src="/imgUpload/${like.weibo.repost.pic4 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
										<c:if test="${like.weibo.repost.pic5!=null }">
											<td><img src="/imgUpload/${like.weibo.repost.pic5 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
										<c:if test="${like.weibo.repost.pic6!=null }">
											<td><img src="/imgUpload/${like.weibo.repost.pic6 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${like.weibo.repost.pic7!=null }">
											<td><img src="/imgUpload/${like.weibo.repost.pic7 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
										<c:if test="${like.weibo.repost.pic8!=null }">
											<td><img src="/imgUpload/${like.weibo.repost.pic8 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
										<c:if test="${like.weibo.repost.pic9!=null }">
											<td><img src="/imgUpload/${like.weibo.repost.pic9 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
									</tr>
								</table>
							</div>
						</div>
					</c:if>
					<!-- 底部 -->
					<div class="container"
						style="width: auto; background-color: #fff;">
						<br>
						<div class="btn-group" style="width: 100%">
							<!---------------------------------------------------- 收藏 --------------------------------------------------------->
							<c:if test="${like.weibo.collect==0 }">
								<button id="collect${like.weibo.weiboId }"
									onclick="javascript:collect(${like.weibo.weiboId});" type="button"
									style="width: 20%;margin-right:8px; color:#958d8f;" class="btn btn-md"><span class="glyphicon glyphicon-file"></span>收藏</button>
							</c:if>
							<c:if test="${like.weibo.collect==1 }">
								<button id="collect${like.weibo.weiboId }"
									onclick="javascript:collect(${like.weibo.weiboId});" type="button"
									style="width: 20%;margin-right:8px; color:#958d8f;" class="btn btn-md"><span class="glyphicon glyphicon-file"></span>已收藏</button>
							</c:if>
							<!------------------------------------------------------------------------------------------------------------------->
							<button type="button" style="width: 20%;margin-right:8px; color:#958d8f;" class="btn btn-md"
								onclick="javascript:repost(${like.weibo.weiboId});"><span class="glyphicon glyphicon-share"></span> 转发</button>
							
							<button onclick="javascript:loadComment(${like.weibo.weiboId});"
								type="button" style="width: 20%;margin-right:8px; color:#958d8f;" class="btn btn-md"
								id="bt${status.index }"><span style="color:#71636333;"  id="commentspan${weibo.weiboId }"
									class="glyphicon glyphicon-comment" aria-hidden="true"></span>
								<span  id="commentCount${like.weibo.weiboId}">${like.weibo.commentCount}</span></button>
							<!---------------------------------------------------- 点赞 --------------------------------------------------------->
							<c:if test="${like.weibo.likes==0 }">
								<button id="like${like.weibo.weiboId}" type="button"
									style="width: 20% ;color:#958d8f;"  class="btn btn-md"
									onclick="javascript:likes(${like.weibo.weiboId})">
									<span id="likespan${like.weibo.weiboId }"
										class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
									<span  id="likeCount${like.weibo.weiboId}">${like.weibo.likeCount}</span>
								</button>
							</c:if>
							<c:if test="${like.weibo.likes==1 }">
								<button id="like${like.weibo.weiboId}" type="button"
									style="width: 20% ;color:#958d8f;"  class="btn btn-md"
									onclick="javascript:likes(${like.weibo.weiboId})">
									<span id="likespan${like.weibo.weiboId }"
										class="glyphicon glyphicon-heart" aria-hidden="true"></span> <span
										 id="likeCount${like.weibo.weiboId}">${like.weibo.likeCount}</span>
								</button>
							</c:if>


						</div>
					</div>
				</div>
				<!-- 转发模态框 -->
				<div class="modal fade" id="Modal${like.weibo.weiboId}" role="dialog"
					aria-labelledby="myModalLabel">
					<form method="POST" action="repost.action">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel" style="color: #987">转发这条微博</h4>
								</div>
								<div class="modal-body">
									<c:if test="${like.weibo.original == 0 }">
										<input type="hidden" name="repostId"
											value="${weibo.repostId }">
									</c:if>
									<c:if test="${like.weibo.original == 1 }">
										<input type="hidden" name="repostId" value="${like.weibo.weiboId }">
									</c:if>
									<textarea autofocus="autofocus" id="repostContent"
										name="repostContent" class="content comment-input "
										placeholder="说点什么吧"></textarea>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="submit" class="btn btn-primary">转发</button>
								</div>
							</div>
						</div>
					</form>
				</div>
				<!-- 评论区 -->
				<div class="container "
					style="width: auto; background-color: #fff;">
					<div class="commentAll " id="com${status.index }"
						style="display: none;">
						<!--评论区域 begin-->
						<div class="reviewArea clearfix ">
							<textarea class="content comment-input "
								placeholder="等待输入......." onkeyup="keyUP(this) "></textarea>
							<a href="javascript:; " class="plBtn ">评论</a>
							<!-- 微博id -->
							<input type="hidden" value="${like.weibo.weiboId}" class="weiboId">
						</div>
						<!--评论区域 end-->
						<!--回复区域 begin-->
						<div class="comment-show " id="${like.weibo.weiboId }"></div>
						<!--回复区域 end-->
					</div>
					<hr>
				</div>
			</c:forEach>
			<div class="container" style="width: auto; background-color: #fff;">
				<!-- 分页 -->
				<ul class="pagination pagination-lg">
					<!-- 上一页 -->
					<li><a href="toMyLikes.action?pageNo=${page.pageNo-1 }">&laquo;</a></li>
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
							<li><a href="toMyLikes.action?pageNo=${i }">${i}</a></li>
						</c:if>
					</c:forEach>
					<!-- 下一页 -->
					<li><a href="toMyLikes.action?pageNo?pageNo=${page.pageNo+1 }">&raquo;</a></li>
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
		window.location="queryMinePage.action";
	}

	//跳至userId的用户主页
	function clickother(userId) {
		var url = "queryUserPage.action?userId=" + userId;
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