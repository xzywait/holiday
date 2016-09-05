/**
 * Created by luxiaoping on 2016/8/12.
 */

var userDatagird;
var editRow = undefined;
$(function () {
    userDatagird = $('#userDatagrid').datagrid({
        url: '/user/searchUser',
        iconCls: 'icon-save',
        pagination: true,
        pageSize: 5,
        pageList: [5, 8, 10, 20, 30, 40],
        fit: true,
        fitColumns: true,
        idField: 'id',
        sortName: 'id',
        sortOrder: 'desc',
        frozenColumns: [[
            { field: 'ck', checkbox: true }
        ]],
        columns: [[
            {
                title: '编号',
                field: 'id',
                width: 100,
                sortable: true
            }, {
                title: '用户名',
                field: 'name',
                width: 100,
                sortable: true,
                editor: {
                    type: 'validatebox',
                    options: {
                        required: true
                    }
                }
            }, {
                title: '真实姓名',
                field: 'realName',
                width: 100,
                sortable: true,
                editor: {
                    type: 'validatebox',
                    options: {
                        required: false
                    }
                }
            }, {
                title: '密码',
                field: 'passwd',
                width: 100,
                editor: {
                    type: 'validatebox',
                    options: {
                        required: false
                    }
                }
            }, {
                title: '创建日日期',
                field: 'createDate',
                width: 100,
                sortable: true,
                editor: {
                    type: 'datetimebox',
                    options: {
                        required: true
                    }
                }
            }]],
        toolbar: [{
            text: '增加',
            iconCls: 'icon-add',
            handler: function () {
                if (editRow != undefined) {
                    userDatagird.datagrid('endEdit', editRow);
                }
                if (editRow == undefined) {
                    userDatagird.datagrid('removeEditor', 'createDate');//扩展的方法，让创建时间在添加时不可编辑

                    userDatagird.datagrid('insertRow', {
                        index: 0,
                        row: {}
                    });
                    userDatagird.datagrid('beginEdit', 0);
                    editRow = 0;
                }
                console.info(editRow);
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                var rows = userDatagird.datagrid('getSelections');
                if (rows.length > 0) {
                    $.messager.confirm('请确认', '您确定要删除当前所有选择的项目吗？', function (b) {
                        if (b) {
                            var ids = [];
                            for (var i = 0; i < rows.length; i++) {
                                ids.push(rows[i].id);
                            }
                            console.info(ids.join(','));
                            $.ajax({
                                url: '/user/deleteUsers',
                                data: {
                                    ids: ids.join(',')
                                },
                                dataType: 'json',
                                success: function (r) {
                                    userDatagird.datagrid('load');
                                    userDatagird.datagrid('unselectAll');
                                    $.messager.show({
                                        title: '提示',
                                        msg: '删除成功！'
                                    });
                                }
                            });
                        }
                    });
                } else {
                    $.messager.alert('提示', '请选择要删除的记录', 'warning');
                }
            }
        }, '-', {
            text: '修改',
            iconCls: 'icon-edit',
            handler: function () {
                var rows = userDatagird.datagrid('getSelections');
                if (rows.length == 1) {
                    if (editRow != undefined) {
                        userDatagird.datagrid('endEdit', editRow);
                    }
                    if (editRow == undefined) {
                        userDatagird.datagrid('removeEditor', 'createDate');
                        var index = userDatagird.datagrid('getRowIndex', rows[0]);
                        userDatagird.datagrid('beginEdit', index);
                        editRow = index;
                    }
                } else {
                    $.messager.show({
                        msg: '请选择一项进行修改！',
                        title: '错误'
                    });
                }
            }
        }, '-', {
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                userDatagird.datagrid('endEdit', editRow);
                console.info("保存");
            }
        }, '-', {
            text: '取消编辑',
            iconCls: 'icon-redo',
            handler: function () {
                editRow = undefined;
                userDatagird.datagrid('rejectChanges');//回滚
                userDatagird.datagrid('unselectAll');
                editRow = undefined;
            }
        }, '-', {
            text: '取消选中',
            iconCls: 'icon-undo',
            handler: function () {
                editRow = undefined;
                userDatagird.datagrid('unselectAll');
            }
        }],
        onAfterEdit: function (rowIndex, rowData, changes) {
            console.info("提交到后台");

            console.info(rowData);

            var inserted = userDatagird.datagrid('getChanges', 'inserted');
            console.info(inserted);
            var updated = userDatagird.datagrid('getChanges', 'updated');
            console.info(updated);
            var url = '';
            if (inserted.length > 0) {
                url = '/user/addUser';
            }
            if (updated.length > 0) {
                url = '/user/editUser';
            }
            console.info(url);
            $.ajax({
                url: url,
                data: rowData,
                dataType: 'json',
                success: function (r) {
                    alert(JSON.stringify(r));
                    alert(r.result.success);
                    if (r && r.result.success) {
                        userDatagird.datagrid('acceptChanges');
                        $.messager.show({
                            msg: r.result.msg,
                            title: '成功'
                        });
                        userDatagird.datagrid('load');
                    } else {
                        userDatagird.datagrid('rejectChanges');
                        $.messager.alert('错误', r.result.msg, 'error');
                    }
                    editRow = undefined;
                    userDatagird.datagrid('unselectAll');
                }
            });
        },
        onDblClickRow: function (rowIndex, rowData) {
            if (editRow != undefined) {
                userDatagird.datagrid('endEdit', editRow);
                editRow = undefined;
                userDatagird.datagrid('unselectAll');
            }
            if (editRow == undefined) {
                userDatagird.datagrid('removeEditor', 'createDate');
                userDatagird.datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        }
    });
});

