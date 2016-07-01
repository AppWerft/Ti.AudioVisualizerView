package ti.audiovisualizerview;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.util.TiConvert;

import android.app.Activity;
import android.content.res.*;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;
//import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.pheelicks.visualizer.VisualizerView;
import com.pheelicks.visualizer.renderer.*;

public class VisualizerImageView extends TiUIView {
	private static final String LCAT = "PheelicksView";
	final public int DEFAULT_AUDIOSESSIONID = 0;
	public int audioSessionId = DEFAULT_AUDIOSESSIONID;
	public VisualizerView pheelicksView;

	public VisualizerImageView(final TiViewProxy proxy) {
		super(proxy);
		Log.d(LCAT,
				"VisualizerImageView started  °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
		Activity activity = proxy.getActivity();
		String packageName = activity.getPackageName();
		Resources res = activity.getResources();
		LayoutInflater inflater = LayoutInflater.from(activity);
		View visualizerContainer = inflater.inflate(
				res.getIdentifier("main", "layout", packageName), null);
		pheelicksView = (VisualizerView) visualizerContainer.findViewById(res
				.getIdentifier("pheelicksView", "id", packageName));
		setNativeView(visualizerContainer);
		init();
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
		lineFlashPaint.setStrokeWidth(3f);
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

	public void addBarGraphRenderer(float width, int color, int devisions) {
		Paint paint = new Paint();
		float mWidth = width;
		float height = 50;
		Log.d(LCAT, "starting addBarGraphRenderers " + mWidth);
		paint.setStrokeWidth(mWidth);
		paint.setAntiAlias(true);
		paint.setColor(color);
		BarGraphRenderer barGraphRendererTop = new BarGraphRenderer(32, paint,
				false, height);
		Log.d(LCAT, "adding addBarGraphRenderers ========== ");
		if (pheelicksView != null)
			pheelicksView.addRenderer(barGraphRendererTop);
		else
			Log.d(LCAT, "Error: pheelicksView is null");
	}

	public void init() {
		if (proxy.hasProperty("audioSessionId")) {
			audioSessionId = TiConvert.toInt(proxy
					.getProperty("audioSessionId"));
			Log.d(LCAT, "audioSessionId for pheelicks is still "
					+ audioSessionId);
			pheelicksView.link(audioSessionId); // binding to mixer
		} else
			pheelicksView.link(0);
		if (proxy.hasListeners("ready")) {
			proxy.fireEvent("ready", new KrollDict());
		}
	}

	public void cleanUp() {
		if (pheelicksView != null) {
			// pheelicksView.clearRenderers();
			// pheelicksView.release();

			Log.d(LCAT, "cleanView after pause");
		} else
			Log.e(LCAT, "cannot cleanView ");
	}

	@Override
	public void processProperties(KrollDict props) {
		super.processProperties(props);
	}

	public static float dipToPixels(float dipValue) {
		Context context = TiApplication.getInstance();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue,
				context.getResources().getDisplayMetrics());

	}
}