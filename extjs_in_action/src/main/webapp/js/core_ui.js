/**
 * Created by DINGJUN on 2019.08.04.
 */
Ext.onReady(function () {
    /**4
     * 核心UI部件
     */

    // 构建一个复杂的面板
    var myBtnHandler = function (btn) {//点击回调
        Ext.Msg.alert('you clicked', btn.text);
    },
        fileBtn = Ext.create('Ext.button.Button', {//文件按钮
            text:'File',
            handler: myBtnHandler
        }),
        editBtn = Ext.create('Ext.button.Button', {//编辑按钮
            text:'Edit',
            handler: myBtnHandler
        }),
        tbFill = new Ext.toolbar.Fill();//会将这个元素后面的向右推,也就是中间使用空白填充
    var myTopToolbar = Ext.create('Ext.toolbar.Toolbar', {//顶部工具栏
        items: [fileBtn, tbFill, editBtn]
    });

    var myBottomToolbar = [{//配置底部工具栏
        text: 'Save',
        handler: myBtnHandler
    },
    '-', // 按钮之间的'|'分隔符
    {
        text: 'Cancel',
        handler: myBtnHandler
    },
    '->',// 空白填充符号,也就是会将后面的元素向右推
    '<b>Items open:1</b>']

    var myPanel = Ext.create('Ext.panel.Panel', {
        width: 200,
        height: 200,
        title: 'Ext Panels rock',
        collapsible: true, // 面板可折叠
        renderTo: Ext.getBody(),
        tbar: myTopToolbar, // 渲染的工具栏被置于内容体的上方和下方.这个过程称为停靠(docking),是由Dock组件布局类进行管理的.
        bbar: myBottomToolbar,
        html: 'My first Toolbar Panel'
    })

    // 向现存的面板上添加按钮和工具
    var myPanel = Ext.create('Ext.panel.Panel', {
        width: 200,
        height: 200,
        title: 'Ext Panels rock',
        collapsible: true, // 面板可折叠
        renderTo: Ext.getBody(),
        tbar: myTopToolbar, // 渲染的工具栏被置于内容体的上方和下方.这个过程称为停靠(docking),是由Dock组件布局类进行管理的.
        bbar: myBottomToolbar,
        html: 'My first Toolbar Panel',
        buttonAlign: 'left', // 指定buttons数组停靠的位置
        buttons: [{ // 这里的button出现在了bbar的上面,可以通过下面的DockedItems进行调整
            text: 'Press me!',
            handler: myBtnHandler
        }],
        tools: [{ // 右上角处的工具栏
            type: 'gear', // 设置图标
            handler: function(evt, toolEl, panel) {
                var toolClassNames = toolEl.className.split(' ');
                var toolClass = toolClassNames[1];
                var toolId = toolClass.split('-')[2];
                Ext.MessageBox.alert('you clicked', 'tool ' + toolId);
            }
        }, {
            type: 'help', // 帮助图标
            handler: function () {
                Ext.Msg.alert('you clicked', 'the help tool');
            }
        }]
    })

    // Dock布局认识使用
    // 顶部停靠单个工具栏
    var buttons = [
        {text: 'Btn1'},
        {text: 'Btn2'},
        {text: 'Btn3'}
    ];

    var topDockedToolbar = { // 创建一个顶部Toolbar配置对象
        xtype: 'toolbar',
        weight:1,
        dock: 'top',
        items: buttons
    }
    var bottomDockedToolbar = { // 底部
        xtype: 'toolbar',
        dock: 'bottom',
        items: buttons
    }
    var leftDockedToolbar = { // 左侧
        xtype: 'toolbar',
        weight:10, // 设置权重,让低侧工具栏占整个面板的100%区域.
        dock: 'left',
        items: buttons
    }
    var rightDockedToolbar = { // 右侧
        xtype: 'toolbar',
        weight:10,
        dock: 'right',
        items: buttons
    }

    var myPanel = Ext.create('Ext.panel.Panel', {
        width: 350,
        height: 250,
        title: 'Ext Panels rock',
        renderTo: Ext.getBody(),
        html: 'content body',
        buttons: { // 这里的buttons还是会出现在配置的dockedItems的组件上面,修正这些问题需要调整配置停靠元素的权重.
            weight: -1, // 解决按钮的尺寸和位置错误的问题.
            items: buttons
        },
        dockedItems: [//权重默认值:顶部-1,左侧-3,右侧-5,底部-7
            topDockedToolbar, // 面板上配置的tbar和bbar属性,其实就是在内部把配置推送给所谓的dockedItems.Dock布局的作用就是把一个或者多个组件渲染在内容面板"内容体"左右任意一侧,它会组织排列每一个weight属性的部件.
            bottomDockedToolbar,
            leftDockedToolbar,
            rightDockedToolbar
        ]
    })

    /**
     * 4.2 显示窗口和对话框
     */
    // 构建一个窗口
    var win;
    var newWindow = function(btn) {
        if(!win) {
            win = Ext.create('Ext.window.Window', { // 创建新的窗口
                animateTarget: btn.el, // 动画源
                html: 'my first vanilla window',
                closeAction: 'hide',
                id: 'myWin',
                height: 200,
                width: 300,
                constrain: true // 约束实例,可以对窗口的拖动进行限制,true,窗口不可以被拖动到浏览器之外.false,可以拖动到浏览器之外
            })
        }
        win.show();
    }
    new Ext.Button({
        renderTo: Ext.get('newWin'),
        text: 'open my window',
        style: 'margin:50px', //留白50px
        handler: newWindow
    });

    // 更多的窗口配置
    // 创建一个刚性模态窗口
    var win = Ext.create('Ext.window.Window', {
        height: 75,
        width: 200,
        modal: true, // true:此时聚焦到当前的window上面上,其他的地方不能操作,masked
        title: 'this is one rigid window',
        html: 'try to move or resize me. i dare you',
        plain: true,
        border: false, // 文字边界线条显示
        resizable: false, // 无法改变大小
        draggable: false, // 无法拖动
        closable: false, // 无法关闭
        buttonAlign: 'center',
        buttons: [{
            text: 'i give up',
            handler: function () {
                win.close();
            }
        }]
    })
    // win.show();

    /**
     * 4.3 window的子类:消息框使用
     */
    var myCallBack = function (btn, text) {
        console.log('you pressed ' + btn);
        if(text) {
            console.log('you entered :' + text);
        }
    }
    var msg = 'your document was saved successfully';
    var title = 'save status';

    //Ext.MessageBox.alert(title, msg); // 提示框.第一个参数:提示框标题,第二个参数:提示框内容,第三个参数:提示框的回调
    //Ext.MessageBox.prompt(title, msg, myCallBack); // 确认框.
    Ext.Msg.show({
        title: 'input required',
        msg: 'please tell us a little about youself',
        width: 300,
        // buttons: Ext.Msg.OKCANCEL,// 两个按钮
        buttons: Ext.Msg.YESNOCANCEL,//三个按钮
        multiline: true,
        fn: myCallBack,
        // icon: Ext.MessageBox.INFO
        icon: Ext.MessageBox.ERROR
    })

    // 静态的进度条,没有交互
    Ext.MessageBox.wait("we're doing something...", 'hold on...'); // 第一个参数:消息主体文本 第二个参数:提示框标题 第三个参数:进度条上的参数

    // 动态
    Ext.MessageBox.show({
        title: 'hold on there cowboy',
        msg: 'we are doing something',
        progressText: 'Initializing...',
        width: 300,
        progress: true,
        closable: false,
    })
    var updateFn = function (num) {
        return function() {
            if(num == 6) {
                Ext.MessageBox.updateProgress(100, 'All Items saved');
                Ext.Function.defer(Ext.MessageBox.hide, 1500, Ext.MessageBox)
            } else {
                var i = num / 6;
                var pct = Math.round(i * 100);
                Ext.MessageBox.updateProgress(i, pct + '% completed');
            }
        }
    }
    for(var i = 1; i < 7; i ++) {
        setTimeout(updateFn(i), i * 500);
    }

    // 构建标签面板
    var simpleTab = { // 静态标签
        title: 'simple tab',
        html: 'this is a simple tab'
    }
    var closableTab = { // 创建可关闭标签
        title: 'i am closable',
        html: 'please close when done reading.',
        closable: true
    }
    var disabledTab = { // 添加禁用标签
        title: 'disable tab',
        itemId: 'disabledTab',
        html: 'peekaboo!',
        disabled: true,
        closable: true
    }
    var tabPanel = Ext.create('Ext.tab.Panel', { // 实例化标签面板
        activeTab: 0, //通过索引指定激活显示的panel
        itemId: 'myTPanel',
        items: [
            simpleTab,
            closableTab,
            disabledTab
        ]
    })
    Ext.create('Ext.window.Window', { // 渲染标签面板
        height: 300,
        width: 400,
        layout: 'fit',
        items: tabPanel
    }).show();
    tabPanel.down('#disabledTab').enable(); // 启用
    tabPanel.down('#disabledTab').tab.hide(); // 隐藏
    tabPanel.down('#disabledTab').tab.show(); // 显示

})