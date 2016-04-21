Ti.AudioVisualizerView
===========================================

This is the Titanium module for wrappng Visualizer class of Android. It uses https://github.com/felixpalmer/android-visualizer

An view like imageView that takes the input from the Android MediaPlayer and displays visualizations, like in iTunes or WinAmp.

![](https://github.com/felixpalmer/android-visualizer/raw/master/demo/demo-1.gif) or more complex: ![](https://github.com/felixpalmer/android-visualizer/raw/master/demo/demo-4.gif )

Usage:
------


~~~
! function() {
    var Visualizer = require('ti.audiovisualizerview');
    var window = Ti.Ui.createWindow();
    var visualizerView = Visualizer.createVisualizerView({
        width : 500,
        height : 200
    });
    visualizerView.addEventListener('ready', function() {
        visualizerView.addLineRenderer();
        visualizerView.addCircleBarRenderer();
        visualizerView.addCircleRenderer();
        visualizerView.addBarGraphRenderers();
    });
    window.add(visualizerView);
    Ti.Media.createAudioPlayer({
        url : "http://audio.scdn.arkena.com/11016/fip-midfi128.mp3"
    }).start();
    window.open();
}();
~~~
