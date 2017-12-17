var elementId;

function changeElementId(node) {
    elementId = node.getAttribute("value");
}

function getTables() {
    var schema = $("#schmeselect").find("option:selected").val();
    $.get(
        "/gettable/" + schema,
        function (data) {
            var node = '';
            for (var i = 0; i < data.length; i++) {
                node += "<option value='" + data[i] + "'>" + data[i] + "</option>";
            }
            $("#tableselect").html(node);
        }
    )
}

function getColumns() {
    var schema = $("#schmeselect").find("option:selected").val();
    var table = $("#tableselect").find("option:selected").val();
    $.get(
        "/getcolumns/" + schema + '/' + table,
        function (data) {
            var node = '';
            for (var i = 0; i < data.length; i++) {
                node += "<div class='col-lg-3 col-md-4 col-sm-6'><input name='selectedcolumns' class='col-sm-2' type='checkbox' value='" + data[i] + "'>" +
                    "<span class='col-sm-10'>" + data[i] + "</span></div>";
            }
            console.log(node);
            $("#columnsselect").html(node);
        }
    )
}

function addDataSource() {
    var columnsElement = document.getElementsByName("selectedcolumns");
    var columns = [];
    for (var i = 0; i < columnsElement.length; i++) {
        columns.push(columnsElement[i].getAttribute("value"));
    }
    $.post(
        "/admin/views/adddatasource/add",
        {
            columns: JSON.stringify(columns),
            elementId: elementId,
            schema: $("#schmeselect").find("option:selected").val(),
            table: $("#tableselect").find("option:selected").val()
        },
        function (data) {
            console.log(data);
        }
    )
}