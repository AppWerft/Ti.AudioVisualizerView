Ti.AudioVisualizerView
===========================================

This is the Titanium module for wrappng Visualizer class of Android. It uses https://github.com/felixpalmer/android-visualizer

An view like imageView that takes the input from the Android MediaPlayer and displays visualizations, like in iTunes or WinAmp.

![](https://github.com/felixpalmer/android-visualizer/raw/master/demo/demo-1.gif) or more complex: ![](https://github.com/felixpalmer/android-visualizer/raw/master/demo/demo-4.gif)

Usage:
------


~~~
var Visualizer = require('ti.audiovisualizerview');

var window = Ti.Ui.createWindow();
window.add(Visualizer.createVisualizerView({
    audioID : 0  // for mixer output
}));
window.open();

~~~