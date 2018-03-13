<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%@include file="/common/s.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/subject/main-skin.css"  type="text/css" />
<script src="http://localhost:8080/rap.plugin.js?projectId=1"></script>
<div class="gas-station">
    <img src="${ctx}/upload/subject-top-bg.png" onError="defaultImg(this)" class="background">
    <div class="container">
        <div class="space"></div>
        <div class="space"></div>
        <div class="space"></div>
        <%-- 轮播图、动态 --%>
        <div class="fix-right-layout">
            <%-- 轮播图 --%>
            <div class="adaptive-left" unselectable="none" onselectstart="return false;">
                <div class="viwepager">
                    <ul>
                    </ul>
                </div>
            </div>
        </div>
        <div class="space"></div>
        <div class="space"></div>
        <div class="space"></div>
        <!-- 上面OK -->
        
        <!-- 分隔符号*************************************************************** -->
        <div class="space"></div>
        <div class="space"></div>
                <%-- 关注、精华文章 --%>
            <div class="attention">
                <div class="title">
                    <div class="more">
                        <div class="fix-right more-right">
                            <a target="_blank" href="subject-list.106100.${subject.id}.html">更多>></a>
                        </div>
                    </div>
                </div>
                <div class="content">
                
                </div>
        </div>
        <div class="space"></div>
        <div class="space"></div>
        <%-- <div class="news-header">
            <span class="font">新闻</span>
        </div> --%>
        <%-- 新闻 --%>
        <div class="news-wrapper">
            <div class="news-module employ-guide">
                <div class="title">
                    <label class="a" onclick="window.open('subject-list.106104.${subject.id}.html')">更多>></label>
                </div>
            </div>
            <div class="news-module activity-locale">
                <div class="title">
                    <label class="a" onclick="window.open('subject-list.106105.${subject.id}.html')">更多>></label>
                </div>
            </div>
            <div class="news-module hostpot-topic">
                <div class="title">
                    <label class="a" onclick="window.open('subject-list.106106.${subject.id}.html')">更多>></label>
                </div>
            </div>
        </div>
        <div class="news-wrapper">
        	<s:iterator value="#request.userDefinedTypes"  status="stat"  var="stu">
        		<div class="news-module userDefinedType${id}" style="height: 60%;">
	                <div class="title">
	                    ${value}
	                    <label class="a" onclick="window.open('subject-list.${id}.${subject.id}.html')">更多>></label>
	                </div>
	            </div>
        	</s:iterator>
        </div>
    </div>
    <div class="space"></div>
    <div class="space"></div>
    <div class="space"></div>
</div>
<jsp:include page="gas-station-bottom.jsp" />
<script>
function bulidSlider() {
    var slider = $('.viwepager').smallslider({
        time:5000,
        switchTime:1000,
        onImageStop:false,
        showButtons:true,
        buttonPosition:'rightBottom',
        switchEffect:'ease',
        buttonOffsetY :	10,
        switchEase: 'easeOutQuad',
        switchPath: 'left',
        switchMode: 'hover',
        showIntro:false,
        showText:false,
        beforeSlide:function(index) {
            switchShaft(index);
        }
    });
    $(".shaft li").on("click", function() {
        slider.slideTo($(this).index());
    });
}
var animating = false;
function switchShaft(index) {
    if(animating) return;
    $(".shaft li:eq("+index+")").addClass("selected").siblings().removeClass("selected");
    switchViwepager(index);
}
function switchViwepager(index) {
    var prevSelected = $(".brief li.selected");
    var currentSelected = $(".brief li:eq("+index+")");
    currentSelected.css("left",prevSelected.index()>currentSelected.index()?"160px":"-160px");
    currentSelected.addClass("selected");
    currentSelected.animate({left: 0},1000, function() {currentSelected.css("left", "0");animating=false;});
    prevSelected.animate({left:prevSelected.index()>currentSelected.index()?"-160px":"160px"}, 1000, function() {prevSelected.removeClass("selected");animating=false;});
    animating = true;
}


