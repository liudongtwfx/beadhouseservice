/**
 * Created by liudong on 2017/6/2.
 */

var currPage;
var pageNumber;
$(document).ready(
    function () {
        changePage(1);
    }
);
function getList(page, size) {
    $.get(
        "/admin/beadhouse/list",
        {
            page: page - 1,
            size: size
        },
        function (data) {
            displayData(data, page);
        }
    )
}
function display(originaldatas) {
    var nodes;
    var datas = originaldatas['content'];
    for (var i = 0; i < datas.length; i++) {
        var log = datas[i];
        var fullname = log['fullName'];
        var location = log['fullLocation'];
        //var addTime = new Date(log['addTime']).format("yyyy-MM-dd");
        var node = '<tr id="log' + log["id"] + '">' +
            '<td>' + (i + 1) + '</td>' +
            '<td><a href="beadhouse/single?beadhouseId=' + log['id'] + '">' + fullname + '</a></td>' +
            '<td>' + location + '</td>' +
            '</tr>';
        nodes += node;
    }
    $("#beadhouselist").append(nodes);
}

function changePage(expectPage) {
    $("#beadhouselist").html("");
    var id = "page" + currPage;
    $("#" + id).removeClass();
    currPage = expectPage;
    getList(expectPage, $("#page_size").find("option:selected").val());
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

function displayData(data, page) {
    currPage = page;
    display(data);
    $("#page_number").text(data['totalPages']);
    $("#log_number").text(data['totalElements']);
    pageNumber = data['totalPages'];
    $(".tablesorter").tablesorter();
    $("#page_turning_list").html("");
    changePageCommon(currPage, pageNumber);
}
$("#province").change(
    function () {
        $.get(
            "/admin/beadhouse/provincelist",
            {
                provinceId: $("#province").find("option:selected").val()
            },
            function (data) {
                displayData(data);
            }
        )
    }
);