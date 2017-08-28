/**
 * Created by liudong on 2016/12/26.
 */
var usernameCanBeUsed = false;
var passwordCanBeUsed = false;
var emailAddressCanBeUsed = false;
var telephoneNumberCanBeUsed = false;
var pwr;
$('#userName').blur(function () {
    var name = $('#userName').val();
    if (name == "") {
        $("#name_info").text("用户名不能为空");
        $("#name_info").css("color", "red");
        return;
    }
    $.get(
        "/user",
        {name: name},
        function (data) {
            if (data == true) {
                //alert("用户名已存在");
                $("#name_info").text("用户名已存在");
                $("#name_info").css("color", "red");
                usernameCanBeUsed = false;

            } else {
                $("#name_info").text("该用户可以注册");
                $("#name_info").css("color", "green");
                usernameCanBeUsed = true;
            }
        }
    );
});
$('#userName').focus(function () {
    $("#name_info").css("color", "black");
    $("#name_info").text("请填写用户名");
});

$('#password').focus(function () {
    passwordCanBeUsed = false;
    if (document.getElementById("password_info") != null) {
        console.log("not null");
        $("#password_info").remove();
    }
    var newNode = "<div id='password_info'><p>密码必须在8位数字以上</p><p>必须包含数字和密码</p><p>不能包含空格</p></div>"
    $('#password').after(newNode);
    $("#password_info p").css("font-size", "10px");
});
$('#password').keyup(function () {
    var s = $("#password").val();
    pwr = computePasswordStrongness(s);
    $("#password_info").remove("p");
    if (pwr == "weak" || pwr == "medium" || pwr == "strong") {
        var newChild = "<div id='pwd_image'><div id='img1'></div><div id='img2'></div><div id='img3'></div></div>";
        $("#password_info").html(newChild);
    }
    switch (pwr) {
        case "notenough":
            $("#password_info").text("密码长度不够");
            break;
        case "notconsistdigtalornumber":
            $("#password_info").text("请至少包含数字或字母");
            break;
        case "weak":
            $("#img1").css("background-color", "red");
            $("#img2").css("background-color", "gray");
            $("#img3").css("background-color", "gray");
            var weakWords = "<div>: 弱</div>"
            $("#img3").after(weakWords);
            break;
        case "medium":
            $("#img1").css("background-color", "yellow");
            $("#img2").css("background-color", "yellow");
            $("#img3").css("background-color", "gray");
            var weakWords = "<div>: 中</div>"
            $("#img3").after(weakWords);
            break;
        case "strong":
            $("#img1").css("background-color", "green");
            $("#img2").css("background-color", "green");
            $("#img3").css("background-color", "green");
            var weakWords = "<div>: 强</div>"
            $("#img3").after(weakWords);
            break;
    }
});

function computePasswordStrongness(data) {
    var s = String(data);
    if (s.indexOf(" ") >= 0) {
        return "space";
    }
    if (s.length < 6) {
        return "notenough";
    }
    var numArray = [], digitalArray = [], otherArray = [];
    for (var i = 0; i < s.length; i++) {
        if (s[i] <= '9' && s[i] >= '0') {
            numArray.push(i);
            continue;
        }
        if (s[i] >= 'a' && s[i] <= 'z' || (s[i] >= 'A' && s[i] <= 'Z')) {
            digitalArray.push(i);
        } else {
            otherArray.push(i);
        }
    }
    if (digitalArray.length == 0 || numArray.length == 0) {
        return "notconsistdigtalornumber";
    }
    if (otherArray.length > 0 && digitalArray.length > 0 && numArray.length > 0) {
        return "strong";
    }
    var numSerial = true;
    var digitalSerial = true;
    for (var i = 1; i < numArray.length; i++) {
        if (numArray[i] - numArray[i - 1] != 1) {
            numSerial = false;
        }
    }
    for (var i = 1; i < digitalArray.length; i++) {
        if (digitalArray[i] - digitalArray[i - 1] != 1) {
            digitalSerial = false;
        }
    }
    if (digitalSerial || numSerial) {
        if (s.length < 12) {
            return "weak";
        } else {
            return "medium"
        }
    }
    return "strong";
}

