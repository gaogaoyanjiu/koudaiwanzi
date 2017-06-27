/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 mob.com. All rights reserved.
 */

package cn.sharesdk.onekeyshare.themes.classic;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.sharesdk.framework.CustomPlatform;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.CustomerLogo;
import cn.sharesdk.onekeyshare.OnekeySharePage;
import cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;

import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.mob.tools.gui.MobViewPager;
import com.mob.tools.utils.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/** 九宫格的抽象类 */
public abstract class PlatformPage extends OnekeySharePage {
	private ClassicTheme impl;
	/** 点击九格宫，展示编辑界面，要执行的子线程 */
	private Runnable beforeFinish;
	/** 九宫格显示时的动画 */
	private Animation animShow;
	/** 九宫格隐藏时的动画 */
	private Animation animHide,animHide2;
	private LinearLayout llPanel, myLayout, titleLayout;
	private boolean finished;
	private DetailsActivity detail;
	private Context context;

	public PlatformPage(OnekeyShareThemeImpl impl) {
		super(impl);
		this.impl = R.forceCast(impl);
	}

	public void onCreate() {

		activity.getWindow().setBackgroundDrawable(new ColorDrawable(0x4c000000));
		initAnims();

		LinearLayout llPage = new LinearLayout(activity);
		llPage.setOrientation(LinearLayout.VERTICAL);
		activity.setContentView(llPage);

		SharedPreferences share = activity.getSharedPreferences("invite", Context.MODE_PRIVATE);
		String invite = share.getString("invite", "");

//		String url = MyUrl.url+"Users.svc/useritem/" + UID;


		TextView vTop = new TextView(activity);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lp.weight = 8;
		vTop.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		llPage.addView(vTop, lp);

		//添加我的邀请码
		TextView tv_my = new TextView(activity);
		tv_my.setText("我的邀请码:");
		tv_my.setTextSize(20);
		tv_my.setTextColor(Color.parseColor("#000000"));
		TextView tv_num = new TextView(activity);
		tv_num.setText(invite);
		tv_num.setTextSize(20);
		tv_num.setTextColor(Color.parseColor("#000000"));
		myLayout = new LinearLayout(activity);
		lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		myLayout.setOrientation(LinearLayout.HORIZONTAL);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		params.setMargins(200,40,0,0);

		myLayout.setLayoutParams(params);
		myLayout.addView(tv_my);
		myLayout.addView(tv_num);
		myLayout.setBackgroundDrawable(new ColorDrawable(0xffffffff));
//		myLayout.setAnimation(animShow);
//		llPage.addView(myLayout, lp);

		TextView tv_title = new TextView(activity);
		tv_title.setText("成功填写邀请人  +100积分");
		tv_title.setTextSize(15);
		tv_title.setTextColor(Color.parseColor("#000000"));

		TextView tv_name = new TextView(activity);
		tv_name.setText("给邀请人增加10%消费金额的积分");
		tv_name.setTextSize(15);
		tv_name.setTextColor(Color.parseColor("#000000"));
//		LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
//				LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//		params2.setMargins(300,0,0,0);
		tv_name.setLayoutParams(params);
		tv_title.setLayoutParams(params);
		titleLayout = new LinearLayout(activity);
		titleLayout.setOrientation(LinearLayout.VERTICAL);

		titleLayout.addView(myLayout);
		titleLayout.addView(tv_title);
		titleLayout.addView(tv_name);
		titleLayout.setBackgroundDrawable(new ColorDrawable(0xffffffff));
		titleLayout.setAnimation(animShow);

		lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lp.weight = 1;
		llPage.addView(titleLayout, lp);




		llPanel = new LinearLayout(activity);
		llPanel.setOrientation(LinearLayout.VERTICAL);
		lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		llPanel.setAnimation(animShow);
		llPage.addView(llPanel, lp);

		MobViewPager mvp = new MobViewPager(activity);
		ArrayList<Object> cells = collectCells();
		PlatformPageAdapter adapter = newAdapter(cells);
		lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, adapter.getPanelHeight());
		llPanel.addView(mvp, lp);

		IndicatorView vInd = new IndicatorView(activity);
		lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, adapter.getBottomHeight());
		llPanel.addView(vInd, lp);

		vInd.setScreenCount(adapter.getCount());
		vInd.onScreenChange(0, 0);
		adapter.setIndicator(vInd);
		mvp.setAdapter(adapter);
	}

	protected abstract PlatformPageAdapter newAdapter(ArrayList<Object> cells);

	protected ArrayList<Object> collectCells() {
		ArrayList<Object> cells = new ArrayList<Object>();

		Platform[] platforms = ShareSDK.getPlatformList();
		if (platforms == null) {
			platforms = new Platform[0];
		}
		HashMap<String, String> hides = getHiddenPlatforms();
		if (hides == null) {
			hides = new HashMap<String, String>();
		}
		for (Platform p : platforms) {
			if (!hides.containsKey(p.getName())) {
				cells.add(p);
			}
		}

		ArrayList<CustomerLogo> customers = getCustomerLogos();
		if (customers != null && customers.size() > 0) {
			cells.addAll(customers);
		}

		return cells;
	}

	public final void showEditPage(final Platform platform) {
		beforeFinish = new Runnable() {
			public void run() {
				boolean isSilent = isSilent();
				boolean isCustomPlatform = platform instanceof CustomPlatform;
				boolean isUseClientToShare = isUseClientToShare(platform);
				if (isSilent || isCustomPlatform || isUseClientToShare) {
					shareSilently(platform);
				} else {
					ShareParams sp = formateShareData(platform);
					if (sp != null) {
						// 编辑分享内容的统计
						ShareSDK.logDemoEvent(3, null);
						if (getCustomizeCallback() != null) {
							getCustomizeCallback().onShare(platform, sp);
						}
						impl.showEditPage(activity, platform, sp);
					}
				}
			}
		};
		finish();
	}

	public final void performCustomLogoClick(final View v, final CustomerLogo logo) {
		beforeFinish = new Runnable() {
			public void run() {
				logo.listener.onClick(v);
			}
		};
		finish();
	}

	private void initAnims() {
		animShow = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 1,
				Animation.RELATIVE_TO_SELF, 0);
		animShow.setDuration(300);

		animHide = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 1);
		animHide.setDuration(300);
		animHide2 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 1);
		animHide2.setDuration(280);

	}

	public boolean onFinish() {
		if (finished) {
			finished = false;
			return false;
		}

		animHide.setAnimationListener(new Animation.AnimationListener() {
			public void onAnimationStart(Animation animation) {

			}

			public void onAnimationRepeat(Animation animation) {

			}

			public void onAnimationEnd(Animation animation) {
				if (beforeFinish == null) {
					// 取消分享菜单的统计
					ShareSDK.logDemoEvent(2, null);
				} else {
					beforeFinish.run();
					beforeFinish = null;
				}

				finished = true;
				finish();
			}
		});
		llPanel.clearAnimation();
		llPanel.setAnimation(animHide);
		llPanel.setVisibility(View.GONE);
//		myLayout.setAnimation(animHide);
//		myLayout.setVisibility(View.GONE);
		titleLayout.setAnimation(animHide2);
		titleLayout.setVisibility(View.GONE);
		return true;
	}

}
