/**
 * Created by DINGJUN on 2019.08.04.
 */
Ext.onReady(function () {
    /** 3.1
     * 所有的UI组件都是Component的子类
     * 常见的Component子类:
     *      容器
     *      数据视图
     *      图形图标
     *      按钮
     *      编辑器
     *      表单输入框
     *      菜单项
     *      工具栏项
     */

    /**
     * xtype的使用,可以使代码更整洁,这将通过XType配置对象配置成为一个Ext.Panel部件
     * 在Ext中所有的部件都通过一个唯一的字符串和一个对该类的引用,注册到Ext.ComponentManager类,然后在被引用为一个XType
     *
     * 框架中,每一个UI类,都会有一个'.widget'为前缀的alias声明(Ext6就不用这个前缀)
     *
     */
    //使用xtype创建一个Ext.panel组件
    var myPanel = {
            xtype: 'panel',
            heigth: 100,
            width: 100,
            title: 'myPanel',
            html: 'hello',
        }

    // 自定义组件注册到Ext.ComponentManager
    Ext.define('MyApp.CustomClass', {
        extend: 'Ext.panel.Panel',
        alias: 'widget.myCustomComponent'
    })
    var myPanel2 = new Ext.Panel({
        title: 'myPanel2',
        width: 200,
        html: '<p>World!</p>',
        items:[{
            xtype: 'myCustomComponent', // 通过自定义的xtype组件配置对象,注意自定义的组件别名需要有widget.前缀
            title: 'MyApp.CustomClass'
        }],
    })

    // 如果xtype没有定义,则可视化组件会在调用ComponentManger.create的时候定义它的defaultType属性
    var panel1 = {
        xtype: 'panel',
        title: 'Plain Panel1',
        html: 'Panel with an xtype specified'
    }
    var panel2 = { // 未指定xtype
        title: 'Plain Panel2',
        html: 'Panel with <b>no</b> xtype specified'
    }

    Ext.create('Ext.window.Window', {
        width: 200,
        heigth: 200,
        title: 'Accordion window',
        border: false,
        layout: {
            type: 'accordion', // 折叠布局
            animate: true
        },
        items: [myPanel,myPanel2,panel1,panel2]
    }).show();

    Ext.create('Ext.window.Window', {
        width: 200,
        heigth: 150,
        title: 'Accordion window',
        layoutConfig: {
            animate: true
        },
        border: false,
        items: [{
            xtype: 'panel',
            title: 'Plain Panel',
            html: 'Panel with an xytpe specified'
        }, {
            title: 'Plain Panel',
            html: 'Panel with <b>an</b> xytpe specified'
        }]
    }).show();

    /**
     * 组件的渲染:1.直接渲染 2.懒惰渲染
     * 1.通过子类的配置项:renderTo或applyTo属性进行渲染
     * 2.通过调用render方法进行渲染,而且此时需要忽略组件的直接渲染属性配置
     *
     * 注:当组件是另一个组件的子组件的时候,绝对不能指定applyTo或者renderTo属性.
     * 如果一个组件是另一个组件的子组件的时候,它就会在配置对象的items属性中指定,
     * 而它的父组件将在必要的时候管理对其render方法的调用 -- 懒惰/延时渲染
     */

    /** 3.2
     *  组件的生命周期1.初始化,2.渲染,3.销毁
     *  1.initComponent
     *  2.beforerender(e) - onRender - render - afterRender - afterRender(e)
     *  3.beforedestory(e) - beforeDestroy - onDestroy - destroy - destroy(e)
     */

    /**3.3
     * 容器给组件提供了管理子元素基础
     */
    var panel331 = {
        html: 'i am panel',
        id: 'pane331',
        frame: true,
        height: 100
    }
    var panel332 = { // 这个panel并没有设置高度,在mywin33和panel331展示的时候,并不是下面的部分都是这个panel的展示区域
        html: '<b>i am panel</b>',
        id: 'panel332',
        frame: true,
    }
    var mywin33 = Ext.create('Ext.window.Window', {
        id: 'mywin',
        height: 400,
        width: 400,
        items: [panel331, panel332]
    })
    mywin33.show();

    // 添加组件到容器里面,add在容器的最后添加组件
    Ext.getCmp('mywin').add({
        title: 'appended panel333',
        id: 'addedPanel',
        html: 'hello here'
    });
    // 将组件添加到容器的特定的位置,会在指定组件位置的组件元素之前进行显示
    Ext.getCmp('mywin').insert(1, {
        title: 'inserted panel',
        id: 'insertedpanel',
        html: 'it is cool here'
    })
    // 删除组件remove方法有两个参数,第一个参数:是删除组件的标识,第二个参数:是否摧毁这个组件,true/false.默认true
    Ext.getCmp('mywin').remove(Ext.getCmp('addedPanel'));

    /**3.5
     * 视图容器:Viewport管理着浏览器100%的视口,也就是显示区域
     * 直接继承自Container
     */
    Ext.create('Ext.container.Viewport', {
        layout: 'border',   // 上下左右中的布局方式,此种布局方式center区域必须存在
        items: [{
            height: 75,
            region: 'north',
            title: 'does santa live here'
        }, {
            width: 150,
            region: 'west',
            title: 'the west region rules'
        }, {
            region: 'center',
            title: 'no, this region rules'
        }]
    })

})