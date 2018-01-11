/**
 * Created by beadhouse on 17-7-1.
 */
function displayCarouseldatas() {

}

function getCarousel() {
    $.get(
        "/index/carouseltop",
        function getData(datas) {
            displayCarouseldatas();
        }
    )
}

$(document).ready(
    function () {
        getBeadhouseInfo();
        getCarousel();
    }
);

function displayBeadhouseInfo(datas) {
    var node = "";
    for (var i = 0; i < datas.length; i++) {
        var data = datas[i];
        try {
            var beadhouseurl = "/beadhousesingle?beadhouseid=" + data['id'];
            var newNode = "<div class='row indexrowline' id='beadhouse" + data['id'] + "'>" +
                "<div class='col-sm-5'><img src='" + data['imageUrl'] + "'></div>" +
                "<div class='col-sm-6 col-sm-offset-1 contentouter'><div><span>名称：</span><span>" + data['fullName'] + "</span></div>" +
                "<div ><span>简介：</span><span class='descripitionoverflow'>" + data['briefDescription'] + "</span>" +
                "<div><a href='" + beadhouseurl + "'>详情介绍</a></div></div></div></div>";
            node += newNode;
        } catch (e) {
            console.log(e);
            continue;
        }
    }
    $("#beadhouse_content").html(node);
}

function getBeadhouseInfo() {
    $.get(
        "/index/beadhouseinfo",
        function GetDataSuccess(data) {
            displayBeadhouseInfo(data);
        }
    )
}