/**
 * Created by liudong on 2017/5/3.
 */
Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
};

function getElderInfo(e) {
    var id = e.textContent;
    if (id === null || id.length === 0) {
        return;
    }
    $("#elder_name").text("");
    $("#elder_name").text("");
    $("#elder_address").text("");
    $("#elder_contact").text("");
    $("#elder_family_contact").text("");
    $.get(
        "/beadhouse/elderInfo",
        {
            elderIdNum: id
        },
        function (data) {
            if (data === "" || data === null) {
                var node = "<span style='color: red'>此身份证号不存在，请先注册</span>";
                $("#elder_id").html(node);
                return;
            }
            $("#elder_id").text(id);
            $("#elder_name").text(data["elderName"]);
            $("#elder_address").text(data["elderAddress"]);
            $("#elder_contact").text(data["elderContact"]);
            $("#elder_family_contact").text(data["elderFamilyContact"]);
        }
    )
}