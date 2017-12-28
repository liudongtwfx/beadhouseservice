var insertdrag = 0;
var schema = ['test', 'demo'];
var tables = ['student', "school"];
var validateData = ['固定值', ''];
var validateType = ['等于', '不等于', '小于', '大于', '不小于', '不大于'];
var returnType = ['成功', '失败'];

function createInsertArea() {
    var node = '<div class="draggable"><div class="drag-inner panel panel-primary">' +
        '<div class="panel-heading handle"><h6 class="panel-title">增加</h6></div> ' +
        '<div class="panel-body"><form class="form-horizontal" role="form">' +
        '<div class="form-group"><label >数据库：</label><select>';
    for (var i in schema) {
        node += '<option value="' + schema[i] + '">' + schema[i] + '</option>'
    }
    node += '</select></div>';
    node += '<div class="form-group"><label >数据表：</label><select>';
    for (var i in tables) {
        node += '<option value="' + tables[i] + '">' + tables[i] + '</option>'
    }
    node += '</select></div>';
    node += '</form></div></div></div>';
    $("#events_area").append(node);
    createDraggable();
}

function createValidateArea() {
    var node = '<div class="draggable"><div class="drag-inner panel panel-primary">' +
        '<div class="panel-heading handle"><h6 class="panel-title">验证</h6></div> ' +
        '<div class="panel-body"><form class="form-horizontal" role="form">' +
        '<div class="form-group"><label >类型：</label><select>';
    for (var i in validateType) {
        node += '<option value="' + validateType[i] + '">' + validateType[i] + '</option>'
    }
    node += '</select></div>';
    node += '<div class="form-group"><label >数据类型：</label><select>';
    for (var i in validateData) {
        node += '<option value="' + validateData[i] + '">' + validateData[i] + '</option>'
    }
    node += '</select></div>';
    node += '</form></div></div></div>';
    $("#events_area").append(node);
    createDraggable();
}

function createReturnArea() {
    var node = '<div class="draggable"><div class="drag-inner panel panel-primary">' +
        '<div class="panel-heading handle"><h6 class="panel-title">返回</h6></div> ' +
        '<div class="panel-body"><form class="form-horizontal" role="form">' +
        '<div class="form-group"><label >类型：</label><select>';
    for (var i in returnType) {
        node += '<option value="' + returnType[i] + '">' + returnType[i] + '</option>'
    }
    node += '</select></div>';
    node += '<div class="form-group"><label >返回信息：</label><input class="form-control" type="text"></div>';
    node += '</form></div></div></div>';
    $("#events_area").append(node);
    createDraggable();
}

function createDraggable() {
    var $draggable = $(".draggable").draggabilly({
        containment: true,
        handle: '.handle'
    });
}