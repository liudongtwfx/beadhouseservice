/**
 * Created by liudong on 2017/6/9.
 */
var imageUrlBase;
function getData() {
    $.get(
        "/admin/beadhouse/single",
        function (data) {
            imageUrlBase = data['imageBasePath'] + '/' + data['baseInfo']['id'] + '/';
            displayBaseInfo(data['baseInfo']);
            displayImages(data['imageList'])
        }
    )
}

function displayBaseInfo(base) {
    try {
        $("#beadhouse_name").text(base['fullName']);

        var des = base['description'];
        var contents = des.split('\n');
        var node = "";
        for (var i = 0; i < contents.length; i++) {
            var temp = "<span>&nbsp&nbsp&nbsp&nbsp";
            temp += contents[i];
            temp += "</span><br>";
            node += temp;
        }
        $("#location").text(base['fullLocation']);
        $("#description").html(node);
        $("#lng").val(base['beadhouseLNG']);
        $("#lat").val(base['beadhouseLAT']);
    } catch (e) {
        console.log(e);
    }
}

function displayImages(imageData) {
    var newNode = "";
    for (var i = 0; i < imageData.length; i++) {
        var imagePath = imageUrlBase + imageData[i]['imagePath'];
        if (i % 3 === 0) {
            if (i !== 0) {
                newNode += "</div>";
                $("#beadhouse_images").append(newNode);
                newNode = "<div class='row inner_content'>";
            }
        }
        var tempNode = "<div class='col-lg-4'>" +
            "<img src='" + imagePath + "' alt='养老院图片'>" +
            "<div class='image_desciption'><span>" + imageData[i]['imageDescription'] + "</span></p></div></div>";
        newNode += tempNode;
    }
    $("#beadhouse_images").append(newNode);
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