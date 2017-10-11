function addLeisureGroup() {
    $.post(
        "/admin/leisuregroup/add",
        {
            title: $("#leisuregroup_title").val(),
            details: $("#leisuregroup_details").val()
        },
        function (data) {
            if (data === true) {
                alert("添加成功")
            }
        }
    );
}

$(document).ready(function () {
    $("#kv-explorer").fileinput({
        'theme': 'explorer',
        'uploadUrl': '/admin/leisuregroup/imagesupload',
        overwriteInitial: false,
        initialPreviewAsData: true,
        'language': 'zh'
    });
});