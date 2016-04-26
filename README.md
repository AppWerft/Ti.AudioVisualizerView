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
lifecycleContainer : window 
        height : 200
    });
    visualizerView.addEventListener('ready', function() {
        visualizerView.addLineRenderer();
        visualizerView.addBarGraphRenderers();
    });
    window.add(visualizerView);
    Ti.Media.createAudioPlayer({
        url : "http://audio.scdn.arkena.com/11016/fip-midfi128.mp3"
    }).start();
    window.open();
}();
~~~

The optional audioSessionId 0 means you want visualize the mixer out. In other cases you need the ID. The standard mediaPlayer doesn't gives you this ID. There is a JIRA ticket: https://jira.appcelerator.org/browse/AC-3538 

YOu can use this code to retreive audioSessionId:
~~~
var mp = require('com.kcwdev.audio').createAudioPlayer({
    url : "https://raw.githubusercontent.com/felixpalmer/android-visualizer/master/res/raw/test.mp3"
});
mp.start();
console.log(mp.getAudioSessionId());
~~~