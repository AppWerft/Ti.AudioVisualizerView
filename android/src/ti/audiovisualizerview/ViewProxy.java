package ti.audiovisualizerview;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiContext.OnLifecycleEvent;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.view.TiUIView;

//import org.appcelerator.titanium.TiLifecycle.OnLifecycleEvent;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.TypedValue;
import android.widget.LinearLayout.LayoutParams;

import com.pheelicks.visualizer.VisualizerView;
import com.pheelicks.visualizer.renderer.*;

@Kroll.proxy(creatableInModule = AudiovisualizerviewModule.class)
public class ViewProxy extends TiViewProxy implements OnLifecycleEvent {
	private static final String LCAT = "PheelicksVP";
	private PheelicksView pheelicksView; // instance of TiUIView
	/* all dicts for options */
	BargraphDict bargraphDict = null;
	LinegraphDict linegraphDict = null;
	CirclegraphDict circlegraphDict = null;
	CirclebargraphDict circlebargraphDict = null;

	int audioSessionId = 0;

	public ViewProxy() {
		super();
		Log.d(LCAT, "ViewProxy constructor ================");
	}

	@Override
	public TiUIView createView(Activity activity) {
		pheelicksView = new PheelicksView(this);
		pheelicksView.getLayoutParams().autoFillsHeight = true;
		pheelicksView.getLayoutParams().autoFillsWidth = true;
		return pheelicksView;
	}

	@Kroll.method
	public void release() {
		pheelicksView.release();
	}

	@Kroll.method
	public void clearRenderers() {
		pheelicksView.clearRenderers();
	}

	@Override
	public void handleCreationDict(KrollDict args) {
		if (args.containsKeyAndNotNull("bargraphRenderer")) {
			KrollDict bargraphRenderer = args.getKrollDict("bargraphRenderer");
			bargraphDict = new BargraphDict();
			if (bargraphRenderer.containsKeyAndNotNull("color"))
				bargraphDict.setColor(Color.parseColor(bargraphRenderer
						.getString("color")));
			if (bargraphRenderer.containsKeyAndNotNull("width"))
				bargraphDict.setStrokeWidth(TiConvert.toFloat(bargraphRenderer
						.getString("strokeWidth")));
			if (bargraphRenderer.containsKeyAndNotNull("verticalAlign"))
				bargraphDict.setVerticalAlign(TiConvert.toInt(bargraphRenderer
						.getString("verticalAlign")));
			if (bargraphRenderer.containsKeyAndNotNull("divisions"))
				bargraphDict.setDivisions(TiConvert.toInt(bargraphRenderer
						.getString("divisions")));
		}
		if (args.containsKeyAndNotNull("linegraphRenderer")) {
			KrollDict linegraphRenderer = args
					.getKrollDict("linegraphRenderer");
			linegraphDict = new LinegraphDict();
			if (linegraphRenderer.containsKeyAndNotNull("color"))
				linegraphDict.setColor(Color.parseColor(linegraphRenderer
						.getString("color")));
			if (linegraphRenderer.containsKeyAndNotNull("strokeWidth"))
				linegraphDict.setStrokeWidth(TiConvert
						.toFloat(linegraphRenderer.getString("strokeWidth")));
		}

		if (args.containsKeyAndNotNull("circleGraphRenderer")) {
			final KrollDict opts = args.getKrollDict("circleGraphRenderer");
			circlegraphDict = new CirclegraphDict();
			if (opts.containsKeyAndNotNull("color"))
				circlegraphDict.setColor(Color.parseColor(opts
						.getString("color")));
			if (opts.containsKeyAndNotNull("strokeWidth"))
				circlegraphDict.setStrokeWidth(TiConvert.toFloat(opts
						.getString("strokeWidth")));
		}

		if (args.containsKeyAndNotNull("circlebarGraphRenderer")) {
			final KrollDict opts = args.getKrollDict("circlebarGraphRenderer");
			circlebargraphDict = new CirclebargraphDict();
			if (opts.containsKeyAndNotNull("color"))
				circlebargraphDict.setColor(Color.parseColor(opts
						.getString("color")));
			if (opts.containsKeyAndNotNull("strokeWidth"))
				circlebargraphDict.setWidth(TiConvert.toFloat(opts
						.getString("strokeWidth")));
			if (opts.containsKeyAndNotNull("cycleColors"))
				circlebargraphDict.setCycleColor(TiConvert.toBoolean(opts
						.getString("cycleColors")));
			if (opts.containsKeyAndNotNull("divisions"))
				circlebargraphDict.setDivisions(TiConvert.toInt(opts
						.getString("divisions")));
		}

		if (args.containsKeyAndNotNull("audioSessionId")) {
			audioSessionId = TiConvert.toInt(args.getInt("audioSessionId"));
		}
		super.handleCreationDict(args);
	}

	@Override
	public void onPause(Activity activity) {
		if (pheelicksView != null)
			pheelicksView.cleanUp();
		super.onPause(activity);
	}

