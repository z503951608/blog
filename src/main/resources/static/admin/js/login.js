layui.define(['form'], function (exports) {
    var form = layui.form,
        layer = layui.layer;
    $ = layui.jquery;
    //自定义验证规则
    form.verify({
        username: function (value) {
            if (value.length <= 0 || value.trim() == "") {
                return '请输入用户名';
            }
        },
        password: [/(.+){6,12}$/, '密码必须6到12位'],
        verity: [/(.+){4}$/, '验证码必须是4位'],

    });
    //监听提交
    form.on('submit(login)', function (data) {
        $.ajax({
            url:'/admin/login',
            contentType:'application/json',
            type:'post',
            data:JSON.stringify(data.field),
            dataType:'json',
            success:function (data) {
                if(data.code == 500){
                    layer.msg(data.msg);
                }else if(data.code == 200){
                    window.location.href = "/admin/toIndex";
                }
            },
            error:function (data) {
                layer.msg("服务异常");
            }

        });
/*        $.post('${req.contextPath}/admin/login', JSON.stringify(data.field), function (data,status) {
            console.log(data);
        }, 'json');*/


/*                layer.alert(JSON.stringify(data.field), {
                    title: '最终的提交信息'
                })*/
                return false;

    });
    exports('login', {});
});