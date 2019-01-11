<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

		<c:forEach items="${weiboList }" var="weibo" varStatus="status">
				<div id="weiboItem">
					<!-- 头部 -->
					<div class="container"
						style="width: auto; background-color: white; margin-top:20px;" >
						<!-- 头像 -->
						<div
							style="cursor: pointer; height: 50px; width: 50px; margin: 10px; float: left;">
							<c:if test="${weibo.user.userId==user.userId}">
								<img onclick="javascript:clickme();"
									src="/imgUpload/${weibo.user.face}" width="50px" height="50px"
									class="img-circle">
							</c:if>
							<c:if test="${weibo.user.userId!=user.userId}">
								<img onclick="javascript:clickother(${weibo.user.userId});"
									src="/imgUpload/${weibo.user.face}" width="50px" height="50px"
									class="img-circle">
							</c:if>
						</div>
						<!-- 昵称+日期 -->
						<div
							style="text-align: left; margin: 10px; margin-left: 20px; float: left;">
							<a style="color: #333; font-size: 20px" href="javascrip:void(0);">${weibo.user.nickname }</a><br>
							<span style="color: #333; font-size: 15px">${weibo.date }</span>
						</div>
					</div>
					<!-- 内容 -->
					<div class="container"
						style="width: auto; background-color: #fff;">
						<!-- 文字 -->
						<!-- onclick="javascript:clickWeibo(${weibo.weiboId});" -->
						<div style="text-align: left; margin-left: 85px">
							<p style="color: #333; font-size: 17px">${weibo.content }</p>
						</div>
						<!-- 原创微博 -->
						<c:if test="${weibo.original == 1 }">
							<!-- 图片 -->
							<div class="example"
								style="margin-left: 85px; margin-bottom: 20px;">
								<table>
									<tr>
										<c:if test="${weibo.pic1!=null }">
											<td><img src="/imgUpload/${weibo.pic1 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
										<c:if test="${weibo.pic2!=null }">
											<td><img src="/imgUpload/${weibo.pic2 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
										<c:if test="${weibo.pic3!=null }">
											<td><img src="/imgUpload/${weibo.pic3 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${weibo.pic4!=null }">
											<td><img src="/imgUpload/${weibo.pic4 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
										<c:if test="${weibo.pic5!=null }">
											<td><img src="/imgUpload/${weibo.pic5 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
										<c:if test="${weibo.pic6!=null }">
											<td><img src="/imgUpload/${weibo.pic6 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${weibo.pic7!=null }">
											<td><img src="/imgUpload/${weibo.pic7 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
										<c:if test="${weibo.pic8!=null }">
											<td><img src="/imgUpload/${weibo.pic8 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
										<c:if test="${weibo.pic9!=null }">
											<td><img src="/imgUpload/${weibo.pic9 }"
												style="width: 130px; height: 130px"></td>
										</c:if>
									</tr>
								</table>
							</div>
						</c:if>
					</div>

					<!-- 非原创 转发微博 -->
					<c:if test="${weibo.original == 0 }">
						<!-- 头部 -->
						<div class="container"
							style="width: auto; background-color: #eaeaec;">
							<!-- 头像 -->
							<div
								style="cursor: pointer; height: 30px; width: 30px; margin: 10px; float: left; margin-left: 100px;">

								<c:if test="${weibo.repost.user.userId==user.userId }">
									<img onclick="javascript:clickme();"
										src="/imgUpload/${weibo.repost.user.face}" width="40px"
										height="40px" class="img-circle">
								</c:if>
								<c:if test="${weibo.repost.user.userId!=user.userId }">
									<img
										onclick="javascript:clickother(${weibo.repost.user.userId});"
										src="/imgUpload/${weibo.repost.user.face}" width="40px"
										height="40px" class="img-circle">
								</c:if>
							</div>
							<!-- 昵称+日期 -->
							<div
								style="text-align: left; margin: 10px; margin-left: 20px; float: left;">
								<a style="color: #333; font-size: 14px" href="javascrip:;">${weibo.repost.user.nickname }</a><br>
								<span style="color: #333; font-size: 10px">${weibo.repost.date }</span>
							</div>
						</div>
						<div class="container"
							style="width: auto; background-color: #eaeaec;">
							<!-- 文字 -->
							<!-- onclick="javascript:clickWeibo(${weibo.weiboId});" -->
							<div style="text-align: left; margin-left: 160px">
								<p style="color: #333; font-size: 17px">${weibo.repost.content }</p>
							</div>
							<!-- 图片 -->
							<div class="example"
								style="margin-left: 160px; margin-bottom: 20px;">
								<table>
									<tr>
										<c:if test="${weibo.repost.pic1!=null }">
											<td><img src="/imgUpload/${weibo.repost.pic1 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
										<c:if test="${weibo.repost.pic2!=null }">
											<td><img src="/imgUpload/${weibo.repost.pic2 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
										<c:if test="${weibo.repost.pic3!=null }">
											<td><img src="/imgUpload/${weibo.repost.pic3 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${weibo.repost.pic4!=null }">
											<td><img src="/imgUpload/${weibo.repost.pic4 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
										<c:if test="${weibo.repost.pic5!=null }">
											<td><img src="/imgUpload/${weibo.repost.pic5 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
										<c:if test="${weibo.repost.pic6!=null }">
											<td><img src="/imgUpload/${weibo.repost.pic6 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${weibo.repost.pic7!=null }">
											<td><img src="/imgUpload/${weibo.repost.pic7 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
										<c:if test="${weibo.repost.pic8!=null }">
											<td><img src="/imgUpload/${weibo.repost.pic8 }"
												style="width: 90px; height: 90px"></td>
										</c:if>
										<c:if test="${weibo.repost.pic9!=null }">
											<td><img src="/imgUpload/${weibo.repost.pic9 }"
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
							<c:if test="${weibo.collect==0 }">
								<button id="collect${weibo.weiboId }"
									onclick="javascript:collect(${weibo.weiboId});" type="button"
									style="width: 20%;margin-right:8px; color:#958d8f;" class="btn btn-md"><span class="glyphicon glyphicon-file"></span>收藏</button>
							</c:if>
							<c:if test="${weibo.collect==1 }">
								<button id="collect${weibo.weiboId }"
									onclick="javascript:collect(${weibo.weiboId});" type="button"
									style="width: 20%;color:#958d8f;margin-right:8px;" class="btn btn-md"><span class="glyphicon glyphicon-file"></span>已收藏</button>
							</c:if>
							<!------------------------------------------------------------------------------------------------------------------->
							<button type="button" style="width: 25% ;color:#958d8f;margin-right:8px;" class="btn btn-md"
								onclick="javascript:repost(${weibo.weiboId});"><span class="glyphicon glyphicon-share"></span> 转发</button>
							<!---------------------------------------------------- 评论 --------------------------------------------------------->
							<button onclick="javascript:loadComment(${weibo.weiboId});"
								type="button" style="width: 20% ;margin-right:8px; color:#958d8f;" class="btn btn-md"
								id="bt${status.index }"><span style="color:#71636333;"  id="commentspan${weibo.weiboId }"
										class="glyphicon glyphicon-comment" aria-hidden="true"></span>
									<span  id="commentCount${weibo.weiboId}">${weibo.commentCount}</span></button>
							<!---------------------------------------------------- 点赞 --------------------------------------------------------->
							<c:if test="${weibo.likes==0 }">
								<button id="like${weibo.weiboId}" type="button"
									style="width: 20% ;color:#958d8f;" class="btn btn-md"
									onclick="javascript:likes(${weibo.weiboId})">
									<span  id="likespan${weibo.weiboId }"
										class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
									<span  id="likeCount${weibo.weiboId}">${weibo.likeCount}</span>
								</button>
							</c:if>
							<c:if test="${weibo.likes==1 }">
								<button id="like${weibo.weiboId}" type="button"
									style="width: 20%;color:#958d8f;" class="btn btn-md"
									onclick="javascript:likes(${weibo.weiboId})">
									<span  id="likespan${weibo.weiboId }"
										class="glyphicon glyphicon-heart" aria-hidden="true"></span> <span
										 id="likeCount${weibo.weiboId}">${weibo.likeCount}</span>
								</button>
							</c:if>


						</div>
					</div>
				</div>
				<!-- 转发模态框 -->
				<div class="modal fade" id="Modal${weibo.weiboId}" role="dialog"
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
									<c:if test="${weibo.original == 0 }">
										<input type="hidden" name="repostId"
											value="${weibo.repostId }">
									</c:if>
									<c:if test="${weibo.original == 1 }">
										<input type="hidden" name="repostId" value="${weibo.weiboId }">
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
							<input type="hidden" value="${weibo.weiboId}" class="weiboId">
						</div>
						<!--评论区域 end-->
						<!--回复区域 begin-->
						<div class="comment-show " id="${weibo.weiboId }"></div>
						<!--回复区域 end-->
					</div>
					<hr>
				</div>
			</c:forEach>