<%@page contentType="text/html;charset=UTF-8"%>
 <%@include file="/taglibs.jsp"%><%--语法标签 --%>
<%
	pageContext.setAttribute("currentHeader", "college");
%>
<%
	pageContext.setAttribute("currentMenu", "college");
%>
<!doctype html>
<html>
<head>
<title>学院专业</title>
<%@include file="/common/s.jsp"%>
<script type="text/javascript">
var setting = {
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "parentId"
			},
			key: {
				url: "xUrl",
				name:"text"
			}
		},
		async: {
			enable: true,
			url: "${tenantPrefix}/admin/college/buildTree",
			autoParam:["id"],
			otherParam:["graduationYear",2016]
		},
		callback: {
			onAsyncSuccess:function(event, treeId,treeNode){
				var tree=$.fn.zTree.getZTreeObj(treeId);
				tree.expandAll(true);
			},
			onClick:function(event, treeId, treeNode){
				$(".menu-contents").show();
				if(null==treeNode.parentId){
					return;
				}
				$("input[name='id']").val(treeNode.id);
				$("input[name='name']").val(treeNode.text);
				$("input[name='sort']").val(treeNode.sort);
				$("input[name='parentId']").val(treeNode.parentId);
				$("input[name='year']").val($("input[name='graduationYear']").val());
				$("input[name='isCollege']").val(treeNode.getParentNode()&&treeNode.getParentNode().parentId==null);
				var pnode = treeNode.getParentNode();
				while(!!pnode&&!!pnode.parentId) {
					pnode = pnode.getParentNode();
				}
				$("input[name='education']").val(pnode?pnode.id:treeNode.id);
			}
		}
	};
	$(function(){
		$('#visitorInfo_visitTime').datetimepicker({
			viewSelect:'decade',
			format: 'yyyy', 
			autoclose: true, 
			startView: 4, 
			maxViewMode: 4,
			minViewMode:4,
			forceParse: false, 
			language: 'zh-CN' 
		});
		
		var tree = $.fn.zTree.init($("#tree"), setting);
		
		$(".refrsh-menu").click(function(){
			tree.reAsyncChildNodes(null, "refresh" ,true);
		});
		$(".add-menu").click(function(){
			$(".menu-contents").show();
			var nodes = tree.getSelectedNodes();
			if(nodes.length==0){
				$.alert("请选择要增加菜单的父菜单！");
				return;
			}
			$(".menu-contents").show();
			var treeNode=nodes[0];
			$("input[name='id']").val("");
			$("input[name='name']").val("");
			$("input[name='parentId']").val(treeNode.id);
			$("input[name='isCollege']").val(!treeNode.parentId);
			$("input[name='year']").val($("input[name='graduationYear']").val());
			var pnode = treeNode.getParentNode();
			while(!!pnode&&!!pnode.parentId) {
				pnode = pnode.getParentNode();
			}
			$("input[name='education']").val(pnode?pnode.id:treeNode.id);
		});
		
		$(".remove-menu").click(function(){
			var nodes = tree.getSelectedNodes();
			if(nodes.length==0){
				$.alert("请选择要删除的菜单!");
				return;
			}
			if(nodes[0].level==0){
				$.alert("学历节点不能删除！");
				return;
			}
			if(!nodes[0].isParent){
				deleteNode(tree,nodes[0]);
				return;
			}
			$.confirm({
				icon: "icon-question",
				content: "确定要将菜单的子菜单一起删除吗？",
				labConfirm: "确认",
				labCancel: "取消",
				onConfirm: function($dialog) {
					deleteNode(tree,nodes[0]);
				}
			});
		});
		
		var deleteNode=function(tree,node){
			var pnode = node.getParentNode();
			while(!!pnode&&!!pnode.parentId) {
				pnode = pnode.getParentNode();
			}
			$.post("remove.do",{'id':node.id,'education':(pnode?pnode.id:node.id),
					'isCollege':(node.getParentNode()&&node.getParentNode().parentId==null)},function(msg){
				if(msg.success){
					$.alert("删除成功！");
					tree.reAsyncChildNodes(null, "refresh" ,true);
					$(".menu-contents").hide();
				}
			},"json");
		}
		
		$("#submit").click(function() {
					function filter(node) {
						return (node.level == ($("#isCollege").val()=="true"?1:2) && node.text==$("#name").val());
					}
					var treeObj = $.fn.zTree.getZTreeObj("tree");
					var node = treeObj.getNodesByFilter(filter, true,treeObj.getNodeByParam("id",$("#education").val(),null)); // 仅查找一个节点
					if ($("#isCollege").val()!="true"){
						node = treeObj.getNodesByFilter(filter, true,treeObj.getNodeByParam("id",$("#parentId").val(),null));
					}
					if (node&&node.id!=$("#collegeSpecialtyId").val()){
						$.alert({
							title:"提示",
							content:"名称重复！"
						});
						return;
					}
					
				  $.post("save.do",$("#baseForm").serializeArray(),function(msg){
					  
					  if(msg.success){
						  $.alert("保存成功！");
						  var tree = $("#tree").zTree.getZTreeObj("tree");
						  tree.reAsyncChildNodes(null, "refresh" ,true);
						  /* $("#addForm").hide(); */
						  $(".menu-contents").hide();
					  }
				  },"json");
			  
			});
	});
    </script>
