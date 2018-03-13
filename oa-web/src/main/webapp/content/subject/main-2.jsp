<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%@include file="/common/s.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/subject/main-skin.css"
	type="text/css" />
	<style type="text/css">
*{
box-sizing:border-box;}
html{
height:1000px
}
</style>
<script src="http://localhost:8080/rap.plugin.js?projectId=1"></script>
<div class="gas-station">
	<img src="${ctx}/upload/subject-top-bg.png" onError="defaultImg(this)"
		class="background">
	<div class="container">
		<div class="space"></div>
		<div class="space"></div>
		<div class="space"></div>
		<%-- 轮播图、动态 --%>
		<div class="fix-right-layout">
			<%-- 轮播图 --%>
			<div class="adaptive-left" unselectable="none"
				onselectstart="return false;">
				<div class="viwepager">
					<ul>
					</ul>
				</div>
			</div>
		</div>
		<div class="space"></div>
		<div class="space"></div>
		<div class="space"></div>
		<!-- 分隔符号*************************************************************** -->
		<div class="space"></div>
		<div class="space"></div>
		<%-- 关注、精华文章 --%>
		<div class="attention">
			<div class="title">
				<div class="more">
					<div class="fix-right more-right">
						<a
							href="subject-list.106100.402821f0515cbab601515cbce93f0002.html"
							target="_blank">更多>></a>
					</div>
				</div>
				最新<span style="color:yellow;font-size: 22px;">动态</span>
			</div>
			<div class="space"></div>
			<div class="content">
				<div class="attention-module2">
					<div class="adaptive-left" style="width: 70%;">
						<div class="module-title overflow-omit" >测试图片显示0324-1</div>
						<div class="module-content">两款设备有什么区别，看完之后你应该会有更深的了解。
							威锋网讯，“iPhone SE 就是将 iPhone 6s 的配置放进 4 ...</div>
					</div>
					<div class="fix-right img-wrapper">
						<img src="${ctx}/upload/1445505770748_6441.png">
					</div>
				</div>
				<div class="attention-module2">
					<div class="adaptive-left" style="width: 70%;">
						<div class="module-title overflow-omit" >电邮之父雷·汤姆林森离世：他让邮件有了@</div>
						<div class="module-content">据《商业内幕》杂志（Business
							Insider）报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森（Ray Tomlinson...</div>
					</div>
					<div class="fix-right img-wrapper">
						<img src="${ctx}/upload/1445505770748_6441.png">
					</div>
				</div>
				<div class="attention-module2">
					<div class="adaptive-left" style="width: 70%;">
						<div class="module-title overflow-omit" >111</div>
						<div class="module-content">111</div>
					</div>
					<div class="fix-right img-wrapper">
						<img src="${ctx}/upload/1445505770748_6441.png">
					</div>
				</div>
			</div>
		</div>
		<div class="space"></div>
		<div class="space"></div>
		<div class="news-module employ-guide">
			<div class="title">
				就业政策 <label class="a" >更多&gt;&gt;</label>
			</div>
			<div class="first-content">
				<img src="upload/1445505770748_6441.png">
				<div class="content-title overflow-omit"
					onclick="#">司机路边停车
					为躲处罚把别人罚单贴自己车上</div>
				<div class="content">“给自己贴罚单，真的不是传说”。1月2日，华西都市报客户端记者路过成都王府井附近一条小街，看见一辆白色小车正在路边停放，车主从车里下来后，“拍”给自己...</div>
			</div>
			<ul class="list">
				<li><b></b><a target="_blank"
					href="subjectNews-detail-402821ef51f5f9a40151f6da83540012.html">测试就业指导01测试就业指导01测试就业指导01测试就业指导01</a></li>
			</ul>
		</div>
		<div class="news-module activity-locale">
			<div class="title">
				校友信息 <label class="a" >更多&gt;&gt;</label>
			</div>
			<div class="first-content">
				<img src="${ctx}/upload/1445505770748_6441.png">
				<div class="content-title overflow-omit"
					onclick="">湖北十堰现恐龙鱼
					憨态可掬引游客围观</div>
				<div class="content">日前，湖北十堰人民公园引进了恐龙鱼，这名字听着霸气，但实际体型怪异，憨态可掬，萌态十足，引来不少游客参观。
					恐龙鱼的体形都是筒形，身体上有菱形硬鳞，...</div>
			</div>
			<ul class="list">
				<li><b></b><a target="_blank"
					href="subjectNews-detail-402821ef51f5f9a40151f6de9bb90019.html">测试活动现场01测试活动现场01测试活动现场01测试活动现场01</a></li>
			</ul>
		</div>
		<div class="news-module hostpot-topic">
			<div class="title">
				好书推荐 <label class="a" >更多&gt;&gt;</label>
					
			</div>
			<div class="first-content">
				<img src="${ctx}/upload/1445505770748_6441.png">
				<div class="content-title overflow-omit"
					onclick="">iPhone
					7疯狂曝光！真正的皇帝版</div>
				<div class="content">一直以来，很多人都把iPhone 6S Plus
					128GB版称作是“皇帝版”，因为它是全系售价中最贵的。但就是这个皇帝版，其实并没有拉开跟其它版本的...</div>
			</div>
			<ul class="list">
				<li><b></b><a target="_blank"
					href="subjectNews-detail-402821ef51f5f9a40151f6e00846001e.html">测试热点话题01测试热点话题01测试热点话题01</a></li>
			</ul>
		</div>

		<%-- <div class="news-header">
            <span class="font">新闻</span>
        </div> --%>
		<%-- 新闻 --%>
		<div class="news-wrapper">
			<div class="news-module employ-guide">
				<div class="title">
					<label class="a"
						onclick="window.open('subject-list.106104.${subject.id}.html')">更多>></label>
				</div>
			</div>
			<div class="news-module activity-locale">
				<div class="title">
					<label class="a"
						onclick="window.open('subject-list.106105.${subject.id}.html')">更多>></label>
				</div>
			</div>
			<div class="news-module hostpot-topic">
				<div class="title">
					<label class="a"
						onclick="window.open('subject-list.106106.${subject.id}.html')">更多>></label>
				</div>
			</div>
		</div>
		<div class="news-wrapper">
			<s:iterator value="#request.userDefinedTypes" status="stat" var="stu">
				<div class="news-module userDefinedType${id}" style="height: 60%;">
					<div class="title">
						${value} <label class="a"
							onclick="window.open('subject-list.${id}.${subject.id}.html')">更多>></label>
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

</script>
