// Ext.define('DataFormPanel', {
// 	extend : 'Ext.form.Panel',
// 	alias : 'widget.dataform',
// 	initComponent : function() {
// 		this.on({
// 			beforeaction : {
// 				fn : function(me, action, eOpts) {
// 					var values = me.getFieldValues();
// 					if (values) {
// 						action.params = action.params || {};
// 						action.params.data = Ext.encode(values);
// 					}
// 				},
// 				scope : this
// 			}
// 		});
// 		this.callParent();
// 	}
// });

// extjs3中的dataUrl在extjs6中的实现
var _initComponent = Ext.tree.Panel.prototype.initComponent
    || Ext.tree.Panel.prototype.initComponent
Ext.override(Ext.tree.Panel, {
    initComponent: function () {
        if (this.dataUrl) {
            this.store = new Ext.data.TreeStore({
                proxy: {
                    type: 'ajax',
                    url: this.dataUrl
                },
                root: {
                    id: '0',
                    text: 'root',
                    iconCls: 'icon-ontology-root'
                }
            })
        }
        return _initComponent.apply(this, arguments)
    }
});
var _initComponentJsonStore = Ext.data.JsonStore.prototype.constructor
Ext.override(Ext.data.JsonStore, {
    constructor: function (cfg) {
        if (cfg.url) {
            cfg.proxy = {
                type: 'ajax',
                url: cfg.url,
                reader: {
                    type: 'json',
                    implicitIncludes: false
                }
            }
            if (cfg.root) {
                cfg.proxy.reader.rootProperty = 'data';
            }
        }
        return _initComponentJsonStore.apply(this, arguments)
    }
});

Function.prototype.dg = function (obj, args, appendArgs) {
    var method = this;
    return function () {
        var callArgs = args || arguments;
        if (appendArgs === true) {
            callArgs = Array.prototype.slice.call(arguments, 0);
            callArgs = callArgs.concat(args);
        } else if (Ext.isNumber(appendArgs)) {
            callArgs = Array.prototype.slice.call(arguments, 0); // copy arguments first
            var applyArgs = [appendArgs, 0].concat(args); // create method call params
            Array.prototype.splice.apply(callArgs, applyArgs); // splice them in
        }
        return method.apply(obj || window, callArgs);
    };
};

function getLicence() {
    if (!window.__licence) {
        var res = http_get('licence.action');
        if (res) {
            window.__licence = Ext.decode(res)
        }
    }
    return window.__licence
}

function isVersionStandard(strict) {
    var licence = getLicence();
    var ret = licence && licence.edition == 0;
    if (ret && strict !== true) {
        var un = getUsername();
        if (un == 'INCE.SUPERVISOR') {
            ret = false
        }
    }
    return ret;
}

