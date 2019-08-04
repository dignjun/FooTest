/**
 * Created by DINGJUN on 2019.08.03.
 */
Ext.onReady(function () {
    /**
     * Ext对dom元素的操作
     */
    var myDiv1 = Ext.get('div1');
    //设置div的高度
    myDiv1.setHeight(60);
    // 方法效果等同于setHeight,setWidth，同时提供的第三个参数可以让设置效果存在过度的效果
    myDiv1.setSize(600, 100, {duration: 3000 , easing: 'bounceOut'});

    // 为div的innerHTML中添加字符节点--也就是innerHTML中添加字符串内容
    // createChild ( config, [insertBefore], [returnDom] ) : Ext.dom.Element/HTMLElement
    // 1.插入元素的配置对象 2.插入元素的位置 3.是否是替代某一个是元素，而不是创建一个元素，默认是false
    myDiv1.createChild('Child from a string');

    // 为div添加子元素 -- 1.可以通过在innerHTML中添加的字符串内容中携带div标签
    myDiv1.createChild('<div>Element from a string</div>')

    // 为div添加子元素 -- 2.通过对象进行配置
    myDiv1.createChild({
        tag: 'div',
        html: 'Child from a config object'
    })

    // 为div添加嵌套子元素 -- 2.通过对象配置实现
    myDiv1.createChild({
        tag: 'div',
        id : 'nestedDid',
        style: 'border:1px dashed; padding:5px',
        children: {
            tag: 'div',
            html: '... a nested div',
            style: 'color:#ee000; border:1px solid'
        }
    })

    // 列表的顶端（位置0）插入子节点
    myDiv1.insertFirst({
        tag: 'div',
        html: 'Child inserted as node 0 of myDiv'
    })

    // 插入到特定的位置
    myDiv1.createChild({
        tag: 'div',
        id: 'removeMeLater',
        html: 'Child inserted as node 2 of myDiv'
    }, myDiv1.dom.childNodes[3])

    // 删除子节点
    var mydiv1 = Ext.get('div2');
    var firstChild = mydiv1.down('div:first-child');
    firstChild.remove();

    var mydiv1 = Ext.get('div2');
    var firstChild = mydiv1.down('div:last-child');
    firstChild.remove();

    // 配合Ext.Element使用Ajax
    Ext.getBody().load({
        url: 'htmlfragment.html',
        script: true
    })
})
