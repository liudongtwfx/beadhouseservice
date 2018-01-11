/**
 * Created by liudong on 2017/4/6.
 */
var line;
var logsNumber;
var pageNumber;
var logsPerPage;
var logs;
var currPage;
var livein_type = ['网上预定', '到院入住'];
var has_paid = ['是', '否'];
$(".update_info").click(function (e) {
    console.log("123");
    console.log(e);
});

function addNewCheckin() {
    var elderId = $("#id_number").val();
    var eldercheckin = $("#checkin_time").val();
    var elderleavetime = $("#leave_time").val();
    var leavereason = $("#leave_reason").val();
    var extra = $("#extra_info").val();
    var principle = $("#principle").val();
    if (elderId === "" || eldercheckin === "" || principle === "") {
        if ($("#add_warning").length > 0) {
            return;
        }
        var node = "<span style='color: red' id='add_warning'>请填写必要信息</span>";
        $("#new_log").append(node);
        return;
    }
    $.post(
        "/beadhouse/eldercheckin/add",
        {
            elderId: elderId,
            eldercheckin: eldercheckin,
            elderleavetime: elderleavetime,
            leavereason: leavereason,
            extra: extra,
            principle: principle
        },
        function (data) {
            console.log(data);
            window.location.reload();
        }
    )
}

$(document).ready(
    function () {
        $.get(
            "/beadhouse/eldercheckin/list",
            function (data) {
                logs = data;
                changeLogsByPerPage();
            }
        )
    }
);

function displayexist(e) {
    var node = e.parentNode.parentNode;
    var childrenNode = node.children;
    line = String($(node).attr("id")).substr(3);
    $("#update_id_number").val($(childrenNode[1]).text());
    $("#update_checkin_time").val($(childrenNode[2]).text());
    $("#update_leave_time").val($(childrenNode[3]).text());
    $("#update_leave_reason").val($(childrenNode[4]).text());
    $("#update_extra_info").val($(childrenNode[5]).text());
    $("#update_principle").val($(childrenNode[6]).text());
}

function deleteLog() {
    $.get(
        "/beadhouse/eldercheckin/delete",
        {
            checkinId: line
        },
        function (data) {
            console.log(data);
            window.location.reload();
        }
    )
}

function updateCheckin() {
    var elderId = $("#update_id_number").val();
    var eldercheckin = $("#update_checkin_time").val();
    var elderleavetime = $("#update_leave_time").val();
    var leavereason = $("#update_leave_reason").val();
    var extra = $("#update_extra_info").val();
    var principle = $("#update_principle").val();
    if (elderId === "" || eldercheckin === "" || principle === "") {
        if ($("#add_warning").length > 0) {
            return;
        }
        var node = "<span style='color: red' id='add_warning'>请填写必要信息</span>";
        $("#new_log").append(node);
        return;
    }
    $.post(
        "/beadhouse/eldercheckin/update",
        {
            checkinId: line,
            elderId: elderId,
            eldercheckin: eldercheckin,
            elderleavetime: elderleavetime,
            leavereason: leavereason,
            extra: extra,
            principle: principle
        },
        function (data) {
            console.log(data);
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

var getPaidMoney = function () {
    return 500 + Math.ceil(Math.random() * 50) * 10;
};

function displaypage(pageIndex) {
    var startIndex = (pageIndex - 1) * logsPerPage;
    var endIndex = Math.min((pageIndex) * logsPerPage, logs.length);
    $("#checkin_log").html("");
    for (var i = startIndex; i < endIndex; i++) {
        var log = logs[i];
        var checkintime = new Date(log["checkinTime"]).format("yyyy-MM-dd  hh:mm");
        var leavetime = log["leaveTime"] !== null ? new Date(log["leaveTime"]).format("yyyy-MM-dd  hh:mm") : "";
        var leaveReason = log["leaveReason"] !== null ? log["leaveReason"] : "";
        var extraContent = log["extraContent"] !== null ? log["extraContent"] : "";
        var liveinType = livein_type[Math.round(Math.random())];
        var hasPaid = has_paid[Math.round(Math.random())];
        var paidMoney = '';
        var trclass = 'success';
        if (hasPaid === '是') {
            paidMoney = getPaidMoney();
        } else {
            leavetime = '';
            trclass = 'danger';
        }
        var principleMan = log["principleMan"] !== null ? log["principleMan"] : "";
        var node = '<tr class="' + trclass + '" id="log' + log["id"] + '">' +
            '<td>' + (i + 1) + '</td>' +
            '<td><a href="#elder_info" data-toggle="modal" onclick="getElderInfo(this)">' + log["elderIdNumber"] + '</a></td>' +
            '<td>' + checkintime + '</td>' +
            '<td>' + liveinType + '</td>' +
            '<td>' + hasPaid + '</td>' +
            '<td>' + paidMoney + '</td>' +
            '<td>' + leavetime + '</td>' +
            '<td>' + leaveReason + '</td>' +
            '<td>' + extraContent + '</td>' +
            '<td>' + principleMan + '</td>' +
            '<td >' +
            '<a href="#update_info" role="button" data-toggle="modal" onclick="displayexist(this)">' +
            '<i class="fa fa-pencil" val="1"></i></a> <a href="#delete" role="button" data-toggle="modal" onclick="changeLog(this)">' +
            '<i class="fa fa-trash-o"></i></a> </td> </tr>'
        $("#checkin_log").append(node);
    }
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
var checkinTimeOrderType = true;
var leaveTimeOrderType = true;

function sortLogs(type) {
    var order;
    if (type === "elderIdNumber") {
        idNumberOrdertype = !idNumberOrdertype;
        order = idNumberOrdertype;
    }
    if (type === "checkinTime") {
        checkinTimeOrderType = !checkinTimeOrderType;
        order = checkinTimeOrderType;
    }
    if (type === "leaveTime") {
        leaveTimeOrderType = !leaveTimeOrderType;
        order = leaveTimeOrderType;
    }
    logs.sort(
        function (a, b) {
            return order === true ? a[type] - b[type] : b[type] - a[type];
        }
    );
    changeLogsByPerPage();
}