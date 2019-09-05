/**
 * Created by DINGJUN on 2019.08.03.
 */
Ext.onReady(function () {
    /**
     * 树形面板测试练习
     */
    /**
     * 术语:
     * 树形面板(Tree Panel):一个拥有Ext.panel.Panel的容器,用于将层次数据渲染成树形格式
     * 树视图(Tree View):负责渲染和操作的树的dom组件
     * 树(Tree):代表项目的层次
     * 节点(Node):层次中一个项目.Ext4中一个节点就表示一个Ext.data.NodeInterface配置
     * 父节点(Parent):作为参照物的节点所属的节点.根(Root)是最高级别的父节点
     * 孩子节点(Child):另一个孩子节点的直接后代.所有节点都是根节点的孩子节点
     * 根(Root):包含了第一层和其它各层的所有节点(孩子节点)
     * 叶子(Leaf):没有孩子节点的集合
     * 分支(Branch):直接共享同一个父节点的集合
     * 深度(Depth):从该节点到根节点的距离
     */

    /**
     * 第一个课树
     */
    var store = Ext.create('Ext.data.TreeStore', {
        root: {
            text: 'Root Node',
            expanded: true,
            children: [
                {
                    text: 'child 1',
                    leaf: true,
                },
                {
                    text: 'child 2',
                    leaf: true,
                },
                {
                    text: 'child 3',
                    children: [
                        {
                            text: 'Grand child 1',
                            children: [
                                {
                                    text: 'grand... you get the point',
                                    leaf: true,
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    })
    Ext.create('Ext.window.Window', {
        title: 'our first tree',
        width: 180,
        height: 220,
        autoShow: true, // 配置之后没有调用这个组件的show方法也可以显示出来
        layout: 'fit',
        items: {
            xtype: 'treepanel',
            border: false,
            store: store,
            rootVisible: true,
        }
    })


    /**
     * 创建一个远程加载的面板
     */
    var remoteStore = Ext.create('Ext.data.TreeStore', {
        autoSync: true,
        proxy: {
            type: 'ajax',
            url: '/tree/read' // 访问服务会自动给域名后面添加端口,导致访问不了数据???
        },
        root: {
            text: 'my company1',
            id: 'mycompany',
            expanded: true,
        }
    })
    Ext.create('Ext.window.Window', {
        title: 'remote tree',
        layout: 'fit',
        autoShow: true,
        height: 360,
        width: 280,
        items: {
            xtype: 'treepanel',
            store: remoteStore
        }
    })
})