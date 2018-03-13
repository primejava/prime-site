<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%
	pageContext.setAttribute("currentHeader", "attachment");
%>
<%
	pageContext.setAttribute("currentMenu", "attachment");
%>
<!doctype html>
<html>
<head>
<title>查看附件</title>
<%@include file="/common/s.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/disk/sprite_list_icon.css"  type="text/css" />
<script type="text/javascript" src="${ctx}/resources/jquery.qrcode.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/jquery.qrcode.min.js"></script>
</head>
<body>
	<%@include file="/header/attachment.jsp"%>
	<div class="container" style="padding-top: 65px;" id="top">
		<div class="row">
			<div class="col-md-12">
				<div class="alert-fixed-top" data-alerts="alerts" data-titles="{}"
					data-ids="myid" data-fade="1000"></div>
				<div class="row">
					<div class="col-md-12">
						<a href="listByGrid.do?pageNo=${pageNo}"><i
							class=" glyphicon glyphicon-arrow-left"></i>返回</a>
					</div>
					<div class="col-md-12 text-center">
						<i class="icon-62 icon-62-${attachment.type}"></i>
						<div>${attachment.name}</div>
						<div>
							<div class="btn-group" role="group" aria-label="...">
								<a href="download.do?id=${attachment.id}"
									class="btn btn-default">下载</a>
								<div class="btn-group" role="group">
									<button type="button" class="btn btn-default dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">
										二维码 <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><span id="qrcode"></span></li>
										<script>
											$('#qrcode')
													.qrcode(
															"<tags:baseUrl/>/disk/disk-info-download.do?id=${attachment.id}");
										</script>
									</ul>
								</div>
								<c:if test="${isDoc}">
								<a href="convert.do?id=${attachment.id}"
									class="btn btn-default">在线预览</a>
								</c:if>
							</div>

							<hr>
						</div>
					</div>
					<div class="col-md-12">
						<table class="table">
							<tbody>
								<tr>
									<td class="col-md-3">文件类型</td>
									<td class="col-md-3">${attachment.type}</td>
									<td class="col-md-3">文件大小</td>
									<td class="col-md-3">${attachment.fileSize}</td>
								</tr>
								<tr>
									<td>创建人</td>
									<td>我</td>
									<td>创建时间</td>
									<td><fmt:formatDate value="${attachment.uploadTime}"
											type="both" /></td>
								</tr>
							</tbody>
						</table>
						<!-- 文件预览、观看、播放 -->
						<c:choose>
						<c:when test="${fn:contains('jpg,png', attachment.type)}">
					       		<img  src="${ctx}${attachment.url}" />
					    </c:when>
					    <c:when test="${fn:contains('avi,mp4', attachment.type)}">
					       		<jsp:include page="/player/play-video.jsp"/>
					    </c:when>
					    <c:when test="${fn:contains('mp3', attachment.type)}">
					       		<jsp:include page="/player/play-music.jsp"/>
					    </c:when>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<!--/col-->
			
	</div>
	<!--/row-->
</body>
</html>
