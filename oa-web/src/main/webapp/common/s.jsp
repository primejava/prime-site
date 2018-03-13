<%@page language="java" pageEncoding="UTF-8" %>
    <!--[if lt IE 9]>
    <script type="text/javascript">
	//alert('您使用的浏览器版本太低，请使用IE9+，或者FireFox，Chrome浏览。');
	</script>
    <![endif]-->

    <link rel="shortcut icon" type="image/x-icon" href="${tenantPrefix}/favicon.ico" />
    <!-- bootstrap -->
    <link rel='stylesheet' href='${tenantPrefix}/resources/bootstrap/3.3.6/css/bootstrap.min.css' type='text/css' media='screen' />
	<style type="text/css">
.navbar-search .search-query {
  -webkit-border-radius: 15px;
     -moz-border-radius: 15px;
          border-radius: 15px;
}

.navbar .navbar-search .search-query {
    border-radius: 15px;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1) inset, 0 1px 0 rgba(255, 255, 255, 0.15);
    color: gray;
    transition: width 0.3s ease 0s;
    width: 100px;
}

.navbar .navbar-search .search-query:focus {
    transition: width 0.3s ease 0s;
    width: 200px;
}
	</style>

    <!-- html5 -->
    <!--[if lt IE 9]>
	<script src="${ctx}/resources/respond/1.4.2/respond.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/html5/html5shiv.js"></script>
    <![endif]-->

    <!-- jquery -->
    <script type='text/javascript' src='${tenantPrefix}/resources/jquery/1.11.3/jquery.min.js'></script>
    <script type="text/javascript" src="${ctx}/resources/jquery/jquery-migrate-1.2.1.min.js"></script>
	
<!-- bootstrap -->
    <script type='text/javascript' src='${tenantPrefix}/resources/bootstrap/3.3.6/js/bootstrap.min.js'></script>
    
    <!-- queryBuilder -->
    <link href="${ctx}/resources/jqueryBuilder_test/css/query-builder.default.min.css" rel="stylesheet">
    <script src="${ctx}/resources/jqueryBuilder_test/js/query-builder.standalone.min.js"></script>
    
    <!-- message -->
    <script type="text/javascript" src="${ctx}/resources/jquery-sliding-message/jquery.slidingmessage.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/mossle/js/table.js"></script>

    <!-- table and pager -->
    <script type="text/javascript" src="${ctx}/resources/pagination/pagination.js"></script>
    <script type="text/javascript" src="${ctx}/resources/table/table.js"></script>
    <script type="text/javascript" src="${ctx}/resources/table/messages_${locale}.js"></script>

    <!-- validater -->
    <script type="text/javascript" src="${ctx}/resources/jquery-validation/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/jquery-validation/additional-methods.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/jquery-validation/localization/messages_${locale}.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/jquery-validation/jquery.validate.css" />

    <!-- datepicker -->
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/bootstrap-datepicker/datepicker.css">
    <script type="text/javascript" src="${ctx}/resources/bootstrap-datepicker/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${ctx}/resources/bootstrap-datepicker/locales/bootstrap-datepicker.${locale}.js"></script>
    <link href="${ctx}/resources/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="${ctx}/resources/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.${locale}.js"></script>

	<!-- tree -->
    <link rel="stylesheet" href="${ctx}/resources/ztree/zTreeStyle/zTreeStyle.css" type="text/css" />
    <script type="text/javascript" src="${ctx}/resources/ztree/jquery.ztree.all-3.5.min.js"></script>

    <!-- ckeditor -->
    <script type="text/javascript" src="${ctx}/resources/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${ctx}/resources/ckfinder/ckfinder.js"></script>
    <!-- smallslide -->
	<script type="text/javascript" src="${ctx}/resources/smallslide/jquery.smallslider.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/smallslide/smallslide.css" />

	<!-- tablednd -->
    <script type="text/javascript" src="${ctx}/resources/jquery-tablednd/jquery.tablednd.min.js"></script>
    
    	<!-- tag -->
    <link rel="stylesheet" href="${ctx}/resources/bootstrap-tagsinput/bootstrap-tagsinput.css" type="text/css" />
    <script type="text/javascript" src="${ctx}/resources/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
    	<!-- tag -->
    <link rel="stylesheet" href="${ctx}/resources/tagsinput/jquery.tagsinput.css" type="text/css" />
    <script type="text/javascript" src="${ctx}/resources/tagsinput/jquery.checkbox.tags.js"></script>
    	
 
    <!-- echarts -->
<%--     <script type="text/javascript" src="${ctx}/resources/echarts/echarts.js"></script> --%>
    
    <link href="${ctx}/resources/comment/comment.css" rel="stylesheet">
    <script src="${ctx}/resources/comment/comment.js"></script>
    <style type="text/css">
#accordion .panel-heading {
	cursor: pointer;
}
#accordion .panel-body {
	padding:0px;
}
    </style>
    <script type="text/javascript">
$(function() {
    $.showMessage($('#m-success-message').html(), {
        position: 'top',
        size: '55',
        fontSize: '20px'
    });

    $('.datepicker').datepicker({
		language: '${locale}',
		format: 'yyyy-mm-dd',
        autoclose: true
	});

    $('.datetimepicker').datetimepicker({
		language: '${locale}',
        format: "yyyy-mm-dd hh:ii",
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left"
    });

    function widgetToggleContent() {
        var self = $(this);
        self.toggleClass('glyphicon-chevron-up');
        self.toggleClass('glyphicon-chevron-down');
        var widget = self.parents('.panel');
        var content = widget.find('.panel-body');
        content.toggle(200);
    }

    $(document).delegate('.panel .panel-heading .ctrl .glyphicon-chevron-up', 'click', widgetToggleContent);
    $(document).delegate('.panel .panel-heading .ctrl .glyphicon-chevron-down', 'click', widgetToggleContent);
});
    </script>
