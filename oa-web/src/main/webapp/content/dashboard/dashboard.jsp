<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%
	pageContext.setAttribute("currentHeader", "dashboard");
%>
<%
	pageContext.setAttribute("currentMenu", "dashboard");
%>
<!doctype html>
<html lang="en">
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>portal</title>
	<%@include file="/common/s.jsp"%>
    <script src='${ctx}/resources/dashboard/dashboard.js' type='text/javascript'></script>
    <link rel='stylesheet' href='${ctx}/resources/dashboard/dashboard.css' type='text/css' media='screen' />
    <script type="text/javascript" src="${ctx}/resources/dashboard/portal.js"></script>
</head>
<body>
	<%@include file="/header/dashboard.jsp"%>
  <div data-height="300" class="container-fluid dashboard dashboard-draggable"  style="margin-top:70px;">
      <header></header>
      <section class="row">
        <div class="portal-col col-md-4 col-sm-6" >
		<div class="portlet" >
          <div  class="panel panel-default" >
            <div class="panel-heading">
              <div class="panel-actions">
                <button class="btn btn-sm refresh-panel"><i class="glyphicon glyphicon-refresh"></i></button>
                <div class="dropdown">
                  <button data-toggle="dropdown" class="btn btn-sm" role="button"><span class="caret"></span></button>
                  <ul aria-labelledby="dropdownMenu1" role="menu" class="dropdown-menu">
                    <li><a href="javascript:void(0);updateWidget(${item.id}, ${item.portalWidget.id}, '${item.name}')"><i class="glyphicon glyphicon-pencil"></i> 编辑</a></li>
                    <li><a class="remove-panel" href="#"><i class="glyphicon glyphicon-remove"></i> 移除</a></li>
                  </ul>
                </div>
              </div>
		      <i class="glyphicon glyphicon-list"></i>待办任务
            </div>
            <div class="panel-body">
		      <table class="table table-hover">
			    <thead>
			  	<tr>
					<th>编号</th>
					<th>名称</th>
					<th>创建时间</th>
					<th>&nbsp;</th>
				</tr>
			    </thead>
			    <tbody>
				<c:forEach items="${personalTasks.result}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td><fmt:formatDate value="${item.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><a
							href="${scopePrefix}/admin/flow/showStartflow.do?humanTaskId=${item.id}"
							class="btn btn-small btn-primary">处理</a></td>
					</tr>
				</c:forEach>
			    </tbody>
		      </table>
            </div>
          </div>
        </div>
        
        	<div class="portlet" >
          <div  class="panel panel-default" >
            <div class="panel-heading">
              <div class="panel-actions">
                <button class="btn btn-sm refresh-panel"><i class="glyphicon glyphicon-refresh"></i></button>
                <div class="dropdown">
                  <button data-toggle="dropdown" class="btn btn-sm" role="button"><span class="caret"></span></button>
                  <ul aria-labelledby="dropdownMenu1" role="menu" class="dropdown-menu">
                    <li><a href="javascript:void(0);updateWidget(${item.id}, ${item.portalWidget.id}, '${item.name}')"><i class="glyphicon glyphicon-pencil"></i> 编辑</a></li>
                    <li><a class="remove-panel" href="#"><i class="glyphicon glyphicon-remove"></i> 移除</a></li>
                  </ul>
                </div>
              </div>
		      <i class="glyphicon glyphicon-list"></i>待办任务
            </div>
            <div class="panel-body">
		      <table class="table table-hover">
			    <thead>
			  	<tr>
					<th>编号</th>
					<th>名称</th>
					<th>创建时间</th>
					<th>&nbsp;</th>
				</tr>
			    </thead>
			    <tbody>
				<c:forEach items="${personalTasks.result}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td><fmt:formatDate value="${item.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><a
							href="${scopePrefix}/admin/flow/showStartflow.do?humanTaskId=${item.id}"
							class="btn btn-small btn-primary">处理</a></td>
					</tr>
				</c:forEach>
			    </tbody>
		      </table>
            </div>
          </div>
        </div>
		</div>
		
		 <div class="portal-col col-md-4 col-sm-6" >
		<div class="portlet" >
          <div  class="panel panel-default" >
            <div class="panel-heading">
              <div class="panel-actions">
                <button class="btn btn-sm refresh-panel"><i class="glyphicon glyphicon-refresh"></i></button>
                <div class="dropdown">
                  <button data-toggle="dropdown" class="btn btn-sm" role="button"><span class="caret"></span></button>
                  <ul aria-labelledby="dropdownMenu1" role="menu" class="dropdown-menu">
                    <li><a href="javascript:void(0);updateWidget(${item.id}, ${item.portalWidget.id}, '${item.name}')"><i class="glyphicon glyphicon-pencil"></i> 编辑</a></li>
                    <li><a class="remove-panel" href="#"><i class="glyphicon glyphicon-remove"></i> 移除</a></li>
                  </ul>
                </div>
              </div>
		      <i class="glyphicon glyphicon-list"></i>待办任务
            </div>
            <div class="panel-body">
		      <table class="table table-hover">
			    <thead>
			  	<tr>
					<th>编号</th>
					<th>名称</th>
					<th>创建时间</th>
					<th>&nbsp;</th>
				</tr>
			    </thead>
			    <tbody>
				<c:forEach items="${personalTasks.result}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td><fmt:formatDate value="${item.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><a
							href="${scopePrefix}/admin/flow/showStartflow.do?humanTaskId=${item.id}"
							class="btn btn-small btn-primary">处理</a></td>
					</tr>
				</c:forEach>
			    </tbody>
		      </table>
            </div>
          </div>
        </div>
        
        	<div class="portlet" >
          <div  class="panel panel-default" >
            <div class="panel-heading">
              <div class="panel-actions">
                <button class="btn btn-sm refresh-panel"><i class="glyphicon glyphicon-refresh"></i></button>
                <div class="dropdown">
                  <button data-toggle="dropdown" class="btn btn-sm" role="button"><span class="caret"></span></button>
                  <ul aria-labelledby="dropdownMenu1" role="menu" class="dropdown-menu">
                    <li><a href="javascript:void(0);updateWidget(${item.id}, ${item.portalWidget.id}, '${item.name}')"><i class="glyphicon glyphicon-pencil"></i> 编辑</a></li>
                    <li><a class="remove-panel" href="#"><i class="glyphicon glyphicon-remove"></i> 移除</a></li>
                  </ul>
                </div>
              </div>
		      <i class="glyphicon glyphicon-list"></i>待办任务
            </div>
            <div class="panel-body">
		      <table class="table table-hover">
			    <thead>
			  	<tr>
					<th>编号</th>
					<th>名称</th>
					<th>创建时间</th>
					<th>&nbsp;</th>
				</tr>
			    </thead>
			    <tbody>
				<c:forEach items="${personalTasks.result}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td><fmt:formatDate value="${item.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><a
							href="${scopePrefix}/admin/flow/showStartflow.do?humanTaskId=${item.id}"
							class="btn btn-small btn-primary">处理</a></td>
					</tr>
				</c:forEach>
			    </tbody>
		      </table>
            </div>
          </div>
        </div>
		</div>
		
		 <div class="portal-col col-md-4 col-sm-6" >
		<div class="portlet" >
          <div  class="panel panel-default" >
            <div class="panel-heading">
              <div class="panel-actions">
                <button class="btn btn-sm refresh-panel"><i class="glyphicon glyphicon-refresh"></i></button>
                <div class="dropdown">
                  <button data-toggle="dropdown" class="btn btn-sm" role="button"><span class="caret"></span></button>
                  <ul aria-labelledby="dropdownMenu1" role="menu" class="dropdown-menu">
                    <li><a href="javascript:void(0);updateWidget(${item.id}, ${item.portalWidget.id}, '${item.name}')"><i class="glyphicon glyphicon-pencil"></i> 编辑</a></li>
                    <li><a class="remove-panel" href="#"><i class="glyphicon glyphicon-remove"></i> 移除</a></li>
                  </ul>
                </div>
              </div>
		      <i class="glyphicon glyphicon-list"></i>待办任务
            </div>
            <div class="panel-body">
		      <table class="table table-hover">
			    <thead>
			  	<tr>
					<th>编号</th>
					<th>名称</th>
					<th>创建时间</th>
					<th>&nbsp;</th>
				</tr>
			    </thead>
			    <tbody>
				<c:forEach items="${personalTasks.result}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td><fmt:formatDate value="${item.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><a
							href="${scopePrefix}/admin/flow/showStartflow.do?humanTaskId=${item.id}"
							class="btn btn-small btn-primary">处理</a></td>
					</tr>
				</c:forEach>
			    </tbody>
		      </table>
            </div>
          </div>
        </div>
        
        	<div class="portlet" >
          <div  class="panel panel-default" >
            <div class="panel-heading">
              <div class="panel-actions">
                <button class="btn btn-sm refresh-panel"><i class="glyphicon glyphicon-refresh"></i></button>
                <div class="dropdown">
                  <button data-toggle="dropdown" class="btn btn-sm" role="button"><span class="caret"></span></button>
                  <ul aria-labelledby="dropdownMenu1" role="menu" class="dropdown-menu">
                    <li><a href="javascript:void(0);updateWidget(${item.id}, ${item.portalWidget.id}, '${item.name}')"><i class="glyphicon glyphicon-pencil"></i> 编辑</a></li>
                    <li><a class="remove-panel" href="#"><i class="glyphicon glyphicon-remove"></i> 移除</a></li>
                  </ul>
                </div>
              </div>
		      <i class="glyphicon glyphicon-list"></i>待办任务
            </div>
            <div class="panel-body">
		      <table class="table table-hover">
			    <thead>
			  	<tr>
					<th>编号</th>
					<th>名称</th>
					<th>创建时间</th>
					<th>&nbsp;</th>
				</tr>
			    </thead>
			    <tbody>
				<c:forEach items="${personalTasks.result}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td><fmt:formatDate value="${item.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><a
							href="${scopePrefix}/admin/flow/showStartflow.do?humanTaskId=${item.id}"
							class="btn btn-small btn-primary">处理</a></td>
					</tr>
				</c:forEach>
			    </tbody>
		      </table>
            </div>
          </div>
        </div>
		</div>
      </section>
    </div>
</body>
</html>
