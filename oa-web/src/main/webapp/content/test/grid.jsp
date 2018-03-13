<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%
	pageContext.setAttribute("currentHeader", "echart");
%>
<%
	pageContext.setAttribute("currentMenu", "echart");
%>
<!doctype html>
<html>
<head>
<title>测试</title>
<%@include file="/common/s.jsp"%>
<link href="${ctx}/resources/educations/education.plug.css" rel="stylesheet">
<script src="${ctx}/resources/educations/jquery.educations.plug.js"></script>

<script type="text/javascript">
$(function(){
	$("#hiringSpecialty").buildCollegeSelect({
		collegeIds:"collegeIds",
		specialtyIds:"SpecialtyIds",
		educationIds:"educationIds",
		collegeNames:"collegeTexts",
		educationNames:"educationTexts",
		dataUrl:"collegeSpecialties.do"
	});
	
	$("#tags").tagsInput({
		'height':'30px',
		'width':'900px',
		'defaultText':'添加标签...', 
		'placeholderColor' : '#666666'
	}).importTags("111");
});
</script>
</head>
<body>
	<%@include file="/header/echart.jsp"%>
	<section id="m-main" class="col-md-10" style="padding-top: 65px;"><button id="hiringSpecialty">提交</button></section>
	<section id="m-main" class="col-md-10" style="padding-top: 65px;"><button id="hiringSpecialty">评论</button></section>
	
	<input id="tags" type="text" name="description" size="40" class="text" />
									
									
	<div class="container">
		<div class="row-fluid">
							
		</div>
	</div>
</body>
</html>
