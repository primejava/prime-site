<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%
	pageContext.setAttribute("currentHeader", "flow");
%>
<%
	pageContext.setAttribute("currentMenu", "flow");
%>
<!doctype html>
<html>
<head>
<title>流程实例列表</title>
<%@include file="/common/s.jsp"%>
<script type="text/javascript">
	
	var config = {
		    id: 'flowGrid',
		    pageNo: ${page.pageNo},
		    pageSize: ${page.pageSize},
		    totalCount: ${page.totalCount},
		    resultSize: ${page.resultSize},
		    pageCount: ${page.pageCount},
		    orderBy: '${page.orderBy == null ? "" : page.orderBy}',
		    asc: ${page.asc},
		    params: {
		    },
			selectedItemClass: 'selectedItem',
			gridFormId: 'flowGridForm',
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
</head>

<body>
	<%@include file="/header/flow.jsp"%>
	<div class="row-fluid">
		<%@include file="/menu/flow.jsp"%>
		<section id="m-main" class="col-md-10" style="padding-top:65px;">
		
		<!-- 搜索框开始 -->
		<div class="panel panel-default">
	        <div class="panel-heading">
	        <i class="glyphicon glyphicon-list"></i>
			  查询
			  <div class="pull-right ctrl">
		  		<a class="btn btn-default btn-xs"><i class="glyphicon glyphicon-chevron-up"></i></a>
			  </div>
		</div>
		
  		<div class="panel-body">
		  <form name="userForm" method="post" action="users.do" class="form-inline">
		    <label for="user_username">账号:</label>
		    	<input type="text" id="user_username" name="filter_LIKES_username" value="${param.filter_LIKES_username}" class="form-control">
		    <label for="user_enabled">状态:</label>
			    <select id="user_enabled" name="filter_EQI_status" class="form-control">
				  <option value=""></option>
				  <option value="1" ${param.filter_EQI_status == 1 ? 'selected' : ''}><spring:message code='user.user.list.search.enabled.true' text='启用'/></option>
				  <option value="0" ${param.filter_EQI_status == 0 ? 'selected' : ''}><spring:message code='user.user.list.search.enabled.false' text='禁用'/></option>
			    </select>
				<button class="btn btn-default a-search" onclick="document.userForm.submit()">查询</button>
		  </form>
		</div>
	 	</div>
		<!-- 搜索框结束 -->

		<!-- 工具条开始 -->
		<div style="margin-bottom: 20px;">
			<div class="pull-left btn-group" role="group">
				<button class="btn btn-default a-insert"
					onclick="location.href='upload.do'">上传</button>
				<button class="btn btn-default a-remove" onclick="table.removeAll()">删除</button>
				<button class="btn btn-default a-export"
					onclick="table.exportExcel()">导出</button>
			</div>

			<div class="pull-right">
				每页显示 <select class="m-page-size form-control" style="display:inline;width:auto;">
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="50">50</option>
				</select> 条
			</div>
		   <div class="clearfix"></div>
		</div>
		<!-- 工具条结束-->


		<div class="panel panel-default">
			        <div class="panel-heading">
					  <i class="glyphicon glyphicon-list"></i>
						流程实例列表
					</div>
		</div>
		<form id="flowGridForm" name="flowGridForm" method='post'
			action="#" class="m-form-blank">
			<table id="flowGrid" class="table table-hover">
				<thead>
					<tr>
						<th width="10" class="table-check"><input
							type="checkbox" name="checkAll"
							onchange="toggleSelectedItems(this.checked)"></th>
						<th >实例编号</th>
						<th >业务编号</th>
						<th >流程定义编号</th>
						<th >流程开始时间</th>
						<th >流程结束时间</th>
						<th >发起人ID</th>
						<th>操作</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${page.result}" var="item">
						<tr>
							<td><input type="checkbox" class="selectedItem a-check"
								name="selectedItem" value="${item.id}"></td>
							<td>${item.id}</td>
							<td>${item.businessKey}</td>
							<td>${item.processDefinitionId}</td>
							<td><fmt:formatDate value='${item.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
							<td><fmt:formatDate value='${item.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
							<td>${item.startUserId}</td>
							<td>haha</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	
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
		<!-- end of main -->
	</div>
</body>
</html>
