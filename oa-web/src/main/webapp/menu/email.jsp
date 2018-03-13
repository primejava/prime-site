<%@ page language="java" pageEncoding="UTF-8"%>
<style type="text/css">
#accordion .panel-heading {
	cursor: pointer;
}

#accordion .panel-body {
	padding: 0px;
}
</style>
<div id="accordion" class="panel-email col-md-2" role="tablist"
	aria-multiselectable="true" style="padding-top: 65px;">

	<div class="panel panel-default">
		<div class="panel-heading" role="tab" id="collapse-header-user"
			data-toggle="collapse" data-parent="#accordion"
			href="#collapse-body-user" aria-expanded="true"
			aria-controls="collapse-body-user">
			<h4 class="panel-title">
				<i class="glyphicon glyphicon-list"></i> <span class="title">邮件管理</span>
			</h4>
		</div>
		<div id="collapse-body-user"
			class="panel-collapse collapse ${currentMenu == 'email' ? 'in' : ''}"
			role="tabpanel" aria-labelledby="collapse-header-user">
			<div class="panel-body">
				<ul class="nav nav-list">
					<li><a href="${ctx}/admin/email/emails"><i class="glyphicon glyphicon-list"></i>邮件列表</a></li>
				</ul>
			</div>
		</div>
	</div>

	<footer id="m-footer" class="text-center">
		<hr>
		&copy;PrimeJava
	</footer>
</div>