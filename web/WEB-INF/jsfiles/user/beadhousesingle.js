/**
 * Created by liudong on 17-7-2.
 */
var imageurlPrefix;
function fillAlldata(datas) {
    imageurlPrefix = datas['imagePrefix'];
    fillbaseinfo(datas['baseinfo']);
    fillimagesinfo(datas['imagesinfo']);
}

function fillbaseinfo(baseinfo) {
    $("#mypos_name").text(baseinfo['fullName']);
    $("#beadhouse_name").text(baseinfo['fullName']);
    $("#beadhouse_location").text(baseinfo['fullLocation']);
    $("#input-21e").attr('value', baseinfo['score']);
    $("#beadhouse_brief").text(baseinfo['description']);
    mapFunction(baseinfo['lng'], baseinfo['lat']);
}

function fillimagesinfo(imagesinfo) {
    var node = "";
    for (var i = 0; i < imagesinfo.length; i++) {
        var data = imagesinfo[i];
        try {
            var tempNode = "<td><img src='" + (imageurlPrefix + data['imagePath']) + "'></td>"
            node += tempNode;
        } catch (e) {
            continue;
        }
    }
    $("#images_tr").html(node);
    imagesScroll();
}

function getBeadhouseSingleData() {
    $.get(
        "/beadhousesinglepage/getdata",
        function getData(datas) {
            if (datas === null || datas === false) {
                return;
            }
            fillAlldata(datas);
        }
    )
}
$(document).ready(
    function () {
        getBeadhouseSingleData();
    }
);

function imagesScroll() {
    var speed = 30;
    var demo = $("#images_area");
    var demo1 = $("#demo1");
    var demo2 = $("#demo2");
    demo2.html(demo1.html());
    function Marquee() {
        if (demo.scrollLeft() >= demo1.width())
            demo.scrollLeft(0);
        else {
            demo.scrollLeft(demo.scrollLeft() + 1);
        }
    }

    var MyMar = setInterval(Marquee, speed)
    demo.mouseover(function () {
        clearInterval(MyMar);
    });
    demo.mouseout(function () {
        MyMar = setInterval(Marquee, speed);
    });
}

function mapFunction(lng, lat) {
    var map = new BMap.Map("map_area");
    map.enableScrollWheelZoom(true);
    var point = new BMap.Point(lng, lat);
    map.centerAndZoom(point, 12);
    var geoc = new BMap.Geocoder();
    map.addOverlay(new BMap.Marker(point));
    map.addEventListener("click", function (e) {
        var pt = e.point;
        map.clearOverlays();
        geoc.getLocation(pt, function (rs) {
            var addComp = rs.addressComponents;
            //alert(addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber);
            var pos = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
            $("#lng").val(pt.lng);
            $("#lat").val(pt.lat);
            $("#beadhouse_position").val(pos);
            map.addOverlay(new BMap.Marker(pt));
        });
    });
}

function addComment() {
    if ($("#user_name").text().length === 0) {
        alert("请先登录")
        return false;
    }
    if ($("#comment_content").val().length === 0) {
        return false;
    }
    $.post(
        "/beadhousecomment/add",
        {
            content: $("#comment_content").val(),
            anonymous: $("#is_anonymous").is(':checked')
        },
        function response(data) {
            if (data === true) {
                alert("评论成功");
                window.location.reload();
            } else {
                alert("评论失败，请重新尝试");
                return false;
            }
        }
    )
}