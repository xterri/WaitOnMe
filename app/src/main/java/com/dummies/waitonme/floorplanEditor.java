package com.dummies.waitonme;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class floorplanEditor extends AppCompatActivity{
		//implements ColorPickerDialog.OnColorChangedListener {

	MyView mv;
	AlertDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mv= new MyView(this);
		mv.setDrawingCacheEnabled(true);
		mv.setBackgroundColor(0xFFFFFFFF);
		setContentView(mv);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(0xFF000000);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(12);
	}

	private Paint       mPaint;

	public class MyView extends View {

		public int width;
		public  int height;
		private static final float MINP = 0.25f;
		private static final float MAXP = 0.75f;
		private Bitmap  mBitmap;
		private Canvas  mCanvas;
		private Path    mPath;
		private Paint   mBitmapPaint;
		Context context;

		public MyView(Context c) {
			super(c);
			context=c;
			mPath = new Path();
			mBitmapPaint = new Paint(Paint.DITHER_FLAG);

		}

		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh)
		{
			super.onSizeChanged(w, h, oldw, oldh);

			width = w;      // don't forget these
			height = h;

			mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			mCanvas = new Canvas(mBitmap);
		}


		public void clearDrawing()
		{
			setDrawingCacheEnabled(false);
			// don't forget that one and the match below,
			// or you just keep getting a duplicate when you save.

			onSizeChanged(width, height, width, height);
			invalidate();

			setDrawingCacheEnabled(true);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
			canvas.drawPath(mPath, mPaint);
		}

		private float mX, mY;
		private static final float TOUCH_TOLERANCE = 4;

		private void touch_start(float x, float y) {
			//showDialog();
			mPath.reset();
			mPath.moveTo(x, y);
			mX = x;
			mY = y;

		}
		private void touch_move(float x, float y) {
			float dx = Math.abs(x - mX);
			float dy = Math.abs(y - mY);
			if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
				mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
				mX = x;
				mY = y;
			}
		}

		private void touch_up() {
			mPath.lineTo(mX, mY);
			mCanvas.drawPath(mPath, mPaint);
			mPath.reset();
			mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			float x = event.getX();
			float y = event.getY();

			switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					touch_start(x, y);
					invalidate();
					break;
				case MotionEvent.ACTION_MOVE:

					touch_move(x, y);
					invalidate();
					break;
				case MotionEvent.ACTION_UP:
					touch_up();
					invalidate();
					break;
			}
			return true;
		}
	}

	private static final int BRUSH_MENU_ID = Menu.FIRST;
	private static final int ERASE_MENU_ID = Menu.FIRST + 1;
	private static final int Clear = Menu.FIRST + 2;
	private static final int Save = Menu.FIRST + 3;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		menu.add(0, BRUSH_MENU_ID, 0, "Brush").setShortcut('3', 'c');
		menu.add(0, ERASE_MENU_ID, 0, "Erase").setShortcut('5', 'z');
		menu.add(0, Clear, 0, "Clear").setShortcut('5', 'z');
		menu.add(0, Save, 0, "Save").setShortcut('5', 'z');

		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		mPaint.setXfermode(null);
		mPaint.setAlpha(0xFF);

		switch (item.getItemId()) {
			case BRUSH_MENU_ID:
				mPaint.setColor(0xFF000000);
				mPaint.setStrokeWidth(12);
				return true;
			case ERASE_MENU_ID:
				mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
				mPaint.setAlpha(0xFF);
				mPaint.setStrokeWidth(30);
				return true;
			case Clear:
				AlertDialog.Builder clearalert = new AlertDialog.Builder(floorplanEditor.this);
				clearalert.setTitle("Are you sure?");
				LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.FILL_PARENT,
						LinearLayout.LayoutParams.FILL_PARENT);
				clearalert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
							mv.clearDrawing();
					}
				});

				clearalert.show();
				return true;
			case Save:
				AlertDialog.Builder editalert = new AlertDialog.Builder(floorplanEditor.this);
				editalert.setTitle("Please Enter the name with which you want to Save");
				final EditText path = new EditText(floorplanEditor.this);
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.FILL_PARENT,
						LinearLayout.LayoutParams.FILL_PARENT);
				path.setLayoutParams(lp);
				editalert.setView(path);
				editalert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						String name= path.getText().toString();
						Bitmap bitmap = mv.getDrawingCache();

						String path = Environment.getExternalStorageDirectory().getAbsolutePath();
						File file = new File("/sdcard/"+name+".png");
						try
						{
							if(!file.exists())
							{
								file.createNewFile();
							}
							FileOutputStream ostream = new FileOutputStream(file);
							bitmap.compress(Bitmap.CompressFormat.PNG, 10, ostream);
							ostream.close();
							mv.invalidate();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}finally
						{

							mv.setDrawingCacheEnabled(false);
						}
					}
				});

				editalert.show();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
