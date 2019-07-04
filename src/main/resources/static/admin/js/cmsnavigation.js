layui.config({
    base: '/static/admin/js/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    common: 'common'
});

layui.use(['laypage', 'layer', 'table', 'form','common'],function () {
        $ = layui.jquery
        ,laypage = layui.laypage //分页
        ,layer = layui.layer //弹层
        ,table = layui.table //表格
        ,form = layui.form
        ,common = layui.common
    //执行一个 table 实例
    var tableIns = table.render({
            elem: '#cmsnavigationtable'
            ,height: 600
            ,url: '/admin/cmsnavigation/list' //数据接口
            ,title: '导航表'
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', sort: true}
                ,{field: 'name', title: '导航名称', sort: true}
                ,{field: 'isShow', title: '是否显示',
                    templet: function(d){
                        if(d.isShow == 1){
                            return "是";
                        }else{
                            return "否";
                        }
                    },sort: true}
                ,{field: 'isOrder', title: '排序', sort: true}
                ,{field: 'url', title: '路径', sort: true}
                ,{fixed: 'right',title: '操作', align:'center', toolbar: '#barcmsnavigation'}
            ]]
        });
    //搜索
    form.on('submit(search)',function (data) {
        tableIns.reload( {
            where: {
                key:$("#key").val().trim(),
            } //设定异步数据接口的额外参数
        });
        return false;
    })

    //监听头工具栏事件
    table.on('toolbar(cmsnavigationtable)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data; //获取选中的数据
        switch(obj.event){
            case 'add':
                common.page("添加", "/admin/cmsnavigation/toAdd", w = "700px", h = "420px");
                break;
            case 'update':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else if(data.length > 1){
                    layer.msg('只能同时编辑一个');
                } else {
                    common.page("编辑", "/admin/cmsnavigation/toAdd?id=" + checkStatus.data[0].id, w = "700px", h = "420px");
                }
                break;
            case 'delete':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else {
                    var arr = new Array();
                    for (var i = 0;i<checkStatus.data.length;i++){
                        arr[i] = checkStatus.data[i].id;
                    }
                    remove(arr);
                }
                break;
        };
    });

    //监听行工具事件
    table.on('tool(cmsnavigationtable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'detail'){
            layer.msg('查看操作');
        } else if(layEvent === 'del'){
            var arr = new Array();
            arr[0] = data.id;
            remove(arr);
        } else if(layEvent === 'edit'){
            common.page("编辑", "/admin/cmsnavigation/toAdd?id=" + data.id, w = "700px", h = "420px");
        }
    });
    //编辑
    form.on("submit(cmsnavigationadd)",function (data) {
        $.ajax({
            url:'/admin/cmsnavigation/add',
            contentType:'application/json',
            type:'post',
            data:JSON.stringify(data.field),
            dataType:'json',
            success:function (data) {
                if(data.code == 500){
                    layer.msg(data.msg);
                }else if(data.code == 200){
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                    parent.table.reload('cmsnavigationtable');
                }
            },
            error:function (data) {
                layer.msg("服务异常");
            }

        });
        return false;
    });

    //删除
    function remove(arr) {
        layer.confirm('确认删除', function(index){
            layer.close(index);
            //向服务端发送删除指令
            $.ajax({
                url:'/admin/cmsnavigation/remove',
                type:'post',
                data:{
                    ids: arr,
                },
                dataType:'json',
                success:function (data) {
                    if(data.code == 500){
                        layer.msg(data.msg);
                    }else if(data.code == 200){
                        table.reload('cmsnavigationtable');
                    }
                },
                error:function (data) {
                    layer.msg("服务异常");
                }
            });
        });
    }
});
