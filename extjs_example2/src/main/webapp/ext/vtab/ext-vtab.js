/*
 * icyfire
 * 
 * http://icyfire.javaeye.com
 */
Ext.namespace("Ext.ux");

Ext.ux.TabPanel = function(cfg) {
	if (cfg.tabPosition == 'left' || cfg.tabPosition == 'right') {
		cfg.cls = cfg.cls || '';
//		cfg.cls = cfg.cls + ' x-tab-panel-vertical x-tab-panel-vertical-'
//				+ cfg.tabPosition;
		this.intendedTabPosition = cfg.tabPosition;
		this.verticalTabs = true;
		cfg.tabPosition = 'top';
		cfg.tabWidth = 60;
		this.isEn = cfg.isEn;
	}
	Ext.ux.TabPanel.superclass.constructor.call(this, cfg);
};

Ext.extend(Ext.ux.TabPanel, Ext.TabPanel, {
	afterRender : function() {
		Ext.ux.TabPanel.superclass.afterRender.call(this);
		if (this.verticalTabs) {
			var d = Ext.fly(Ext.DomHelper.createDom({tag:'div'}));
			this.header.parent().insertFirst(d)
			d.appendChild(this.header.dom)
			d.addClass('x-tab-panel-vertical x-tab-panel-vertical-'
				+ this.intendedTabPosition)
			d.setStyle('float','left')
			if (this.isEn)
				d.addClass('x-tab-panel-vertical-en');
			this.header.setWidth(this.isEn ? 73 : 30);
			this.header.setHeight(this.height || this.container.getHeight()
					- 48);
		}
	},
	onBodyResize : function(w, h) {
		Ext.ux.TabPanel.superclass.onBodyResize.apply(this, arguments);
		this.header.setHeight(this.height || this.container.getHeight() - 48);
	},
	// adjustBodyHeight : function(h) {
	// if (this.verticalTabs) {
	// this.header.setHeight(h + (this.tbar ? this.tbar.getHeight() : 0));
	// }
	// return Ext.ux.TabPanel.superclass.adjustBodyHeight.call(this, h);
	// },

	getFrameWidth : function() {
		return Ext.ux.TabPanel.superclass.getFrameWidth.call(this)
				+ this.verticalTabs ? (this.isEn ? 73 : 30) : 0;
	},

	getFrameHeight : function() {
		return 5;// PATCH
		// return Ext.ux.TabPanel.superclass.getFrameHeight.call(this)
		// - (this.verticalTabs ? this.header.getHeight() : 0);
	},

	autoSizeTabs : function() {
		var count = this.items.length;
		var ce = this.tabPosition != 'bottom' ? 'header' : 'footer';
		var ow = this[ce].dom[this.verticalTabs
				? 'offsetHeight'
				: 'offsetWidth'];
		var aw = this[ce].dom[this.verticalTabs
				? 'clientHeight'
				: 'clientWidth'];

		if (!this.resizeTabs || count < 1 || !aw) { // !aw for display:none
			return;
		}

		var each = Math.max(Math.min(Math.floor((aw - 4) / count)
								- this.tabMargin, this.tabWidth),
				this.minTabWidth); // -4 for float errors in IE
		this.lastTabWidth = each;
		var lis = this.stripWrap.dom.getElementsByTagName('li');
		for (var i = 0, len = lis.length - 1; i < len; i++) { // -1 for the
			// "edge" li
			var li = lis[i];
			var inner = li.childNodes[1].firstChild.firstChild;
			var tw = li[this.verticalTabs ? 'offsetHeight' : 'offsetWidth'];
			var iw = inner[this.verticalTabs ? 'offsetHeight' : 'offsetWidth'];
			inner.style[this.verticalTabs ? 'height' : 'width'] = (each - (tw - iw))
					+ 'px';
		}
	},

	// private
	adjustBodyWidth : function(w) {
		if (this.verticalTabs) {
			if (Ext.isIE6 || Ext.isIE7) {
				this.bwrap.setWidth(w - 3);
			}
			return w;
		}
		return Ext.ux.TabPanel.superclass.adjustBodyWidth.call(this, w);
	},

	// private
	autoScrollTabs : function() {
		var topTab = this.tabPosition == 'top';
		var count = this.items.length;
		var ow = this[topTab ? 'header' : 'footer'].dom[this.verticalTabs
				? 'offsetHeight'
				: 'offsetWidth'];
		var tw = this[topTab ? 'header' : 'footer'].dom[this.verticalTabs
				? 'clientHeight'
				: 'clientWidth'];

		var wrap = this.stripWrap;
		var cw = wrap.dom[this.verticalTabs ? 'offsetHeight' : 'offsetWidth'];
		var pos = this.getScrollPos();
		var l = this.edge.getOffsetsTo(this.stripWrap)[this.verticalTabs
				? 1
				: 0]
				+ pos;

		if (!this.enableTabScroll || count < 1 || cw < 20) { // 20 to prevent
			// display:none
			// issues
			return;
		}
		if (l <= tw) {
			this.verticalTabs
					? (wrap.dom.scrollTop = 0)
					: (wrap.dom.scrollLeft = 0)
			wrap[this.verticalTabs ? 'setHeight' : 'setWidth'](tw);
			if (this.scrolling) {
				this.scrolling = false;
				this[topTab ? 'header' : 'footer']
						.removeClass('x-tab-scrolling');
				this.scrollLeft.hide();
				this.scrollRight.hide();
			}
		} else {
			if (!this.scrolling) {
				this[topTab ? 'header' : 'footer'].addClass('x-tab-scrolling');
			}
			tw -= wrap.getMargins(this.verticalTabs ? 'tb' : 'lr');
			wrap[this.verticalTabs ? 'setHeight' : 'setWidth'](tw > 20
					? tw
					: 20);
			if (!this.scrolling) {
				if (!this.scrollLeft) {
					this.createScrollers();
				} else {
					this.scrollLeft.show();
					this.scrollRight.show();
				}
			}
			this.scrolling = true;
			if (pos > (l - tw)) { // ensure it stays within bounds
				wrap.dom.scrollLeft = l - tw;
			} else { // otherwise, make sure the active tab is still visible
				this.scrollToTab(this.activeTab, false);
			}
			this.updateScrollButtons();
		}
	},

	// private
	createScrollers : function() {
		var topTab = this.tabPosition == 'top';
		var h = this.stripWrap.dom[this.verticalTabs
				? 'offsetWidth'
				: 'offsetHeight'];

		// left
		var sl = this[topTab ? 'header' : 'footer'].insertFirst({
					cls : 'x-tab-scroller-left'
				});
		sl[this.verticalTabs ? 'setWidth' : 'setHeight'](h);
		if (this.verticalTabs) {
			sl.setHeight(this.stripWrap.getMargins('t'));
		}
		sl.addClassOnOver('x-tab-scroller-left-over');
		this.leftRepeater = new Ext.util.ClickRepeater(sl, {
					interval : this.scrollRepeatInterval,
					handler : this.onScrollLeft,
					scope : this
				});
		this.scrollLeft = sl;

		// right
		var sr = this[topTab ? 'header' : 'footer'].insertFirst({
					cls : 'x-tab-scroller-right'
				});
		sr[this.verticalTabs ? 'setWidth' : 'setHeight'](h);
		if (this.verticalTabs) {
			sr.setHeight(this.stripWrap.getMargins('b'));
		}
		sr.addClassOnOver('x-tab-scroller-right-over');
		this.rightRepeater = new Ext.util.ClickRepeater(sr, {
					interval : this.scrollRepeatInterval,
					handler : this.onScrollRight,
					scope : this
				});
		this.scrollRight = sr;
	},

	// private
	getScrollWidth : function() {
		return this.edge.getOffsetsTo(this.stripWrap)[this.verticalTabs ? 1 : 0]
				+ this.getScrollPos();
	},

	// private
	getScrollPos : function() {
		return parseInt(this.stripWrap.dom[this.verticalTabs
						? 'scrollTop'
						: 'scrollLeft'], 10)
				|| 0;
	},

	// private
	getScrollArea : function() {
		return parseInt(this.stripWrap.dom[this.verticalTabs
						? 'clientHeight'
						: 'clientWidth'], 10)
				|| 0;
	},

	scrollToTab : function(item, animate) {
		if (!item) {
			return;
		}
		var el = this.getTabEl(item);
		var pos = this.getScrollPos(), area = this.getScrollArea();
		var left = Ext.fly(el).getOffsetsTo(this.stripWrap)[this.verticalTabs
				? 1
				: 0]
				+ pos;
		var right = left
				+ el[this.verticalTabs ? 'offsetHeight' : 'offsetWidth'];
		if (left < pos) {
			this.scrollTo(left, animate);
		} else if (right > (pos + area)) {
			this.scrollTo(right - area, animate);
		}
	},

	// private
	scrollTo : function(pos, animate) {
		this.stripWrap.scrollTo(this.verticalTabs ? 'top' : 'left', pos,
				animate ? this.getScrollAnim() : false);
		if (!animate) {
			this.updateScrollButtons();
		}
	}
});