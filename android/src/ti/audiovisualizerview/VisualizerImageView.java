package ti.audiovisualizerview;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.util.TiConvert;

import com.pheelicks.visualizer.VisualizerView;

import android.app.Activity;
import android.view.View;
import android.view.LayoutInflater;
import android.content.res.*;

public class VisualizerImageView extends TiUIView {
	private static final String LCAT = "Pheelicks";
	final public int DEFAULT_AUDIOSESSIONID = 0;
	public int audioSessionId = DEFAULT_AUDIOSESSIONID;
	
	private VisualizerView pheelicksView;
	
    public VisualizerImageView(final TiViewProxy proxy) {
        super(proxy);
        /*
         * you can bind the visualizer to id=0, this is the mixer out and
         * depends on volume , the ids >0 are result of getAudioSessionId()
         */
        if (proxy.hasProperty("audioSessionId")) {
            audioSessionId = TiConvert.toInt(proxy
                                             .getProperty("audioSessionId"));
            Log.d(LCAT, "audioSessionId " + audioSessionId);
        }
        
        // creating view from xml res
        Log.d(LCAT, "initView started");
        Activity activity = proxy.getActivity();
        String packageName = activity.getPackageName();
        Resources res = activity.getResources();
        LayoutInflater inflater = LayoutInflater.from(activity);
        View visualizerContainer = inflater.inflate(
                                                    res.getIdentifier("main", "layout", packageName), null);
        pheelicksView = (VisualizerView) visualizerContainer
        .findViewById(res.getIdentifier("pheelicksView", "id",
                                        packageName));
        setNativeView(visualizerContainer);
        
    }
    
    public void init() {
        pheelicksView.link(audioSessionId); // binding to mixer
        if (proxy.hasListeners("ready")) {
            Log.d(LCAT, "fireEvent 'ready' ");
            proxy.fireEvent("ready", new KrollDict());
        }
    }
    
    public void cleanUp() {
        pheelicksView.release();
        Log.d(LCAT, "cleanView");
    }
    
    @Override
    public void processProperties(KrollDict props) {
        super.processProperties(props);
    }
}