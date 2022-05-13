const webpack = require('webpack')
const ParallelUglifyPlugin = require('webpack-parallel-uglify-plugin');
const configs = {
    module: {
        rules: []
    },
    plugins: [
        new ParallelUglifyPlugin({
            cacheDir: '.cache/',
            uglifyJS: {
                output: {
                    comments: false
                },
                warnings: false
            }
        }),
        new webpack.BannerPlugin({
            banner: `// { "framework": "Vue"} \n`,
            raw: true,
            exclude: 'Vue'
        })
    ]
}

module.exports = configs
