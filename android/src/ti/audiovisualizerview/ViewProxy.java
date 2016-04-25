/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.audiovisualizerview;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.TiApplication;
import com.pheelicks.visualizer.renderer.*;
import com.pheelicks.visualizer.VisualizerView;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import org.appcelerator.titanium.TiContext.OnLifecycleEvent;

@Kroll.proxy(creatableInModule = AudiovisualizerviewModule.class)
public class ViewProxy extends TiViewProxy implements OnLifecycleEvent {
	// instance of pheelicks view
	private VisualizerView pheelicksView;
	private static final String LCAT = "Pheelicks";
	final public int DEFAULT_AUDIOSESSIONID = 0;
	public int audioSessionId = DEFAULT_AUDIOSESSIONID;
	OnLifecycleEvent lifecyleListener;
	TiApplication appContext;
	ti.audiovisualizerview.VisualizerImageView mView; // instance of TiUIView

	// Constructor
	public ViewProxy() {
		super();
		
		
	}
	
	@Override
	public TiUIView createView(Activity activity) {
		mView = new VisualizerImageView(this);
		//mView.getLayoutParams().autoFillsHeight = true;
		//mView.getLayoutParams().autoFillsWidth = true;
		return mView;
	}

	// Handle creation options
	@Override
	public void handleCreationDict(KrollDict options) {
		super.handleCreationDict(options);
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
		Log.d(LCAT, "try to init view");
		this.mView.init();
	}
	
	
	
	

	/* INTERFACE */
	@Kroll.method
	public void release() {
		if (pheelicksView != null)
			pheelicksView.release();
		else
			Log.d(LCAT, "Error: pheelicksView is null");
	}

	@Kroll.method
	public void clearRenderers() {
		if (pheelicksView != null)
			pheelicksView.clearRenderers();
		else
			Log.d(LCAT, "Error: pheelicksView is null");
	}

	@Kroll.method
	public void addLineRenderer(@Kroll.argument(optional = true) KrollDict args) {
		if (args != null && args.containsKey("basicColor")) {

		}
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
		if (pheelicksView != null)
			pheelicksView.addRenderer(lineRenderer);
		else
			Log.d(LCAT, "Error: pheelicksView is null");
	}

	@Kroll.method
	public void addCircleRenderer() {
		Paint paint = new Paint();
		paint.setStrokeWidth(3f);
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 222, 92, 143));
		CircleRenderer circleRenderer = new CircleRenderer(paint, true);
		if (pheelicksView != null)
			pheelicksView.addRenderer(circleRenderer);
		else
			Log.d(LCAT, "Error: pheelicksView is null");

	}

	@Kroll.method
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

	@Kroll.method
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
		pheelicksView.addRenderer(barGraphRendererTop);
		Log.d(LCAT, "finishing addBarGraphRenderers ");
	}
}