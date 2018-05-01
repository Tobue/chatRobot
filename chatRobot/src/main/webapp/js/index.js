
window.onload = function(){
	$('#loading-mask').fadeOut();
}
var onlyOpenTitle="欢迎使用";//不允许关闭的标签的标题

var _menus={
		"icon":"icon-sys",
		"menuid":"0",
		"menuname":"系统菜单",
		"menus":
			[
			 	{
			 		"icon":"icon-sys","menuid":"100","menuname":"什么值得买","menus":
					[

                        {"icon":"icon-search","menuid":"101","menuname":"爬取查询","url":"smzdm.html"},
                        {"icon":"icon-search","menuid":"102","menuname":"已发布","url":"smzdm-issueArticle.html"},
                        {"icon":"icon-search","menuid":"103","menuname":"未发布","url":"smzdm-issueNoArticle.html"},
                        {"icon":"icon-search","menuid":"104","menuname":"发布失败","url":"smzdm-issueFail.html"},
                        {"icon":"icon-sys","menuid":"105","menuname":"开启爬虫","url":"crawler/smzdm"}

					]
			 	},
                {
                    "icon":"icon-sys","menuid":"200","menuname":"买个便宜货","menus":
                    [
                        {"icon":"icon-search","menuid":"201","menuname":"爬取查询","url":"mgpyh.html"},
                        {"icon":"icon-search","menuid":"202","menuname":"已发布","url":"mgpyh-issueArticle.html"},
                        {"icon":"icon-search","menuid":"203","menuname":"未发布","url":"mgpyh-issueNoArticle.html"},
                        {"icon":"icon-search","menuid":"204","menuname":"发布失败","url":"mgpyh-issueFail.html"},
                        {"icon":"icon-sys","menuid":"205","menuname":"开启爬虫","url":"crawler/mgpyh"}
                    ]
                },
                {
                    "icon":"icon-sys","menuid":"300","menuname":"惠惠网","menus":
                    [
                        {"icon":"icon-search","menuid":"301","menuname":"爬取查询","url":"huihui.html"},
                        {"icon":"icon-search","menuid":"302","menuname":"已发布","url":"huihui-issueArticle.html"},
                        {"icon":"icon-search","menuid":"303","menuname":"未发布","url":"huihui-issueNoArticle.html"},
                        {"icon":"icon-search","menuid":"304","menuname":"发布失败","url":"huihui-issueFail.html"},
                        {"icon":"icon-sys","menuid":"305","menuname":"开启爬虫","url":"crawler/huihui"}
                    ]
                },
                {
                    "icon":"icon-sys","menuid":"400","menuname":"逛丢网","menus":
                    [
                        {"icon":"icon-search","menuid":"401","menuname":"爬取查询","url":"gdui.html"},
                        {"icon":"icon-search","menuid":"402","menuname":"已发布","url":"gdui-issueArticle.html"},
                        {"icon":"icon-search","menuid":"403","menuname":"未发布","url":"gdui-issueNoArticle.html"},
                        {"icon":"icon-search","menuid":"404","menuname":"发布失败","url":"gdui-issueFail.html"},
                        {"icon":"icon-sys","menuid":"405","menuname":"开启爬虫","url":"crawler/gdui"}
                    ]
                },
                {
                    "icon":"icon-sys","menuid":"500","menuname":"值值值","menus":
                    [
                        {"icon":"icon-search","menuid":"501","menuname":"爬取查询","url":"zhizhizhi.html"},
                        {"icon":"icon-search","menuid":"502","menuname":"已发布","url":"zhizhizhi-issueArticle.html"},
                        {"icon":"icon-search","menuid":"503","menuname":"未发布","url":"zhizhizhi-issueNoArticle.html"},
                        {"icon":"icon-search","menuid":"504","menuname":"发布失败","url":"zhizhizhi-issueFail.html"},
                        {"icon":"icon-sys","menuid":"505","menuname":"开启爬虫","url":"crawler/zhizhizhi"}
                    ]
                },
                {
                    "icon":"icon-sys","menuid":"600","menuname":"dealamCn","menus":
                    [
                        {"icon":"icon-search","menuid":"601","menuname":"爬取查询","url":"dealamCn.html"},
                        {"icon":"icon-search","menuid":"602","menuname":"已发布","url":"dealamCn-issueArticle.html"},
                        {"icon":"icon-search","menuid":"603","menuname":"未发布","url":"dealamCn-issueNoArticle.html"},
                        {"icon":"icon-search","menuid":"604","menuname":"发布失败","url":"dealamCn-issueFail.html"},
                        {"icon":"icon-sys","menuid":"605","menuname":"开启爬虫","url":"crawler/dealamCn"}
                    ]
                },
                {
                    "icon":"icon-sys","menuid":"600","menuname":"惠喵","menus":
                    [
                        {"icon":"icon-search","menuid":"601","menuname":"爬取查询","url":"huimao.html"},
                        {"icon":"icon-search","menuid":"602","menuname":"已发布","url":"huimao-issueArticle.html"},
                        {"icon":"icon-search","menuid":"603","menuname":"未发布","url":"huimao-issueNoArticle.html"},
                        {"icon":"icon-search","menuid":"604","menuname":"发布失败","url":"huimao-issueFail.html"},
                        {"icon":"icon-sys","menuid":"605","menuname":"开启爬虫","url":"crawler/huimao"}
                    ]
                },
                {
                    "icon":"icon-sys","menuid":"700","menuname":"slickdeals","menus":
                    [
                        {"icon":"icon-search","menuid":"701","menuname":"爬取查询","url":"slickdeals.html"},
                        {"icon":"icon-search","menuid":"702","menuname":"已发布","url":"slickdeals-issueArticle.html"},
                        {"icon":"icon-search","menuid":"703","menuname":"未发布","url":"slickdeals-issueNoArticle.html"},
                        {"icon":"icon-search","menuid":"704","menuname":"发布失败","url":"slickdeals-issueFail.html"},
                        {"icon":"icon-sys","menuid":"705","menuname":"开启爬虫","url":"crawler/slickdeals"}
                    ]
                }, {
                    "icon":"icon-sys","menuid":"800","menuname":"dealsea","menus":
                    [
                        {"icon":"icon-search","menuid":"801","menuname":"爬取查询","url":"dealsea.html"},
                        {"icon":"icon-search","menuid":"802","menuname":"已发布","url":"dealsea-issueArticle.html"},
                        {"icon":"icon-search","menuid":"803","menuname":"未发布","url":"dealsea-issueNoArticle.html"},
                        {"icon":"icon-search","menuid":"804","menuname":"发布失败","url":"dealsea-issueFail.html"},
                        {"icon":"icon-sys","menuid":"805","menuname":"开启爬虫","url":"crawler/dealsea"}
                    ]
                },
                {
                    "icon":"icon-sys","menuid":"900","menuname":"dealnews","menus":
                    [
                        {"icon":"icon-search","menuid":"901","menuname":"爬取查询","url":"dealnews.html"},
                        {"icon":"icon-search","menuid":"902","menuname":"已发布","url":"dealnews-issueArticle.html"},
                        {"icon":"icon-search","menuid":"903","menuname":"未发布","url":"dealnews-issueNoArticle.html"},
                        {"icon":"icon-search","menuid":"904","menuname":"发布失败","url":"dealnews-issueFail.html"},
                        {"icon":"icon-sys","menuid":"905","menuname":"开启爬虫","url":"crawler/dealnews"}
                    ]
                }, {
                    "icon":"icon-sys","menuid":"1000","menuname":"dealgogogo","menus":
                    [
                        {"icon":"icon-search","menuid":"1001","menuname":"爬取查询","url":"dealgogogo.html"},
                        {"icon":"icon-search","menuid":"1002","menuname":"已发布","url":"dealgogogo-issueArticle.html"},
                        {"icon":"icon-search","menuid":"1003","menuname":"未发布","url":"dealgogogo-issueNoArticle.html"},
                        {"icon":"icon-search","menuid":"1004","menuname":"发布失败","url":"dealgogogo-issueFail.html"},
                        {"icon":"icon-sys","menuid":"1005","menuname":"开启爬虫","url":"crawler/dealgogogo"}
                    ]
                },
                {
                    "icon":"icon-sys","menuid":"1000","menuname":"dealam","menus":
                    [
                        {"icon":"icon-search","menuid":"1001","menuname":"爬取查询","url":"dealam.html"},
                        {"icon":"icon-search","menuid":"1002","menuname":"已发布","url":"dealam-issueArticle.html"},
                        {"icon":"icon-search","menuid":"1003","menuname":"未发布","url":"dealam-issueNoArticle.html"},
                        {"icon":"icon-search","menuid":"1004","menuname":"发布失败","url":"dealam-issueFail.html"},
                        {"icon":"icon-sys","menuid":"1005","menuname":"开启爬虫","url":"crawler/dealam"}
                    ]
                },
                {
                    "icon":"icon-sys","menuid":"800","menuname":"旧数据","menus":
                    [
                        {"icon":"icon-search","menuid":"801","menuname":"清除数据","url":"delete.html"}

                    ]
                }
			 ]
		};




