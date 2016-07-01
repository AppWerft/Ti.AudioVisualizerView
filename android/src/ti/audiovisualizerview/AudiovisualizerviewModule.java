/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.audiovisualizerview;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;

import android.content.Context;

import com.pheelicks.utils.TunnelPlayerWorkaround;

//import android.app.Activity;

@Kroll.module(name = "Audiovisualizerview", id = "ti.audiovisualizerview")
public class AudiovisualizerviewModule extends KrollModule {

	@Kroll.constant
	public static final int AUDIOSESSION_OUTPUTMIX = 0;
	public static final String LCAT = "PheelicksM";

	public AudiovisualizerviewModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		Log.d(LCAT, "inside onAppCreate");
		Context context = TiApplication.getInstance().getApplicationContext();
		if (TunnelPlayerWorkaround.isTunnelDecodeEnabled(context)) {
			TunnelPlayerWorkaround.createSilentMediaPlayer(context);
		}

	}
}
