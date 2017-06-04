/**
 * Created by liudong on 2017/1/9.
 */
$(document).ready(
    function () {
        $.get(
            "/getUserName",
            function (data) {
                if (data == "") {
                    var newNode = '<a href="/user/register">[点击注册]</a> <a href="/user/login" >[点击登陆]</a>';
                    $("#register_login").html(newNode);
                } else {
                    $("#register_login").html("欢迎您：" + data +
                        "<a href='/user/usercenter'>   进入会员中心   </a> <a href='javascript:void(0)' onclick='logout()'>注销</a>");
                }
            }
        )
        /*if ($.cookie("userName") != null) {
         $("#register_login").html("欢迎您：" + $.cookie("userName") +
         "<a href='/user/usercenter'>   进入会员中心   </a> <a href='javascript:void(0)' onclick='logout()'>注销</a>");
         } else {
         var newNode = '<a href="/user/register">[点击注册]</a> <a href="/user/login" >[点击登陆]</a>';
         $("#register_login").html(newNode);
         }*/
    }
)
function logout() {
    var newNode = '<a href="/user/register"">[点击注册]</a> <a href="/user/login" >[点击登陆]</a>';
    $("#register_login").html(newNode);
    $.removeCookie("userName");
}