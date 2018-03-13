<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%><%--语法标签 --%>
<%
	pageContext.setAttribute("currentHeader", "message");
%>
<%
	pageContext.setAttribute("currentMenu", "message");
%>
<!doctype html>
<html>
<head>
<title>查看消息</title>
<%@include file="/common/s.jsp"%>
<script type="text/javascript">

</script>
</head>
<body>
	<%@include file="/header/message.jsp"%>
	<div class="row-fluid">
		<%@include file="/menu/message.jsp"%>
		<!-- start of main -->
		<section id="m-main" class="col-md-10" style="padding-top: 65px;">
			<div class="row" >	
				<div class="col-md-9" >
				题目:${messageReceive.title}
				作者:${messageReceive.sender}
				内容：${messageReceive.contents.content }
				</div>
				<div class="col-md-3" >
				  
				</div>
			</div>
		</section>
	
	
	</div>
</body>
</html>