function LoginUser(ns) {
    this.getUsername = function () {
        return window[(ns ? ns : '') + '_username'];
    }
    this.allow = function (permissionName) {
        var r = window[(ns ? ns : '') + '_check_perm_result'];
        var ret = r.indexOf(permissionName) != -1 || r.indexOf('ALL') != -1 || this.isSupervisor();
        if (!ret) {// check ALL perm
            if (!this.allowx(permissionName))
                return false;
            var paths = permissionName.split('.');
            var perm = paths[paths.length - 1];
            for (var i = 1; i < paths.length; i++) {
                paths[i] = paths[i - 1] + '.' + paths[i]
            }
            for (var i = 0; i < paths.length; i++) {
                if (r.indexOf(paths[i] + '.ALL') != -1) {
                    return true;
                }
            }
            if (paths.length > 1) {
                for (var i = 0; i < paths.length - 1; i++) {
                    if (r.indexOf(paths[i] + '.' + perm) != -1) {
                        return true;
                    }
                }
            }
        }
        return ret
    }
    this.find = function (permissionNamePattern) {
        var r = window[(ns ? ns : '') + '_check_perm_result']
        var ret = []
        for (var i = 0; i < r.length; i++) {
            if (r[i].match(permissionNamePattern))
                ret.push(r[i]);
        }
        return ret
    }
    this.filterAllowed = function (objlist, authNamePath, authNamePatternPath) {
        authNamePath = authNamePath || 'authName'
        authNamePatternPath = authNamePatternPath || 'authNamePattern'
        var hierarchy = authNamePath.indexOf('.') != -1;
        var hierarchyP = authNamePatternPath.indexOf('.') != -1;
        var lastobj;
        var curversion = isVersionStandard() ? 's' : 'e'
        for (var i = objlist.length - 1; i >= 0; i--) {
            // auth
            var authName;
            var removeMe = false;
            if (hierarchy) {
                authName = eval('(objlist[i].' + authNamePath + ')')
            } else {
                authName = objlist[i][authNamePath]
            }
            if (authName) {
                if (!this.allow(authName))
                    removeMe = true
            } else {
                var authNamePattern;
                if (hierarchyP) {
                    authNamePattern = eval('(objlist[i].' + authNamePatternPath + ')')
                } else {
                    authNamePattern = objlist[i][authNamePatternPath]
                }
                if (authNamePattern && !this.find(authNamePattern).length)
                    removeMe = true
            }
            // version check
            if (!removeMe && objlist[i].authVersion && objlist[i].authVersion != curversion) {
                removeMe = true
            }
            if (removeMe || objlist[i] == '-' && (lastobj == null || lastobj == '-')) {
                objlist.splice(i, 1);
            } else
                lastobj = objlist[i]
        }
        return objlist
    }
    this.allowx = function (perm) {
        if (!isVersionStandard())
            return true;
        var rs = this.xrules;
        if (!rs) {
            var rsarray = Ext.decode(http_get('property!getAuthPermRulex.action'))
            this.xrules = rs = []
            Ext.each(rsarray, function (r) {
                var rpart = r.split(' ')
                rs.push({
                    pattern: new RegExp('^' + rpart[0] + '$'),
                    perm: rpart[1]
                })
            }, this)
        }
        var result = null;
        Ext.each(rs, function (r) {
            if (r.pattern.test(perm)) {
                result = r.perm;
                return false;
            }
        }, this)
        if ('-' != result)
            return true;
    }
    this.allowPrivilege = function (privilege) {
        if (window.user_privilege == null) {
            window.user_privilege = http_get('../authmgr/auth!getPrivilege.action')
            !window.user_privilege ? window.user_privilege = 0 : parseInt(window.user_privilege)
        }
        return window.user_privilege ? (window.user_privilege & privilege) == privilege : false;
    }
    this.isAdmin = function () {
        return this.getUsername() == 'admin'
    }
    this.isSupervisor = function () {
        return this.getUsername() == 'INCE.SUPERVISOR'
    }
}

