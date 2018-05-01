$(function() {
    var webSiteType = $("#issue").val();
    $('#dg').datagrid({
        url: 'article/queryPageIssueArticle?issueState=未发布&webSiteType='+webSiteType,
        method: 'get',
        striped: true,
        pagination: true,
        pageNumber: 1,
        nowrap: true,
        rownumbers:true,
        autoRowHeight:false,
        pageSize:30,
        pageList: [10,20,30,40,50],
       /* toolbar: [{
            iconCls: 'icon-edit',
            text:'批量发布',
            handler: function(){
                var sels = $("#dg").datagrid("getSelections");
                $.ajax({
                    url:"article/batchIssue",
                    data:{
                        dataAll:JSON.stringify(sels)
                    },
                    type:"post",
                    dataType:"json",
                    success:function (rtn) {
                        $.messager.alert("提示",rtn.message,"info",function () {
                            if(rtn.success){
                                $("#dg").datagrid("reload");
                            }else{
                                $("#dg").datagrid("reload");
                            }
                        })
                    }
                });
            }
        }],*/

        columns: [
            [
                /*{
                    field: 'ck',
                    checkbox: true
                },*/
                {
                    field: 'articleId',
                    title: '编号ID',
                    width: 50,
                },
                {
                    field:'collarTime',
                    title:'抓取时间',
                    width:100,
                },
               /* {
                    field:'-',
                    title:'TapDealing',
                    width:100,
                    formatter:function (value,row,index) {
                        var ret = '<a href="javascript:void(0)" onclick="issueArticle('+row.articleId+')">发布</a>';
                        return ret;
                    }
                },*/
                {
                    field:'issueState',
                    title:'发布状态',
                    width:100,
                },
                {
                    field: 'articleTitle',
                    title: '标题',
                    width: 200
                },
                {
                    field: 'articleSubTitle',
                    title: '副标题',
                    width: 250,
                },
                {
                    field: 'articleTag',
                    title: '标签',
                    width: 200
                },
                {
                    field: 'articleContent',
                    title: '内容',
                    width: 250,
                },
                {
                    field: 'articleImage',
                    title: '图片',
                    width: 150
                },
                {
                    field: 'shopBuyLink',
                    title: '去购买链接',
                    width: 100
                },
                {
                    field: 'articleLink',
                    title: '文章链接',
                    width: 100
                },
                {
                    field: 'articleType',
                    title: '导购/帖子',
                    width: 100
                },
                {
                    field:'^',
                    title:'操作',
                    width:100,
                    formatter: function(value,row,index){
                        var articleId = row.articleId;
                        var oper = '<a href="javascript:void(0)" onclick="deleteArticle('+articleId+')">删除</a>';
                        return oper;
                    }
                }
            ]
        ],
        onDblClickRow: function(rowIndex, rowData){   //双击一行,弹出窗口,获取数值
            $('#articleDiv').dialog({
                title: '文章详情',
                width: 950,
                height: 650,
                closed: false,
                cache: false,
                modal: true,
                minimizable:true,
                maximizable:true
            });

            $("#articleDiv").css("display","block");
            $("#articleId").val(rowData.articleId);
            $("#collarTime").val(rowData.collarTime);
            $("#articleTitle").val(rowData.articleTitle);
            $("#articleSubTitle").val(rowData.articleSubTitle);
            $("#articleTag").val(rowData.articleTag);
            $("#shopBuyLink").val(rowData.shopBuyLink);
            $("#articleContent").val(rowData.articleContent);
            $("#image-1").val(rowData.articleImage);
            var ab = rowData.articleImage;
            $("#image-2").attr("src",ab);
            $("#articleLink").val(rowData.articleLink);
            $("#issueState").val(rowData.issueState);
        },
    });
});

/**
 *	根据id删除文章
 */
function deleteArticle(articleId){
    $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
        console.log("你好");
        if (r){
            $.ajax({
                url:"article/deleteArticle?id=" + articleId,
                type:"put",
                dataType:"json",
                success:function (rtn) {
                    console.log(articleId);
                    $.messager.alert("提示",rtn.message,"info",function(){
                        if(rtn.success){
                            $('#dg').datagrid('reload');
                        }
                    });
                }
            });
        }
    });
}

