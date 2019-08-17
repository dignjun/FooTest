class any_xhr {
    constructor() {
        this.XHR = window.XMLHttpRequest;
        this.init();
    }

    init() {
        let _this = this;
        window.XMLHttpRequest = function () {
            this._xhr = new _this.XHR();   // 在实例上挂一个保留的原生的xhr实例

            _this.overwrite(this);
        }
    }

    overwrite(proxyXHR) {
        for (let key in proxyXHR._xhr) {

            if (typeof proxyXHR._xhr[key] === 'function') {
                this.overwriteMethod(key, proxyXHR);
                continue;
            }
        }
    }

    overwriteMethod(key, proxyXHR) {
        let hooks = this.hooks;
        let execedHooks = this.execedHooks;

        proxyXHR[key] = (...args) => {

            // 如果当前方法有对应的钩子 则执行钩子
            if (hooks[key] && (hooks[key].call(proxyXHR, args) === false)) {
                return;
            }

            // 执行原生xhr实例中对应的方法
            const res = proxyXHR._xhr[key].apply(proxyXHR._xhr, args);

            // 看看还有没有原生xhr实例对应的方法执行后需要执行的钩子 如果有则执行
            execedHooks[key] && execedHooks[key].call(proxyXHR._xhr, res);

            return res;

        }
    }
}