</head>
<body>
	<%@include file="/header/college.jsp"%>
	<div class="row-fluid">
		<%@include file="/menu/college.jsp"%>
		<!-- start of main -->
		<section id="m-main" class="col-md-10" style="padding-top:65px;">
			<div class="panel panel-default">
					<!-- 头部开始 -->
	        <div class="panel-heading">
			  <i class="glyphicon glyphicon-list"></i>
			  编辑
			</div>
			<!-- 头部结束 -->
				<div class="panel-body">
						<div class="row" >	
							<div class="col-md-4" >
								<div class="menu-tree">
									<div class="menu-buttons">
										<span class="refrsh-menu">刷新</span>
										<span class="add-menu">添加</span>
										<span class="remove-menu">删除</span>
									</div>
									<div class="tree"><ul id="tree" class="ztree"></ul></div>
								</div>
							</div>
							<div class="col-md-3" >
							<form id="baseForm" method="post" action="#" class="form-horizontal">	
								<div class="menu-contents" style="display: none;">
									<input type="hidden" name="id" id="collegeSpecialtyId"/>
									<input type="hidden" name="parentId" id="parentId"/>
									<input type="hidden" name="isCollege" id="isCollege"/>
									<input type="hidden" name="education" id="education"/>
									<input type="hidden" name="year"/>
										<div class="form-group">
											<label class="control-label col-sm-5" for="name">名称</label>
											<div class="col-sm-5">
												<input id="name" type="text" name="name"
													value="${model.name}" size="40" class="text required"
													minlength="2" maxlength="50">
											</div>
										</div>
										<br/>
										<div class="form-group">
											<label class="control-label col-sm-5" for="sort">顺序</label>
											<div class="col-sm-5">
												<input id="sort" type="text" name="sort"
													value="${model.sort}" size="40" class="text required"
													minlength="2" maxlength="50">
											</div>
										</div>
								</div>
							</form>
							<div class="form-group">
							    <label class="control-label col-md-1" for="visitorInfo_visitTime">年份</label>
									<div class="col-sm-5">
							      <div class="input-append ">
								    <input id="visitorInfo_visitTime" type="text" name="graduationYear" >
								  </div>
							    </div>
							  </div>      
							</div>
					   </div>
					   
						<div class="form-group">
							<div class="col-md-offset-1 col-md-11">
								<button id="submit" class="btn btn-default a-submit">
									保存
								</button>
								<button  onclick="history.back();"
									type="button" class="btn a-cancel">
									返回
								</button>
							</div>
						</div>
				
				</div>
			</div>
		</section>
		<!-- end of main -->
	</div>
</body>
</html>
