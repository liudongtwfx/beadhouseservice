/**
 * Created by liudong on 2017/1/18.
 */
function authenticLogin(url) {
    var username = new String($('#userName').val());
    var pwd = new String($('#password').val());
    if (username.trim() == "") {
        $('#userName_info').text("用户名不能为空");
        return false;
    }
    if (pwd.trim() == "") {
        $("#password_info").text("密码不能为空");
        return false;
    }
    var emailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    var telephoneReg = /^1[3|4|5|7|8][0-9]{9}$/;
    var loginType = new String;
    if (emailReg.test(username)) {
        loginType = "emailAddress";
    } else {
        if (telephoneReg.test(username)) {
            loginType = "telephoneNumber";
        }
        else {
            loginType = "userName";
        }
    }
    var postValue = {};
    postValue[loginType] = username.toString();
    postValue['password'] = pwd.toString();
    console.log(postValue);
    $.post(
        url,
        eval(postValue),
        function (data) {
            console.log(data);
            if (data == true) {
                if (url === '/beadhouse/login') {
                    window.location.href = "/beadhouse/index";
                }
                if (url = '/admin/login') {
                    window.location.href = "/admin/index";
                }
                return true;
            } else {
                alert("用户名或密码不正确");
                return false;
            }
        }
    );
    return false;
}