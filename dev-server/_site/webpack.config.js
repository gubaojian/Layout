module.exports = {

    entry: {
      index : './src/index.js',
      alipay : './src/alipay.js',
      koubei : './src/koubei.js',
      friends : './src/friends.js',
      wealth  : './src/wealth.js'
    },
    output: {
        path: './dist',
        filename: '[name].js'
    },
    devServer: {
       hot: true
    },
    // configuration
    module: {
        loaders: [
        {
          test: /\.jsx?$/,
          exclude: /(node_modules|bower_components)/,
          loader: 'babel', // 'babel-loader' is also a legal name to reference
          query: {
                presets: ['react', 'es2015']
          }
        }
      ]
    }

};
