/**
 * Created by liudong on 2017/5/9.
 */
var line;
var logsNumber;
var pageNumber;
var logsPerPage;
var logs;
var currPage;
var pathPrefix;

function uploadImage() {
    var formData = new FormData($("#image_manage")[0]);
    formData.append("priority", $("#image_priority option:selected").val());
    $.ajax({
        url: '/beadhouse/imagemanage/uploadimage',
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            if (returndata === true) {
                alert("添加成功")
                window.location.reload();
                // getList();
                // $("#addNew").css("display", "none");
            } else {
                alert("添加失败，请重新上传")
            }

        },
        error: function (returndata) {
            alert("添加失败，请重新上传")
            //alert(returndata);
        }
    });
}
function getFileUrl(sourceId) {
    var url;
    if (navigator.userAgent.indexOf("MSIE") >= 1) { // IE
        url = document.getElementById(sourceId).value;
    } else if (navigator.userAgent.indexOf("Firefox") > 0) { // Firefox
        url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
    } else if (navigator.userAgent.indexOf("Chrome") > 0) { // Chrome
        url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
    }
    return url;
}
function displayPic(id) {
    var path = getFileUrl(id);
    var node = "<img src='" + path + "' height=180px width=180px>";
    $("#image_preview").html(node);
}

function displayPicUpdate(id) {
    var path = getFileUrl(id);
    var node = "<img src='" + path + "' height=180px width=180px>";
    $("#update_image_preview").html(node);
}

function getList() {
    $.get(
        "/beadhouse/imagemanage/list",
        function (data) {
            logs = data['imageManageList'];
            pathPrefix = data['imagePath'];
            changeLogsByPerPage();
        }
    )
}
$(document).ready(
    getList()
);

function displayImage(pageIndex) {
    var startIndex = (pageIndex - 1) * logsPerPage;
    var endIndex = Math.min((pageIndex) * logsPerPage, logs.length);
    $("#beadhouse_images").html("");
    var totalNode;
    for (var i = startIndex; i < endIndex; i++) {
        var log = logs[i];
        var node = '<tr id="log' + log["id"] + '">' +
            '<td>' + (i + 1) + '</td>' +
            '<td>' + log['imageDescription'] + '</td>' +
            '<td><img src="' + (pathPrefix + "/" + log['imagePath']) + '" height="60px">' + '</td>' +
            '<td>' + log['imagePriority'] + '</td>' +
            '<td>' +
            '<a href="#update_info" role="button" data-toggle="modal" onclick="displayexist(this)">' +
            '<i class="fa fa-pencil" val="1"></i></a> <a href="#delete" role="button" data-toggle="modal" onclick="changeLog(this)">' +
            '<i class="fa fa-trash-o"></i></a> </td> </tr>';
        totalNode += node;
    }
    $("#beadhouse_images").append(totalNode);
}
function displayexist(e) {
    var node = e.parentNode.parentNode;
    var childrenNode = node.children;
    line = String($(node).attr("id")).substr(3);
    $("#update_image_description").val($(childrenNode[1]).text());
    $("#update_image_priority").val($(childrenNode[3]).text());
}

function deleteLog() {
    $.get(
        "/beadhouse/imagemanage/delete",
        {
            imageId: line
        },
        function (data) {
            getList();
        }
    )
}
function updateImage() {
    var formData = new FormData($("#update_image_manage")[0]);
    formData.append("priority", $("#update_image_priority option:selected").val());
    formData.append("imageId", line);
    $.ajax({
        url: '/beadhouse/imagemanage/update',
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            if (returndata === true) {
                alert("添加成功")
                window.location.reload();
                // getList();
                // $("#addNew").css("display", "none");
            } else {
                alert("添加失败，请重新上传")
            }
        },
        error: function (returndata) {
            alert("添加失败，请重新上传")
            //alert(returndata);
        }
    });
}
function changeLog(e) {
    var node = e.parentNode.parentNode;
    line = String($(node).attr("id")).substr(3);
}

function removeWarning() {
    $("#add_warning").remove();
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
    displayImage(1);
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
    logs.sort(
        function (a, b) {
            return order === true ? a[type] - b[type] : b[type] - a[type];
        }
    );
    changeLogsByPerPage();
}