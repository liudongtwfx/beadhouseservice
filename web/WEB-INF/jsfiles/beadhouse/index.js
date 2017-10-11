/**
 * Created by liudong on 2017/3/8.
 */
$
$(document).ready(
    function () {
        $.get(
            "/beadhouse/getAdminUserName",
            function (data) {
                if (data['adminUserName'] == null || data['adminUserName'] == "") {
                    alert("请获得管理员权限");
                    window.location.href = "/beadhouse/login";
                } else {
                    $("#user-name").html(data['adminUserName']);
                    $("#beadhouse_name").val(data['beadhouseName']);
                    $("#beadhouse_brief").val(data['beadhouseDescription']);
                    $("#beadhouse_position").val(data['beadhouseLocation']);
                    $("#lng").val(data['beadhouseLNG']);
                    $("#lat").val(data['beadhouseLAT']);
                    $("#beadhouse_linksite").val(data['beadhouseLinksite']);
                    $("#beadhouse_contact").val(data['beadhouseContact'])
                    $("#beadhouse_rooms").val(data['beadhouseRooms'])
                    $("#beadhouse_totalbeds").val(data['beadhouseTotalBeds']);
                    $("#beadhouse_currenbeds").val(data['beadhouseCurrentBeds'])
                    $("#beadhouse_otherinfo").val(data['beadhouseOtherInfo'])
                    mapFunction();
                }
            }
        )
    }
)

function logout() {
    $.get(
        "/beadhouse/logout",
        function (data) {
            if (data == true) {
                window.location.href = "/beadhouse/login";
            }
        }
    )
}

function submitInfo() {
    var beadhousename = $("#beadhouse_name").val();
    var beadhouseinfo = $("#beadhouse_brief").val();
    var fullloation = $("#beadhouse_position").val();
    var lng = $("#lng").val();
    var lat = $("#lat").val();
    var linksite = $("#beadhouse_linksite").val();
    var contact = $("#beadhouse_contact").val()
    var rooms = $("#beadhouse_rooms").val()
    var totalbeds = $("#beadhouse_totalbeds").val();
    var currentbeds = $("#beadhouse_currenbeds").val()
    var other = $("#beadhouse_otherinfo").val()
    $.post(
        "/beadhouse/updateBeadhouseInfo",
        {
            beadhouseName: beadhousename,
            beadhouseInfo: beadhouseinfo,
            fullLocation: fullloation,
            lng: lng,
            lat: lat,
            linksite: linksite,
            contact: contact,
            rooms: rooms,
            totalbeds: totalbeds,
            currentbeds: currentbeds,
            other: other
        },
        function (data) {
            if (data == true) {
                alert("更新信息成功");
                window.location.refresh();
            } else {
                alert("更新失败");
            }
        }
    )
    $.post(
        "/uploadImage",
        {},
        function () {
        }
    )
}

function mapFunction() {
    var map = new BMap.Map("allmap");
    var lng = $("#lng").val();
    var lat = $("#lat").val();
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

$("li").click(function () {
        var href = $(this).find("a").attr('href');
        $('#content').empty();
        $('#content').load(href);
        //阻止跳转
        $(this).parents('li').addClass('active').siblings('li').removeClass('active');
        return false;
    }
);


