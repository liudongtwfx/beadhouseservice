/**
 * Created by liudong on 2017/1/11.
 */
var idNumberCanbeUsed = false;
function elderRegister() {
    if (!idNumberCanbeUsed) {
        alert("请输入正确身份证信息");
        return false;
    }
    var elderName = $("#elderName").val();
    var elderIdNumber = $("#elderIdNumber").val();
    var gender = new String($("input[name='gender']:checked").val());
    var year = $("#birthdate_year").val();
    var month = $("#birthdate_month").val();
    var day = $("#birthdate_day").val();
    var areaid = $("#county").val();
    var date = year + "-" + month + "-" + day;
    var postData = {};
    postData['elderName'] = elderName;
    postData['elderIdNumber'] = elderIdNumber;
    postData['elderGender'] = gender;
    postData['elderBirthdate'] = date;
    postData['elderAreaId'] = areaid;
    $.ajax({
        url: "/user/usercenter/elderRegister",
        data: postData,
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json",
        success: function () {
            window.location.href = "usercenter";
            return true;
        }
    })
    return false;
};
$(document).ready(
    function () {
        var start_year = 1900;
        var date = new Date();
        var this_year = date.getFullYear();
        var newnode = "<option value='" + start_year + "'>" + start_year + "年</option>";
        $("#birthdate_year").html(newnode);
        for (var i = start_year + 1; i <= this_year; i++) {
            newnode = "<option value='" + i + "'>" + i + "年</option>";
            $("#birthdate_year").append(newnode);
        }
        newnode = "<option value='1'>1月</option>";
        $("#birthdate_month").html(newnode);
        for (var i = 2; i <= 12; i++) {
            newnode = "<option value='" + i + "'>" + i + "月</option>";
            $("#birthdate_month").append(newnode);
        }
        newnode = "<option value='1'>1日</option>";
        $("#birthdate_day").html(newnode);
    }
);
$("#birthdate_day").focus(
    function () {
        $("#birthdate_day").html("");
        var newnode = "<option value='1'>1日</option>";
        $("#birthdate_day").html(newnode);
        var endDay = getDaysNumber();
        for (var i = 2; i <= endDay; i++) {
            newnode = "<option value='" + i + "'>" + i + "日</option>";
            $("#birthdate_day").append(newnode);
        }
    }
);
function getDaysNumber() {
    var days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    var year = $("#birthdate_year").val();
    var month = $("#birthdate_month").val();
    if (year % 100 != 0 && year % 4 == 0 || year % 400 == 0) {
        days[1] = 29;
    }
    return days[month - 1];
}

$('#select_province').focus(
    function () {
        $("#select_province").html("");
        for (var i in provinceData) {
            var newnode = "<option value=" + provinceData[i]['id'] + ">" + provinceData[i]['name'] + "</option>";
            $("#select_province").append(newnode);
        }
    }
);

function displayElderRegister() {
    if ($("#elder_register").css("display") == "none") {
        $("#elder_register").css("display", "block");
    } else {
        $("#elder_register").css("display", "none")
    }
}
function displayElderInformation() {
    if ($("#myApp").css("display") == "block") {
        $("#myApp").css("display", "none");
        return;
    }
    $("#myApp").css("display", "block");
    $.get(
        "/user/usercenter/elderlist",
        function (data) {
            $("[ng-app=myApp]").remove();
            var elderlist = data['list'];
            if (elderlist.length == 0) {
                alert("NO RESULT");
                return;
            }
            var newnode = '<div ng-app="myApp" ng-controller="customersCtrl">' +
                '<table id="elder_table"> <tr ng-repeat="x in names"> ' +
                '<th>姓名</th>' +
                '<th>身份证号</th>' +
                '<th>性别</th>' +
                '</tr> ' +
                '</table>' +
                '</div>';
            $("#elder_register").after(newnode);
            var app = angular.module('myApp', []);
            for (var a in elderlist) {
                var newnode = '<tr><td>' + elderlist[a]['name'] + '</td>' + '<td>' +
                    elderlist[a]['idNumber'] + '</td>' + '<td>' + elderlist[a]['gender'] + '</td></tr>';
                $("#elder_table").append(newnode);
            }
        }
    )
}

$("#elderIdNumber").focus(
    function () {
        $("[id$=id_info]").remove();
    }
);
$("#elderIdNumber").blur(
    function () {
        var idnumber = $("#elderIdNumber").val();
        var newNode;
        if (IdentityCodeValid(idnumber)) {
            $.get(
                "/user/usercenter/getIdNumber",
                {idNumber: idnumber},
                function (exist) {

                    if (exist == true) {
                        newNode = "<div id='wrong_id_info'>该身份证号输入有误</div>";
                    } else {
                        newNode = "<div id='correct_id_info'>该身份证号可以注册</div>";
                        idNumberCanbeUsed = true;
                    }
                    $("#elderIdNumber").after(newNode);
                }
            );
        } else {
            newNode = "<div id='wrong_id_info'>该身份证号输入有误</div>";
            $("#elderIdNumber").after(newNode);
            idNumberCanbeUsed = false;
        }
    }
);

//身份证号合法性验证
//支持15位和18位身份证号
//支持地址编码、出生日期、校验位验证
function IdentityCodeValid(code) {
    var city = {
        11: "北京",
        12: "天津",
        13: "河北",
        14: "山西",
        15: "内蒙古",
        21: "辽宁",
        22: "吉林",
        23: "黑龙江 ",
        31: "上海",
        32: "江苏",
        33: "浙江",
        34: "安徽",
        35: "福建",
        36: "江西",
        37: "山东",
        41: "河南",
        42: "湖北 ",
        43: "湖南",
        44: "广东",
        45: "广西",
        46: "海南",
        50: "重庆",
        51: "四川",
        52: "贵州",
        53: "云南",
        54: "西藏 ",
        61: "陕西",
        62: "甘肃",
        63: "青海",
        64: "宁夏",
        65: "新疆",
        71: "台湾",
        81: "香港",
        82: "澳门",
        91: "国外 "
    };
    var tip = "";
    var pass = true;

    if (!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)) {
        tip = "身份证号格式错误";
        pass = false;
    }

    else if (!city[code.substr(0, 2)]) {
        tip = "地址编码错误";
        pass = false;
    }
    else {
        //18位身份证需要验证最后一位校验位
        if (code.length == 18) {
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
            //校验位
            var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++) {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if (parity[sum % 11] != code[17]) {
                tip = "校验位错误";
                pass = false;
            }
        }
    }
    if (!pass) alert(tip);
    return pass;
}

$(document).ready(
    function () {
        getUserName(true);
    }
);