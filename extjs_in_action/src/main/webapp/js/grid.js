/**
 * Created by DINGJUN on 2019.08.03.
 */
Ext.onReady(function () {
    /**
     * 8 网格面板
     *
     * 8.1
     * 操控面板的关键支持类有:grid.view,SelectionModel和store
     * Store类:数据存储用一个读取器(reader)来工作,读取器负责从数据源映射数据点,并填充到数据存储中(model).
     * 它们可以分别用数组,XML和JSON读取器来读取数组,XML和JSON数据
     * grid.view类:是网格视图的一个UI组件.读取数据(model),并控制显示在屏幕上.
     * 列(column):是把数据字段从每一个记录中映射数据字段到屏幕上的类.它们通过dataIndex属性来做到.dataIndex被设置在每一列上.
     * SelectionModel: 是一个和视图一起工作用来让用户在屏幕上选择一个或者多个项目的支持类.Ext JS支持行,单元,选择框以及树选择模型.用来跟踪你在屏幕上选择了什么
     */

    /**
     * 8.2
     * 一个简单的网格面板
     */
    var arrayData = [ // 网格grid需要用到的数据的,一个二维数组,其实就像是一个List<Map<String, Object>>一样...待续...似乎并不是,数组的data的每一个元素就是一条数据的所有值,如果是在store中使用,按照store中的fields指定的顺序映射值.
        ['jay garcia', 'md'],
        ['aaron baker', 'va'],
        ['susan smith', 'dc'],
        ['mary stein', 'de'],
        ['bryan shanley', 'nj'],
        ['nyri selgado', 'ca']
    ];
    var store = Ext.create('Ext.data.ArrayStore', {
        data: arrayData,
        fields: ['fullName', 'state'] // 数据存储这里的值会映射到column的值上
    });
    var grid = Ext.create('Ext.grid.Panel', {
        title: 'our first grid',    // 标题**
        renderTo: Ext.getBody(),    // 渲染到**
        autoHeight: true,           // 自动高度
        width: 250,                 // 宽度
        store: store,               // 指定store**
        // singleSelect: true,         // rowModel指定选择模式,选中了之后,可以通过键盘上的上下浏览其他的行.不过似乎开不开启都是可以的...
        multiSelect: true,          // 可以进行多行选择
        columns: [                  // 这个grid所能展示的列
            {
                header: 'full name',    // 网格的列名
                sortable: true,         // 列排序
                dataIndex: 'fullName',  // 网格的列对应的到store中的fields的哪个字段,最终通过store中指定的data一个元素所对应的数据的相应的顺序指定的数据
            },
            {
                header: 'state',
                sortable: false,    // 默认是true
                dataIndex: 'state'

            }
        ]
    });

    /**
     * 8.3
     * 高级的网格面板构建
     * 1.使用分页
     * 2.使用TemplateColumn类给列定制渲染器
     * 3.rowdblclick的使用
     * 4.介绍上上下文菜单,正如网格面板的rowcontextmenu事件
     */
    // 创建model
    Ext.define('Employee', {
        extend: 'Ext.data.Model',
        idProperty: 'id',
        fields: [   // model的定义必须extend自Ext.data.Model,fields中的每一个元素就是一条数据的定义,像是数据库表中的列定义
            {name: 'id', type: 'int'},
            {name: 'departmentId', type: 'int'},
            {name: 'dateHired', type: 'date', format: 'Y-m-d'},
            {name: 'dateFired', type: 'date', format: 'Y-m-d'},
            {name: 'dob', type: 'data', format: 'Y-m-d'},
            'firstName',
            'lastName',
            'title',
            'street',
            'city',
            'state',
            'zip'
        ]
    });
    // 创建store
    var employeeStore = Ext.create('Ext.data.Store', {
        model: 'Employee',
        pageSize: 1,   // 如果grid采用分页插件,那么在store中也是需要指定这个属性的.否则可能出现分页工具栏出现显示不正常的问题.***
        proxy: {    // 一个store必须配置的两个部件,1.proxy 2.reader
            type: 'ajax',
            url: '/read',
            reader: {
                type: 'json',   // reader的属性,json,jsonp,http等等***
                // metaProperty: 'meta',   // 这又是啥属性???配置之后不起作用,注释...
                root: 'data',   // 读取数据的key值,也就是返回的数据中,data中的数据向数据存储store中按照model的格式进行转化存储
                idProperty: 'id',
                totalProperty: 'total',
                successProperty: 'success'
            },
            writer: {
                type: 'json',
                encode: true,
                writeAllFields: true,
                root: 'data',
                allowSingle: true,
                batck: false,
                writeRecords: function (request, data) {
                    request.jsonData = data;
                    return request;
                }
            }
        }
    })
    // 创建column
    var columns = [
        {
            xtype: 'templatecolumn', // 使用模版列
            header: 'ID',
            dataIndex: 'id',
            sortable: true,
            width: 50,
            resizeable: false,
            hidden: true,           // 隐藏ID列
            tpl: '<span style="color: #0000FF;">{id}</span>'    // 将id渲染成蓝色?列的渲染不是通过renderer进行渲染的么,这个tpl效果也是一样的?
        }, {
            header: 'Last Name',
            dataIndex: 'lastName',
            sortable: true,
            hideable: false,
            width: 100
        }, {
            header: 'First Name',
            dataIndex: 'firstName',
            sortable: true,
            width: 100
        }, {
            header: 'Address',
            dataIndex: 'street',
            sortable: false,
            flex: 1,    // 这个
            tpl: '{street}<br />{city} {state}, {zip}' // 为地址设置模版
        }]
    // 配置分页工具条
    var pagingToolbar = {
        xtype: 'pagingtoolbar',
        store: employeeStore,
        dock: 'bottom',
        displayInfo: true
    }
    // 创建交互事件处理程序,并将这些函数作为grid的事件监听的回调 -- 在使用的之前进行定义,如果放在grid的定义后面,则页面会出现错误***
    var doMsgBoxAlert = function () {
        var record = grid.selModel.getSelection()[0];
        var firstName = record.get('firstName');
        var lastName = record.get('lastName');
        var msg = 'the record you chose:<br /> '+firstName+', '+lastName;
        Ext.MessageBox.alert('', msg);
    }
    // 行双击事件回调
    var doRowDblClick = function () {
        doMsgBoxAlert();
    }
    // 行右键点击事件回调
    var doRowCtxMenu = function (view, record, item, idex, e) {
        e.stopEvent();
        if(!view.rowCtxMenu) {
            view.rowCtxMenu = Ext.create('Ext.menu.Menu', {
                items: {
                    text: 'View Record',
                    handler: function () {
                        doMsgBoxAlert();
                    }
                }
            })
        }
        view.rowCtxMenu.showAt(e.getXY());
    }
    // 构建网格面板
    var grid = Ext.create('Ext.grid.Panel', {
        xtype: 'grid',
        columns: columns,
        store: employeeStore,
        loadMask: true,
        selType: 'rowmodel',
        singleSelect: true,
        stripeRows: true,
        dockedItems: [
            pagingToolbar
        ],
        listeners: {
            itemcontextmenu: doRowCtxMenu,
            itemdblclick: doRowDblClick,
            destroy: function (grid) {
                if(grid.rowCtxMenu) {
                    grid.rowCtxMenu.destroy();
                }
            }
        }
    })
    // 构建网格面板一个容器
    Ext.create('Ext.Window', {
        title: 'grid',
        height: 350,
        width: 350,
        border: false,
        layout: 'fit',
        items: grid
    }).show();
    employeeStore.load(); // 发送ajax请求获取数据

    //启用编辑插件
    Ext.define('State', {   // combo的model
        extend: 'Ext.data.Model',
        fields: ['id', 'state']
    })
    var url = '/combo/read';
    var stateStore = Ext.create('Ext.data.Store', { // combo的store
        model: 'State',
        proxy: {
            url: url,
            type: 'ajax',
            reader: {
                type: 'json',
                root: 'data',
                idProperty: 'id',
                successProperty: 'success'
            }
        }
    });
    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
        clicksToEdit: 2,
        autoCancel: false
    });
    var textField = {
        xtype: 'textfield'
    };
    var stateEditor = {
        xtype: 'combo',
        triggerAction: 'all',
        displayField: 'state',
        valueField: 'state',
        store: stateStore
    };
    // 创建带编辑器的列
    var columns = [
        {
            header: 'Last Name',
            dataIndex: 'lastName',
            sortable: true,
            editor: textField
        },
        {
            header: 'First Name',
            dataIndex: 'firstName',
            sortable: true,
            editor: textField
        },
        {
            header: 'Street Address',
            dataIndex: 'street',
            flex: 1,
            sortable: true,
            editor: textField
        },
        {
            header: 'City',
            dataIndex: 'city',
            sortable: true,
            editor: textField
        },
        {
            header: 'State',
            dataIndex: 'state',
            sortable: true,
            width: 50,
            editor: stateEditor
        },
        {
            header: 'Zip Code',
            dataIndex: 'zip',
            sortable: true,
            editor: textField
        }
    ]
    // 再次创建分页工具条,网格面板和窗口.添加保存和拒绝修改
    var pagingToolbar = {
        xtype: 'pagingtoolbar',
        store: employeeStore,
        displayInfo: true,
        pageSize: 50,
        items: [
            '-',
            {
                text: 'Save Changes',
                hander: function () {
                    employeeStore.sync();   // 保存更改.注意ext4的保存更改的方法调用
                }
            },
            {
                text: 'Reject Changes',
                hander: function () {
                    employeeStore.rejectChanges();     // 拒接更改.也就是将数据状态重置
                }
            },
            '-'
        ]
    }
    // 添加创建和删除功能
    var onDelete = function () {
        var selected = this.selModel.getSelection();
        Ext.MessageBox.confirm(
            'Confirm delete',
            'Are you sure?',
            function (btn) {
                if(btn == 'yes') {
                    grid.store.remove(selected);
                    grid.store.sync();
                }
            }
        )
    }
    var onInsertRecord = function () {
        var selected = this.selModel.getSelection();
        rowEditing.cancelEdit();
        var newEmployee = Ext.create('Employee');
        employeeStore.insert(selected[0].index, newEmployee);
        rowEditing.startEdit(selected[0].index, 0)
    }
    var doRowCtxMenu = function (view, record, item, idex, e) {
        e.stopEvent();
        if(!view.rowCtxMenu) {
            view.rowCtxMenu = Ext.create('Ext.menu.Menu', {
                items: [{
                    text: 'Insert Record',
                    handler: onInsertRecord
                },{
                    text: 'Delete Record',
                    handler: onDelete
                }]
            })
        }
        view.selModel.select(record);
        view.rowCtxMenu.showAt(e.getXY());
    }
    var grid = Ext.create('Ext.grid.Panel', {
        columns: columns,
        store: employeeStore,
        loadMask: true,
        bbar: pagingToolbar,
        plugins: [rowEditing], // 启用编辑插件
        stripeRows: true,
        selType: 'rowmodel',
        viewConfig: {
            forceFit: true,
        },
        listeners: {
            itemcontextmenu: doRowCtxMenu,
            destroy: function (thisGrid) {
                if(thisGrid.rowCtxMenu) {
                    this.rowCtxMenu.destroy()
                }
            }
        }
    })
    Ext.create('Ext.Window', {
        height: 350,
        width: 600,
        border: false,
        layout: 'fit',
        items: [grid]
    }).show();


})