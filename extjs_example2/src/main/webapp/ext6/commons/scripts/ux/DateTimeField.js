Ext.define('commons.ux.DateTimeField', {
	extend : 'Ext.form.field.Date',
	alias : 'widget.datetimefield',
	format : "m/d/Y",
	getValue: function() {
		var me = this;
		var raw = me.callParent();
		if(raw){
			return Ext.Date.format(raw, this.format);
		}
		return null;
	}
})