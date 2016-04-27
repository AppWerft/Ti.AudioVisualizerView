package ti.audiovisualizerview;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.TiContext.OnLifecycleEvent;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiC;

//import org.appcelerator.titanium.TiLifecycle.OnLifecycleEvent;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;

@Kroll.proxy(creatableInModule = AudiovisualizerviewModule.class)
public class ViewProxy extends TiViewProxy implements OnLifecycleEvent {
	private static final String LCAT = "PheelicksViewProxy";

	private VisualizerImageView mView; // instance of TiUIView

	public ViewProxy() {
		super();
	}

	@Override
	public TiUIView createView(Activity activity) {
		Log.d(LCAT, "VisualizerView mView start creating ================");
		mView = new VisualizerImageView(this);
		Log.d(LCAT, "VisualizerView mView created ================");
		mView.getLayoutParams().autoFillsHeight = true;
		mView.getLayoutParams().autoFillsWidth = true;
		return mView;
	}

	@Kroll.method
	public void release() {
		mView.release();
	}

	@Kroll.method
	public void clearRenderers() {
		mView.clearRenderers();
	}

	@Kroll.method
	public void addLineRenderer(@Kroll.argument(optional = true) KrollDict args) {
		mView.addLineRenderer();
	}

	@Kroll.method
	public void addCircleRenderer() {
		mView.addCircleBarRenderer();
	}

	@Kroll.method
	public void addBarGraphRenderer(@Kroll.argument(optional = true) KrollDict d) {
		int mColor = 0;
		float mWidth = 10;
		if (d != null) {
			if (d.containsKey(TiC.PROPERTY_COLOR)) {

				mColor = TiConvert.toColor(d, TiC.PROPERTY_COLOR);
				Log.d(LCAT, "COLOR ================" + mColor);

			}
			if (d.containsKey(TiC.PROPERTY_WIDTH)) {
				mWidth = TiConvert.toFloat(d, TiC.PROPERTY_WIDTH);
			}
		}
		mView.addBarGraphRenderer(mWidth, mColor, 16);
	}

	@Override
	public void handleCreationDict(KrollDict options) {
		super.handleCreationDict(options);
	}

	@Override
	public void handleCreationArgs(KrollModule createdInModule, Object[] args) {
		super.handleCreationArgs(createdInModule, args);
	}

	@Override
	public void onPause(Activity activity) {
		Log.d(LCAT, "onPause ======");
		if (mView != null)
			mView.cleanUp();
		else
			Log.e(LCAT, "cannot call cleanUp");
		super.onPause(activity);
	}

	@Override
	public void onDestroy(Activity activity) {
		Log.d(LCAT, "onDestroy  ====");

		if (mView != null)
			mView.cleanUp();
		else
			Log.e(LCAT, "cannot call cleanUp");
		super.onDestroy(activity);
	}

	@Override
	public void onStop(Activity activity) {
		Log.d(LCAT, "onStop =====");

		if (mView != null)
			mView.cleanUp();
		else
			Log.e(LCAT, "cannot call cleanUp");
		super.onStop(activity);
	}

	@Override
	public void onResume(Activity activity) {
		Log.d(LCAT, "onResume ======");
		super.onResume(activity);
		// mView=null;
		Log.d(LCAT, "onResume called <<<<<<<<<<<<<<<<<<<< try to reinit");
		if (mView != null)
			mView.init();
		else
			Log.e(LCAT, "cannot call reinit");
	}

	public void onStart(Activity activity) {
		Log.d(LCAT, "onStart ======");
		super.onStart(activity);
		// mView=null;
		if (mView != null)
			mView.init();
		else
			Log.e(LCAT, "cannot call reinit");
	}

	public static float dipToPixels(float dipValue) {
		Context context = TiApplication.getInstance();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue,
				context.getResources().getDisplayMetrics());

	}
}