$(function(){	
	
	
	InitLeftMenu();
	tabClose();
	tabCloseEven();
	
	
})




//初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({animate:false,fit:true,border:false});
	var selectedPanelname = '';
	
	    $.each(_menus.menus, function(i, n) {
			var menulist ='';
			menulist +='<ul class="navlist">';
	        $.each(n.menus, function(j, o) {
				menulist += '<li><div ><a ref="'+o.menuid+'" href="#" rel="' + o.url + '" ><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div> ';
				/*
				if(o.child && o.child.length>0)
				{
					//li.find('div').addClass('icon-arrow');
	
					menulist += '<ul class="third_ul">';
					$.each(o.child,function(k,p){
						menulist += '<li><div><a ref="'+p.menuid+'" href="#" rel="' + p.url + '" ><span class="icon '+p.icon+'" >&nbsp;</span><span class="nav">' + p.menuname + '</span></a></div> </li>'
					});
					menulist += '</ul>';
				}
				*/
				menulist+='</li>';
	        })
			menulist += '</ul>';
	
			$('#nav').accordion('add', {
	            title: n.menuname,
	            content: menulist,
					border:false,
	            iconCls: 'icon ' + n.icon
	        });
	
			if(i==0)
				selectedPanelname =n.menuname;
	
	    });
	
	$('#nav').accordion('select',selectedPanelname);



	$('.navlist li a').click(function(){
		var tabTitle = $(this).children('.nav').text();

		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = $(this).find('.icon').attr('class');

		var third = find(menuid);
		if(third && third.child && third.child.length>0)
		{
			$('.third_ul').slideUp();

			var ul =$(this).parent().next();
			if(ul.is(":hidden"))
				ul.slideDown();
			else
				ul.slideUp();



		}
		else{
			addTab(tabTitle,url,icon);
			$('.navlist li div').removeClass("selected");
			$(this).parent().addClass("selected");
		}
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});





	//选中第一个
	//var panels = $('#nav').accordion('panels');
	//var t = panels[0].panel('options').title;
    //$('#nav').accordion('select', t);
}
//获取左侧导航的图标
function getIcon(menuid){
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				icon += o.icon;
			}
		 })
	})

	return icon;
}

