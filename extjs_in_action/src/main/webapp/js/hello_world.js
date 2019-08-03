/**
 * Created by DINGJUN on 2019.08.03.
 */
var tp1 = Ext.create('Ext.Template', [
    'Hello {firstName} {lastName}',
    'Nice to meet you'
])
var formpanel = Ext.create('Ext.form.FormPanel', {
    itemId: 'formPanel',
    frame: true,
    layout: 'anchor',
    defaultType: 'textfield',
    defaults: {
        anchor: '-10',
        labelWidth: 65
    },
    items: [{
        fieldLabel: 'First name',
        name: 'firstname',
        height: 25
    }, {
        fieldLabel: 'Last name',
        name: 'lastname',
        height: 25
    }],
    buttons: [{
        text: 'Submit',
        handler: function () {
            var formPanel = this.up('#formPanel'),
                vals = formPanel.getValues(),
                greeting = tp1.apply(vals);     // 为Template赋值，通过数组完成
            Ext.Msg.alert('Hello', greeting);
        }
    }]
})
Ext.onReady(function () {

    Ext.create('Ext.window.Window', {
        height: 125,
        width: 200,
        closable: true,
        title: 'Input needed',
        border: false,
        layout: 'fit',
        items: formpanel    // window里面放着的组件是一个formpanel
    }).show()

})