function search() {
    alert("aaa");
    userDatagird.datagrid('load', {
        username: $('#searchForm').find('[name=username]').val(),
        realName: $('#searchForm').find('[name=realName]').val(),
        createTimeStart: $('#searchForm').find('[name=createTimeStart]').val(),
        createTimeEnd: $('#searchForm').find('[name=createTimeEnd]').val(),
    });
    // userDatagird.datagrid({
    //     url: '/user/searchUser',
    //     queryParams: {
    //         username: $('#searchForm').find('[name=username]').val(),
    //         realName: $('#searchForm').find('[name=realName]').val(),
    //         createTimeStart: $('#searchForm').find('[name=createTimeStart]').val(),
    //         createTimeEnd: $('#searchForm').find('[name=createTimeEnd]').val(),
    //     },
    //     iconCls: 'icon-save',
    //     pagination: true,
    //     pageSize: 10,
    //     pageList: [10, 20, 30, 40],
    //     fit: true,
    //     fitColumns: true,
    //     idField: 'id',
    //     sortName:'id',
    //     sortOrder:'desc',
    //     columns: [[
    //         {
    //             title: '编号',
    //             field: 'id',
    //             width: 100,
    //             sortable:true
    //         }, {
    //             title: '用户名',
    //             field: 'name',
    //             width: 100,
    //             sortable:true
    //         }, {
    //             title: '真实姓名',
    //             field: 'realName',
    //             width: 100,
    //             sortable:true
    //         }, {
    //             title: '密码',
    //             field: 'passwd',
    //             width: 100
    //         }, {
    //             title: '创建日日期',
    //             field: 'createDate',
    //             width: 100,
    //             sortable:true
    //         }]]
    // });
}

function clearSearchForm() {
    userDatagird.datagrid('load', {});
    $('#searchForm').find('input').val('');
}

/*
 * 为datagrid扩展一个datetimebox类型
 */
$.extend($.fn.datagrid.defaults.editors, {
    datetimebox: {
        init: function (container, options) {
            var editor = $('<input />').appendTo(container);
            options.editable = false;
            editor.datetimebox(options);
            return editor;
        },
        getValue: function (target) {
            return $(target).datetimebox('getValue');
        },
        setValue: function (target, value) {
            $(target).datetimebox('setValue', value);
        },
        resize: function (target, width) {
            $(target).datetimebox('resize', width);
        },
        destory: function (target) {
            $(target).datetimebox('destory');
        }
    }
});

/*
 * 动态扩展editor编辑器
 */
$.extend($.fn.datagrid.methods, {
    //让不可编辑框变成可编辑
    addEditor: function (jq, param) {
        if (param instanceof Array) {
            $.each(param, function (index, item) {
                var e = $(jq).datagrid('getColumnOption', item.field);
                e.editor = item.editor;
            });
        } else {
            var e = $(jq).datagrid('getColumnOption', param.field);
            e.editor = param.editor;
        }
    },
    //让可编辑框变成不可编辑
    removeEditor: function (jq, param) {
        if (param instanceof Array) {
            $.each(param, function (index, item) {
                var e = $(jq).datagrid('getColumnOption', item);
                e.editor = {};
            });
        } else {
            var e = $(jq).datagrid('getColumnOption', param);
            e.editor = {};
        }
    }
});