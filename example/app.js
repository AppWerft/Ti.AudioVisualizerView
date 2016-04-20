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