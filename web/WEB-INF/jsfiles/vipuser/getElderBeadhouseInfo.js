/**
 * Created by liudong on 17-7-4.
 */

var currentElderId;
var elderinfoLoadedMap = {};
function changeElder(t) {

}

//display (goout health accident) in one checkin
function displayDetailsData(datas, id) {
    var node = "<div>" + datas['beadhouseName'] + "</div>";
    var healthData = datas['elderHealth'];
    if (healthData === null || healthData.length === 0) {
        node += "<span>该期间没有护理记录</span>";
    } else {
        node += "<div class='inner_content tab_content'>" +
            "<table class='tablesorter table_area table-bordered'>" +
            "<thead>" +
            "<tr>" + "<th>血压(低)</th><th>血压(高)</th> <th>心率</th><th>健康状态</th><th>护理结果</th><th>护理时间</th></tr>" +
            "</thead>" +
            "<tbody>";
    }
    for (var i = 0; i < healthData.length; i++) {
        var data = healthData[i];
        console.log(data);
        var examingTime = new Date(data["examingTime"]).format("yyyy-MM-dd hh:mm");
        node += "<tr><td>" + data['lowBloodPressure'] + "</td>" +
            "<td>" + data['highBloodPressure'] + "</td>" +
            "<td>" + data['heartRate'] + "</td>" +
            "<td>" + data['healthStatus'] + "</td>" +
            "<td>" + data['nursingResult'] + "</td>" +
            "<td>" + examingTime + "</td>" +
            "</tr>"
    }
    node += "</tbody></table></div>";
    $("#collapse_body" + id).html(node);
    elderinfoLoadedMap[currentElderId][id] = true;
}

//get (goout health accident) in one checkin
function getDetailsData(t) {
    console.log(t);
    var id = $(t).attr('id').substr(9);
    if (elderinfoLoadedMap[currentElderId][id] === true) {
        return;
    }
    $.get(
        "/user/usercenter/checkinId",
        {'id': id},
        function getInfoBaseOnCheckInId(datas) {
            displayDetailsData(datas, id);
        }
    )
}

function displayElderList(list) {
    var node = "";
    currentElderId = list[0]['id'];
    for (var i = 0; i < list.length; i++) {
        var data = list[i];
        var id = data['id'];
        elderinfoLoadedMap[id] = {};
        var tempNode = "";
        tempNode += "<li id='elder" + data['id'] + "' ><a href='#' onclick='changeElder(this)'>" +
            data['name'] + "</a></li>";
        node += tempNode;
    }
    $("#elder_user_list").html(node);
    var first = "elder" + currentElderId;
    $("#" + first).addClass("active");
}

function displayElderBeadhouseData(datas) {
    var elderCheckin = datas['elderCheckin'];
    if (elderCheckin === null || elderCheckin.length === 0) {
        var node = "<span>该老人没有相关入住记录</span>";
        $("#elder_beadhouse_data_area").html(node);
        return;
    }
    var node = "";
    for (var i = 0; i < elderCheckin.length; i++) {
        var data = elderCheckin[i];
        var checkintime = new Date(data["checkinTime"]).format("yyyy-MM-dd");
        var leavetime = data["leaveTime"] !== null ? new Date(data["leaveTime"]).format("yyyy-MM-dd") : "";
        var tempNode = "<div class='panel panel-default'>" +
            "<div class='panel-heading'>" +
            "<h4 class='panel-title'>" +
            "<a data-toggle='collapse' id='checkinId" + data['id'] + "'data-parent='#accordion' href='#collapse" + data['id'] + "' onclick='getDetailsData(this)'>" +
            (checkintime + "&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp" + leavetime) + "<span style='display: none'>data['beadhouseId']</span>" +
            "</a></h4></div>" +
            "<div id='collapse" + data['id'] + "'class='panel-collapse collapse in'>" +
            "<div class='panel-body' id='collapse_body" + data['id'] + "'></div></div></div>";
        node += tempNode;
    }
    $("#accordion").html(node);
}

function getEldersData() {
    $.get(
        "/user/usercenter/elderlist",
        function (data) {
            displayElderList(data['list']);
            displayElderBeadhouseData(data['elderInfo']);
        }
    )
}

function displayEldersBeadhouseInfo() {
    $("#elder_beadhouseinfo_area").css('display', 'block')
    getEldersData();
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