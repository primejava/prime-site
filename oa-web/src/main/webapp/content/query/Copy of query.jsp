<%@page contentType="text/html;charset=UTF-8"%>
 <%@include file="/taglibs.jsp"%><%--语法标签 --%>
<%
	pageContext.setAttribute("currentHeader", "query");
%>
<%
	pageContext.setAttribute("currentMenu", "query");
%>
<!doctype html>
<html>
<head>
<title>编辑流程组</title>
<%@include file="/common/s.jsp"%>
<script src="${ctx}/resources/jqueryBuilder_test/js/bootbootbox.min.js"></script>
</head>
<body>
	<%@include file="/header/query.jsp"%>
	<div class="row-fluid" >
		<%@include file="/menu/query.jsp"%>
			<section id="m-main" class="col-md-10" style="padding-top:65px;">
				<div id="builder-basic"></div>
			<button class="btn btn-primary parse-json" data-target="basic">Get rules</button>
			<script src="${ctx}/resources/jqueryBuilder_test/js/demo-basic.js"></script>
			<script src="${ctx}/resources/jqueryBuilder_test/js/demo.js"></script>
			</section>
	</div>
</body>
</html>
