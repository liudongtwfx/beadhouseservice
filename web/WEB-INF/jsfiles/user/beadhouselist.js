/**
 * Created by liudong on 17-7-2.
 */
function displayBeadhouseInfo(datas) {
    var node = "";
    for (var i = 0; i < datas.length; i++) {
        var data = datas[i];
        try {
            var beadhouseurl = "/beadhousesingle?beadhouseid=" + data['id'];
            var newNode = "<div class='col-lg-6' id='beadhouse" + data['id'] + "'>" +
                "<a href='" + beadhouseurl + "'><img src='" + data['imageUrl'] + "'></a>" +
                "<div ><span>名称：</span><a href='" + beadhouseurl + "'><span>" + data['fullName'] + "</span></a></div>" +
                "<div ><span>简介：</span><span>" + data['briefDescription'] + "</span></div></div>";
            node += newNode;
        } catch (e) {
            console.log(e);
            continue;
        }
    }
    $("#beadhousearea_list").html(node);
}
function getBeadhouseListData() {
    $.get(
        "/beadhouselist/list",
        {
            page: 0,
            size: 18
        },
        function getData(data) {
            displayBeadhouseInfo(data);
        }
    )
}
$(document).ready(
    function () {
        getBeadhouseListData();
    }
);