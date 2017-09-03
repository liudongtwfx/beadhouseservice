/**
 * Created by liudong on 2017/6/2.
 */

var currPage;
var pageNumber;
var urlSuffix;
$(document).ready(
    function () {
        urlSuffix = 'list';
        changePage(1);
    }
);

function getList(page, args, endIndex) {
    $("#beadhouselist").html("");
    var postData = {};
    if (urlSuffix === "search") {
        var searchcontent = $("#search_region").val();
        if (searchcontent === null || searchcontent.length === 0) {
            return;
        }
        postData['searchContent'] = searchcontent;
    }
    postData['page'] = page - 1;
    postData["size"] = $("#page_size").find("option:selected").val();
    $.get(
        "/admin/articleforelder/" + urlSuffix,
        eval(postData),
        function (data) {
            displayData(data, page);
        }
    )
}

function display(originaldatas) {
    console.log(originaldatas);
    var nodes;
    var datas = originaldatas['list'];
    for (var i = 0; i < datas.length; i++) {
        var article = datas[i];
        var title = article['title'];
        var tag = article['tag'];
        var node = '<tr id="log' + article["id"] + '">' +
            '<td>' + ((currPage - 1) * $("#page_size").find("option:selected").val() + i + 1) + '</td>' +
            '<td><a href="/admin/articleforelder/singlepage?articleId=' + article['id'] + '">' + title + '</a></td>' +
            '<td>' + tag + '</td>' +
            '</tr>';
        nodes += node;
    }
    $("#articles_list").append(nodes);
}

function changePage(expectPage) {
    $("#articles_list").html("");
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
    $("#page_number").text(data['pageNumber']);
    $("#log_number").text(data['articleNumber']);
    pageNumber = data['pageNumber'];
    $(".tablesorter").tablesorter();
    $("#page_turning_list").html("");
    changePageCommon(currPage, pageNumber);
}

function searchArticles() {
    urlSuffix = "search";
    getList(1, "", "");
}