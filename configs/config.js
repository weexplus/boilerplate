const path = require('path');
const ROOT = path.resolve(__dirname, '..')+"/dist";
const ip = require('ip').address();
const config = {
  root: ROOT,
  // webpack-dev-server
  pluginConfigPath: 'plugins/plugins.json',
  pluginFilePath: 'plugins/plugins.js',
  // router
  routerFilePath: 'web/router.js',
  // common
  templateDir: 'dist',
  entryFilePath: 'web/entry.js',
  // Module exclude from compile process
  excludeModuleReg: /node_modules(?!(\/|\\).*(weex).*)/,
  dev: {
    // Various Dev Server settings
    contentBase: ROOT,
    host: ip,
    port: 8081,
    historyApiFallback: true,
    open: true,
    watchContentBase: true,
    openPage: 'web/preview.html',
    watchOptions: {
      ignored: /node_modules/,
      aggregateTimeout: 300,
      poll: false
    },
        /**
     * Source Maps
     */
    // https://webpack.js.org/configuration/devtool/#development
    devtool: 'eval-source-map',
    env: JSON.stringify('development'),
    // If you have problems debugging vue-files in devtools,
    // set this to false - it *may* help
    // https://vue-loader.vuejs.org/en/options.html#cachebusting
    cacheBusting: true,
    // CSS Sourcemaps off by default because relative paths are "buggy"
    // with this option, according to the CSS-Loader README
    // (https://github.com/webpack/css-loader#sourcemaps)
    // In our experience, they generally work as expected,
    // just be aware of this issue when enabling this option.
    cssSourceMap: false,
    proxyTable: {
      '/api': {
        target: 'http://192.168.0.102:8080/movie/', // 设置调用接口域名和端口号别忘了加http
        changeOrigin: true,
        pathRewrite: {
          '^/api': '/' // 这里理解成用‘/api’代替target里面的地址，组件中我们调接口时直接用/api代替
          // 比如我要调用'http://0.0:300/user/add'，直接写‘/api/user/add’即可 代理后地址栏显示/
        }
      }
    },
    autoOpenBrowser: false,
    errorOverlay: true,
    notifyOnErrors: true,
    htmlOptions: {
      devScripts: `
        <script>
          window.addEventListener('load', function () {
            var is_touch_device = function () {
              return 'ontouchstart' in window // works on most browsers
                  || 'onmsgesturechange' in window; // works on ie10
            };
            if(!is_touch_device()) {
              if (window.parent === window) { // not in iframe.
                window.phantomLimb.stop()
              }
            }
          })
        </script>
        `
    }
  },
  prod: {
    env: JSON.stringify('production'),
    /**
     * Source Maps
     */
    productionSourceMap: true,
    // https://webpack.js.org/configuration/devtool/#production
    devtool: '#source-map',
    cssSourceMap: true,
    productionSourceMap: true
  },
  nodeConfiguration: {
    global: false,
    Buffer: false,
    __filename: false,
    __dirname: false,
    setImmediate: false,
    clearImmediate: false,
    // see: https://github.com/webpack/node-libs-browser
    assert: false,
    buffer: false,
    child_process: false,
    cluster: false,
    console: false,
    constants: false,
    crypto: false,
    dgram: false,
    dns: false,
    domain: false,
    events: false,
    fs: false,
    http: false,
    https: false,
    module: false,
    net: false,
    os: false,
    path: false,
    process: false,
    punycode: false,
    querystring: false,
    readline: false,
    repl: false,
    stream: false,
    string_decoder: false,
    sys: false,
    timers: false,
    tls: false,
    tty: false,
    url: false,
    util: false,
    vm: false,
    zlib: false
  }
}
module.exports = config;
