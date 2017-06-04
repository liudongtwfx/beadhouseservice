/**
 * Created by liudong on 2017/5/27.
 */

function adminAuthority() {
    $.get(
        "/admin/getAdminUerName",
        {},
        function (data) {
            if (data === null || data === "") {
                alert("请以管理员的身份登入");
                window.location.href = "/admin/login";
                return false;
            } else {
                $("#user_name").text(data);
                return true;
            }
        }
    )

}

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