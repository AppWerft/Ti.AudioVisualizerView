Ti.AudioVisualizerView
===========================================

This is the Titanium module for wrappng Visualizer class of Android. It uses https://github.com/felixpalmer/android-visualizer

An view like imageView that takes the input from the Android MediaPlayer and displays visualizations, like in iTunes or WinAmp.

![](https://github.com/felixpalmer/android-visualizer/raw/master/demo/demo-1.gif) or more complex: ![](https://github.com/felixpalmer/android-visualizer/raw/master/demo/demo-4.gif )

Usage:
------

For usage in Marshmellow devices, you need AUDIO_RECORDING permission on runtime. You must wrap the call of Visualizer into a permissionRequester. 

~~~
! function() {
    var Visualizer = require('ti.audiovisualizerview');
    var window = Ti.Ui.createWindow();
    var visualizerView = Visualizer.createVisualizerView({
        width : 500,
        audioSessionId :0,
        height : 200
    });
    visualizerView.addEventListener('ready', function() {
        visualizerView.addLineRenderer({
            basicStrokeWidth : 5,
            basicColor : "#800000ff",
            flashStrokeWidth : 5,
            flashColor : "#800000ff"
        });
        visualizerView.addBarGraphRenderers({
            color : "#800000ff",
            bars  : 12,
            direction : 'top'
        });
    });
    window.add(visualizerView);
    Ti.Media.createAudioPlayer({
        url : "http://audio.scdn.arkena.com/11016/fip-midfi128.mp3"
    }).start();
    window.open();
}();
~~~

The optional audioSessionId 0 means you want visualize the mixer out. In other cases you need the ID. The standard mediaPlayer doesn't gives you this ID.  