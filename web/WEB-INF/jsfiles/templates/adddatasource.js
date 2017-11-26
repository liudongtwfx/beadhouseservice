function getTables() {
    var schema = $("#schmeselect").find("option:selected").val();
    $.get(
        "/gettable/" + schema,
        function (data) {
            var node = '';
            for (var i = 0; i < data.length; i++) {
                node += "<option value='" + data + "'>" + data + "</option>";
            }
            $("#tableselect").html(node);
        }
    )
}

function getColumns() {
    var schema = $("#schmeselect").find("option:selected").val();
    var table = $("#schmeselect").find("option:selected").val();
    $.get(
        "/getcolumns/" + table + '/' + schema,
        function (data) {
            var node = '';
            for (var i = 0; i < data.length; i++) {
                node += "<input class='col-lg-3 col-md-4 col-sm-6' type='checkbox' value='" + data + "'>" + data + "</option>";
            }
            $("#tableselect").html(node);
        }
    )
}

function addDataSource() {

}