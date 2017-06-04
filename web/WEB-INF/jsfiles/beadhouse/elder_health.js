/**
 * Created by liudong on 2017/4/6.
 */
var line;
var logsNumber;
var pageNumber;
var logsPerPage;
var logs;
var currPage;
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
function addNewHealth() {
    var elderId = $("#id_number").val();
    var elderbloodprelow = $("#bloodpressure_low").val();
    var elderbloodprehigh = $("#bloodpressure_high").val();
    var heartrate = $("#heart_rate").val();
    var healthstatus = $("#health_status").val();
    var nursingresult = $("#nursing_result").val();
    var examingtime = $("#examing_time").val();
    if (elderId === "" || healthstatus === "" || nursingresult === "") {
        if ($("#add_warning").length > 0) {
            return;
        }
        var node = "<span style='color: red' id='add_warning'>请填写必要信息</span>";
        $("#new_log").append(node);
        return;
    }
    $.post(
        "/beadhouse/elderhealth/add",
        {
            elderIdNumber: elderId,
            bloodPressureLow: elderbloodprelow,
            bloodPressureHigh: elderbloodprehigh,
            heartRate: heartrate,
            healthStatus: healthstatus,
            nursingResult: nursingresult,
            examingTime: examingtime
        },
        function (data) {
            //console.log(data);
            getList();
        }
    )
}


function getList() {
    $.get(
        "/beadhouse/elderhealth/list",
        function (data) {
            logs = data;
            changeLogsByPerPage();
        }
    )
}
$(document).ready(
    getList()
);

function displayexist(e) {
    var node = e.parentNode.parentNode;
    var childrenNode = node.children;
    line = String($(node).attr("id")).substr(3);
    $("#update_id_number").val($(childrenNode[1]).text());
    $("#update_bloodpressure_low").val($(childrenNode[2]).text());
    $("#update_bloodpressure_high").val($(childrenNode[3]).text());
    $("#update_heart_rate").val($(childrenNode[4]).text());
    $("#update_health_status").val($(childrenNode[5]).text());
    $("#update_nursing_result").val($(childrenNode[6]).text());
    $("#update_examing_time").val($(childrenNode[7]).text());
}

function deleteLog() {
    $.get(
        "/beadhouse/elderhealth/delete",
        {
            healthId: line
        },
        function (data) {
            console.log(data);
            window.location.reload();
        }
    )
}
function updateHealth() {
    var elderId = $("#update_id_number").val();
    var elderbloodprelow = $("#update_bloodpressure_low").val();
    var elderbloodprehigh = $("#update_bloodpressure_high").val();
    var heartrate = $("#update_heart_rate").val();
    var healthstatus = $("#update_health_status").val();
    var nursingresult = $("#update_nursing_result").val();
    var examingtime = $("#update_examing_time").val();
    if (elderId === "" || healthstatus === "" || nursingresult === "") {
        if ($("#add_warning").length > 0) {
            return;
        }
        var node = "<span style='color: red' id='add_warning'>请填写必要信息</span>";
        $("#new_log").append(node);
        return;
    }
    $.post(
        "/beadhouse/elderhealth/update",
        {
            healthId: line,
            elderIdNumber: elderId,
            bloodPressureLow: elderbloodprelow,
            bloodPressureHigh: elderbloodprehigh,
            heartRate: heartrate,
            healthStatus: healthstatus,
            nursingResult: nursingresult,
            examingTime: examingtime
        },
        function (data) {
            window.location.reload();
        }
    )
}
function changeLog(e) {
    var node = e.parentNode.parentNode;
    line = String($(node).attr("id")).substr(3);
}

function removeWarning() {
    $("#add_warning").remove();
}