//修正extjs在启用cookie state provider以后默认会根据id判断是否持久化界面窗口状态，从而导致自定义id的界面的状态被过量保存
Ext.override(Ext.Component, {
    getStateId: function () {
        return this.stateful ? (this.stateId || ((/^(ext-comp-|ext-gen)/).test(String(this.id)) ? null : this.id)) : null;
    }
});
(function () {
    var capitalize = i18n.cap
    // === 国际化 ===
    // 所有组件的文字
    var extfieldc = Ext.Component.prototype.initComponent
    Ext.override(Ext.Component, {
        initComponent: function () {
            var config = this
            Ext.each('title text fieldLabel emptyText blankText invalidText boxLabel tooltip beforePageText afterPageText displayMsg emptyMsg'.split(' '), function (k) {
                config[k] && typeof config[k] == 'string' && config[k].indexOf('#') == 0 ? config[k] = i18n.ts(config[k]) : 0
                config[k] = capitalize(config[k])
            })
            Ext.each('width height'.split(' '), function (k) {
                config[k] && typeof config[k] == 'string' && config[k].indexOf('#') == 0 ? config[k] = parseInt(i18n.ts(config[k])) : 0
            })
            extfieldc.apply(this, arguments)
        }
    })
    //TODO:i18n pagingtoolbar
    // var ptb_initc = Ext.PagingToolbar.prototype.initComponent
    // Ext.override(Ext.PagingToolbar, {
    //     initComponent : function() {
    //         if (this.localeSuite) {
    //             this.beforePageText = capitalize(i18n.t('paging.befpagetxt'))
    //             this.afterPageText = capitalize(i18n.t('paging.aftpagetxt'))
    //             this.displayMsg = capitalize(i18n.t('paging.dismsg'))
    //             this.emptyMsg = capitalize(i18n.t('paging.emptymsg'))
    //         }
    //         ptb_initc.apply(this, arguments)
    //     }
    // })
    // treenode
    var _treep_init = Ext.tree.Panel.prototype.initComponent
    Ext.override(Ext.tree.Panel, {
        initComponent: function () {
            function transform(n) {
                n.text = i18n.ts(n.text)
                if (n.children) {
                    Ext.each(n.children, function (nchild) {
                        transform(nchild)
                    })
                }
            }

            if (this.root) {
                transform(this.root)
            }
            _treep_init.apply(this, arguments)
        }
    })
    // grid column
    var _cmsc = Ext.grid.ColumnModel.prototype.setConfig
    Ext.override(Ext.grid.ColumnModel, {
        setConfig: function (columns, initial) {
            if (!this.i18ned) {
                Ext.each(columns, function (c) {
                    c.header = capitalize(i18n.ts(c.header))
                    Ext.each('width height'.split(' '), function (k) {
                        c[k] && typeof c[k] == 'string' && c[k].indexOf('#') == 0 ? c[k] = parseInt(i18n.ts(c[k])) : 0
                    })
                })
                this.i18ned = true
            }
            return _cmsc.apply(this, arguments)
        }
    })
    // this.el.mask
    var _elmask = Ext.Element.prototype.mask
    Ext.override(Ext.Element, {
        mask: function (msg, msgCls) {
            msg = capitalize(i18n.ts(msg))
            return _elmask.apply(this, arguments)
        }
    })
    // setAlert
    Ext.onReady(function () {
        // Ext.Msg.*
        var e = Ext.MessageBox
        var _p = e.progress, _a = e.alert, _c = e.confirm, _pr = e.prompt, _s = e.show
        e.progress = function (title, msg, progressText) {
            title = capitalize(i18n.ts(!title ? '#extmsg.progress.title' : title))
            return _p.apply(e, arguments)
        }, e.alert = function (title, msg, fn, scope) {
            title = capitalize(i18n.ts(!title ? '#extmsg.alert.title' : title))
            return _a.apply(e, arguments)
        }, e.confirm = function (title, msg, fn, scope) {
            title = capitalize(i18n.ts(!title ? '#extmsg.confirm.title' : title))
            return _c.apply(e, arguments)
        }, e.prompt = function (title, msg, fn, scope, multiline, value) {
            title = capitalize(i18n.ts(!title ? '#extmsg.prompt.title' : title))
            return _pr.apply(e, arguments)
        }, e.show = function (options) {
            options.title = capitalize(i18n.ts(options.title))
            options.message = capitalize(i18n.ts(options.message))
            options.msg = capitalize(i18n.ts(options.msg))
            return _s.apply(e, arguments)
        }
        if (window.Dashboard) {
            var setalert = Dashboard.setAlert
            Dashboard.setAlert = function (msg, status, delay) {
                msg = capitalize(i18n.ts(msg))
                return setalert.apply(Dashboard, arguments)
            }
        }
    })
})()


