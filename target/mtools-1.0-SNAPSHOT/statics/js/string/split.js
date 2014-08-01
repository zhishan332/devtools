/**
 * Created with IntelliJ IDEA.
 * User: yfwangqing
 * Date: 14-4-10
 * Time: 下午4:31
 * 字符串拆分
 */
$(document).ready(main);
function main() {
    $("#strInput").keydown(function (e) {
        if (e.keyCode == 13) {
            split();
        }
    });
}
function split() {
    var str = $("#strInput").val();
    if (str != null) {

        str = str.replace(/^\s+|\s+$/g, "");
        var arr = str.split("");
        if (arr.length > 0) {
            $.blockUI({
                message: '<h2><img src=' + waitimg + '>努力计算中...</h2>'
            });
            $("#tbHeader").html("");
            $("#tbData").html("");
            $("#res").text("解析结果：字符串长度" + arr.length);
            var hd = "";
            var dt = "";
            for (var i = 0; i < arr.length; i++) {
                var ch = arr[i];
                if ((i + 1) % 20 == 1) {
                    hd += '<tr class="success">';
                    hd += "<td>" + (i + 1) + "</td>";
                    dt += "<tr><td>" + ch + "</td>";
                } else if ((i + 1) % 20 == 0) {
                    hd += "<td>" + (i + 1) + "</td></tr>";
                    dt += "<td>" + ch + "</td></tr>";
                    $("#tbData").append(hd);
                    $("#tbData").append(dt);
                    hd = "";
                    dt = "";
                } else {
                    hd += "<td>" + (i + 1) + "</td>";
                    dt += "<td>" + ch + "</td>";
                }
                if (i == arr.length - 1) {
                    hd += "</tr>";
                    dt += "</tr>";
                    $("#tbData").append(hd);
                    $("#tbData").append(dt);
                }
            }

            $.unblockUI();
        }

    }

}
