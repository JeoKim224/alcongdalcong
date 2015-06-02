package alcongdalcong_p.user;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;

import com.example.alcongdalcong_p.R;

public class SplashActivity extends Activity {
	private SharedPreferences sp;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);

		sp = getSharedPreferences(ConstantVariables.PREF_NAME, MODE_PRIVATE);

		Log.e("ALDAL", "로그인 한 적 있는가?" + sp.getString("logged", ""));

		if (!sp.getString("logged", "").equals("yes")) {
			Handler handler = new Handler() {
				public void handleMessage(Message msg) {
					Intent i = new Intent(SplashActivity.this, Login.class);
					startActivity(i);

					finish(); // 액티비티 종료
				}
			};
			handler.sendEmptyMessageDelayed(0, 3000);
		} else {
			// String url = ConstantVariables.SERVER_ADDR
			// + ConstantVariables.MEMBER + "?uid="
			// + sp.getString("member", "");
			//
			// new ReadJSONFeed().execute(url);

			Handler handler = new Handler() {
				public void handleMessage(Message msg) {
					Intent i = new Intent(SplashActivity.this, TabMain.class);
					startActivity(i);

					finish(); // 액티비티 종료
				}
			};
			handler.sendEmptyMessageDelayed(0, 3000);
		}
	}

}