<%-- 图片新闻 --%>
$.post("subject-findSubjectNews.html",
    {'page.pageLength':4, 'subject.id':'${subject.id}','subjectNews.type':'106101'},
    function(data) {
//     	alert(data.results.length);
        if(!data.results.length) return;
        $.each(data.results, function(index, obj) {
            var selected = index==0?" class='selected'":"";
            $(".viwepager ul").append("<li><img onclick='window.open(\"subjectNews-detail-"+obj.id+".html\")' src='${ctx}/"+obj.imageUrl+"'></li>");
            $(".viwepager-brief ul.brief").append("<li"+selected+"><div class='title' onclick='window.open(\"subjectNews-detail-"+obj.id+".html\")'>"+obj.title+"</div><div class='content'>"+formatContent(obj.contents.content, 120)+"</div></li>");
            $(".viwepager-brief ul.shaft").append("<li"+selected+"></li>");
        });
        bulidSlider();
    }, 'json'
);
<%-- 动态 --%>
$.post("subject-findSubjectNews.html",
    {'page.pageLength':8, 'subject.id':'${subject.id}','subjectNews.type':'106102'},
    function(data) {
        if(!data.results.length) return;
        $.each(data.results, function(index, obj) {
            $(".dynamic ul").append("<li><a target='_blank' href='subjectNews-detail-"+obj.id+".html'>"+obj.title+"</a></li>");
        });
    }, 'json'
);
<%-- 精华文章 --%>
$.post("subject-findSubjectNews.html",
    {'page.pageLength':4, 'subject.id':'${subject.id}','subjectNews.type':'106103'},
    function(data) {
        if(!data.results.length) return;
        $.each(data.results, function(index, obj) {
            var module = $("<div class='essence-module'><div class='title overflow-omit' onclick='window.open(\"subjectNews-detail-"+obj.id+".html\")'></div><div class='content'></div></div>");
            $(".title", module).html(obj.title);
            $(".content", module).html(formatContent(obj.contents.content, 50));
            $(".essence").append(module);

        });
    }, 'json'
);
<%-- 关注 --%>
$.post("subject-findSubjectNews.html",
    {'page.pageLength':3, 'subject.id':'${subject.id}','subjectNews.type':'106100'},
    function(data) {
        if(!data.results.length) return;
        $.each(data.results, function(index, obj) {
            var module = $("<div class='attention-module2'><div class='adaptive-left' style='width: 440px;'></div><div class='fix-right img-wrapper'></div></div>");
            $('.adaptive-left',module).append("<div class='module-title overflow-omit' onclick='window.open(\"subjectNews-detail-"+obj.id+".html\")'>"+obj.title+"</div>");
            $('.adaptive-left',module).append("<div class='module-content'>"+formatContent(obj.contents.content, 70)+"</div>");
            $('.fix-right',module).append("<img src='${ctx}/"+obj.imageUrl+"'>");
            $(".attention .content").append(module);
        });
    }, 'json'
);
<%-- 就业指导、活动现场、热点话题 --%>
loadNews($(".employ-guide"), "106104", true);
loadNews($(".activity-locale"), "106105", true);
loadNews($(".hostpot-topic"), "106106", true);
getUserDefinedType($("#userDefinedTypesStr").val());
function getUserDefinedType(typeStr) {
	var userDefinedType = typeStr.split(",");
	$.each(userDefinedType, function (index, type) {
		var mark = ".userDefinedType"+type;
		loadNews($(mark), type, false);
	});  
};
/* loadNews($(".userDefinedType"), ""); */
function loadNews(wrapper, type, showImage) {
    $.post("subject-findSubjectNews.html",
        {'page.pageLength':6, 'subject.id':'${subject.id}','subjectNews.type':type},
        function(data) {
            if(!data.results.length) return;
            var first = data.results[0];
            if (showImage) {
            	var firstContent = $("<div class='first-content'></div>");
                firstContent.append("<img src='"+first.imageUrl+"'>");
                firstContent.append("<div class='content-title overflow-omit' onclick='window.open(\"subjectNews-detail-"+first.id+".html\")'>"+first.title+"</div>");
                firstContent.append("<div class='content'>"+formatContent(first.contents.content, 75)+"</div>");
                wrapper.append(firstContent);
           	    wrapper.append("<ul class='list'></ul>");
	            $.each(data.results, function(index, obj) {
	                if(index==0) return;
	                $("ul", wrapper).append("<li><b></b><a target='_blank' href='subjectNews-detail-"+obj.id+".html'>"+obj.title+"</a></li>");
	            });
            } else {
            	wrapper.append("<ul class='list'></ul>");
            	$.each(data.results, function(index, obj) {
	                $("ul", wrapper).append("<li><b></b><a target='_blank' href='subjectNews-detail-"+obj.id+".html'>"+obj.title+"</a></li>");
	            });
            }
        },'json'
    );
}
function formatContent(text, limitLength) {
	if($.trim(text).length<=0) {
		return "";
	}
	var htmlcontents=text.replace(/<\/?.+?>/ig,"").replace(/&nbsp;/ig,"").replace(/\n[\s| ]*\r/ig,'\n');
	return htmlcontents.length>=limitLength?htmlcontents.substring(0, limitLength)+"...":htmlcontents;
}
function defaultImg(ele) {
    if($(ele).attr("src")!="${teacher}/resource/images/subject-default-bg.png") {
        $(ele).attr("src", "${teacher}/resource/images/subject-default-bg.png");
    }
}
</script>
