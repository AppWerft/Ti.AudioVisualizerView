Ti.AudioVisualizerView
===========================================

This is the Titanium module for wrappng Visualizer class of Android. It uses https://github.com/felixpalmer/android-visualizer


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