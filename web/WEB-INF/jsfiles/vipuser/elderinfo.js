function displayElderData(data) {
    if (data === null || data.length === 0) {
        var node = "<span>没有相关的老人信息</span>";
        $("#main_content").html(node);
        return;
    }
    var node = "";
    for (var i = 0; i < data.length; i++) {
        var info = data[i];
        var tempNode = "<div class='elderArea' id='elder" + info['elderId'] + "'>" +
            "<div class='lineinfo'><span class='labelinfo'>老人名称：</span><span class='detailInfo'>" + info['elderName'] + "</span></div>" +
            "<div class='lineinfo'><span class='labelinfo'>老人性别：</span><span class='detailInfo'>" + info['elderGender'] + "</span></div>" +
            "<div class='lineinfo'><span class='labelinfo'>身份证号：</span><span class='detailInfo'>" + info['elderIdNumber'] + "</span></div>" +
            "<div class='lineinfo'><span class='labelinfo'>出生年月：</span><span class='detailInfo'>" + info['elderBirthDate'] + "</span></div>" +
            "<div class='lineinfo'><span class='labelinfo'>联系方式：</span><span class='detailInfo'>" + info['elderContact'] + "</span></div>" +
            "</div>";
        node += tempNode;
    }
    $("#main_content").html(node);
}

function getElderData() {
    $.get(
        "/user/usercenter/getElderInfo",
        function returnData(data) {
            displayElderData(data);
        }
    );

}

$(document).ready(
    getElderData()
);
