<%@ page language="java" pageEncoding="UTF-8"%>
<style type="text/css">
#accordion .panel-heading {
	cursor: pointer;
}

#accordion .panel-body {
	padding: 0px;
}
</style>
<div id="accordion" class="panel-group col-md-2" role="tablist"
	aria-multiselectable="true" style="padding-top: 65px;">

	<div class="panel panel-default">
		<div class="panel-heading" role="tab" id="collapse-header-user"
			data-toggle="collapse" data-parent="#accordion"
			href="#collapse-body-user" aria-expanded="true"
			aria-controls="collapse-body-user">
			<h4 class="panel-title">
				<i class="glyphicon glyphicon-list"></i> <span class="title">请假管理</span>
			</h4>
		</div>
		<div id="collapse-body-user"
			class="panel-collapse collapse in"
			role="tabpanel" aria-labelledby="collapse-header-user">
			<div class="panel-body">
				<ul class="nav nav-list">
					<li><a href="${ctx}/admin/leave/ApplingLeaveFlow"><i class="glyphicon glyphicon-list"></i>我的申请</a></li>
					<li><a href="${ctx}/admin/leave/leaveHistorys"><i class="glyphicon glyphicon-list"></i>请假历史</a></li>
					<li><a href="${ctx}/admin/leave/leaves"><i class="glyphicon glyphicon-list"></i>请假信息</a></li>
				</ul>
			</div>
		</div>
	</div>

	<footer id="m-footer" class="text-center">
		<hr>
		&copy;PrimeJava
	</footer>
</div>