/**
 *更新文章
 */
function updateArticle() {
    var articleId = $("#articleId").val();
    var collarTime = $("#collarTime").val();
    var articleTitle = $("#articleTitle").val();
    var articleSubTitle = $("#articleSubTitle").val();
    var articleTag = $("#articleTag").val();
    var shopBuyLink = $("#shopBuyLink").val();
    var articleContent = $("#articleContent").val();
    var articleImage = $("#image-1").val();
    var articleLink = $("#articleLink").val();
    var issueState = $("#issueState").val();

    var jsonObject = {
        articleId:articleId,
        collarTime:collarTime,
        articleTitle:articleTitle,
        articleSubTitle:articleSubTitle,
        articleTag:articleTag,
        shopBuyLink:shopBuyLink,
        articleContent:articleContent,
        articleImage:articleImage,
        articleLink:articleLink,
        issueState:issueState,
    };
    var jsonString = JSON.stringify(jsonObject);
    $.ajax({
        url:"article/updateArticle",
        type:"post",
        data:{
            article:jsonString
        },
        dataType:"json",
        success:function (rtn) {
                if(rtn.success){
                    $.ajax({
                        url:"article/issueArticle",
                        type:'get',
                        data:{
                            articleId:articleId
                        },
                        dataType:'json',
                        success:function (rtn) {
                            $.messager.alert("提示",rtn.message,"info",function(){
                                if(rtn.success){
                                    $("#articleDiv").dialog("close");
                                    $('#dg').datagrid('reload');
                                }else{
                                    $("#articleDiv").dialog("close");
                                    $('#dg').datagrid('reload');
                                }
                            });
                        }
                    });
                }else{
                    $.messager.alert("提示",rtn.message,"info")
                }
        }
    });
}

/**
 * 更新链接
 */
function updateBuyLink() {
    var shopBuyLink = $("#shopBuyLink").val();
    $.ajax({
        url:"article/updateBuyLink",
        type:"post",
        data:{
            shopBuyLink:shopBuyLink
        },
        dataType:"json",
        success:function (rtn) {
            $("#shopBuyLink").val(rtn.showBuyLink);
        }
    });
}
/**
 * 打开链接
 */
function openLink(){
    var shopBuyLink = $("#shopBuyLink").val();
    window.open(shopBuyLink);
}
function openLink2(){
    var articleImage = $("#articleImage").val();
    window.open(articleImage);
}

function openLink3(){
    var articleLink = $("#articleLink").val();
    window.open(articleLink);
}
/**
 * 截取链接
 */
function getLink() {
    var shopBuyLink1 = $("#shopBuyLink").val();
    var shopBuyLink = null;
    if(shopBuyLink1.indexOf("?")!=-1){
        if(shopBuyLink1.indexOf("tmall")!=-1) {
            var split = shopBuyLink1.split("&");
            shopBuyLink = split[0];
        }else if(shopBuyLink1.indexOf("taobao")!=-1){
            shopBuyLink = shopBuyLink1;
        }else{
            var split = shopBuyLink1.split("?");
            shopBuyLink = split[0];
        }
    }else{
        shopBuyLink = shopBuyLink1;
    }
    $("#shopBuyLink").val(shopBuyLink);
}

function changeImage() {
    var de  = $("#image-1").val();
    $("#image-2").attr("src",de);
}


/**
 *发布文章
 */
function issueArticle(articleId) {
    $.ajax({
        url:"article/issueArticle",
        type:'get',
        data:{
            articleId:articleId
        },
        dataType:'json',
        success:function (rtn) {
            $.messager.alert("提示",rtn.message,"info",function(){
                if(rtn.success){
                    $("#articleDiv").dialog("close");
                    $('#dg').datagrid('reload');
                }else{
                    $("#articleDiv").dialog("close");
                    $('#dg').datagrid('reload');
                }
            });
        }
    });
}