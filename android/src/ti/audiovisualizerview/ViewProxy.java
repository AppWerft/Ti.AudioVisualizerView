package ti.audiovisualizerview;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.KrollModule;

import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.TiContext.OnLifecycleEvent;

import android.app.Activity;

@Kroll.proxy(creatableInModule = AudiovisualizerviewModule.class)
public class ViewProxy extends TiViewProxy implements OnLifecycleEvent {
	private static final String LCAT = "Pheelicks";
	private VisualizerImageView mView; // instance of TiUIView

	public ViewProxy() {
		super();
	}

	@Override
	public void onPause(Activity activity) {
		super.onPause(activity);
		this.mView.cleanUp();
		Log.d(LCAT, "onPause called >>>>>>>>>>>>>>>>>>>>");
	}

	@Override
	public void onResume(Activity activity) {
		super.onResume(activity);
		Log.d(LCAT, "onResume called <<<<<<<<<<<<<<<<<<<<");
		this.mView.init();
	}

	@Override
	public TiUIView createView(Activity activity) {
		mView = new VisualizerImageView(this);
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
	public void addBarGraphRenderers() {
		mView.addBarGraphRenderers();
	}

	@Override
	public void handleCreationDict(KrollDict options) {
		super.handleCreationDict(options);
	}

	public void handleCreationArgs(KrollModule createdInModule, Object[] args) {
		super.handleCreationArgs(createdInModule, args);
	}
}