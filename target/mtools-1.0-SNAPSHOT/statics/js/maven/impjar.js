/**
 * Created with IntelliJ IDEA.
 * User: yfwangqing
 * Date: 14-4-14
 * Time: 下午1:42
 * Maven的本地JAR安装
 */
$(document).ready(main);
function main() {
    $('#installBtn').click(function () {
        install();
    });
}


function install() {
    var grp = $('#grpInput').val();
    var at = $('#atInput').val();
    var vs = $('#vsInput').val();
    var pt = $('#ptInput').val();
    var sendurl = "/maven/doimp";
    var paramObj = {};
    paramObj.groupId = grp;
    paramObj.artifactId = at;
    paramObj.version = vs;
    paramObj.jarPath = pt;
    AjaxUtil.post(sendurl, paramObj, function (resp) {
        if (resp == undefined || resp == null) {
            alert("HTTP请求无数据返回！");
            return;
        }
        if (resp.status != 1) {
            alert(resp.msg);
        }
    });
}