/**
 * Created by DINGJUN on 2019.08.06.
 */
Ext.onReady(function () {
    /**
     * 5 探究布局
     * 布局管理器分为两组:组件布局和容器布局
     * 组件布局:Dock组件布局,Field组件布局及其子类
     * 容器布局:Auto,Fit,Anchor,Accordion,Card,HBox,VBox,Table,Border
     */

    /**
     * 1.Auto:它把元素上下叠加的放置在屏幕上,如果内容体没有被限定,一个子项的宽度可能与容器的内容体宽度一致
     *
     */
    var childPanel1 = {
        frame: true,
        height: 50,
        html: 'my first child panel',
        title: 'first child are fun'
    }
    var childPanel2 = {
        width: 150,
        html: 'second child',
        title: 'second children have all fun'
    }
    var myWin = Ext.create('Ext.Window', {
        height: 300,
        width: 300,
        title: 'a window with a container layout',
        autoScroll: true, // 设置可滚屏,让浏览器在必要的时候才显示滚动条
        items: [childPanel1, childPanel2], // 添加子面板,这是一个数组.注意:任何容器的items属性都可以是一个用来列举多个子项的数组示例,或者是一个单独的子项的对象引用.
        tbar: [{
            text: 'add child',
            handler: function () {
                var numItems = myWin.items.getCount() + 1;
                myWin.add({
                    title: 'child number' + numItems,
                    height: 60,
                    frame: true,
                    collapsible: true,
                    collapsed: true,
                    html: 'yay, another child'
                });
            }
        }]
    })
    myWin.show();


    /**
     * 2.Anchor:也会把子项上下叠在一起,不过它会通过在每个子项上指定的anchor参数来进行动态尺寸的调整.
     * 这个anchor参数用于计算子项相对于父容器内容体的尺寸,要么指定百分比,要么指定一对整数偏移值(相对于父容器缩小的值,一般是负数).
     * anchor参数格式:anchor:"width, height" 或者 "width height"
     * 注意:最好子项都是用百分比或者偏移值,混合使用会导致显示不在框内,需要计算所有的子项高度
     *
     * 另:Anchor默认是Ext.form.Panel类使用
     */
    var myWin = Ext.create('Ext.Window', {
        height: 300,
        width: 300,
        layout: 'anchor', // 设置布局
        border: false,
        anchorSize: 400,
        items: [{
            title: 'panel1',
            anchor: '100% 10%',
            frame: true
        },{
            title: 'panel2',
            anchor: '0 10%', // 0:为100%的简单表达方式
            frame: true
        },{
            title: 'panel3',
            anchor: '50% 10%',
            frame: true
        },{
            title: 'panel4',
            anchor: '-50 -150',
            frame: true
        },{
            title: 'panel5',
            height: 150, // 固定高度
            anchor: '-10 -150',
            frame: true
        }]
    })
    myWin.show();

    /**
     * 3.Absolute:它固定一个子项的位置,具体是通过把子项的CSS'position属性设置为'absolute',并把top和left属性设置为我们在子项上设置的x和y参数
     * 注意:absolute布局可能存在组件元素重叠的风险.
     */
    var  myWin = Ext.create('Ext.Window',{
        height: 300,
        width: 300,
        layout: 'absolute',
        autoScroll: true,
        border: false,
        items: [{
            title: 'panel1',
            x:50,
            y:50,
            height: 100,
            width: 100,
            html: 'x: 50, y: 50',
            frame: true
        },{
            title: 'panel2',
            x:90,
            y:120,
            height: 75,
            width: 100,
            html: 'x: 90, y: 120',
            frame: true
        }]
    })
    myWin.show();

    /**
     * 4.Fit:迫使一个容器的<单个子项>填充它的内容体元素.如果有一个子项,想让它随着父容器一起调整尺寸,fit布局是最佳的选择.
     */
    var myWin = Ext.create('Ext.Window', {
        height: 300,
        width: 300,
        layout: 'fit',
        border: false,
        items: [{
            title: 'panel1',
            html: 'i fit in my parent',
            frame: true
        }]
    });
    myWin.show();

    /**
     * 5.Accordion:一般一个容器都会有多个部件,而一次只展示一个元素,此时就应使用accordion布局.
     * 它可以垂直的堆叠<可折叠>的元素,每次展示一个元素.
     */
    var myWin = Ext.create('Ext.Window',{
        height: 200,
        width: 300,
        border: false,
        title: 'a window with an accordion layout',
        layout:'accordion',
        layoutConfig: {
            animate: true
        },
        items: [{
            xtype: 'form',
            title: 'general info',
            bodyStyle: 'padding: 5px',
            fieldDefaults: {
                labelWidth: 50
            },
            labelWidth: 50,
            items: [{
                xtype: 'field',
                fieldLabel: 'Name',
                anchor: '-10',
            },{
                xtype: 'field',
                fieldLabel: 'Age',
                size: 3
            },{
                xtype: 'combo',
                fieldLabel: 'Location',
                anchor: '-10',
                store: ['here', 'there', 'anywhere']
            }]
        }, {
            xtype: 'panel',
            title: 'bio',
            layout: 'fit',
            items: [{
                xtype: 'textarea',
                value: 'tell us about yourself'
            }]
        }, {
            title: 'instructions',
            html: 'please enter information',
            tools: [{
                id: 'gear'
            }, {
                id: 'help'
            }]
        }]
    });
    myWin.show();


    /**
     * 6.Card:确保容器的子项与容器的尺寸相一致.和fit布局不同,card布局可以控制多个子项.
     */
    var handleNav = function (btn) {
        var activeItem = myWin.layout.activeItem,
            index = myWin.items.indexOf(activeItem),
            numItems = myWin.items.getCount(),
            indicatorEl = Ext.getCmp('indicator').el;
        if(btn.text == 'forward' && index < numItems - 1) {
            index++;
            // myWin.layout.setActiveItem(index);
            myWin.layout.activeItem = index;
            index++
            indicatorEl.update(index + ' of ' + numItems);
        } else if(btn.text == 'back' && index > 0) {
            myWin.layout.setActiveItem(index - 1);
            indicatorEl.update(index + ' of ' + numItems);
        }
    }
    var myWin = Ext.create('Ext.Window', {
        height: 200,
        width: 200,
        border: false,
        title: 'a window with a card layout',
        activeItem: 0,
        defaults: {border: false},
        items: [{
            xtype: 'form',
            title: 'general info',
            bodyStyle: 'padding: 5px',
            defaultType: 'field',
            labelWidth: 50,
            items: [{
                fieldLabel: 'Name',
                anchor: '-10'
            }, {
                xtype:'numberfield',
                fieldLabel: 'Age',
                size: 3
            },{
                xtype: 'combo',
                fieldLabel: 'Location',
                anchor: '-10',
                store: ['here', 'there', 'anywhere']
            }]
        }, {
            xtype: 'panel',
            title: 'bio',
            layout: 'fit',
            items: [{
                xtype: 'textarea',
                value: 'tell us about yourself'
            }]
        }, {
            title: 'congratulations',
            html: 'thank you for filling out our form'
        }],
        dockedItems: [{
            xtype: 'toolbar',
            dock: 'bottom',
            items:[{    //添加导航按钮
                text: 'back',
                handler: handleNav
            }, '-' ,{
                text: 'forward',
                handler: handleNav
            }, '->' ,{  // 添加指示器组件
                xtype: 'component',
                id: 'indicator',
                style: 'margin-right: 5px',
                html:  '1 of 3'
            }]
        }]
    })
    myWin.show(); // TODO 下一页异常

    /**
     * 7.Column:把组件组织为列,可以在一个容器上并排显示多个组件.
     */
    var myWin = Ext.create('Ext.Window', {
        height: 200,
        width: 400,
        autoScroll: true,
        id: 'myWin',
        title: 'a window with a column layout',
        layout: 'column',
        defaults: {
            frame: true
        },
        items: [{
            title: 'Col 1',
            id: 'col1',
            columnWidth: .3 // 设置相对宽度
        },{
            title: 'Col 2',
            html: '20% relative width',
            columnWidth: .2 // 设置相对宽度
        },{
            title: 'Col 3',
            html: '100px fixed width',
            width: 100
        },{
            title: 'Col 4',
            frame: true,
            html: '50% relative width',
            columnWidth: .5 // 设置相对宽度
        }]
    });
    myWin.show();

    /**
     * 8.HBox和VBox:可以把元素分列显示,有点像column,不过更灵活.
     */
    var myWin = Ext.create('Ext.Window', {
        height: 300,
        width: 300,
        title: 'a window with a Hbox layout',
        layout: 'hbox', // hbox/vbox
        layoutConfig: {
            pack: 'start' //HBox:pack:垂直对齐方式:start/center/end.VBox:align:水平对齐方式:top/middle/stretch/stretchmax
        },
        defaults: {
          frame: true,
            width: 75
        },
        items: [{
            title: 'panel 1',
            height: 100
        },{
            title: 'panel 2',
            height: 75,
            width: 100
        },{
            title: 'panel 3',
            height: 200
        }]
    });
    myWin.show();

    /**
     * 9.Table:可以组织排列组件
     */
    var myWin = Ext.create('Ext.Window', {
        height: 300,
        width: 300,
        border: false,
        autoScroll: true,
        title: 'a window with a Table layout',
        layout: {
            type: 'table',
            columns: 3 // 设置列数,指定table显示的列数
        },
        defaults: { // 所有子项的宽高
            height: 50,
            width: 50
        },
        items: [{
            html: '1',
            colspan: 3,
            width: 150
        },{
            html: '2',
            rowspan: 2,
            height: 100
        },{
            html: '3',
        },{
            html: '4',
            rowspan: 2,
            height: 100
        },{
            html: '5',
        },{
            html: '6',
        },{
            html: '7',
        },{
            html: '8',
        },{
            html: '9',
            colspan: 3,
            width: 150
        }]
    });
    myWin.show();

    /**
     * 10.Border:东西南北中
     */
    Ext.create('Ext.Viewport', {
        layout: 'border',
        defaults: {
            frame: true,
            split: true
        },
        items: [
            {
                title: 'north panel',
                region: 'north',
                height: 100,
                minHeight: 100,
                maxHeight: 150,
                collapsible: true
            },
            {
                title: 'south panel',
                region: 'south',
                height: 75,
                split: false,
                margins: {
                    top: 5
                }
            },
            {
                title: 'east panel',
                region: 'east',
                width: 100,
                minWidth: 75,
                maxWidth: 150,
                collapsible: true
            },
            {
                title: 'west panel',
                region: 'west',
                collapsible: true,
                collapseNode: 'mini',
                width: 100
            },
            {
                title: 'center panel',
                region: 'center'
            }
        ]
    })
})