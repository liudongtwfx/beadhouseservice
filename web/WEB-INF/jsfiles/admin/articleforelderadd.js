function addArticlesForElder() {
    $.post(
        "/admin/articleforelder/add",
        {
            title: $("#article_title").val(),
            source: $("#article_source").val(),
            tag: $("#article_tag").val(),
            content: $("#article_content").val()
        },
        function getData(data) {
            if (data === true) {
                alert("添加成功");
            } else {
                alert("添加失败");
            }
        }
    )
}