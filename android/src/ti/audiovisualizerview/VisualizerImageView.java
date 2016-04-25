package ti.audiovisualizerview;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.util.TiConvert;

import com.pheelicks.visualizer.VisualizerView;
import com.pheelicks.visualizer.renderer.BarGraphRenderer;
import com.pheelicks.visualizer.renderer.CircleBarRenderer;
import com.pheelicks.visualizer.renderer.LineRenderer;

import android.app.Activity;
import android.view.View;
import android.view.LayoutInflater;
import android.content.res.*;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff.Mode;

public class VisualizerImageView extends TiUIView {
	private static final String LCAT = "Pheelicks";
	final public int DEFAULT_AUDIOSESSIONID = 0;
	public int audioSessionId = DEFAULT_AUDIOSESSIONID;

	public VisualizerView pheelicksView;

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
		pheelicksView = (VisualizerView) visualizerContainer.findViewById(res
				.getIdentifier("pheelicksView", "id", packageName));
		setNativeView(visualizerContainer);
		this.init();

	}

	public void release() {
		if (pheelicksView != null)
			pheelicksView.release();
		else
			Log.d(LCAT, "Error: pheelicksView is null");
	}

	public void clearRenderers() {
		if (pheelicksView != null)
			pheelicksView.clearRenderers();
		else
			Log.d(LCAT, "Error: pheelicksView is null");
	}

	public void addLineRenderer() {
		Paint linePaint = new Paint();
		linePaint.setStrokeWidth(1f);
		linePaint.setAntiAlias(true);
		linePaint.setColor(Color.argb(88, 0, 128, 255));

		Paint lineFlashPaint = new Paint();
		lineFlashPaint.setStrokeWidth(5f);
		lineFlashPaint.setAntiAlias(true);
		lineFlashPaint.setColor(Color.argb(188, 255, 255, 255));
		LineRenderer lineRenderer = new LineRenderer(linePaint, lineFlashPaint,
				true);
		pheelicksView.addRenderer(lineRenderer);
	}

	public void addCircleBarRenderer() {
		Paint paint = new Paint();
		paint.setStrokeWidth(8f);
		paint.setAntiAlias(true);
		paint.setXfermode(new PorterDuffXfermode(Mode.LIGHTEN));
		paint.setColor(Color.argb(255, 222, 92, 143));
		CircleBarRenderer circleBarRenderer = new CircleBarRenderer(paint, 32,
				true);
		if (pheelicksView != null)
			pheelicksView.addRenderer(circleBarRenderer);
		else
			Log.d(LCAT, "Error: pheelicksView is null");
	}

	public void addBarGraphRenderers() {
		Log.d(LCAT, "starting addBarGraphRenderers ");
		/*
		 * Paint paint = new Paint(); paint.setStrokeWidth(50f);
		 * paint.setAntiAlias(true); paint.setColor(Color.argb(200, 56, 138,
		 * 252)); BarGraphRenderer barGraphRendererBottom = new
		 * BarGraphRenderer(16, paint, false);
		 * pheelicksView.addRenderer(barGraphRendererBottom);
		 */
		Paint paint2 = new Paint();
		paint2.setStrokeWidth(12f);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.argb(200, 56, 138, 252));
		BarGraphRenderer barGraphRendererTop = new BarGraphRenderer(4, paint2,
				false);
		Log.d(LCAT, "adding addBarGraphRenderers ========== ");
		if (pheelicksView != null)
			pheelicksView.addRenderer(barGraphRendererTop);
		else
			Log.d(LCAT, "Error: pheelicksView is null");
	}

	public void init() {
		Log.d(LCAT, "init started ======= ");
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