/**
 * Created by liudong on 2017/5/30.
 */

$(document).ready(
    function () {
        $.get(
            "/admin/elderpeople/list",
            {
                page: 0,
                size: $("#page_size:selected").val()
            },
            function (data) {
                console.log(data);
                display(data);
                $(".tablesorter").tablesorter();
            }
        )
    }
);

function display(originaldatas) {
    var nodes;
    var datas = originaldatas['content'];
    for (var i = 0; i < datas.length; i++) {
        var log = datas[i];
        var name = log['name']
        var gender = log['gender'];
        var idNumber = log['idNumber'];
        var birthDate = new Date(log['birthDate']).format("yyyy-MM-dd");
        var location = log['location'];
        var addTime = new Date(log['addTime']).format("yyyy-MM-dd");
        var contact = log['contact'];
        var node = '<tr id="log' + log["id"] + '">' +
            '<td>' + (i + 1) + '</td>' +
            '<td>' + name + '</td>' +
            '<td>' + gender + '</td>' +
            '<td>' + idNumber + '</td>' +
            '<td>' + birthDate + '</td>' +
            '<td>' + location + '</td>' +
            '<td>' + addTime + '</td>' +
            '</tr>';
        nodes += node;
    }
    $("#elderpeople").append(nodes);
}