# UrovoChip

The following project is an Android-based App built with `Apache Cordova` which contains a straight-forward implementation logic for reading ICC or `EMV Based cards` with Urovo Payment terminal (Safedroid OS)

The bridge between `JavaScript` and `Java` (Android-Native) was done with `plugman`, a simple utility tool for bidi communications between Android java activities and the HTML-CSS-JS DOM. This one is the `ChipPlugin` folder located at the root directory.

Check `plugman` docs [here](https://cordova.apache.org/docs/en/latest/plugin_ref/plugman.html)

## Usage

You will need to install `npm` or `yarn` dependencies and sign your APK, or you can test it with a USB-wired device

This was built and tested with a Urovo i9100 terminal POS

## Commands

Assuming you have your everything set up, you can:

1. Building Java plugin like: `cordova add plugin ./ChipPlugin`
2. Run your app with a wired device like: `cordova run android --device`

## Credits

 - [David E Lares](https://twitter.com/davidlares3)

## License

 - [MIT](https://opensource.org/licenses/MIT)
