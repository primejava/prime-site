<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%><%--语法标签 --%>
<%
	pageContext.setAttribute("currentHeader", "article");
%>
<%
	pageContext.setAttribute("currentMenu", "article");
%>
<!doctype html>
<html>
<head>
<title>文章预览</title>
<%@include file="/common/s.jsp"%>
    <!-- jqcloud -->
<link rel="stylesheet" href="${ctx}/resources/jqcloud/miaov_style.css" type="text/css" />
<script type="text/javascript" src="${ctx}/resources/jqcloud/miaov.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<%@include file="/header/article.jsp"%>
	<div class="row-fluid">
		<%@include file="/menu/article.jsp"%>
		<!-- start of main -->
		<section id="m-main" class="col-md-10" style="padding-top: 65px;">
			<div class="row" >	
				<div class="col-md-9" >
				题目:${article.title}
			${article.contents.content }
				标签:${article.keyword}
				<div id="div1">
<%-- 				<c:set value="${fn:split(article.keyword, ',')}" var="keywords" />  --%>
				<c:forEach items="${tags}" var="item" varStatus="status">
				<c:choose>
					<c:when test="${fn:contains(article.keyword, item)}">
						<a href="previewByKey.do?key=${item}" class="red">${item}</a> 
				</c:when> 
					<c:otherwise>
						<a href="previewByKey.do?key=${item}" class="blue">${item}</a> 
					</c:otherwise>
				</c:choose>
				</c:forEach>
				</div>
				</div>
				<div class="col-md-3" >
				  
				</div>
			</div>
		</section>
	
	
	</div>
</body>
</html>
