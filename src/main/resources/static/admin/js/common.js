layui.define(['jquery', 'layer'], function (exports) {
    var common = {
        page: function (title, url, w, h) {
            if (title == null || title == '') {
                title = false;
            }
            ;
            if (url == null || url == '') {
                url = "404.html";
            }
            ;
            if (w == null || w == '') {
                w = '700px';
            }
            ;
            if (h == null || h == '') {
                h = '350px';
            }
            ;
            //如果手机端，全屏显示
            if (window.innerWidth <= 768) {
                var index = layer.open({
                    type: 2,
                    title: title,
                    area: [320, h],
                    fixed: false, //不固定
                    content: url
                });
                layer.full(index);
            } else {
                var index = layer.open({
                    type: 2,
                    title: title,
                    area: [w, h],
                    fixed: false, //不固定
                    content: url
                });
            }
        }
    };
    //输出common接口
    exports('common', common);
});
