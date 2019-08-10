/**
 * Created by DINGJUN on 2019.08.07.
 */
Ext.onReady(function () {
    /**
     * 6.表单
     */
    Ext.QuickTips.init();
    var fpItems = [
        {
            fieldLabel: 'alpha only',
            allowBlank: false,
            emptyText: 'this field is empty',
            maskRe: '/[a-z]/i', // 指定唯一子母
            msgTarget: 'side' // 校验提示信息的位置
        },
        {
            fieldLabel: 'simple 3 to 7 chars',
            allowBlank: false,
            msgTarget: 'under',
            minLength: 3,
            maxLength: 7
        },
        {
            fieldLabel: 'special chars only',
            msgTarget: 'qtip',
            stripCharsRe: '/[a-zA-Z0-9]/ig' // 正则表达式,只容许特殊字符
        },
        {
            fieldLabel: 'we only with VType',
            vtype: 'url',   // 使用url VType,对比记忆xtype
            msgTarget: 'side'
        }
    ];
    var fp = Ext.create('Ext.form.Panel', {
        renderTo: Ext.getBody(),
        width: 400,
        height: 240,
        title: 'exercising textfield',
        frame: true,
        bodyStyle: 'padding: 6px',
        labelWidth: 126,
        defaultType: 'textfield', // 输入框的默认xtype
        defaults: {
            msgTarget: 'side',
            anchor: '-20'
        },
        items: fpItems
    });

    /**
     * 表单执行校验的方式有两种:1.输入框失去焦点 2.表单的isValid方法的调用,fp.getForm().isValid().
     * 每一个输入框都有一个自己的msgTarget特性,可以是以下五种之一:
     * qtip:触发mouseover
     * title: 在默认浏览器的标题区显示报错信息
     * under: 把报错信息显示在输入框下
     * side:在输入框下面右侧渲染一个惊叹号图标
     * [elementid]:把报错信息的文本作为目标元素的innerHTML添加
     * 注意:提示的msgTarget属性只有在输入框位于一个输入框容器里面的时候,也就是表单面板里面的时候.
     */


    /**6.1.x
     * 密码和文件选择框
     */
    var fpItems = [
        {
            fieldLabel: 'Password', // 输入框前面的显示文字
            allowBlank: false,  // 是否可空
            inputType: 'password' // 输入框类型,密码类型
        }, {
            fieldLabel: 'File',
            allowBlank: false,
            xtype: 'filefield' // 输入框类型,文件类型
        }
    ]
    var fp = Ext.create('Ext.form.Panel', {
        renderTo: Ext.getBody(), // 创建的时候就渲染出来,如果延迟加载可以通过调用form.Panel的show方法.
        width: 400,
        height: 900,
        title: 'exercising password and filefield',
        frame: true,
        bodyStyle: 'padding:6px',
        labelWidth: 126,
        defaultType: 'textfield',
        defaults: {
            msgTarget: 'side',
            anchor: '-20'
        },
        items: fpItems
    })


    // 注:好奇怪,上面两个的表单都是通过Ext.getBody()进行渲染,然后页面居然显示出了两个表单,说明此种方式不会覆盖之前的页面元素


    /**
     * 构建多行文本框
     */
    var textarea = {
        xtype: 'textarea', // 输入框类型: textarea
        fieldLabel: 'my textArea',
        name: 'mytextarea',
        // anchor: '100%', // 导致了下面的两个输入框的添加看不见,还以为出现了错误...
        // height: 100
    }
    fp.add(textarea)


    /**
     * 便利的数字输入框
     */
    var numberfield = {
        xtype: 'numberfield', // 数字输入框,只能输入数字,默认的输入框类型就是textfield,其他的输入框都需要执行xtype类型,非也,form有defaultType属性指定items的输入框类型
        fieldLabel: 'numbers only',
        allowBlank: false,
        emptyText: 'this field is empty',
        decimalPrecision: 3, // 输入框的数据精度
        minValue: 0.001, // 最小值
        maxValue: 2 // 最大值
    }
    fp.add(numberfield)


    /**
     * 组合框实现提前输入
     */
    var mySimpleStore = {
        type: 'array',  // 构建数组数据存储
        fields: ['name'], // 数据列,类比数据库中的表字段
        data: [           // 数据,数据库中的数据
            ['jack slocum'],
            ['abe elias'],
            ['aaron conran'],
            ['evan trimboli']
        ]
    }
    var combo = {
        xtype: 'combo',
        fieldLabel: 'select a name',
        store: mySimpleStore, // 指定数据存储
        displayField: 'name', //设置显示文本框
        typeAhead: true,
        mode: 'local' // 组合框设置为本地模式, 默认是:remote
    }
    fp.add(combo)

    /**
     * 远程组合框
     */
    var remoteJsonStore = Ext.create(Ext.data.JsonStore, { // 一个store一定有两个属性1.proxy 2.reader 才能正常工作.
        storeId: 'people',
        fields: [
            'fullName',
            'id'
        ],
        proxy: {
            type: 'jsonp',
            url: 'http://extjsinaction.com/dataQuery.php',
            reader: {
                type: 'json',
                root: 'records',
                totalProperty: 'totalCount' // 启用分页功能,需要这个属性,在一个需要在combo的配置中配置pageSize
            }
        }
    })
    var combo = {
        xtype: 'combo',
        queryMode: 'remote',
        fieldLabel: 'search by name',
        // pageSize: 20,  // combo启用分页功能1.需要在远程返回的数据中添加totalProperty属性,2.combo的配置属性添加pageSize
        width: 320,
        forceSelection: true,
        displayField: 'fullName', // 输入下拉框的显示值
        valueField: 'id', // 输入框的值
        minChars: 1, // 配置自动填充阈值??
        triggerAction: 'all',
        store: remoteJsonStore
    }
    fp.add(combo)


    /**
     * 自定义组合框
     */
    var combo = Ext.create('Ext.form.field.ComboBox', {
        fieldLabel: 'Search by name',
        forceSelection: true,
        displayField: 'fullName',
        loadingText: 'Querying...',
        pageSize: 20, // 分页属性
        width: 320,
        minChars: 1,
        valueField: 'id',
        triggerAction: 'all',
        store: remoteJsonStore,
        listConfig: {
            getInnerTpl: function () { // combo自定义显示样式
                return '';
            }
        }
    })
    fp.add(combo)


    /**
     * 时间输入框
     */
    var timeinput = {
        xtype: 'timefield',
        fieldLabel: 'Please select time',
        // anchor: '100%'
        minvalue: '09:00', // 可以设置最小时间
        maxValue: '18:00', // 可以设置最大时间
        increment: 30,
        format: 'H:i'
    }
    fp.add(timeinput)

    /**
     * HTML编辑器
     */
    var htmlEditor = {
        xtype: 'htmleditor',
        fieldLabel: 'enter in any text',
        // anchor: '100% 100%'
        enableFontSize: false,
        enableFont: false
    }
    fp.add(htmlEditor)


    /**
     * html编辑器的校验问题
     */
    var htmlEditor = {
        fieldLabel: 'enter in any text',
        // anchor: '100% 100%',
        allowBlank: false,
        validate: function () { // 使用validate属性,通过回调进行校验
            var val = this.getValue();
            return (this.allowBlank || value.len > 1);
        }
    }
    fp.add(htmlEditor)


    /**
     * 选择日期
     */
    var dateField = {
        xtype: 'datefield',
        fieldLabel: 'Please select a date',
        // anchor: '100%'
    }
    fp.add(dateField)


    /**
     * 复选框和单选按钮
     */
    var checkboxes = [
        {
            xtype: 'checkbox',
            fieldLabel: 'whick do you own',
            boxLabel: 'Cat',
            inputValue: 'cat'
        }, {
            xtype: 'checkbox',
            fieldLabel: ' ',
            labelSeparator: ' ',
            boxLabel: 'Dog',
            inputValue: ''
        }, {
            xtype: 'checkbox',
            fieldLabel: ' ',
            labelSeparator: ' ',
            boxLabel: 'Fish',
            inputValue: 'fish'
        }, {
            xtype: 'checkbox',
            fieldLabel: ' ',
            labelSeparator: ' ',
            boxLabel: 'Bird',
            inputValue: 'bird'
        }
    ]
    fp.add(checkboxes)
    /**
     * 使用复选框组,相对上面的复选框,节省屏幕空间
     */
    var checkboxes = {
        xtype: 'checkboxgroup',
        fieldLabel: 'whick do you own',
        // anchor: '100%',
        items: [
            {
                boxLabel: 'Cat',
                inputValue: 'cat'
            },{
                boxLabel: 'Dog',
                inputValue: 'dog'
            },{
                boxLabel: 'Fish',
                inputValue: 'fish'
            },{
                boxLabel: 'Bird',
                inputValue: 'bird'
            }
        ]
    }
    fp.add(checkboxes)


    /**
     * 通过字段集构建复杂的表单
     */
    var fieldset1 = { // 1.创建第一个字段集:fieldset
        xtype: 'fieldset',
        title: 'Name',
        flex: 1,
        border: false,
        labeWidth: 60,
        defaultType: 'field',
        defaults: {
            anchor: '-10',
            allowBlank: false
        },
        items: [
            {
                fieldLabel: 'First',
                name: 'firstName'
            },{
                fieldLabel: 'Middle',
                name: 'middle'
            }, {
                fieldLabel: 'Last',
                name: 'last'
            }
        ]
    }
    var fieldset2 = Ext.apply({}, { // 1.创建第二个字段集:fieldset
        flex: 1,
        labelWidth: 30,
        title: 'Address Information',
        defaults: {
            layout: 'column',
            anchor: '100%'
        },
        items: [
            {
                fieldLabel: 'Address',
                name: 'address'
            },
            {
                fieldLabel: 'Street',
                name: 'street'
            },
            {
                xtype: 'container', //添加了列的布局容器
                columnWidth: .5,
                items: [
                    {
                        xtype: 'textfield', // 配置州单行文本行
                        fieldLabel: 'State',
                        name: 'state',
                        labelWidth: 100,
                        width: 150
                    },
                    {
                        xtype: 'fieldcontainer',
                        columnWidth: .5,
                        items: [
                            {
                                xtype: 'textfield', // 设置邮政编码单行文本行
                                fieldLabel: 'Zip',
                                name: 'zip',
                                labelWidth: 30,
                                width: 162
                            }
                        ]
                    }
                ]
            }
        ]
    }, fieldset1)
    var fieldsetContainer = { // 1.准备一个表单,含有两个字段集:fieldset在这里的使用,并没有放在form.Panel里面,而是container容器中,当然,form.Panel也是集成自container中的
        xtype: 'container',
        layout: 'hbox',
        layoutConfig: {
            align: 'stretch'
        },
        items: [fieldset1, fieldset2]
    }
    var tabs = [    // 2.准备一个tabPanel
        {
            xtype: 'fieldcontainer',
            title: 'Phone Number',
            layout: 'form',
            bodyStyle: 'padding:6px 6px 0',
            defaults:{
                xtype: 'textfield',
                width: 230
            },
            items: [
                {
                    fieldLabel: 'Home',
                    name: 'home'
                },
                {
                    fieldLabel: 'Business',
                    name: 'business'
                }, {
                    fieldLabel: 'Phone',
                    name: 'phone'
                }, {
                    fieldLabel: 'Fax',
                    name: 'fax'
                }
            ]
        }, {
            title: 'Resume',
            xtype: 'htmleditor',
            name: 'resume'
        }, {
            title: 'Bio',
            xtype: 'htmleditor',
            name: 'bio'
        }
    ];
    var tabPanel = {    // 2.准备一个tabPanel
        xtype: 'tabpanel',
        activeTab: 0,
        deferredRender: false,
        layoutOnTabChange: true,
        border: false,
        flex: 1,
        plain: true,
        items: tabs
    }
    var myFormPanel = Ext.create('Ext.form.Panel', {    // 3.复杂的表单显示
        renderTo: Ext.getBody(),
        width: 800,
        title: 'Our complex form',
        frame: true, // 面板可以有圆角效果,内容面板有css样式效果
        id: 'myFormPanel',
        // layout: 'vbox', // TODO:这个布局有毒,使用之后tabPanel不能正常显示
        layoutConfig: {
            align: 'stretch'
        },
        items:[
            fieldsetContainer,
            tabPanel
        ]
    })


    /**
     * ===6.8 数据的提交和加载===
     */
    // 传统方式 Ext.getCmp('myFormPanel').getForm().submit()
    // 提交一个表单,必须访问BasicPanel组件,为此使用访问方法getForm()或者formPanel.getForm().这样就可以访问BasicForm的的submit方法
    // 注意:表单的提交是通过Ajax发送请求的
    var onSuccessOrFail = function (form, action) {
        var formPanel = Ext.getCmp('myFormPanel');
        formPanel.el.unmask();
        var result = action.result;
        if(result.success) {
            ext.MessageBox.alert('Success', action.result.msg);
        } else {
            Ext.MessageBox.alert('Failure', action.result.msg)
        }
    };
    var submitHandler = function () {
        var formPanel = Ext.getCmp('myFormPanel');
        formpanel.el.mask('Please wait', 'x-mask-loading');
        formpanel.getForm().submit({
            url: '',
            success: onSuccessOrFail,
            failure: onSuccessOrFail
        })
    }





})