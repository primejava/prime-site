jQuery.fn.buildCollegeSelect=function(opts){
	var items= new Array();
	opts = jQuery.extend({
		collegeIds:"",
		specialtyIds:"",
		educationIds:"",
		collegeNames:"",
		educationNames:"",
		dataUrl:""
	},opts||{});
	
	if(opts.dataUrl){
        $.ajax({
             url: opts.dataUrl,
             async: false,
             dataType: "json",
             success: function(datas){
            	 $.merge(items,datas);
             }
        });
   };

	return this.each(function(){
		var selecteds=new Array();
		var target=jQuery(this);
		target.blur().attr("readonly","readonly").bind("click",function(){
			panel.fadeIn("slow");
		});

		var panel=$('<div class="selected-panel"><div class="tab"><ul></ul></div><div class="selected-contents"></div></div>');
		target.after(panel);

		var initTab=function(education){
			var li=$("<li><input type='hidden' name='educations'></li>");
			li.append(education.text).find("input").val(education.id);
			li.bind("click",function(){
				$("#education-"+panel.find("div.tab").find("li.selected").find("input").val()).hide();
				panel.find("div.tab").find("li.selected").removeClass("selected");
				$(this).addClass("selected");
				$("#education-"+$(this).find("input").val()).show();
			});
			panel.find("div.tab ul").append(li);
		}

		var initBtn=function(){
			var btn=$('<div class="selected-btn"><input type="button" value=" 确 定 " id="confirm"><input type="button" value=" 取 消 " id="cancel"></div>');
			panel.find("div.tab").append(btn);
			btn.find("#confirm").bind("click",confirms);
			btn.find("#cancel").bind("click",function(){
				panel.fadeOut("fast");
			});
		}
		var confirms=function(){
			var collegeIds=new Array();
			var specialtyIds=new Array();
			var educationIds=new Array();
			var collegeNames=new Array();
			var specialtyNames=new Array();
			var educationNames=new Array();
			if(selecteds.length==0){
				$.alert({icon: "icon-exclamation",labClose: "关闭",title: "提示",content: "请选择专业啊！"});
				return;
			}
			$.each(selecteds,function(i,input){
				specialtyIds.push(input.attr("id"));
				specialtyNames.push(input.attr("name"));
				collegeIds=$.grep(collegeIds,function(collegeId,i){
					return collegeId!=input.attr("parentid");
				});
				collegeIds.push(input.attr("parentid"));
				collegeNames=$.grep(collegeNames,function(collegeName,i){
					return collegeName!=input.attr("college");
				});
				collegeNames.push(input.attr("college"));
				educationIds=$.grep(educationIds,function(education,i){
					return education!=input.attr("education");
				});
				educationIds.push(input.attr("education"));
				educationNames=$.grep(educationNames,function(education,i){
					return education!=input.attr("educationname");
				});
				educationNames.push(input.attr("educationname"));
			});
			target.val(specialtyNames);
			$("#"+opts.specialtyIds).val(specialtyIds);
			$("#"+opts.specialtyNames).val(specialtyNames);
			$("#"+opts.collegeIds).val(collegeIds);
			$("#"+opts.collegeNames).val(collegeNames);
			$("#"+opts.educationIds).val(educationIds);
			$("#"+opts.educationNames).val(educationNames);
			panel.fadeOut("fast");
		}
		var initSearch=function(){
			var search=$('<div class="selected-search"><input id="search" type="text" class="" name="search" placeholder="关键字查询">'+
							'<span class="capion">最多可选10个专业</span></div>');
			panel.find("div.tab").after(search);
			search.find("#search").bind("keypress",searchKey);
		}
		var searchKey = function(){
			var value=$(this).val();
			if(!value){
				$("#template label").show();
				return;
			}
			$("#template label:contains("+value+")").show();
			$("#template label").not(":contains("+value+")").hide();
		}

		var buildDiv=function(){
			$.each(items,function(i,item){
				if(!item.parentId){
					initTab(item);
					educationTemplate(item);
				}
			});
			initCollegeSpecialty();
			$("div.tab").find("li:first").click();
		}

		var educationTemplate=function(education){
			var template=$("<div></div>");
			template.attr("id","education-"+education.id).hide();
			panel.find("div.selected-contents").append(template);
		}

		var initCollegeSpecialty= function(){
			$.each(items,function(i,item){
				if(!item.parentId){
					return true;
				}
				var template=$("<div id='template'><div class='title'></div><div class='checkboxs'></div></div>");
				template.find("div.title").text(item.text);
				template.find("div.checkboxs").attr("id","checkboxs-"+item.id+"-"+item.parentId);
				$("#education-"+item.parentId).append(template);
				if(!item.children){
					return true;
				}
				$.each(item.children,function(n,specialty){
					var checkbox=$("<label><input type='checkbox'></label>");
					$.each(specialty,function(name,val){
						if(name=="text"){
							name="name";
						}
						checkbox.find("input").attr(name,val);
					});
					template.find("div.checkboxs").append(checkbox);
					checkbox.find("input").attr("college",item.text);
					checkbox.find("input").attr("education",item.parentId);
					checkbox.find("input").attr("educationname",$("div.tab").find("input[value="+item.parentId+"]").parent().text());
					checkbox.find("input").after(specialty.text);
					checkbox.bind("change",checked);
				});
				
			});
			initValues();
		}
		var checked=function(){
			var input=$(this).find("input");
			selecteds=$.grep(selecteds,function(check,i){
				return check.attr("id")!=input.attr("id");
			});
			if(selecteds.length>9){
				input.prop("checked",false);
				$.alert({title: "提示",icon: "icon-exclamation",content: "最多可选10个专业"});
				return;
			}
			if(input.prop("checked")){
				selecteds.push(input);
			}
		}

		var initValues=function(){
			var specialty=$("#"+opts.specialtyIds);
			if(!specialty.val()){
				return;
			}
			var specialties=specialty.val().split(",");
			$.each(specialties,function(i,id){
				var input=panel.find("div.selected-contents").find("input#"+id);
				input.change();
			});
		}

		initBtn();
		initSearch();
		buildDiv();
	});
};