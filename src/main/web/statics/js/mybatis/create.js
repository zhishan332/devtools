/**
 * Created with IntelliJ IDEA.
 * User: yfwangqing
 * Date: 14-4-15
 * Time: 下午7:31
 * To change this template use File | Settings | File Templates.
 */
$(document).ready(main);
function main() {
    $('#dbTypeSel').selectpicker({
        width: 300
    });
    $('#ctBtn').click(function () {
        createConfig();
    });
    $('#saveBtn').click(function () {
        saveDatabase();
    });
    $('#dbSel').change(function(){
        var dbid=$(this).children('option:selected').val();
        loadTables(dbid);
    });
    loadData();
}
function loadData() {
    var sendurl = "/mybatis/finddb";
    var paramObj = {};
    AjaxUtil.post(sendurl, paramObj, function (resp) {
        if (resp == undefined || resp == null) {
            alert("HTTP请求无数据返回！");
            return;
        }
        if (resp.status == 1) {
            var data = resp.data;
            var htmlstr = "";
            var dbSelStr = "";
            $('#tbData').empty();
            $('#dbSel').empty();
            var firstDbId = 0;
            for (var i = 0; i < data.length; i++) {
                var db = data[i];
                if (i == 0) {
                    firstDbId = db.id;
                }
                htmlstr += "<tr>";
                htmlstr += "<td><a href='javascript:void(0)' onclick='delDbsrc(" + db.id + ")'>删除</a></td>";
                htmlstr += "<td>" + (i + 1) + "</td>";
                htmlstr += "<td>" + db.dbType + "</td>";
                htmlstr += "<td>" + db.jdbcUrl + "</td>";
                htmlstr += "<td>" + db.userName + "</td>";
                htmlstr += "<td>" + db.password + "</td>";
                htmlstr += "</tr>";
                dbSelStr += "<option value='" + db.id + "'>";
                dbSelStr += db.jdbcUrl + " - " + db.userName;
                dbSelStr += "</option>";
            }
            $('#dbSel').append(dbSelStr);
            loadTables(firstDbId);
            $('#tbData').append(htmlstr);

        }
        if (resp.status != 1) {
            alert(resp.msg);
        }
    });
}
function loadTables(dbsrcId) {
    alert(dbsrcId);
    var sendurl = "/mybatis/findtables";
    var paramObj = {};
    paramObj.dbsrcId = dbsrcId;
    AjaxUtil.get(sendurl, paramObj, function (resp) {
        if (resp == undefined || resp == null) {
            alert("HTTP请求无数据返回！");
            return;
        }
        if (resp.status == 1) {
            var data = resp.data;
            var tbSelStr = "";
            $('#tbSel').empty();
            $("#tbSel").find("option").remove();
            for (var i = 0; i < data.length; i++) {
                tbSelStr += "<option value='" + data[i] + "'>";
                tbSelStr += data[i];
                tbSelStr += "</option>";
            }
            $('#tbSel').html(tbSelStr);
            $('#dbSel').selectpicker({
                width: 565
            });
        }
        if (resp.status != 1) {
            alert(resp.msg);
        }
    });
}
function delDbsrc(id) {
    if (confirm("确定删除吗（无法恢复的操作）")) {
        var paramObj = {};
        paramObj.dbid = id;
        var sendurl = "/mybatis/deldb";
        AjaxUtil.post(sendurl, paramObj, function (resp) {
            if (resp == undefined || resp == null) {
                alert("HTTP请求无数据返回！");
                return;
            }
            if (resp.status == 1) {
                alert("操作成功");
                loadData();
            }
            if (resp.status != 1) {
                alert(resp.msg);
            }
        });
    }
}
function createConfig() {
    var sendurl = "/mybatis/ctconfig";
    var dbid = $('#dbSel').val();
    var tablename = $('#tbSel').val();
    var path = $('#pathInput').val();
    var packagePath = $('#packInput').val();

    var paramObj = {};
    paramObj.dbid = dbid;
    paramObj.tablename = tablename;
    paramObj.path = path;
    paramObj.packagePath = packagePath;
    AjaxUtil.post(sendurl, paramObj, function (resp) {
        if (resp == undefined || resp == null) {
            alert("HTTP请求无数据返回！");
            return;
        }
        if (resp.status == 1) {
//            alert("成功生成");
        }
        if (resp.status != 1) {
            alert(resp.msg);
        }
    });
}
function saveDatabase() {
    var type = $('#dbTypeSel').val();
    var url = $('#urlInput').val();
    var user = $('#userInput').val();
    var pass = $('#passInput').val();
    var sendurl = "/mybatis/ctdb";
    var paramObj = {};
    paramObj.dbType = type;
    paramObj.jdbcUrl = url;
    paramObj.userName = user;
    paramObj.password = pass;
    $('#myModal').modal('hide')
    AjaxUtil.post(sendurl, paramObj, function (resp) {
        if (resp == undefined || resp == null) {
            alert("HTTP请求无数据返回！");
            return;
        }
        if (resp.status == 1) {
            loadData();
        }
        if (resp.status != 1) {
            alert(resp.msg);
        }
    });
}