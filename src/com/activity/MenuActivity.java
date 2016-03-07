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
	/**************************** 侧滑动画效果实现 ****************************************/
	private CanvasTransformer mTransformer;

	/** menu控件 **/
//	private TextView menu_mapmode;//地图模式
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
	
	
	/** 初始化动画效果 **/
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

	/** 初始化滑动菜单 **/
	protected void initSlidingMenu() {
		// TODO Auto-generated method stub
		/** 获得屏幕宽高 **/
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		setBehindContentView(R.layout.menu_frame);// 设置侧滑出来的布局
		SlidingMenu sm = getSlidingMenu();
		/** 设置侧滑参数 **/
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffset((int) (dm.widthPixels * 0.3));// 左0.7右0.3
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
