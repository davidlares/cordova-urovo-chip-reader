cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
    {
      "id": "com.stv.plugins.chip.ChipPlugin",
      "file": "plugins/com.stv.plugins.chip/www/ChipPlugin.js",
      "pluginId": "com.stv.plugins.chip",
      "clobbers": [
        "cordova.plugins.ChipPlugin"
      ]
    }
  ];
  module.exports.metadata = {
    "cordova-plugin-whitelist": "1.3.4",
    "com.stv.plugins.chip": "1.0.0"
  };
});