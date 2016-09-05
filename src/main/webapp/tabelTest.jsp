<head>
    <title>DataGrid</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" href="/js/easyui/themes/icon.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/easyui/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        $(function() {
            $('#test').datagrid({
                title: 'jQuery EasyUI---DataGrid',
                iconCls: 'icon-save',
                width: 500,
                height: 350,
                nowrap: false,
                striped: true,
                url: '../Data/datagrid_data.json',
                sortName: 'ID',
                sortOrder: 'desc',
                idField: 'ID',
                frozenColumns: [[
                    { field: 'ck', checkbox: true },
                    { title: 'ID', field: 'ID', width: 80, sortable: true }
                ]],
                columns: [[
                    { title: '基本信息', colspan: 2 },
                    { field: 'opt', title: '操作', width: 100, align: 'center', rowspan: 2,
                        formatter: function(value, rec) {
                            return '<span style="color:red">编辑 删除</span>';
                        }
                    }
                ], [
                    { field: 'name', title: 'Name', width: 120 },
                    { field: 'addr', title: 'Address', width: 120, rowspan: 2, sortable: true }
                ]],
                pagination: true,
                rownumbers: true,
                singleSelect: false,
                toolbar: [{
                    text: '添加',
                    iconCls: 'icon-add',
                    handler: function() {
                        alert('添加数据')
                    }
                }, '-', {
                    text: '保存',
                    iconCls: 'icon-save',
                    handler: function() {
                        alert('保存数据')
                    }
                }]
            });
        });

    </script>
</head>
<body>
<table id="test"></table>
</body>
</html>