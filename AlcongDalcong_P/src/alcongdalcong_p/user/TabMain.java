package alcongdalcong_p.user;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import alcongdalcong_p.options.MessagePage;
import alcongdalcong_p.options.SettingList;
import alcongdalcong_p.tab1.Tab1_Main;
import alcongdalcong_p.tab2.Tab2_CM;
import alcongdalcong_p.tab3.Tab3_PM;
import alcongdalcong_p.tab4.Tab4_Comm;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.Toast;

import com.example.alcongdalcong_p.R;

public class TabMain extends FragmentActivity implements
		TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

	static final String TAG = "TabMain";

	public static final String PREFS_NAME = "LoginPrefs";
	public static int tabPosition = 0;

	private boolean mPressFirstBackKey = false;
	private Timer timer;

	private TabHost mTabHost;
	private ViewPager mViewPager;
	private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, TabMain.TabInfo>();
	private PagerAdapter mPagerAdapter;

	private class TabInfo {
		private String tag;
		private Class<?> clss;
		private Bundle args;
		private Fragment fragment;

		TabInfo(String tag, Class<?> clazz, Bundle args) {
			this.tag = tag;
			this.clss = clazz;
			this.args = args;
		}
	}

	class TabFactory implements TabContentFactory {

		private final Context mContext;

		public TabFactory(Context context) {
			mContext = context;
		}

		public View createTabContent(String tag) {
			View v = new View(mContext);
			v.setMinimumWidth(0);
			v.setMinimumHeight(0);
			return v;
		}
	}

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Inflate the layout
		setContentView(R.layout.pager_main);

		// Initialise the TabHost
		this.initialiseTabHost(savedInstanceState);
		// Intent intent = getIntent();
		// int postion = intent.getIntExtra("POSITION", 0)

		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); // set
			// the tab as per the saved state
		}
		// Intialise ViewPager
		this.intialiseViewPager();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "설정").setIcon(android.R.drawable.ic_menu_manage);
		menu.add(0, 2, 0, "메세지함").setIcon(android.R.drawable.ic_menu_myplaces);
		menu.add(0, 3, 0, "로그아웃").setIcon(android.R.drawable.ic_menu_myplaces);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Intent intentSetting = new Intent(this, SettingList.class);
			startActivity(intentSetting);
			break;
		case 2:
			Intent intentMessage = new Intent(this, MessagePage.class);
			startActivity(intentMessage);
			break;
		case 3:

			finish();
			Intent intent = new Intent(TabMain.this, Login.class);
			startActivity(intent);
			break;

		}
		return true;
	}

	public void onBackPressed() {
		if (mPressFirstBackKey == false) {
			Toast.makeText(this, "한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
			mPressFirstBackKey = true;
			TimerTask second = new TimerTask() {
				@Override
				public void run() {
					timer.cancel();
					timer = null;
					mPressFirstBackKey = false;
				}
			};
			if (timer != null) {
				timer.cancel();
				timer = null;
			}
			timer = new Timer();
			timer.schedule(second, 2000);
		} else
			super.onBackPressed();
	}

	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("tab", mTabHost.getCurrentTabTag());
		// save the tab selected
		super.onSaveInstanceState(outState);
	}

	private void intialiseViewPager() {

		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, Tab1_Main.class.getName()));
		fragments.add(Fragment.instantiate(this, Tab2_CM.class.getName()));
		fragments.add(Fragment.instantiate(this, Tab3_PM.class.getName()));
		fragments.add(Fragment.instantiate(this, Tab4_Comm.class.getName()));

		this.mPagerAdapter = new PagerAdapter(
				super.getSupportFragmentManager(), fragments);

		this.mViewPager = (ViewPager) super.findViewById(R.id.viewpager);
		this.mViewPager.setAdapter(this.mPagerAdapter);
		this.mViewPager.setOnPageChangeListener(this);

	}

	private void initialiseTabHost(Bundle args) {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		TabInfo tabInfo = null;
		TabMain.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab1")
				.setIndicator("자녀상태"), (tabInfo = new TabInfo("Tab1",
				Tab1_Main.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);

		TabMain.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab2")
				.setIndicator("자녀미션"), (tabInfo = new TabInfo("Tab2",
				Tab2_CM.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);

		TabMain.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab3")
				.setIndicator("부모미션"), (tabInfo = new TabInfo("Tab3",
				Tab3_PM.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);

		TabMain.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab4")
				.setIndicator("커뮤니티"), (tabInfo = new TabInfo("Tab4",
				Tab4_Comm.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		mTabHost.setOnTabChangedListener(this);
	}

	private static void AddTab(TabMain activity, TabHost tabHost,
			TabHost.TabSpec tabSpec, TabInfo tabInfo) {
		tabSpec.setContent(activity.new TabFactory(activity));
		tabHost.addTab(tabSpec);
	}

	public void onTabChanged(String tag) {
		int pos = this.mTabHost.getCurrentTab();
		this.mViewPager.setCurrentItem(pos);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		this.mTabHost.setCurrentTab(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	public void startCheckingChild() {

		// PendingIntent pendingIntent;
		// AlarmManager sendingAlarmManager;
		//
		// long firstTime = SystemClock.elapsedRealtime();
		// firstTime += 1 * 1000; // add 1 second
		//
		// long interval = 10 * 1000; // 600 seconds
		//
		// Intent intent = new Intent(this, SendingListAlarmReceiver.class);
		//
		// intent.putExtra("idKey", idKey);
		// intent.putStringArrayListExtra("childs", childId);
		// pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		//
		// sendingAlarmManager = (AlarmManager)
		// getSystemService(Context.ALARM_SERVICE);
		// sendingAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
		// firstTime, interval, pendingIntent);

	}

	public void sendSMS(String smsNumber, String smsText) {
		PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0,
				new Intent("SMS_SENT_ACTION"), 0);
		PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0,
				new Intent("SMS_DELIVERED_ACTION"), 0);
		final Context mContext = TabMain.this;
		/**
		 * SMS가 발송될때 실행 When the SMS massage has been sent
		 */
		registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					// 전송 성공
					Toast.makeText(mContext, "전송 완료", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					// 전송 실패
					Toast.makeText(mContext, "전송 실패", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					// 서비스 지역 아님
					Toast.makeText(mContext, "서비스 지역이 아닙니다", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					// 무선 꺼짐
					Toast.makeText(mContext, "무선(Radio)가 꺼져있습니다",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					// PDU 실패
					Toast.makeText(mContext, "PDU Null", Toast.LENGTH_SHORT)
							.show();
					break;
				}
			}
		}, new IntentFilter("SMS_SENT_ACTION"));

		SmsManager mSmsManager = SmsManager.getDefault();
		mSmsManager.sendTextMessage(smsNumber, null, smsText, sentIntent,
				deliveredIntent);
	}

}
