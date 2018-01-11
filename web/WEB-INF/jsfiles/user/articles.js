/**
 * Created by liudong on 17-7-9.
 */

var pageNumber;
var totalArticleNumber;
var currPage;

function displayArticleList(articleList) {
    var node = "";
    for (var i = 0; i < articleList.length; i++) {
        var article = articleList[i];
        var addtime = new Date(article["addtime"]).format("yyyy-MM-dd");
        var tempNode = "<div class='article_line'><a class='col-lg-8' href='/articles/single?articleId=" + article['id'] + "'>" +
            article['title'] + "</a><span class='col-lg-4'>" + addtime + "</span></div>";
        node += tempNode;
    }
    $("#articles_list").html(node);
}

function displayPageTurning(currpage, pageNumber, articleNumber) {
    totalArticleNumber = articleNumber;
    pageNumber = pageNumber;
    currPage = currpage;
    changePageCommon(currpage, pageNumber);
}

function getArticleData(currpage) {
    $.get(
        "/articles/articlePage",
        {page: currpage},
        function getData(datas) {
            displayArticleList(datas['list']);
            displayPageTurning(currpage, datas['pageNumber'], datas['articleNumber']);
        }
    )
}

function searchArticleData(currpage) {
    $.get(
        "/articles/search",
        {
            page: currpage,
            content: $("#search_content").val()
        },
        function getData(datas) {
            displayArticleList(datas['list']);
            displayPageTurning(currpage, datas['pageNumber'], datas['articleNumber']);
        }
    )
}

$(document).ready(
    function () {
        getArticleData(0);
    }
);

function changePage(expectPage) {
    $("#articles_list").html("");
    var id = "page" + currPage;
    $("#" + id).removeClass();
    currPage = expectPage;
    getArticleData(expectPage);
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