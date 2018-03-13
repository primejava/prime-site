function formatTime(str){
	var date = new Date(str);
	var year = date.getFullYear();
	var month = (date.getMonth()+1)>9 ? (date.getMonth()+1) : ("0"+(date.getMonth()+1));
	var day = date.getDate()>9 ? date.getDate() : ("0"+date.getDate());
	var hours=date.getHours()>9 ? date.getHours() : ("0"+date.getHours());
	var minute=date.getMinutes()>9 ? date.getMinutes() : ("0"+date.getMinutes());
	var second=date.getSeconds()>9 ? date.getSeconds() : ("0"+date.getSeconds());
	return year + "-" + month + "-" + day +" "+hours+":"+minute+":"+second;
}

var showComment = function(obj,url,id){
	$(".show-comment").fadeOut("slow");
	var target=$(obj);
	var offset=target.offset();
	var div=$("<div class='show-comment'><ul></ul></div>");
	var top = $("<div class='top'></div>");
	var close=$("<label class='close'>关闭</label>");
	close.prependTo(top);
	top.prependTo(div);
	if(target.next().is("div")){
		div=target.next();
	}else{
		div.offset({top:offset.top+20,left:offset.left-div.width()/2});
		//div.css({top:offset.top+20,left:offset.left-150,position:"absolute"});
//		div.css({top:20,left:-300,position:"absolute"});
		target.after(div);
	}
	$.post(url,{id:id},function(datas){
		div.find("li").remove();
		$.each(datas,function(i,item){
			var li=$('<li><span class="context"></span><span class="time"></span></li>');
			li.find(".time").text(formatTime(item.time));
			li.find(".context").text(item.message);
			div.find("ul").append(li);
		});
		div.fadeIn("slow");
	},"json");
//	target.blur(function(){
//		div.fadeOut("slow");
//	});
//	div.click(function() {
//		$(this).fadeOut("slow");
//	});
	close.click(function(){
		div.fadeOut("slow");
	});
};