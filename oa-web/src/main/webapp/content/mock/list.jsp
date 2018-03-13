<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%
	pageContext.setAttribute("currentHeader", "group");
%>
<%
	pageContext.setAttribute("currentMenu", "group");
%>
<!doctype html>
<html>
<head>
<title>模拟列表</title>
<%@include file="/common/s.jsp"%>
<script type="text/javascript">
//这样去拦截掉json，不去后台发送
Mock.mock(/\.json/, {
    "code|1": [200, 300],
    "data": {
        "name2": "@CNAME",
        "age|20-30": 30,
        ip: '@ip',
        email: '@email',
        phone: '@phone',
        avatar: '@image'
    }
});
$.get("x.json", function(data){
    console.log(data)
}, "json");
var displayDate=function(){
		var result=Mock.mock('@cname');
		$("#detail").text(result);
};
</script>
</head>
<body>
	<%@include file="/header/group.jsp"%>
	<div class="row-fluid">
		<section id="m-main" class="col-md-10" style="padding-top:65px;">
			<div class="form-group">
					<div id="detail"></div>
					<button type="button" onclick="displayDate()">提交</button>
			</div>
		</section>
	</div>
</body>
</html>