$("#repassword").focus(function () {
    if (document.getElementById("repassword_info")) {
        $("#repassword_info").remove();
    }
    var newNode = "<div id='repassword_info'>请再次输入密码</div>"
    $('#repassword').after(newNode);
});

$("#emailAddress").focus(function () {
    if (document.getElementById("emailAddress_info")) {
        $("#emailAddress_info").remove();
    }
    var newNode = "<div id='emailAddress_info'>请输入邮箱</div>"
    $('#emailAddress').after(newNode);
});

$("#telephoneNumber").focus(function () {
    if (document.getElementById("telephoneNumber_info")) {
        $("#telephoneNumber_info").remove();
    }
    var newNode = "<div id='telephoneNumber_info'>请输入手机号</div>"
    $('#telephoneNumber').after(newNode);
});

$("#repassword").blur(function () {
    var a = $("#password").val();
    var b = $("#repassword").val();
    if (a != b) {
        $("#repassword_info").text("两次密码不一致");
        $("#repassword_info").css("color", "red");
        passwordCanBeUsed = false;
    } else {
        if (pwr == "weak" || pwr == "medium" || pwr == "strong") {
            $("#repassword_info").text("密码一致");
            $("#repassword_info").css("color", "green");
            passwordCanBeUsed = true;
        } else {
            $("#repassword_info").text("密码不符合要求");
            $("#repassword_info").css("color", "red");
            passwordCanBeUsed = false;
        }
    }
});
$('#emailAddress').blur(function () {
    var address = $('#emailAddress').val();
    if (address == "") {
        $("#emailAddress_info").text("邮箱不能为空");
        $("#emailAddress_info").css("color", "red");
        return;
    }
    $.get(
        "/user",
        {emailAddress: address},
        function (data) {
            if (data == true) {
                //alert("用户名已存在");
                $("#emailAddress_info").text("该邮箱已被注册");
                $("#emailAddress_info").css("color", "red");
                emailAddressCanBeUsed = false;
            } else {
                $("#emailAddress_info").text("该邮箱可以注册");
                $("#emailAddress_info").css("color", "green");
                emailAddressCanBeUsed = true;
            }
        }
    );
});
$('#telephoneNumber').blur(function () {
    var number = $('#telephoneNumber').val();
    if (number == "") {
        $("#telephoneNumber_info").text("手机号不能为空");
        $("#telephoneNumber_info").css("color", "red");
        return;
    }
    $.get(
        "/user",
        {telephoneNumber: number},
        function (data) {
            if (data == true) {
                $("#telephoneNumber_info").text("该手机号已被注册");
                $("#telephoneNumber_info").css("color", "red");
                telephoneNumberCanBeUsed = false;
            } else {
                $("#telephoneNumber_info").text("该手机号可以注册");
                $("#telephoneNumber_info").css("color", "green");
                telephoneNumberCanBeUsed = true;
            }
        }
    );
});

function canBeRegister() {
    if (usernameCanBeUsed && passwordCanBeUsed && emailAddressCanBeUsed && telephoneNumberCanBeUsed) {
        if (!$("#cb").is(':checked')) {
            alert("请同意协定");
            return false;
        }
        $.post(
            "/user/register",
            {
                userName: $("#userName").val(),
                password: $("#password").val(),
                emailAddress: $("#emailAddress").val(),
                telephoneNumber: $("#telephoneNumber").val()
            },
            function getData(data) {
                if (data === "success") {
                    alert("注册成功");
                    window.location.href = "/";
                } else {
                    alert("注册失败");
                    return false;
                }
            }
        )

    } else {
        alert("信息有误");
        return false;
    }
}