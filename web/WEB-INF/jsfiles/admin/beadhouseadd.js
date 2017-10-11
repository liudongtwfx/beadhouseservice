function getInviteCodeRondom() {
    var code = generateMixed(8);
    $("#invitecode").val(code);
}

var chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

function generateMixed(n) {
    var res = "";
    for (var i = 0; i < n; i++) {
        var id = Math.ceil(Math.random() * 35);
        res += chars[id];
    }
    return res;
}

function submitInfo() {
    $.post(
        "/admin/beadhouse/add",
        {
            beadhousename: $("#beadhouse_name").val(),
            invitecode: $("#invitecode").val()
        },
        function getData(data) {
            if (data === true) {
                alert("添加成功")
            } else {
                alert("添加失败")
            }
        }
    )
}