function displaypage(pageIndex) {
    var startIndex = (pageIndex - 1) * logsPerPage;
    var endIndex = Math.min((pageIndex) * logsPerPage, logs.length);
    $("#health_log").html("");
    var totalNode;
    for (var i = startIndex; i < endIndex; i++) {
        var log = logs[i];
        var bloodPressureLow = log["lowBloodPressure"] !== null ? log["lowBloodPressure"] : "";
        var bloodPressureHigh = log["highBloodPressure"] !== null ? log["highBloodPressure"] : "";
        var heartRate = log["heartRate"] !== null ? log["heartRate"] : "";
        var healthStatus = log["healthStatus"] !== null ? log["healthStatus"] : "";
        var nursingResult = log["nursingResult"] !== null ? log["nursingResult"] : "";
        var examingTime = log["examingTime"] !== null ? new Date(log["examingTime"]).format("yyyy-MM-dd  hh:mm") : "";
        var node = '<tr id="log' + log["id"] + '">' +
            '<td>' + (i + 1) + '</td>' +
            '<td><a href="#elder_info" data-toggle="modal" onclick="getElderInfo(this)">' + log["elderIdNumber"] + '</a></td>' +
            '<td>' + bloodPressureLow + '</td>' +
            '<td>' + bloodPressureHigh + '</td>' +
            '<td>' + heartRate + '</td>' +
            '<td>' + healthStatus + '</td>' +
            '<td>' + nursingResult + '</td>' +
            '<td>' + examingTime + '</td>' +
            '<td >' +
            '<a href="#update_info" role="button" data-toggle="modal" onclick="displayexist(this)">' +
            '<i class="fa fa-pencil" val="1"></i></a> <a href="#delete" role="button" data-toggle="modal" onclick="changeLog(this)">' +
            '<i class="fa fa-trash-o"></i></a> </td> </tr>';
        totalNode += node;
    }
    $("#health_log").append(totalNode);
}
function changePage(expectPage) {
    var id = "page" + currPage;
    $("#" + id).removeClass();
    currPage = expectPage;
    displaypage(currPage);
    $("#page_turning_list").html("");
    var preNode = "<li id='front_page'><a href='javascript:void(0)' onclick='prevPage()'>&laquo;</a></li>";
    var nextNode = "<li id = 'next_page' ><a href = 'javascript:void(0)' onclick='nextPage()' >&raquo; </a ></li>";
    $("#page_turning_list").append(preNode);

    if (currPage > 3) {
        $("#page_turning_list").append("<li id='front_pages'><a href='javascript:void(0)' onclick='prevPage()'>...</a></li>");
    }
    $("#page_turning_list").append(nextNode);
    if (pageNumber <= 5) {
        for (var i = 0; i < pageNumber; i++) {
            var node = "<li id='page" + (i + 1) + "'><a href='javascript:void(0)' onclick='changePage(Number(this.text))'>" + (i + 1) + "</a></li>";
            $("#next_page").before(node);
        }
    } else {
        for (var i = Math.max(0, currPage - 3); i < currPage + 2 && i < pageNumber; i++) {
            var node = "<li id='page" + (i + 1) + "'><a href='javascript:void(0)' onclick='changePage(Number(this.text))'>" + (i + 1) + "</a></li>";
            $("#next_page").before(node);
        }
    }
    if (currPage <= pageNumber - 3) {
        $("#next_page").before("<li id='next_pages'><a href='javascript:void(0)' onclick='nextPage()'>...</a></li>");
    }
    id = "page" + currPage;
    $("#" + id).attr("class", "active");
}

function changeLogsByPerPage() {
    logsPerPage = $("#logs_per_page option:selected").val();
    logsNumber = logs.length;
    pageNumber = logsNumber % logsPerPage === 0 ? Math.floor(logsNumber / logsPerPage) : Math.floor(logsNumber / logsPerPage) + 1;
    $("#log_number").text(logs.length);
    $("#page_number").text(pageNumber);
    displaypage(1);
    $("#page_turning_list").html("");
    var preNode = "<li id='front_page'><a href='javascript:void(0)' onclick='prevPage()'>&laquo;</a></li>";
    var nextNode = "<li id = 'next_page' ><a href = 'javascript:void(0)' onclick='nextPage()' >&raquo; </a ></li>";
    $("#page_turning_list").append(preNode);
    $("#page_turning_list").append(nextNode);
    for (var i = 0; i < Math.min(5, pageNumber); i++) {
        var node = "<li id='page" + (i + 1) + "'><a href='javascript:void(0)' onclick='changePage(Number(this.text))'>" + (i + 1) + "</a></li>";
        $("#next_page").before(node);
    }
    if (pageNumber > 5) {
        $("#next_page").before("<li id='next_pages'><a href='javascript:void(0)' onclick='nextPage()'>...</a></li>");
    }
    currPage = 1;
    $("#page1").attr("class", "active");
}

function prevPage() {
    if (currPage > 1) {
        changePage(currPage - 1);
    }
}

function nextPage() {
    if (currPage < pageNumber) {
        changePage(currPage + 1);
    }
}

var idNumberOrdertype = true;
function sortLogs(type) {
    var order;
    if (type === "elderIdNumber") {
        idNumberOrdertype = !idNumberOrdertype;
        order = idNumberOrdertype;
    }
    logs.sort(
        function (a, b) {
            return order === true ? a[type] - b[type] : b[type] - a[type];
        }
    );
    changeLogsByPerPage();
}