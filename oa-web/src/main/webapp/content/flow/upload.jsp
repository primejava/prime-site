<%@page contentType="text/html;charset=UTF-8"%>
 <%@include file="/taglibs.jsp"%><%--语法标签 --%>
<%
	pageContext.setAttribute("currentHeader", "flow");
%>
<%
	pageContext.setAttribute("currentMenu", "flow");
%>
<!doctype html>
<html>
<head>
<title>上传流程</title>
<%@include file="/common/s.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	    $("#flowBaseForm").validate({
	        submitHandler: function(form) {
				bootbox.animate(false);
				var box = bootbox.dialog('<div class="progress progress-striped active" style="margin:0px;"><div class="bar" style="width: 100%;"></div></div>');
	            form.submit();
	        },
	        errorClass: 'validate-error'
	    });
});
    </script>
</head>

<body>
	<%@include file="/header/flow.jsp"%>

	<div class="row-fluid">
		<%@include file="/menu/flow.jsp"%>

		<!-- start of main -->
		<section id="m-main" class="col-md-10" style="padding-top:65px;">
		 <div class="panel panel-default">
				<!-- 头部开始 -->
			  <div class="panel-heading">
			  <i class="glyphicon glyphicon-list"></i>
			  编辑
			</div>
			<!-- 头部结束 -->
			<div class="panel-body">				
					<form id="flowBaseForm" method="post"  action="upload.do" 	class="form-horizontal" enctype="multipart/form-data">
						  
						  <div class="form-group">
						    <label class="control-label col-md-1">流程定义</label>
							<div class="col-sm-5">
							  <input type="file" name="file">
						    </div>
						  </div>
						  
					<div class="form-group">
						<div class="col-md-offset-1 col-md-11">
							<button id="submitButton" class="btn btn-default a-submit">
								保存
							</button>
							<button  onclick="history.back();"
								type="button" class="btn a-cancel">
								返回
							</button>
						</div>
					</div>
					</form>
				</div>
			</div>

		</section>
		<!-- end of main -->
	</div>
</body>
</html>
