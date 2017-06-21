/**
 * Created by liudong on 17-6-19.
 */
var prefixUrl;
$(document).ready(
    function () {
        getCarouseList();
    }
);

function getCarouseList() {
    $.get(
        "/admin/carousel/userIndexPage/list",
        function (data) {
            prefixUrl = data["prefixUrl"];
            displayImageList(data["imageList"]);
        }
    )
}
function displayImageList(data) {
    var node;
    for (var i = 0; i < data.length; i++) {
        var tempNode = "<div class='col-lg-4' id='userhome" + data['id'] + "'>" +
            "<img src='" + (prefixUrl + "/" + data[i][path]) + "' alt='图片加载失败' class='square_image'>" +
            "<span>" + data['description'] + "</span></div>";
        node += tempNode;
    }
    $("#add_image_div").before(node);
    carouselImageCSS();
}

function carouselImageCSS() {
    var width = $(".square_image").css("width");
    $(".square_image").css("height", width);
}

function addNewCarousel() {
    $("#image_choose").click();
}

function getFileUrl(sourceId) {
    var url;
    if (navigator.userAgent.indexOf("MSIE") >= 1) { // IE
        url = document.getElementById(sourceId).value;
    } else if (navigator.userAgent.indexOf("Firefox") > 0) { // Firefox
        url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
    } else if (navigator.userAgent.indexOf("Chrome") > 0) { // Chrome
        url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
    }
    return url;
}
function displayPic(id) {
    var path = getFileUrl(id);
    $("#add_image").attr("src", path);
}

function submitNewCarousel() {
    var formData = new FormData($("#image_manage")[0]);
    formData.append("description", $("#description").val());
    $.ajax({
        url: '/admin/carousel/userIndexPage/upload',
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            if (returndata === true) {
                alert("添加成功")
                window.location.reload();
                // getList();
                // $("#addNew").css("display", "none");
            } else {
                alert("添加失败，请重新上传")
            }
        },
        error: function (returndata) {
            alert("添加失败，请重新上传")
            //alert(returndata);
        }
    });
}