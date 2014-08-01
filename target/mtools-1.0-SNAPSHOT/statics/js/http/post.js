/**
 * Created with IntelliJ IDEA.
 * User: yfwangqing
 * Date: 14-4-11
 * Time: 上午11:18
 * 模拟POST GET请求
 */
$(document).ready(main);
function main() {
    $('.selectpicker').selectpicker({
        'selectedText': 'POST'
    });
    $('#sendBtn').click(function () {
        send();
    });
}
function send() {
    var url = $('#urlInput').val();
    var param = $('#paramInput').val();
    var methodType = $('#methodSel').val();
    if (url == null || '' == url) {
        alert("URL不能为空");
    }
    var paramObj = {};
    paramObj.url = url;
    paramObj.param = param;
    paramObj.methodType = methodType;
    var sendurl = "/http/send";
    AjaxUtil.post(sendurl, paramObj, function (resp) {
        if (resp == undefined || resp == null) {
            alert("HTTP请求无数据返回！");
            return;
        }
        if (resp.status == 1) {
            var data = resp.data;
            if (data != null) {
                $('#resUl').empty();
                var resLi = '<li class="list-group-item" >响应内容：<textarea style="width: 99%;min-height: 300px">';
                resLi +=  ToHtmlString(data.content);
                resLi +="</textarea></li>";
                resLi += '<li class="list-group-item">';
                resLi += "响应码：" + data.code;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "响应消息：" + data.message;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "content-type：" + data.contentType;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "method：" + data.method;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "默认端口号：" + data.defaultPort;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "文件名：" + data.file;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "主机名：" + data.host;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "路径：" + data.path;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "端口号：" + data.port;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "协议名称：" + data.protocol;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "查询部分：" + data.query;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "锚点：" + data.ref;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "用户信息：" + data.userInfo;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "内容编码：" + data.contentEncoding;
                resLi +="</li>";

                resLi += '<li class="list-group-item">';
                resLi += "连接超时：" + data.connectTimeout;
                resLi +="</li>";
                resLi += '<li class="list-group-item">';
                resLi += "读入超时：" + data.readTimeout;
                resLi +="</li>";
                $('#resUl').append(resLi);
            }
        }
        else {
            alert(resp.msg);
        }
    });
}
function ToHtmlString(htmlStr) {
    return toTXT(htmlStr).replace(/\<\;br[\&ensp\;|\&emsp\;]*[\/]?\>\;|\r\n|\n/g, "");
}
//Html结构转字符串形式显示
function toTXT(str) {
    var RexStr = /\<|\>|\"|\'|\&|　| /g
    str = str.replace(RexStr,
        function (MatchStr) {
            switch (MatchStr) {
                case "<":
                    return "<";
                    break;
                case ">":
                    return ">";
                    break;
                case "\"":
                    return "\"";
                    break;
                case "'":
                    return "'";
                    break;
                case "&":
                    return "&";
                    break;
                case " ":
                    return " ";
                    break;
                case "　":
                    return " ";
                    break;
                default:
                    break;
            }
        }
    )
    return str;
}
