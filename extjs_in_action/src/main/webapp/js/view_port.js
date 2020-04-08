Ext.onReady(function () { // 页面加载后执行
    var win = Ext.create('Ext.window.Window', {
        title: 'window',
        width: 300,
        height: 300,
        html: 'this is a window'
    });
    Ext.create('Ext.container.Viewport', {
        title:"面板的标题",
        html:"面板中的主体内容部分",
        bbar:[
            {text:"按钮1"},

            {text:"按钮2"}]

    });
})