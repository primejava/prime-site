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
<title>订阅</title>
<%@include file="/common/s.jsp"%>
<script type="text/javascript">
//禁用Enter键表单自动提交  
document.onkeydown = function(event) {  
    var target, code, tag;  
    if (!event) {  
        event = window.event; //针对ie浏览器  
        target = event.srcElement;  
        code = event.keyCode;  
        if (code == 13) {  
            tag = target.tagName;  
            if (tag == "TEXTAREA") { return true; }  
            else { return false; }  
        }  
    }  
    else {  
        target = event.target; //针对遵循w3c标准的浏览器，如Firefox  
        code = event.keyCode;  
        if (code == 13) {  
            tag = target.tagName;  
            if (tag == "INPUT") { return false; }  
            else { return true; }  
        }  
    }  
}; 
</script>
</head>
<body>
	<%@include file="/header/article.jsp"%>
	<div class="row-fluid">
		<%@include file="/menu/article.jsp"%>
		<!-- start of main -->
		<section id="m-main" class="col-md-10" style="padding-top: 65px;">
			<div class="panel panel-default">
				<!-- 头部开始 -->
				<div class="panel-heading">
					<i class="glyphicon glyphicon-list"></i> 编辑
				</div>
				<!-- 头部结束 -->
				<div class="panel-body">
					<form id="articleBaseForm" method="post" action="subscribe.do"
						class="form-horizontal">
			    		<div class="form-group">
			    			<div class="input-group">
						  	<div class="input-group-addon">tag标签</div>
						      <input type="text" data-role="tagsinput"  class="form-control"  name="tag"  value="${tag}">
						    </div>
					    </div>
						<div class="form-group">
							<button class="btn btn-default">提交</button>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
</body>
</html>
