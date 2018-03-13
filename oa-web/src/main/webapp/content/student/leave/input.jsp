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
$(document).ready(function() {
	var validator = $("#leaveBaseForm").validate({
        errorClass: 'validate-error',
        rules: {
        	cause: {
            	required: true
                }
        },
        messages: {
        	cause: {
            	required: "请填写信息"
            }
        }
    });
	
	$("#submitButton").on("click",function(){
		if(validator.form()){
			   form.submit();
		}
	});
});
    </script>
</head>

<body>
	<%@include file="/header/leave.jsp"%>

	<div class="row-fluid">
		<%@include file="/menu/student/leave.jsp"%>

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
					<form id="leaveBaseForm" method="post" action="input.do" 
						class="form-horizontal">
						<c:if test="${model != null}">
							<input id="leaveBase_id" type="hidden" name="id"
								value="${model.id}">
						</c:if>
						
						<div class="form-group">
							<label class="control-label col-md-1" for="leaveBase_name">请假事由</label>
							<div class="col-sm-5">
								<input id="leaveBase_name" type="text" name="cause"
									value="${model.cause}" size="40" class="text required"
									minlength="2" maxlength="50">
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