function ProgressTimer(cfg) {
    var self = this;
    this.progressURL = cfg.progressURL;
    this.progressId = cfg.progressId;
    this.progress = cfg.progress;
    if (!cfg.initData || cfg.initData.active == null) {
        throw new Error('initData.active is required!');
    }
    this.active = cfg.initData.active;
    this.initData = cfg.initData;
    this.progressId = cfg.initData.id;
    this.progressGroup = cfg.initData.group;
    var progressText = cfg.progressText == 'percent' ? function (c, t) {
        return (t <= 0 ? 0 : parseInt(c * 100 / t)) + '%'
    } : function (c, t) {
        return '';
    };
    var cb_finish = cfg.finish;
    var cb_inactive = cfg.inactive;
    var cb_error = cfg.error;
    var cb_poll = cfg.poll;
    var cb_scope = cfg.scope || this;

    cfg.boxConfig = cfg.boxConfig || {};
    this.updateButtonVisibility = function (progress) {
        if (!this.msgbox)
            return
        // this.msgbox.getDialog().getFooterToolbar().hide()
        Ext.each(this.msgbox.msgButtons, function (btn) {
            if (progress && btn.text != i18n.t('cancel') && progress.finished)
                btn.enable()
            else if (progress && btn.text == i18n.t('cancel') && !progress.finished && progress.cancelable) {
                if (cfg.isCancelDisabled) {
                    btn.disable();
                } else {
                    btn.enable();
                }
            } else
                btn.disable()
        })
        // this.msgbox.getDialog().getFooterToolbar().show()
        // this.msgbox.getDialog().hide()
        // this.msgbox.getDialog().show()
    }
    this.createMsgbox = function (config) {
        config = Ext.apply(config || {}, cfg.boxConfig)
        config.message = config.title;
        var m = Ext.Msg.show(Ext.apply({
            title: '#import',
            progress: typeof(self.progress) == 'undefined' ? true : self.progress,
            progressText: '',
            modal: false,
            width: 300,
            fn: function (result) {
                if (result == 'cancel') {
                    Ext.Ajax.request({
                        url: (self.progressURL || '../progress-timer!cancelProgress.action'),
                        params: {
                            progressId: self.progressId,
                            data: Ext.encode({group: self.progressGroup || ''})
                        },
                        success: function () {
                        },
                        failure: function () {
                        }
                    })
                    m.show()
                }
            }
        }, config));

        if (!window.__before_show_bind) {
            window.__before_show_bind = true;
            m.on('beforeshow', function () {
                Ext.each(m.msgButtons, function (btn) {
                    btn.enable()
                })
            })
        }
        return m
    };
    this.msgbox = this.createMsgbox();
    this.updateButtonVisibility()
    this.doWithoutCenter = function (cb) {
        var fn = Ext.Window.prototype.center
        try {
            Ext.Window.prototype.center = Ext.emptyFn
            cb ? cb() : 0
        } finally {
            Ext.Window.prototype.center = fn
        }
    }
    var _requestImportProgress = function () {
        Ext.Ajax.request({
            url: (self.progressURL || '../progress-timer!getProgress.action'),
            params: {
                progressId: self.progressId,
                data: Ext.encode({group: self.progressGroup || ''})
            },
            success: function (resp) {
                if (!this.msgbox)
                    return;
                var progress = Ext.util.JSON.decode(resp.responseText);
                var value = progress.totalCount ? (progress.currentCount / progress.totalCount) : 0
                var text = progressText(progress.currentCount, progress.totalCount)
                progress.message ? progress.message = progress.message.replace(/\r\n/g, '<br>') : 0
                if (typeof cb_poll == 'function')
                    cb_poll.apply(cb_scope, [progress, resp])
                if (progress.finished) {
                    var ok = null;
                    if (typeof cb_finish == 'function') {
                        ok = cb_finish.apply(cb_scope, [progress, resp])
                    }
                    if (progress.multilineMsg) {
                        this.createMsgbox({
                            width: 600
                        })
                        var that = this;
                        this.doWithoutCenter(function () {
                            // this.msgbox.updateProgress(1, progressText(progress.totalCount, progress.totalCount), progress.message);
                            that.msgbox.updateProgress(1, progressText(progress.totalCount, progress.totalCount), progress.message +
                                Ext.String.format(DetailMsgBoxFormat, progress.multilineMsg));
                        })
                        // addMsgBoxDetail(progress.multilineMsg)
                    } else if (ok !== false) {
                        var that = this;
                        this.doWithoutCenter(function () {
                            that.msgbox.updateProgress(1, progressText(progress.totalCount, progress.totalCount), progress.message);
                        })
                    }
                    clearTimeout(this.importTimer);
                    this.updateButtonVisibility(progress);
                } else {
                    var that = this;
                    this.doWithoutCenter(function () {
                        that.msgbox.updateProgress(value, text, progress.message);
                    })
                    this.beginSchedule();
                    this.updateButtonVisibility(progress)
                }
            },
            failure: function (resp) {
                if (!this.msgbox)
                    return;
                if (typeof cb_error == 'function')
                    cb_error.call(cb_scope, resp);
                else
                    clearTimeout(this.importTimer);
                this.updateButtonVisibility({
                    finished: true
                })
            },
            scope: self
        });
    }
    this.pause = function () {
        if (this.importTimer)
            clearTimeout(this.importTimer);
        if (this.msgbox) {
            this.msgbox.hide();
            delete this.msgbox
        }
    }
    this.restart = function (delay) {
        this.msgbox = this.createMsgbox();
        if (delay)
            _requestImportProgress.defer(delay, this);
        else
            this.beginSchedule();
    }
    this.beginSchedule = function () {
        if (this.importTimer)
            clearTimeout(this.importTimer);
        this.importTimer = window.setTimeout(function () {
            _requestImportProgress();
        }, 1000);
    }

    this.start = function () {
        if (!this.active)
            if (typeof cb_inactive == 'function')
                cb_inactive.call(cb_scope)
            else
                Ext.Msg.alert('', this.initData && this.initData.message ? this.initData.message : i18n.t('uni.valid-interact'));
        else {
            this.msgbox.updateProgress(0, progressText(0, 0), '');
            this.beginSchedule();
        }
        return this.active;
    }

    this.hide = function () {
        this.msgbox.hide();
    }
}

