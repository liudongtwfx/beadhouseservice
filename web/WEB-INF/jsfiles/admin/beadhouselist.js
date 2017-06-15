/**
 * Created by liudong on 2017/6/2.
 */

var currPage;
var pageNumber;
var urlSuffix;
$(document).ready(
    function () {
        urlSuffix = 'list'
        changePage(1);
    }
);
function getList(page, args, endIndex) {
    $("#beadhouselist").html("");
    var postData = {};
    if (urlSuffix === "beadhousename") {
        var searchcontent = $("#search_region").val();
        if (searchcontent === null || searchcontent.length == 0) {
            return;
        }
        postData['searchContent'] = searchcontent;
    }
    else if (urlSuffix === "locationlist") {
        postData['provinceOrCityOrArea'] = args;
        postData['endIndex'] = endIndex;
    }
    postData['page'] = page - 1;
    postData["size"] = $("#page_size").find("option:selected").val();
    $.get(
        "/admin/beadhouse/" + urlSuffix,
        eval(postData),
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
            '<td>' + ((currPage - 1) * $("#page_size").find("option:selected").val() + i + 1) + '</td>' +
            '<td><a href="beadhouse/singlepage?beadhouseId=' + log['id'] + '">' + fullname + '</a></td>' +
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
    getList(expectPage);
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

function searchByName() {
    urlSuffix = "beadhousename";
    getList(1, "", "");
}