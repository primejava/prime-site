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
<title>流程定义列表</title>
<%@include file="/common/s.jsp"%>
<script type="text/javascript">
	
</script>
	<link rel="stylesheet" href="${ctx}/resources/lucene/result.css"  type="text/css" />
</head>
<body>
	<%@include file="/header/attachment.jsp"%>
	<div class="row-fluid">
		<%@include file="/menu/attachment.jsp"%>
		<!-- start of main -->
		<section id="m-main" class="col-md-10" style="padding-top:65px;">
						<c:forEach items="${page.result}" var="eachdoc">
							<TABLE cellSpacing=0 cellPadding=0 border=0>
								<TBODY>
									<TR>
										<TD class="f EC_PP"><A href="convert.do?id=${eachdoc.id}"
											target=_blank>${eachdoc.filename} </A> <BR>
											${eachdoc.contents} <BR></TD>
									</TR>
								</TBODY>
							</TABLE>
						</c:forEach>
			<div>
				 <div class="clearfix"></div>
			</div>
		</section>
	</div>
</body>
</html>
