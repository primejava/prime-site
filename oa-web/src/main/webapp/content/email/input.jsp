<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%><%--语法标签 --%>
<%
	pageContext.setAttribute("currentHeader", "email");
%>
<%
	pageContext.setAttribute("currentMenu", "email");
%>
<!doctype html>
<html>
<head>
<title>编辑流程组</title>
<%@include file="/common/s.jsp"%>
<script type="text/javascript">
	$(function() {
		$('.full-height').height($(window).height() - 150);

		var editor = CKEDITOR.replace('message_content');
		
	});

</script>
</head>
<body>
	<%@include file="/header/email.jsp"%>
	<div class="row-fluid">
		<%@include file="/menu/email.jsp"%>
		<!-- start of main -->
		<section id="m-main" class="col-md-10" style="padding-top: 65px;">
			<div class="panel panel-default">
				<!-- 头部开始 -->
				<div class="panel-heading">
					<i class="glyphicon glyphicon-list"></i> 编辑
				</div>
				<!-- 头部结束 -->
				<div class="panel-body">
					<form id="emailBaseForm" method="post" action="send.do"
						class="form-horizontal">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">收件人：</div>
								<input type="text" id="recipients" class="form-control" placeholder="" 
									name="receiver">
							</div>
						</div>

						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">主题：</div>
								<input type="text" class="form-control" placeholder=""
									name="subject">
							</div>
						</div>

						<div class="form-group">
							<textarea id="message_content" name="content"></textarea>
						</div>

						<div class="form-group">
							<button class="btn btn-default">发送</button>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
</body>
</html>
