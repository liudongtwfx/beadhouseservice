/**
 * Created by liudong on 2017/6/2.
 */
function changePageCommon(expectPage, pageNumber) {
    var currPage = expectPage;
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