/**
 * Created by beadhouse on 17-7-9.
 */
function displayArticle(article) {
    $("#article_title").html("<h1>" + article['title'] + "</h1>");
    $("#article_content").html(article['content']);
}
function getSingleArticle() {
    $.get(
        "/articles/singlearticle",
        function getData(article) {
            displayArticle(article);
        }
    )
}
$(document).ready(
    function docReady() {
        getSingleArticle();
    }
);