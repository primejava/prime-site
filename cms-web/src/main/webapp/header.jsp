<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
</script>
<%@include file="/common/meta.jsp"%>
 <c:if test="${not empty flashMessages}">
	<div id="m-success-message" style="display:none;">
	  <ul style="margin-top: 10px;">
	  <c:forEach items="${flashMessages}" var="item">
	    <li style="list-style-type:none;">${item}</li>
	  </c:forEach>
	  </ul>
	</div>
</c:if>

<div class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="${tenantPrefix}/">
			    <img src="${tenantPrefix}/s/logo32.png" class="img-responsive pull-left" style="margin-top:-5px;margin-right:5px;">
			    Lemon <sub><small>1.7.0-SNAPSHOT</small></sub>
		      </a>
		    </div>
		
			<div class="navbar-collapse collapse">
			
			
				<ul class="nav navbar-nav" id="navbar-menu">
					<li class="${currentHeader == 'attachment' ? 'active' : ''}"><a
						href="${scopePrefix}/admin/attachment/listByList.do"><i
							class="icon-user"></i>附件管理</a></li>		
					<li class="${currentHeader == 'article' ? 'active' : ''}"><a
						href="${scopePrefix}/admin/article/articles.do"><i
							class="icon-user"></i>文章管理</a></li>		
					<li class="${currentHeader == 'flow' ? 'active' : ''}"><a
						href="${scopePrefix}/admin/flow/flows.do"><i
							class="icon-user"></i>流程管理</a></li>
					<li class="${currentHeader == 'query' ? 'active' : ''}"><a
						href="${scopePrefix}/admin/query/query.do"><i
							class="icon-user"></i>综合查询</a></li>		
					<li class="dropdown ${currentHeader == 'person' ? 'active' : ''}">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#" aria-expanded="false"><i
							class="glyphicon glyphicon-list"></i>个人事务 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a
								href="<%=request.getContextPath()%>/admin/leave/leaves"><i class="glyphicon glyphicon-list"></i>申请请假</a></li>
							<li class="divider"></li>
							<li><a
								href="<%=request.getContextPath()%>/admin/leave/ApplingLeaveFlow"><i class="glyphicon glyphicon-list"></i>我的请假申请</a></li>
							<li class="divider"></li>
							<li><a
								href="<%=request.getContextPath()%>/admin/leave/auditList"><i class="glyphicon glyphicon-list"></i>请假审核</a></li>
							<li class="divider"></li>
							<li><a
								href="<%=request.getContextPath()%>/admin/email/input"><i class="glyphicon glyphicon-list"></i>发送邮件</a></li>
							<li class="divider"></li>
							<li><a
								href="<%=request.getContextPath()%>/admin/message/list"><i class="glyphicon glyphicon-list"></i>消息管理</a></li>
							<li class="divider"></li>
						</ul>
					</li>
					
					<li class="dropdown ${currentHeader == 'scope' ? 'active' : ''}">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#" aria-expanded="false"><i
							class="glyphicon glyphicon-list"></i>系统管理 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a
								href="<%=request.getContextPath()%>/admin/user/users"><i
									class="glyphicon glyphicon-list"></i>用户管理</a></li>
							<li><a href="${scopePrefix}/admin/group/groups"><i
									class="glyphicon glyphicon-list"></i>用户组管理</a></li>
							<li class="divider"></li>
						</ul>
					</li>
				</ul>
				
				
			    <ul class="nav navbar-nav navbar-right">
					<li>
						<form class="navbar-form navbar-search"
							action="${tenantPrefix}/pim/address-list-list.do" role="search">
							<div class="form-group">
								<input type="text" class="form-control search-query"
									placeholder="搜索" name="username">
							</div>
						</form>
					</li>
					
		        <li class="dropdown">
		          <a data-toggle="dropdown" class="dropdown-toggle" href="#">
				    <img src="${tenantPrefix}/rs/avatar?id=${loginUser.id }&width=16" style="width:16px;height:16px;" class="img-circle">
					${loginUser.username }
		            <b class="caret"></b>
		          </a>
		          <ul class="dropdown-menu">
				    <li class="text-center">&nbsp;<img src="${tenantPrefix}/rs/avatar?id=${loginUser.id }&width=64" style="width:64px;height:64px;" class="img-rounded"></li>
		            <li><a href="${tenantPrefix}/user/my-info-input.do"><i class="glyphicon glyphicon-list"></i> 个人信息</a></li>
		            <li class="divider"></li>
					<li><a href="${tenantPrefix}/login/logout"><i class="glyphicon glyphicon-list"></i> 退出</a></li>
		          </ul>
		        </li>
			</ul>
		
			</div>
	</div>
</div>
