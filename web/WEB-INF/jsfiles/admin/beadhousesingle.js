/**
 * Created by liudong on 2017/6/9.
 */
var imageUrlBase;
function getData() {
    $.get(
        "/admin/beadhouse/single",
        function (data) {
            imageUrlBase = data['imageBasePath'];
            displayBaseInfo(data['baseInfo']);
            displayImages(data['imageList'])
        }
    )
}

function displayBaseInfo(base) {
    $("#beadhouse_name").text(base['fullName']);
    $("#location").text(base['fullLocation']);
    $("#description").text(base['description']);
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
            "<img href='" + imagePath + "' alt='养老院图片'>" +
            "<div><span>图片描述</span><p><span>" + imageData[i]['imageDescription'] + "</span></p></div></div>";
        newNode += tempNode;
    }
    $("#beadhouse_images").append(newNode);
}