ProgressTimer.createTimer = function (config, cb) {
    if (!(config instanceof Array)) {
        config = [config]
    }

    function poll() {
        if (!config.length)
            return
        var c = config.shift();
        if (c.initData) {
            if (cb) {
                var tm = new ProgressTimer(c);
                if (typeof cb == 'function')
                    cb(tm)
                else
                    tm.start()
            }
            return
        }
        Ext.Ajax.request({
            url: (c.progressURL || '../progress-timer!getProgress.action'),
            params: {
                progressId: c.progressId,
                data: c.progressQuery ? Ext.encode(c.progressQuery) : ''
            },
            success: function (resp) {
                var progress = Ext.util.JSON.decode(resp.responseText);
                if (!progress)
                    return;
                if (!progress.finished && cb) {
                    c.initData = progress;
                    var tm = new ProgressTimer(c);
                    if (typeof cb == 'function') {
                        cb(tm)
                    } else {
                        tm.start();
                    }
                } else
                    poll();
            }
        });
    }

    poll();
}


Ext.namespace('ux')
ux.util = {
    resetEmptyString: function (obj) {
        for (var key in obj) {
            var v = obj[key];
            if (!v) {
                delete obj[key];
            } else if (v.push && v.length) {
                for (var i = 0; i < v.length; i++) {
                    this.resetEmptyString(v[i]);
                }
            } else if (key.indexOf('id') != -1 && key.indexOf('-') == 0) {
                delete obj[key]// reset negative id as null
            }
        }
        return obj;
    },
    isEmptyRecord: function (r) {
        return ux.util.isEmptyData(r.data)
    },
    isEmptyData: function (data, excludeFields) {
        for (var key in data) {
            if (key && data[key]) {
                if (!excludeFields || excludeFields.indexOf(key) == -1) {
                    if (data[key] instanceof Array) {
                        if (data[key].length)
                            return false
                    } else {
                        return false
                    }
                }
            }
        }
        return true
    }
}

