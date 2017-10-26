function applyForAuthorize() {
    var department = $("#applySelect").val();
    console.log(department);
    var applyReason = $("#auth_apply_reason").val();
    $.post(
        "/admin/personal/newapply",
        {
            department: department,
            applyReason: applyReason
        },
        function getReturnData(data) {

        }
    )
}