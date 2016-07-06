package ti.audiovisualizerview;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;

import android.content.Context;

import com.pheelicks.utils.TunnelPlayerWorkaround;

@Kroll.module(name = "Audiovisualizerview", id = "ti.audiovisualizerview")
public class AudiovisualizerviewModule extends KrollModule {

	@Kroll.constant
	public static final int AUDIOSESSION_OUTPUTMIX = 0;
	public static final int VERTICAL_ALIGN_BOTTOM = 0;
	public static final int VERTICAL_ALIGN_TOP = 1;

	public static final String LCAT = "PheelicksM";

	public AudiovisualizerviewModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		Context context = TiApplication.getInstance().getApplicationContext();
		if (TunnelPlayerWorkaround.isTunnelDecodeEnabled(context)) {
			TunnelPlayerWorkaround.createSilentMediaPlayer(context);
		}

	}
}
