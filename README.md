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
    window.add(Visualizer.createVisualizerView({
        audioSessionId : Visualizer.AUDIOSESSION_OUTPUTMIX, // for mixer output
        renderType : Visualizer.TYPE_BAR
    }));
    Ti.Media.createAudioPlayer({
        url : "http://audio.scdn.arkena.com/11016/fip-midfi128.mp3"
    }).start();
    window.open();
}();
~~~

Possible types are TYPE_BAR, TYPE_CIRCLE, TYPE_CIRCLEBAR, TYPE_LINE.