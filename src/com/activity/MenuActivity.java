package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.mobliemanager.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class MenuActivity extends SlidingFragmentActivity {
	/**************************** �໬����Ч��ʵ�� ****************************************/
	private CanvasTransformer mTransformer;

	/** menu�ؼ� **/
//	private TextView menu_mapmode;//��ͼģʽ
//	private ListView menu_listview;
	private Button menu_suggest;
	protected void menuView() {
		
		menu_suggest=(Button)findViewById(R.id.menu_suggest);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	protected void menuListener(final Activity activity) {
		menu_suggest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MenuActivity.this,SuggestActivity.class);
				startActivity(intent);
			}
		});

	}
	
	
	/** ��ʼ������Ч�� **/
	protected void initAnimation() {
		// TODO Auto-generated method stub
		mTransformer = new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				float scale = (float) (percentOpen * 0.25 + 0.75);
				canvas.scale(scale, scale, canvas.getWidth() / 2,
						canvas.getHeight() / 2);
			}

		};
	}

	/** ��ʼ�������˵� **/
	protected void initSlidingMenu() {
		// TODO Auto-generated method stub
		/** �����Ļ��� **/
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		setBehindContentView(R.layout.menu_frame);// ���ò໬�����Ĳ���
		SlidingMenu sm = getSlidingMenu();
		/** ���ò໬���� **/
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffset((int) (dm.widthPixels * 0.3));// ��0.7��0.3
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		sm.setBehindScrollScale(0.0f);
		sm.setBehindCanvasTransformer(mTransformer);

		setSlidingActionBarEnabled(true);
	}
	
	protected void show() {
		showMenu();
	}
	/*********************************************************************************/

}