function find(menuid){
	var obj=null;
	$.each(_menus.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				obj = o;
			}
		 });
	});

	return obj;
}

function addTab(subtitle,url,icon){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}


//绑定右键菜单事件
function tabCloseEven() {

    $('#mm').menu({
        onClick: function (item) {
            closeTab(item.id);
        }
    });

    return false;
}

function closeTab(action)
{
    var alltabs = $('#tabs').tabs('tabs');
    var currentTab =$('#tabs').tabs('getSelected');
	var allTabtitle = [];
	$.each(alltabs,function(i,n){
		allTabtitle.push($(n).panel('options').title);
	})


    switch (action) {
        case "refresh":
            var iframe = $(currentTab.panel('options').content);
            var src = iframe.attr('src');
            $('#tabs').tabs('update', {
                tab: currentTab,
                options: {
                    content: createFrame(src)
                }
            })
            break;
        case "close":
            var currtab_title = currentTab.panel('options').title;
            $('#tabs').tabs('close', currtab_title);
            break;
        case "closeall":
            $.each(allTabtitle, function (i, n) {
                if (n != onlyOpenTitle){
                    $('#tabs').tabs('close', n);
				}
            });
            break;
        case "closeother":
            var currtab_title = currentTab.panel('options').title;
            $.each(allTabtitle, function (i, n) {
                if (n != currtab_title && n != onlyOpenTitle)
				{
                    $('#tabs').tabs('close', n);
				}
            });
            break;
        case "closeright":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);

            if (tabIndex == alltabs.length - 1){
                alert('亲，后边没有啦 ^@^!!');
                return false;
            }
            $.each(allTabtitle, function (i, n) {
                if (i > tabIndex) {
                    if (n != onlyOpenTitle){
                        $('#tabs').tabs('close', n);
					}
                }
            });

            break;
        case "closeleft":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);
            if (tabIndex == 1) {
                alert('亲，前边那个上头有人，咱惹不起哦。 ^@^!!');
                return false;
            }
            $.each(allTabtitle, function (i, n) {
                if (i < tabIndex) {
                    if (n != onlyOpenTitle){
                        $('#tabs').tabs('close', n);
					}
                }
            });

            break;
        case "exit":
            $('#closeMenu').menu('hide');
            break;
    }
}


//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}




//设置登录窗口
function openPwd() {
    $('#w').window({
        title: '修改密码',
        width: 300,
        modal: true,
        shadow: true,
        closed: true,
        height: 160,
        resizable:false
    });
}
//关闭登录窗口
function closePwd() {
    $('#w').window('close');
}



//修改密码
function serverLogin() {
    var $newpass = $('#txtNewPass');
    var $rePass = $('#txtRePass');

    if ($newpass.val() == '') {
        msgShow('系统提示', '请输入密码！', 'warning');
        return false;
    }
    if ($rePass.val() == '') {
        msgShow('系统提示', '请在一次输入密码！', 'warning');
        return false;
    }

    if ($newpass.val() != $rePass.val()) {
        msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
        return false;
    }

    $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
        msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
        $newpass.val('');
        $rePass.val('');
        close();
    })
    
}

$(function() {

    openPwd();

    $('#editpass').click(function() {
        $('#w').window('open');
    });

    $('#btnEp').click(function() {
        serverLogin();
    })

	$('#btnCancel').click(function(){closePwd();})

   
});
/**
 * 登出
 */
function logout() {
    $.messager.confirm('确认','您确认要退出吗？',function(r){
        if(r){
            $.ajax({
                url:"user/logout",
                type:"post",
                dataType:"json",
                success:function (rtn) {
                    if(rtn.success){
                        location.href = "login.html";
                    }
                }
            });
        }
    });
}
