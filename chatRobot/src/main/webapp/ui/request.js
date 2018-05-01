var url=location.search;
//http://localhost:8080/erp/updatePwd_reset.html?type=1&oper=add
//url=location.search=?type=1&oper=add
var Request = new Object();
if(url.indexOf("?")!=-1)
{
    var str = url.substr(1)
	//str = type=1&oper=add
    strs = str.split("&");
	//["type=1", "oper=add"]
    for(var i=0;i<strs.length;i++)
    {
        Request[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
		//type=1, split => ["type","1"]
		//unescape去掉url转码
		//Request["type"]="1";
    }
	//Request => {"type":"1", "oper":'add'}
}