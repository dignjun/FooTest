Ext.onReady(function () {
    Ext.create('Ext.container.Viewport', {
        // layout: 'fit',
        border: false,
        items: [{
            xtype:'button',
            text:'展示进度条',
            handler: function () {
                // Ext.Ajax.request({
                //     url: 'progressTest',
                //     params: {},
                //     success: function (res) {
                        var progress = Ext.create('Ext.ProgressBar', {
                            // title: '标题',
                            text: '更新内容...',
                            width: 300,
                            floating:true
                        });
                        progress.show();
                        var count = 0;
                        var curr = 0;
                        var text = '';
                        Ext.TaskManager.start({
                            run: function () {
                                count ++;
                                if (count >= 10) {
                                    progress.hide()
                                }
                                curr = count/10;
                                text = curr*100 + '%';
                                progress.updateProgress(curr, text);
                            },
                            interval:1000
                        });
                //     },
                //     failure: function (res) {
                //         Ext.Msg.alert("错误", '服务器端异常' + res)
                //     }
                // });
            }
        }]
    });
});