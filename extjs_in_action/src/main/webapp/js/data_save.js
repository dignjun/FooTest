/**
 * Created by DINGJUN on 2019.08.11.
 */
Ext.onReady(function () {
    /**
     * 第七章: 数据存储
     * 主要介绍的是Ext.data.Store和它的支持类,包括Ext.data.Model,以及数据的流动,数据的使用和存储
     *
     * 7.1 数据的存储
     * Ext.data.Store用途是提供一个基础,可以存储服务器山数据的一个本地子集,并在将数据发回服务器之间跟踪这一数据的变化(如果应用允许编辑数据)
     *
     * 7.1.1 支持类
     * data Store类似一个接口类.数据存储本身负责组织列举数据,但它主要是管理其它类.
     * 常见有的Store:用于读取和保存数据的接口类
     *        Proxy:管理数据被输入的数据存储的典型方式
     *        Reader:用来转化入站的数据,以便供给Model的实例
     *        Model: 代表对应一个特定数据存储的独立数据行
     * 等.
     *
     * 7.1.2 数据的流动
     * 数据源(服务端的数据) -- 代理(Proxy) -- 读取器(Reader) -- 记录(Model) -- 数据存储(Store) -- 数据使用
     */

    /**
     * 7.2 读取数组数据
     */
    var combofield = {
        xtype: 'combo',
        name: 'title',
        fieldLabel: 'Title',
        queryModel: 'local',
        valueField: 'title',
        store: ['mr.', 'ms.', 'dr'] // 最简单的应用:把内联的数据读入到一个组件里面,这里的使用一行声明了一个数组类型的Store
    }
    var fp = Ext.create('Ext.form.Panel', {
        width: 300,
        title: 'combo中数组Store的演示',
        frame: true,
        items: combofield,
        renderTo: Ext.getBody()
    })

    // 创建一个读取本地数组数据的数据存储
    var arrayData = [   // 创建本地数据数组(二维数组):[[],[]]
        ['jay garcia', 'md'],
        ['aaron baker', 'va'],
        ['susan smith', 'dc'],
        ['mary stein', 'de'],
        ['bryan shanley', 'nj'],
        ['nyri selgado', 'ca']
    ]
    Ext.define('User', {    // 创建一个Model
        extend: 'Ext.data.Model',
        fields: [
            {
                name: 'name',   // 第一个字段
                mapping: 1
            },
            {
                name: 'state',  // 第二个字段
                mapping: 2
            }
        ]
    })
    var store = Ext.create('Ext.data.Store', {   // 创建一个Store
        model: 'User',
        proxy: {
            type: 'memory',
            reader: {
                model: 'User',
                type: 'array'
            }
        }
    })
    store.loadData(arrayData);
    console.log(store.first().data);
    // 使用便利类ArrayStore重构上面的代码
    var store2 = Ext.create('Ext.data.ArrayStore', {
        data: arrayData,
        fields: ['personName', 'state']
    });
    console.log(store2.first().data);


    // 读取JSON数据
    var departmentStore = Ext.create('Ext.data.Store', {
        fields: [   // 这里其实是一个Model的便捷写法形式
            'name',
            'active',
            'dateActive',
            'dateInactive',
            'description',
            'director',
            'numEmployees',
            {
                name: 'id',
                type: 'int'
            }
        ],
        proxy: {
            type: 'ajax',
            url: 'data.json',
            reader: {
                type: 'json',
                root: 'data',
                idProperty: 'id',
                successProperty: 'meta.success'
            }
        }
    })
    departmentStore.load({
        callback: function (records, operation, successful) {
            if(successful) {
                console.log('department name:', records[0].get('name'))
            } else {
                console.log('the serve report an error')
            }
        }
    })

    // 读取xml数据,上面的实例需要修改两处
    // 1. proxy-reader-type:'xml'
    // 2. proxy-reader-successProperty:'meta/success'


    /**
     * 7.3.1 校验模型数据
     * ext中不仅可以指定模型内字段的预期值和格式外,还可以在任何一次尝试更新数据存储之前运行校验
     * 一个Ext.data.Field可能包含:
     *      一个名称
     *      一个类型(自动类型,字符串类型,整型,浮点型, 布尔型,日起类型)
     *      一个defaultValue
     *      一个转换函数(用来转换一条输入记录)
     */

})