$(function() {
    var webSiteType = $("#issue").val();
    $('#dg').datagrid({
        url: 'article/queryPageIssueArticle?issueState=已发布&webSiteType='+webSiteType,
        method: 'get',
        striped: true,
        pagination: true,
        pageNumber: 1,
        nowrap: true,
        rownumbers:true,
        autoRowHeight:false,
        pageSize:30,
        pageList: [10,20,30,40,50],
        columns: [
            [
                {
                    field: 'articleId',
                    title: '编号ID',
                    width: 100,
                },
                {
                    field:'collarTime',
                    title:'抓取时间',
                    width:100,
                },
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
                    width: 200,
                },
                {
                    field: 'articleTag',
                    title: '标签',
                    width: 200
                },
                {
                    field: 'articleContent',
                    title: '内容',
                    width: 200,
                },
                {
                    field: 'articleImage',
                    title: '图片',
                    width: 150
                },
                {
                    field: 'shopBuyLink',
                    title: '去购买链接',
                    width: 150
                },
                {
                    field: 'articleLink',
                    title: '文章链接',
                    width: 150
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
                width: 700,
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
            $("#articleImage").val(rowData.articleImage);
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
        if (r){
            $.ajax({
                url:"article/deleteArticle?id=" + articleId,
                type:"put",
                dataType:"json",
                success:function (rtn) {
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
    var articleImage = $("#articleImage").val();
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
            $.messager.alert('提示',rtn.message,'info',function(){
                if(rtn.success){
                    $("#articleDiv").dialog("close");
                    $('#dg').datagrid('reload');
                }
            });
        }
    });
}
