package com.dummies.dragdrop;

import android.content.ClipData;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

public class DragDrop extends AppCompatActivity {

	TextView txt1, txt2, txt3, txt4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drag_drop);

		txt1 = (TextView) findViewById(R.id.txt1);
		txt2 = (TextView) findViewById(R.id.txt2);
		txt3 = (TextView) findViewById(R.id.txt3);
		txt4 = (TextView) findViewById(R.id.dropzone);

		txt1.setOnLongClickListener(longClickListener);
		txt2.setOnLongClickListener(longClickListener);
		txt3.setOnLongClickListener(longClickListener);

		txt4.setOnDragListener(dragListener);

	}

	View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
		@Override
		public boolean onLongClick(View v) {
			ClipData data = ClipData.newPlainText("", "");
			View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
			v.startDrag(data, myShadowBuilder,v,0);
			return true;
		}
	};

	View.OnDragListener dragListener = new View.OnDragListener() {
		@Override
		public boolean onDrag(View v, DragEvent event) {

			int dragEvent = event.getAction();
			final View view = (View) event.getLocalState();

			switch(dragEvent) {
				case DragEvent.ACTION_DRAG_ENTERED:


					break;
				case DragEvent.ACTION_DRAG_EXITED:

					break;
				case DragEvent.ACTION_DROP:
					if (view.getId() == R.id.txt1) {
						view.animate()
								.x(txt4.getX())
								.y(txt4.getY())
								.setDuration(700)
								.start();
					}
					else {
						MediaPlayer mymedia = MediaPlayer.create(DragDrop.this, R.raw.pikachu);
						mymedia.start();
					}
					break;
			}
			return true;
		}
	};
}


					if (view.getId() == R.id.txt1) {
							txt4.setText("TextView 1 is gone");
							}
							else if (view.getId() == R.id.txt2) {
							txt4.setText("TextView 2 is died");
							}
							else if (view.getId() == R.id.txt3) {
							txt4.setText("TextView 3 was eaten Truman");
							}


							(view.getId() == R.id.txt2) {
							view.animate()
							.x(txt4.getX())
							.y(txt4.getY())
							.setDuration(700)
							.start();
							}
							else if (view.getId() == R.id.txt3) {
							view.animate()
							.x(txt4.getX())
							.y(txt4.getY())
							.setDuration(700)
							.start();
							}