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
var template = function() {
	location.href="${ctx}/template/userImport.xls";
};
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

$("#importButton").on("click",function(){	
	if($("input[name='attachment.id']").val()==''){
		return;
	}
	$("#import").submit();
});

    </script>
</head>

<body>
	<%@include file="/header/flow.jsp"%>

	<div class="row-fluid">
		<%@include file="/menu/flow.jsp"%>

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
						<div class="prompt">
							请先<a href="#" onclick="template()">下载模板</a>,填好数据后再导入。
						</div>
						<form id="flowBaseForm" method="post" action="uploadUserData.do" 
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
					<div class="col-md-4">
						<form id="import" action="importUserData.do" method="post">
							<input type="hidden" name="id" /> 
							<input type="hidden" name="path" />
							<button class="btn btn-default a-submit">导入</button>
						</form>
					</div>
					<div class="clearfix"></div>
					<div id="attachment" style="display: none;">
								<span style="color:green">上传成功！</span>
					</div>
					<div id="result">
					 	<c:if test="${showResult}">
						<span style="color:green">导入完成，总数量：${importRecord.totalRecord},错误数量：${importRecord.errorRecord}, 插入数量：${importRecord.insertRecord}, 编辑数量: ${importRecord.updateCount}, 成功数量：${importRecord.successCount}</span>
						</c:if>
					</div>
				</div>
			</div>

		</section>
		<!-- end of main -->
		<!-- http://www.codingyun.com/article/50.html -->
	</div>
</body>
</html>