	@Override
	public void onDestroy(Activity activity) {
		if (pheelicksView != null)
			pheelicksView.cleanUp();
		super.onDestroy(activity);
	}

	@Override
	public void onStop(Activity activity) {
		if (pheelicksView != null)
			pheelicksView.cleanUp();
		super.onStop(activity);
	}

	@Override
	public void onResume(Activity activity) {
		super.onResume(activity);
		if (pheelicksView != null)
			pheelicksView.initView();
	}

	public void onStart(Activity activity) {
		super.onStart(activity);
		if (pheelicksView != null)
			pheelicksView.initView();
	}

	public static float dipToPixels(float dipValue) {
		Context context = TiApplication.getInstance();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue,
				context.getResources().getDisplayMetrics());

	}

	/* the instance of native view from Felix: */
	private class PheelicksView extends TiUIView {
		final public int DEFAULT_AUDIOSESSIONID = 0;
		public int audioSessionId = DEFAULT_AUDIOSESSIONID;
		public VisualizerView visualizerView;

		public PheelicksView(final TiViewProxy proxy) {
			super(proxy);
			visualizerView = new VisualizerView(proxy.getActivity());
			visualizerView.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			setNativeView(visualizerView);
			initView();
			visualizerView.link(audioSessionId);
			addBarGraphRenderer();
			addCircleBarRenderer();
			addCircleRenderer();
			addLineRenderer();

		}

		public void release() {
			if (visualizerView != null)
				visualizerView.release();
			else
				Log.d(LCAT, "Error: visualizerView is null");
		}

		public void clearRenderers() {
			if (visualizerView != null)
				visualizerView.clearRenderers();
			else
				Log.d(LCAT, "Error: visualizerView is null");
		}

		/* optionally adding of renderes: */
		private void addLineRenderer() {
			if (linegraphDict == null)
				return;
			Paint linePaint = new Paint();
			linePaint.setStrokeWidth(linegraphDict.getStrokeWidth());
			linePaint.setAntiAlias(true);
			linePaint.setColor(Color.argb(88, 0, 128, 255));
			Paint lineFlashPaint = new Paint();
			lineFlashPaint.setStrokeWidth(linegraphDict.getStrokeWidth());
			lineFlashPaint.setAntiAlias(true);
			lineFlashPaint.setColor(Color.argb(188, 255, 255, 255));
			LineRenderer lineRenderer = new LineRenderer(linePaint,
					lineFlashPaint, linegraphDict.isCycleColor());
			visualizerView.addRenderer(lineRenderer);
		}

		private void addCircleBarRenderer() {
			if (circlebargraphDict == null)
				return;
			Paint paint = new Paint();
			paint.setStrokeWidth(circlebargraphDict.getStrokeWidth());
			paint.setAntiAlias(true);
			paint.setXfermode(new PorterDuffXfermode(Mode.LIGHTEN));
			paint.setColor(Color.argb(255, 222, 92, 143));
			CircleBarRenderer circleBarRenderer = new CircleBarRenderer(paint,
					circlebargraphDict.getDivisions(), true);
			visualizerView.addRenderer(circleBarRenderer);
		}

		private void addCircleRenderer() {
			if (circlegraphDict == null)
				return;
			Paint paint = new Paint();
			paint.setStrokeWidth(circlegraphDict.getStrokeWidth());
			paint.setAntiAlias(true);
			paint.setXfermode(new PorterDuffXfermode(Mode.LIGHTEN));
			paint.setColor(Color.argb(255, 222, 92, 143));
			CircleRenderer circleRenderer = new CircleRenderer(paint,
					circlegraphDict.isCycleColor());
			visualizerView.addRenderer(circleRenderer);
		}

		private void addBarGraphRenderer() {
			if (bargraphDict == null)
				return;
			Paint paint = new Paint();
			paint.setStrokeWidth(bargraphDict.getStrokeWidth());
			paint.setAntiAlias(true);
			paint.setColor(bargraphDict.getColor());
			boolean top = (bargraphDict.getVerticalAlign() == 1) ? true : false;
			BarGraphRenderer barGraphRendererTop = new BarGraphRenderer(
					bargraphDict.getDivisions(), paint, top,
					bargraphDict.getHeight());
			visualizerView.addRenderer(barGraphRendererTop);
		}

		public void initView() {
			if (proxy.hasProperty("audioSessionId")) {
				audioSessionId = TiConvert.toInt(proxy
						.getProperty("audioSessionId"));
				Log.d(LCAT, "audioSessionId for pheelicks is still "
						+ audioSessionId);
				visualizerView.link(audioSessionId); // binding to mixer
			} else
				visualizerView.link(0);
			if (bargraphDict != null) {

			}
		}

		public void cleanUp() {
			if (visualizerView != null) {
				visualizerView.clearRenderers();
				visualizerView.release();

				Log.d(LCAT, "cleanView after pause");
			} else
				Log.e(LCAT, "cannot cleanView ");
		}

		@Override
		public void processProperties(KrollDict props) {
			super.processProperties(props);
		}
	}
}