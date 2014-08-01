/**
 * Created with IntelliJ IDEA.
 * User: yfwangqing
 * Date: 14-4-10
 * Time: 下午4:31
 * json格式化
 */
$(document).ready(main);
function main() {
    $("#formatBtn").click(function (e) {
        format();
    });
}
function format() {
    var str = $("#jsonArea").val();
    if (str != null) {
        str = formatJson(str);
        $("#jsonArea").val(str);
        var sendurl = "/str/isjson";
        var paramObj={};
        paramObj.jsonStr=str;
        AjaxUtil.get(sendurl, paramObj, function (resp) {
            if (resp == undefined || resp == null) {
                alert("HTTP请求无数据返回！");
                return;
            }
            if (resp.status == 1) {
                var isJson = resp.data;
                if (isJson ==1) {
                    $('#okJson').show();
                    $('#noJson').hide();
                } else{
                    $('#okJson').hide();
                    $('#noJson').show();
                }
            }
        });
    }else{
        $('#okJson').hide();
        $('#noJson').show();
    }
}
function repeat(s, count) {
    return new Array(count + 1).join(s);
}

function formatJson(json) {
    var i = 0,
        il = 0,
        tab = " ",
        newJson = "",
        indentLevel = 0,
        inString = false,
        currentChar = null;
    for (i = 0, il = json.length; i < il; i += 1) {
        currentChar = json.charAt(i);
        switch (currentChar) {
            case '{':
            case '[':
                if (!inString) {
                    newJson += currentChar + "\n" + repeat(tab, indentLevel + 1);
                    indentLevel += 1;
                } else {
                    newJson += currentChar;
                }
                break;
            case '}':
            case ']':
                if (!inString) {
                    indentLevel -= 1;
                    newJson += "\n" + repeat(tab, indentLevel) + currentChar;
                } else {
                    newJson += currentChar;
                }
                break;
            case ',':
                if (!inString) {
                    newJson += ",\n" + repeat(tab, indentLevel);
                } else {
                    newJson += currentChar;
                }
                break;
            case ':':
                if (!inString) {
                    newJson += ": ";
                } else {
                    newJson += currentChar;
                }
                break;
            case ' ':
            case "\n":
            case "\t":
                if (inString) {
                    newJson += currentChar;
                }
                break;
            case '"':
                if (i > 0 && json.charAt(i - 1) !== '\\') {
                    inString = !inString;
                }
                newJson += currentChar;
                break;
            default:
                newJson += currentChar;
                break;
        }
    }
    return newJson;
}
