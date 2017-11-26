var childlist;
var nodesinfo;
var rootid;
var innerhtml;
var editmode;
var htmlmode = '';
var currNodeNum = 0;
var deleted = [];
var alteredlist = [];
var parentid = 0;
var altered = false;
var updateOradd = 0;//0:no action,1:update,2:add child
var structnodetype = [
    'NULL',//不是结构节点
    'ARTICLE_INFO',
    'COPYRIGHT',
    'LOGO',
    'MAIN_ARTICLE',
    'NAVIGATION_BLOCK',
    'PAGETURNING',
    'TITLE'];
$(document).ready(
    function getNodesInfo() {
        $.get(
            "/admin/views/edit/getnodesinfo",
            function (data) {
                createBody(data);
                editmode = $("#mainhtmlstructure").html();
            }
        )
    }
);

function createBody(data) {
    childlist = data['childlist'];
    nodesinfo = data['nodesinfo'];
    rootid = data['rootid'];
    preOrderCreate(data['rootid']);
}

function preOrderCreate(parentid) {
    ++currNodeNum;
    for (var i = 0; i < childlist[parentid].length; i++) {
        var child = nodesinfo[childlist[parentid][i]];
        var node = decorateNode(child);
        $("#mainhtmlstructure").append(node);
        //arguments.callee(child['elementId'])
        preOrderCreate(child['elementId']);
    }
}

function getSpace(n) {
    var spaceNode = "<span >";
    for (var i = 0; i < n; i++) {
        spaceNode += '--|--';
    }
    spaceNode += '</span>';
    return spaceNode;
}

function decorateNode(child) {
    var node = "<div draggable='true' class='row element-row' id='" + child['elementId'] + "'>" + getSpace(child['depth']);
    node += "<lebal class='elementlebal' for='" + child['elmentId'] + "'>标签名:</lebal>";
    node += "<input type='text' value='" + child['tagName'] + "'>";
    node += "<span class='elementlebal''>文本值:</span>";
    node += "<input type='text' value='" + child['text'] + "'>";
    node += "<button class='btn btn-primary mybtn' data-toggle='modal' data-target='#selectnodetype' onclick='getParentId(this)'>创建孩子节点</button>";
    node += "<button class='btn-success btn mybtn' data-toggle='modal' data-target='#selectnodetype' onclick='updatenode(this)'>更新该结点</button>"
    node += "<button class='btn-danger btn mybtn' onclick='deleteThisNode(this)'>删除该结点</button>";
    node += "</div>";
    return node;
}

function decorateHTMLmode(child) {
    var node = document.createElement(child['tagName']);
    for (var key in child['attr']) {
        if (key === 'id') {
            node.setAttribute('preId', child['attr'][key])
        } else {
            node.setAttribute(key, child['attr'][key])
        }
    }
    node.text = child['text'];
    node.setAttribute("id", child['elementId']);
    return node;
}

function changetoEditmode() {
    htmlmode = $("#mainhtmlstructure").html();
    $("#mainhtmlstructure").html(editmode)
}

var createHTMLNode = function (parentid) {
    for (var i = 0; i < childlist[parentid].length; i++) {
        var child = nodesinfo[childlist[parentid][i]];
        var node = decorateHTMLmode(child)
        $("#" + child['parentId']).append(node)
        createHTMLNode(child['elementId'])
    }
};

function changetoHTMLmode() {
    editmode = $("#mainhtmlstructure").html();
    $("#mainhtmlstructure").html("");
    console.log(htmlmode)
    if (htmlmode === '' || altered) {
        var node = decorateHTMLmode(nodesinfo[rootid]);
        $("#mainhtmlstructure").append(node)
        createHTMLNode(rootid);
        htmlmode = $('#mainhtmlstructure').html();
    }
    altered = false;
    console.log(htmlmode);
    $("#mainhtmlstructure").html(htmlmode);
}

function getParentId(line) {
    var node = line.parentNode;
    parentid = node.getAttribute('id');
    $("#inputtext").val(nodesinfo[parentid]['text']);
    updateOradd = 2;
}

function updatenode(line) {
    var node = line.parentNode;
    parentid = node.getAttribute('id');
    $("#inputtext").val(nodesinfo[parentid]['text']);
    $("#inputattr").val(changeToString(nodesinfo[parentid]['attr']));
    updateOradd = 1;
}

function addNewChildOrUpdateNode() {
    if (updateOradd === 2) {
        addNewChild()
    }
    if (updateOradd === 1) {
        updateNode()
    }
    updateOradd = 0;
}

function updateNode() {
    var node = nodesinfo[parentid];
    node['attr'] = analysis();
    node['text'] = $("#inputtext").val();
    node['tagName'] = $("#inputtagname").val();
    node['depth'] = nodesinfo[parentid]['depth'] + 1;
    node['areaType'] = $("#struct_type").find("option:selected").text();
    alteredlist.push(parentid);
}

function addNewChild() {
    altered = true;
    var id = parentid;
    var presibing = childlist[id].length === 0 ? id : childlist[id][childlist[id].length - 1];
    var newid = ++currNodeNum;
    childlist[id].push(newid);
    childlist[newid] = [];
    var newnode = {};
    newnode['parentId'] = id;
    newnode['elementId'] = newid;
    newnode['attr'] = analysis();
    newnode['text'] = $("#inputtext").val();
    newnode['tagName'] = $("#inputtagname").val();
    newnode['depth'] = nodesinfo[id]['depth'] + 1;
    newnode['areaType'] = $("#struct_type").find("option:selected").text();
    nodesinfo[newid] = newnode;
    alteredlist.push(newid);
    var decnode = decorateNode(newnode);
    $("#" + presibing).after(decnode);
}

function deleteThisNode(line) {
    altered = true;
    var node = line.parentNode;
    var id = node.getAttribute('id');
    console.log(id);
    deleted.push(id);
    displayNone(id)
}

function displayNone(parentid) {
    $("#" + parentid).css("display", "none");
    for (var i = 0; i < childlist[parentid].length; i++) {
        displayNone(childlist[parentid][i]);
    }
}

function analysis() {
    var attr = {};
    var attrtext = $("#inputattr").val();
    if (typeof(attrtext) === 'undefined') {
        return attr;
    }
    var lines = attrtext.split("\n");
    for (var line in lines) {
        var kv = lines[line].split("=");
        console.log(kv);
        attr[kv[0]] = kv[1];
    }
    console.log(attr);
    return attr;
}

function changeToString(json) {
    var text = "";
    for (var key in json) {
        text += key + "=" + json[key] + '\n';
    }
    return text;
}

function submitChanges() {
    $.post(
        "/admin/views/edit/submitchanges",
        {
            nodesinfo: JSON.stringify(nodesinfo),
            childlist: JSON.stringify(childlist),
            altered: JSON.stringify(alteredlist),
            removed: JSON.stringify(deleted)
        },
        function callback(data) {
            var node = '';
            if (data === 'success') {
                node = "<span class='text-suceess'>成功更改视图文件</span>";
                $("#callbackinfo").html(node);
                setTimeout(gotoAddDataSourcePage(), 3 * 1000);
            } else {
                node = "<span class='text-warning'>" + data + "</span>";
            }
            $("#callbackinfo").html(node);
        }
    )
}

function gotoAddDataSourcePage() {
    location.href = "/admin/views/adddatasource";
}