Ext.DomObserver = Ext.extend(Object, {
    constructor: function (config) {
        this.listeners = config.listeners ? config.listeners : config;
    },

    // Component passes itself into plugin's init method
    init: function (c) {
        var p, l = this.listeners;
        for (p in l) {
            if (Ext.isFunction(l[p])) {
                l[p] = this.createHandler(l[p], c);
            } else {
                l[p].fn = this.createHandler(l[p].fn, c);
            }
        }

        // Add the listeners to the Element immediately following the render call
        c.render = c.render.createSequence(function () {
            var e = c.getEl();
            if (e) {
                e.on(l);
            }
        });
    },

    createHandler: function (fn, c) {
        return function (e) {
            fn.call(this, e, c);
        };
    }
});


//<!-- override  修复extjs的row.isModel = null 的 bug -->

Ext.override(Ext.grid.CellContext, {
    setRow: function (row) {
        var me = this,
            dataSource = me.view.dataSource,
            oldRecord = me.record,
            count;
        if (row !== undefined && row !== null) {
            // Row index passed, < 0 meaning count from the tail (-1 is the last, etc)
            if (typeof row === 'number') {
                count = dataSource.getCount();
                row = row < 0 ? Math.max(count + row, 0) : Math.max(Math.min(row, count - 1), 0);
                me.rowIdx = row;
                me.record = dataSource.getAt(row);
            }
            // row is a Record
            else if (row.isModel) {
                me.record = row;
                me.rowIdx = dataSource.indexOf(row);
            }
            // row is a grid row, or Element wrapping row
            else if (row.tagName || row.isElement) {
                me.record = me.view.getRecord(row);
                me.rowIdx = dataSource.indexOf(me.record);
            }
        }
        if (me.record !== oldRecord) {
            me.generation++;
        }
        return me;
    }
})
Ext.override(Ext.view.Table, {
    getDefaultFocusPosition: function (fromComponent) {
        var me = this, store = me.dataSource, focusPosition = me.lastFocused,
            newPosition = new Ext.grid.CellContext((me.isNormalView
                && me.lockingPartner.grid.isVisible() && !me.lockingPartner.grid.collapsed)
                ? me.lockingPartner
                : me).setPosition(0, 0), targetCell, scroller;
        if (fromComponent) {
            // Tabbing in from one of our column headers; the user will expect to land in that column.
            // Unless it is configured cellFocusable: false
            if (fromComponent.isColumn && fromComponent.cellFocusable !== false
                && fromComponent.getView() === me) {
                if (!focusPosition) {
                    focusPosition = newPosition;
                }
                focusPosition.setColumn(fromComponent);
            }
            // Tabbing in from the neighbouring TableView (eg, locking).
            // Go to column zero, same record
            else if (fromComponent.isTableView && fromComponent.lastFocused) {
                focusPosition = new Ext.grid.CellContext(me).setPosition(
                    fromComponent.lastFocused.record, 0);
            }
        }
        // We found a position from the "fromComponent, or there was a previously focused context
        if (focusPosition) {
            scroller = me.getScrollable();
            // Record is not in the store, or not in the rendered block.
            // Fall back to using the same row index.
            if (!store.contains(focusPosition.record)
                || (scroller && !scroller.isInView(focusPosition.getRow()).y)) {
                focusPosition.setRow(store.getAt(Math.min(focusPosition.rowIdx,
                    store.getCount() - 1)));
            }
        } else // All else failes, find the first focusable cell.
        {
            focusPosition = newPosition;
            // Find the first focusable cell.
            targetCell = me.ownerGrid.view.el.down(me.getCellSelector()
                + '[tabIndex="-1"]');
            if (targetCell) {
                focusPosition.setPosition(me.getRecord(targetCell), me
                    .getHeaderByCell(targetCell));
            } else // All visible columns are cellFocusable: false
            {
                focusPosition = null;
            }
        }
        return focusPosition;
    }
})
//<!-- override  修复extjs的row.isModel = null 的 bug -->


