/**
 * Created by liudong on 2017/4/6.
 */
var line;
var logsNumber;
var pageNumber;
var logsPerPage;
var logs;
var currPage;

$(".update_info").click(function (e) {
    console.log("123");
    console.log(e);
});
function addNewGoOut() {
    var elderId = $("#id_number").val();
    var elderGoOutTime = $("#goout_time").val();
    var elderBackTime = $("#back_time").val();
    var elderGoOutReason = $("#goout_reason").val();
    if (elderId === "" || elderGoOutTime === "" || elderGoOutReason === "") {
        if ($("#add_warning").length > 0) {
            return;
        }
        var node = "<span style='color: red' id='add_warning'>请填写必要信息</span>";
        $("#new_log").append(node);
        return;
    }
    $.post(
        "/beadhouse/eldergoout/add",
        {
            elderId: elderId,
            elderGoOutTime: elderGoOutTime,
            elderBacktime: elderBackTime,
            elderGoOutReason: elderGoOutReason
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
            "/beadhouse/eldergoout/list",
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
    $("#update_goout_time").val($(childrenNode[2]).text());
    $("#update_back_time").val($(childrenNode[3]).text());
    $("#update_goout_reason").val($(childrenNode[4]).text());
}

function deleteLog() {
    $.get(
        "/beadhouse/eldergoout/delete",
        {
            goOutId: line
        },
        function (data) {
            console.log(data);
            window.location.reload();
        }
    )
}
function updateGoOut() {
    var elderId = $("#update_id_number").val();
    var elderGoOutTime = $("#update_goout_time").val();
    var elderBackTime = $("#update_back_time").val();
    var elderGoOutReason = $("#update_goout_reason").val();
    if (elderId === "" || elderGoOutTime === "" || elderGoOutReason === "") {
        if ($("#add_warning").length > 0) {
            return;
        }
        var node = "<span style='color: red' id='add_warning'>请填写必要信息</span>";
        $("#new_log").append(node);
        return;
    }
    $.post(
        "/beadhouse/eldergoout/update",
        {
            goOutId: line,
            elderId: elderId,
            elderGoOutTime: elderGoOutTime,
            elderBacktime: elderBackTime,
            elderGoOutReason: elderGoOutReason
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

function displaypage(pageIndex) {
    var startIndex = (pageIndex - 1) * logsPerPage;
    var endIndex = Math.min((pageIndex) * logsPerPage, logs.length);
    $("#goout_log").html("");
    for (var i = startIndex; i < endIndex; i++) {
        var log = logs[i];
        var gooutTime = new Date(log["goOutTime"]).format("yyyy-MM-dd  hh:mm");
        var backTime = log["backTime"] !== null ? new Date(log["backTime"]).format("yyyy-MM-dd  hh:mm") : "";
        var gooutReason = log["reason"] !== null ? log["reason"] : "";
        var node = '<tr id="log' + log["id"] + '">' +
            '<td>' + (i + 1) + '</td>' +
            '<td><a href="#elder_info" data-toggle="modal" onclick="getElderInfo(this)">' + log["elderIdNumber"] + '</a></td>' +
            '<td>' + gooutTime + '</td>' +
            '<td>' + backTime + '</td>' +
            '<td>' + gooutReason + '</td>' +
            '<td >' +
            '<a href="#update_info" role="button" data-toggle="modal" onclick="displayexist(this)">' +
            '<i class="fa fa-pencil" val="1"></i></a> <a href="#delete" role="button" data-toggle="modal" onclick="changeLog(this)">' +
            '<i class="fa fa-trash-o"></i></a> </td> </tr>'
        $("#goout_log").append(node);
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