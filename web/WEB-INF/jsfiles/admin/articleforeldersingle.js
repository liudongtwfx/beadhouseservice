function displayArticleData(data) {
    $("#article_title").val(data['title']);
    $("#article_source").val(data['source']);
    $("#article_tag").val(data['tag']);
    $("#article_content").val(data['content']);
}

$(document).ready(
    function getData() {
        $.get(
            "/admin/articleforelder/single",
            function getData(data) {
                displayArticleData(data);
            }
        )
    }
);