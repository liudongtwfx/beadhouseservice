/**
 * Created by liudong on 2017/5/27.
 */

$(document).ready(
    function () {
        $.get(
            "/admin/vipuser/list",
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
        var userName = log['userName']
        var emailAddress = log['emailAddress'];
        var telephoneNumber = log['telephoneNumber'];
        var addTime = new Date(log['addTime']).format("yyyy-MM-dd");
        var node = '<tr id="log' + log["id"] + '">' +
            '<td>' + (i + 1) + '</td>' +
            '<td>' + userName + '</td>' +
            '<td>' + telephoneNumber + '</td>' +
            '<td>' + emailAddress + '</td>' +
            '<td>' + addTime + '</td>' +
            '</tr>';
        nodes += node;
    }
    $("#vipusers").append(nodes);
}