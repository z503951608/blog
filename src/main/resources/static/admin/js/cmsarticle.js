layui.config({
    base: '/static/admin/js/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    common: 'common'
});

layui.use(['laypage','layer','table','common','upload','form','laydate'],function () {
        $ = layui.jquery
        ,laypage = layui.laypage //分页
        ,layer = layui.layer //弹层
        ,table = layui.table //表格
        ,common = layui.common
        ,upload = layui.upload
        ,form = layui.form
        ,laydate = layui.laydate
    //执行一个 table 实例
    var cmsarticleTable = table.render({
            elem: '#cmsarticletable'
            ,height: 600
            ,url: '/admin/cmsarticle/list' //数据接口
            ,title: '文章表'
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', sort: true}
                ,{field: 'title', title: '标题', sort: true}
                ,{field: 'publicTime', title: '发布时间', sort: true}
                ,{field: 'image', title: '图片',
                templet:function(d){
                    return "<img src='" + d.image + "'>"
                },
                sort: true}
                ,{field: 'views', title: '浏览量', sort: true}
                ,{field: 'isShow', title: '是否显示',
                templet: function(d){
                    if(d.isShow == 1){
                        return "是";
                    }else{
                        return "否";
                    }
                },sort: true}
                ,{fixed: 'right',title: '操作', align:'center', toolbar: '#barcmsarticle'}
            ]]
        });
    //搜索
    form.on('submit(search)',function (data) {
        console.log(data.field);
        cmsarticleTable.reload( {
            where: {
                key:$("#key").val().trim(),
                isShow:data.field.isShow,
                publicTimes:data.field.publicTimes
            } //设定异步数据接口的额外参数
        });
        return false;
    })
    //监听头工具栏事件
    table.on('toolbar(cmsarticletable)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data; //获取选中的数据
        switch(obj.event){
            case 'add':
                common.page("添加", "/admin/cmsarticle/toAdd", w = "700px", h = "420px");
                break;
            case 'update':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else if(data.length > 1){
                    layer.msg('只能同时编辑一个');
                } else {
                    common.page("编辑", "/admin/cmsarticle/toAdd?id=" + checkStatus.data[0].id, w = "700px", h = "420px");
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
    table.on('tool(cmsarticletable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'detail'){
            layer.msg('查看操作');
        } else if(layEvent === 'del'){
            var arr = new Array();
            arr[0] = data.id;
            remove(arr);
        } else if(layEvent === 'edit'){
            common.page("编辑", "/admin/cmsarticle/toAdd?id=" + data.id, w = "700px", h = "420px");
        }
    });
    //删除
    function remove(arr) {
        layer.confirm('确认删除', function(index){
            layer.close(index);
            //向服务端发送删除指令
            $.ajax({
                url:'/admin/cmsarticle/remove',
                type:'post',
                data:{
                    ids: arr,
                },
                dataType:'json',
                success:function (data) {
                    if(data.code == 500){
                        layer.msg(data.msg);
                    }else if(data.code == 200){
                        table.reload('cmsarticletable');
                    }
                },
                error:function (data) {
                    layer.msg("服务异常");
                }
            });
        });
    }
    //时间段控件
    laydate.render({
        elem: '#publicTimes' //指定元素
       ,range: true
       ,type: 'datetime'
    });
    //文件上传
    upload.render({
        elem: '#file' //绑定元素
        ,url: '/admin/cmsarticle/upload' //上传接口
        ,done: function(res){
            console.log(res);
            $("#image").val(res.data.src);
            $("#showImg").remove();
            $("#file").after('<img src="'+res.data.src+'" id="showImg">')
        }
        ,error: function(){
            layer.msg("上传异常");
        }
    });
});
