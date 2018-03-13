<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%><%--语法标签 --%>
<%
	pageContext.setAttribute("currentHeader", "attachment");
%>
<%
	pageContext.setAttribute("currentMenu", "attachment");
%>
<!doctype html>
<html>
<head>
<title>上传附件</title>
<%@include file="/common/s.jsp"%>
<script type="text/javascript">
function loadData(){
	var content=$("#hidden_frame").contents().find("body").html();
	if(content==""||content=="<br>") return;
	content =content.replace("<pre>","").replace("</pre>","");
	var returnData =  eval("("+content+")");
	if(!returnData.success){
		$("#fileTip").css("color", "red").text(returnData.message); 
		return;
	};
	var attachment=eval("("+returnData.data+")");
	$("input[name='id']").val(attachment.id);
	$("input[name='path']").val(attachment.path);
	$('#attachment').css('display', 'block');
};

    </script>
</head>

<body>
	<%@include file="/header/attachment.jsp"%>

	<div class="row-fluid">
		<%@include file="/menu/attachment.jsp"%>

		<!-- start of main -->
		<section id="m-main" class="col-md-10" style="padding-top: 65px;">
			<div class="panel panel-default">
				<!-- 头部开始 -->
				<div class="panel-heading">
					<i class="glyphicon glyphicon-list"></i> 编辑
				</div>
				<!-- 头部结束 -->
				<div class="panel-body">
					<div class="col-md-8">
						<form id="attachmentBaseForm" method="post" action="upload.do" 
							class="form-horizontal" enctype="multipart/form-data"
							target="hidden_frame">
							<div class="form-group">
								<label class="control-label col-md-1">附件</label>
								<div class="col-sm-5">
									<input type="file" name="file">
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-offset-1 col-md-11">
									<button id="submitButton" class="btn btn-default a-submit">
										上传</button>
									<button onclick="history.back();" type="button"
										class="btn a-cancel">返回</button>
								</div>
							</div>
						</form>
						<iframe name='hidden_frame' id="hidden_frame"
							style='display: none' onload="loadData()"></iframe>
					</div>
		
					<div class="clearfix"></div>
					<div id="attachment" style="display: none;">
								<span style="color:green">上传成功！</span>
					</div>
				</div>
			</div>

		</section>
	</div>
</body>
</html>