Ext.override(Ext.grid.plugin.CellEditing, {
    onEditComplete: function (ed, value, startValue) {
        if (!startValue)
            startValue = "";
        var me = this,
            context = ed.context,
            view, record;

        view = context.view;
        record = context.record;
        context.value = value;

        // Only update the record if the new value is different than the
        // startValue. When the view refreshes its el will gain focus
        if (!record.isEqual(value, startValue)) {
            record.set(context.column.dataIndex, value);
            // Changing the record may impact the position
            context.rowIdx = view.indexOf(record);
        }

        // We clear down our context here in response to the CellEditor completing.
        // We only do this if we have not already started editing a new context.
        if (me.context === context) {
            me.setActiveEditor(null);
            me.setActiveColumn(null);
            me.setActiveRecord(null);
            me.editing = false;
        }

        me.fireEvent('edit', me, context);
    }
});
Ext.onReady(function () {
    var e = Ext.MessageBox;
    var _s = e.show;
    e.show = function (options) {
        if (options && options.multiLineMessage != null) {
            options.message = (options.message != null ? options.message : '') + Ext.String.format(DetailMsgBoxFormat, options.multiLineMessage);
        }
        return _s.apply(e, arguments)
    }
});

var DetailMsgBoxFormat = '<textarea style="overflow:auto;height:100px;width:100%;border:1px dotted grey;margin-top:5px;background:transparent">{0}</textarea>'


var __hide_loading_mask_1 = function (conn, response, options) {
    var maskel = options.maskEl
    if (typeof options.getMaskEl == 'function') {
        maskel = options.getMaskEl()
    }
    if (maskel) {
        maskel.unmask()
    }
    if (options.btns) {
        options.btns = !options.btns.length ? [options.btns] : options.btns
        Ext.each(options.btns, function (b) {
            b.enable()
        })
    }
    if (response.getResponseHeader) {
        //TODO 动态样例验证冲突
        /*var actions = response.getResponseHeader('ibot_actions')
        if (actions) {
            actions = Ext.decode(actions);
            Ext.defer(function() {
                ShortcutToolRegistry.execAction([actions]);
            }, 500, ShortcutToolRegistry)
        }*/
    }
}
var __error_alert_1 = function (conn, response, options) {
    __hide_loading_mask_1(conn, response, options)
    if (response.status == 403) {
        if (options.failure)
            delete options.failure
        var ec = response.getResponseHeader('error_code');
        if (ec == '1') {// logged in but target resource restricted
            Ext.Msg.alert('', '#notauthed');
        } else {
            var redirect_url = response.getResponseHeader('redirect_url');
            if (redirect_url) {
                Ext.Msg.show({
                    title: '',
                    msg: '#notlogin-redirect',
                    buttons: Ext.Msg.OKCANCEL,
                    fn: function (result, v) {
                        if (result == 'ok') {
                            getTopWindow().location = redirect_url
                        }
                    }
                });
            } else {
                Ext.Msg.alert('', '#notlogin-refresh');
            }
        }
    } else if (options) {
        var _errorMsg = response.responseText ? '<br>' + response.responseText : '';
        if (!options.failure) {
            if ('true' == response.getResponseHeader('validate')) {
                var _errorMsg = response.responseText ? response.responseText : '';
                Ext.Msg.alert('', _errorMsg);
            } else {
                Ext.Msg.alert('', i18n.t('server-error', _errorMsg));
            }
        }
    }

}
Ext.Ajax.on('requestcomplete', __hide_loading_mask_1);
Ext.Ajax.on('requestexception', __error_alert_1);

Ext.Ajax.on('beforerequest', function(conn, options) {
    if (window.sys_webaction_ns){
        options.url = sgcc_url_correct(options.url)
    }
});
