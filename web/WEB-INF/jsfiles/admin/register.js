var usernameCanBeUsed = false;
var passwordCanBeUsed = false;
var emailAddressCanBeUsed = false;
var telephoneNumberCanBeUsed = false;
var pwr;
$('#adminUserName').blur(function () {
    var name = $('#adminUserName').val();
    if (name == "") {
        $("#name_info").text("用户名不能为空");
        $("#name_info").css("color", "red");
        return;
    }
    $.get(
        "/admin/register/administratorInfo",
        {adminName: name},
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
$('#adminUserName').focus(function () {
    $("#name_info").css("color", "black");
    $("#name_info").text("请填写用户名");
});

$('#password').focus(function () {
    passwordCanBeUsed = false;
    $("#password_info").html("");
    $("#password_fade").css("display", "block");
});
$('#password').blur(function () {
    $("#password_fade").css("display", "none");
});
$('#password').keyup(function () {
    var s = $("#password").val();
    $("#password_fade").css("display", "none");
    pwr = computePasswordStrongness(s);
    $("#password_info").remove("p");
    if (pwr == "weak" || pwr == "medium" || pwr == "strong") {
        var newChild = "<div id='pwd_image' class='row'><div id='img1'></div><div id='img2'></div><div id='img3'></div></div>";
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
    $('#repassword').text("请再次输入密码");
});

$("#emailAddress").focus(function () {
    $('#emailAddress_info').text("请输入邮箱");
});

$("#telephoneNumber").focus(function () {
    $('#telephoneNumber_info').text("请输入手机号");
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
        "/admin/register/administratorInfo",
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
        "/admin/register/administratorInfo",
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
            "/admin/register",
            {
                employeeId: $("#employId").val(),
                realName: $("#realName").val(),
                userName: $("#adminUserName").val(),
                password: $("#password").val(),
                emailAddress: $("#emailAddress").val(),
                telephoneNumber: $("#telephoneNumber").val(),
                department: $("#adminDepartment").val()
            },
            function (data) {
                if (data == true) {
                    alert("注册成功");
                    window.location.href = "/admin/index";
                    return true;
                } else {
                    alert("信息有误");
                    return false;
                }
            }
        )

    } else {
        alert("信息有误");
        return false;
    }
}