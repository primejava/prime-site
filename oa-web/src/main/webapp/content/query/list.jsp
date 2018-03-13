<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%
	pageContext.setAttribute("currentHeader", "user");
%>
<%
	pageContext.setAttribute("currentMenu", "user");
%>
<!doctype html>
<html>
<head>
<title>综合查询</title>
<%@include file="/common/s.jsp"%>
<script type="text/javascript">
	var config = {
		    id: 'userGrid',
		    pageNo: ${page.pageNo},
		    pageSize: ${page.pageSize},
		    totalCount: ${page.totalCount},
		    resultSize: ${page.resultSize},
		    pageCount: ${page.pageCount},
		    orderBy: '${page.orderBy == null ? "" : page.orderBy}',
		    asc: ${page.asc},
		    params: {
		        'filter_LIKES_username': '${param.filter_LIKES_username}',
		        'filter_EQI_status': '${param.filter_EQI_status}'
		    },
			selectedItemClass: 'selectedItem',
			gridFormId: 'userGridForm',
			exportUrl: 'account-info-export.do'
		};

	
	var getColumnsNameAndType=function(sql){
		$.ajax({
	        type: "GET",
	        url: "getColumnsNameAndType.do",
	        dataType: "json",
	        success: function(map){
	        	$.each(map, function(key, value) {
	        		console.log(key + ':' + value);
	        	}); 
	        }
	    });
	};
	
	var getColumnsNameAndType2=function(sql){
		$.ajax({
	        type: "GET",
	        url: "getColumnsNameAndType2.do",
	        dataType: "json",
	        success: function(data){
	        	var mObj=JSON.stringify(data, null, 2);
	        	console.log(mObj);
	    	    $('#builder-basic').queryBuilder({
				    plugins: ['bt-tooltip-errors'],
				    conditions:['AND','OR'],
				    filters: data
				});
	        }
	    });
	};
	
		var table;
		
		$(function() {
			table = new Table(config);
		    table.configPagination('.m-pagination');
		    table.configPageInfo('.m-page-info');
		    table.configPageSize('.m-page-size');
		
		
		
// 		    $('#builder-basic').queryBuilder({
// 			    plugins: ['bt-tooltip-errors'],
// 			    conditions:['AND','OR'],
// 			    filters: [{
// 			        id: 'username',
// 			        label: '用户名',
// 			        type: 'string',
// 			        operators: ['equal']
// 			    },
// 			    {
// 			        id: 'status',
// 			        label: '状态',
// 			        type: 'integer',
// 			        input: 'select',
// 			        values: {
// 			            1: '可用',
// 			            0: '禁用'
// 			        },
// 			        operators: ['equal']
// 			    }]
// 			});
		    
		    getColumnsNameAndType2();
		
		});
	
		
		
</script>
</head>
<body>
	<%@include file="/header/query.jsp"%>
	<div class="row-fluid">
		<%@include file="/menu/query.jsp"%>

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
			<form name="userForm" method="post" action="postQuerySql.do" class="form-inline">
			<input id="sql" type="hidden" name="sql">
			<div id="builder-basic"></div>
			<button class="btn btn-primary parse-json" data-target="basic">查询</button>
			</form>
			<!-- 下面加个列表 -->
<%-- 			<script src="${ctx}/resources/jqueryBuilder_test/js/mydemo-basic.js"></script> --%>
			<script src="${ctx}/resources/jqueryBuilder_test/js/demo.js"></script>
			</div>
	 	</div>
		<!-- 搜索框结束 -->
		<!-- 工具条开始 -->
			<div style="margin-bottom: 20px;">
				<div class="pull-left btn-group" role="group">
					<button class="btn btn-default a-insert"
						onclick="location.href='input.do'">新建</button>
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
			<!-- 列表开始 -->
			<div class="panel panel-default">
		        <div class="panel-heading">
				  <i class="glyphicon glyphicon-list"></i>
					用户列表
				</div>
			</div>
			<form id="userGridForm" name="userGridForm" method='post'
				action="deleteUsers.do" class="m-form-blank">
				<table id="userGrid" class="table table-hover">
					<thead>
						<tr>
							<th width="10" class="table-check"><input
								type="checkbox" name="checkAll"
								onchange="toggleSelectedItems(this.checked)"></th>
							<th class="sorting" name="id">编号</th>
							<th class="sorting" name="username">用户名</th>
							<th class="sorting" name="nickname">昵称</th>
							<th class="sorting" name="email">邮箱</th>
							<th class="sorting" name="phone">电话</th>
							<th class="sorting" name="status">状态</th>
							<th>操作</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${page.result}" var="item">
							<tr>
								<td><input type="checkbox" class="selectedItem a-check"
									name="selectedItem" value="${item.id}"></td>
								<td>${item.id}</td>
								<td>${item.username}</td>
								<td>${item.nickname}</td>
								<td>${item.email}</td>
								<td>${item.phone}</td>
							    <td>
								  <c:if test="${item.status==1}">
								    <span style="color:green;">启用</span>(<a href="disable.do?id=${item.id}">禁用</a>)
								  </c:if>
								  <c:if test="${item.status==0}">
								    <span style="color:red;">禁用</span>(<a href="active.do?id=${item.id}">启用</a>)
								  </c:if>
								</td>
								<td><a href="input.do?id=${item.id}" class="a-update">编辑</a></td>
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
			<!-- 列表结束 -->
			 <div class="m-spacer"></div>
		</section>
		<!-- end of main -->
	</div>

</body>
</html>
