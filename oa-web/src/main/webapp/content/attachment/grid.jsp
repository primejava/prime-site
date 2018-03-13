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
	var config = {
		    id: 'attachmentGrid',
		    pageNo: ${page.pageNo},
		    pageSize: ${page.pageSize},
		    totalCount: ${page.totalCount},
		    resultSize: ${page.resultSize},
		    pageCount: ${page.pageCount},
		    orderBy: '${page.orderBy == null ? "" : page.orderBy}',
		    asc: ${page.asc},
		    params: {
		        'filter_LIKES_name': '${param.filter_LIKES_name}'
		    },
			selectedItemClass: 'selectedItem',
			gridFormId: 'attachmentGridForm',
			exportUrl: 'account-info-export.do'
		};
		var table;
		$(function() {
			table = new Table(config);
		    table.configPagination('.m-pagination');
		    table.configPageInfo('.m-page-info');
		    table.configPageSize('.m-page-size');
		});
</script>
	<link rel="stylesheet" href="${ctx}/resources/disk/sprite_list_icon.css"  type="text/css" />
</head>
<body>
	<%@include file="/header/attachment.jsp"%>
	<div class="row-fluid">
		<section id="m-main" class="col-md-12" style="padding-top: 65px;">
			<div class="btn-group pull-right" role="group" aria-label=""
				data-toggle="buttons">
				<label
					class="btn btn-default glyphicon glyphicon-th-list "
					onclick="location.href='listByList.do'"> <input
					type="radio" name="options" id="option1" autocomplete="off" checked>
				</label> 
				<label
					class="btn btn-default glyphicon glyphicon-th-large active"
					onclick="location.href='listByGrid.do'"> <input
					type="radio" name="options" id="option1" autocomplete="off">
				</label>
			</div>
  <div class="clearfix"></div>
<div class="row">
  <c:forEach items="${page.result}" var="item" varStatus="status">
  <div class="col-md-2 text-center">
    <a href="preview.do?id=${item.id}&pageNo=${page.pageNo}"><div class="icon-62 icon-62-${item.type}"></div></a>
    <a href="preview.do?id=${item.id}&pageNo=${page.pageNo}"><div class="file-62-name">${item.name}</div></a>
  </div>
<c:if test="${status.count % 5 == 0}">
</div>
<div class="row">
</c:if>
  </c:forEach>
</div>
<hr class="soften">
			<div>
				<div class="m-page-info pull-left">共100条记录 显示1到10条记录</div>
				<div class="btn-group m-pagination pull-right">
					<button class="btn btn-default">&lt;</button>
					<button class="btn btn-default">1</button>
					<button class="btn btn-default">&gt;</button>
				</div>
				<div class="clearfix"></div>
			</div>
		</section>
	</div>
</body>
</html>
