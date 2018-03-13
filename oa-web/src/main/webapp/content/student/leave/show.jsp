<%@page contentType="text/html;charset=UTF-8"%>
 <%@include file="/taglibs.jsp"%><%--语法标签 --%>
<%
	pageContext.setAttribute("currentHeader", "leave");
%>
<%
	pageContext.setAttribute("currentMenu", "leave");
%>
<!doctype html>
<html>
<head>
<title>编辑请假条</title>
<%@include file="/common/s.jsp"%>
<script type="text/javascript">
</script>
</head>
<body>
	<%@include file="/header/leave.jsp"%>
	<div class="row-fluid">
		<%@include file="/menu/student/leave.jsp"%>
		<section id="m-main" class="col-md-10" style="padding-top:65px;">
			<div class="panel panel-default">
					<!-- 头部开始 -->
			  <div class="panel-heading">
			  <i class="glyphicon glyphicon-list"></i>
			  查看
			</div>
				<div class="panel-body">
				当前结点:${model.task.name}
				<ul>意见:
				<c:forEach items="${model.comments}" var="item">
						<li><span>${item.message}</span>&nbsp;<span><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
				</c:forEach>
				</ul>
				</div>
			</div>
		</section>
	</div>
</body>
</html>
