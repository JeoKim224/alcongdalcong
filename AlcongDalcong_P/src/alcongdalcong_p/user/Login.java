package alcongdalcong_p.user;

import java.util.ArrayList;

import alcongdalcong_p.util.ConstantVariables;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alcongdalcong_p.R;

public class Login extends Activity {
	static final String TAG = "Login";

	Context mContext;
	ConstantVariables cv;
	EditText idInputEditText, passwdInputEditText;
	TextView findIdPw;
	ArrayList<String> arrayId;
	
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

		mContext = this;
		idInputEditText = (EditText) findViewById(R.id.et_id);
		passwdInputEditText = (EditText) findViewById(R.id.et_password);
		findIdPw = (TextView) findViewById(R.id.find_id_pw);

		findIdPw.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(Login.this, "��й�ȣ ã��", Toast.LENGTH_SHORT);
			}
		});
	}

	public void btnLogin(View view) {
		if (idInputEditText.getText().toString().equals("")
				|| passwdInputEditText.getText().toString().equals("")) {
			Toast.makeText(Login.this, "���̵�� ��й�ȣ�� ��� �Է����ּ���",
					Toast.LENGTH_SHORT).show();
			return;
		}

		String url = ConstantVariables.SERVER_ADDR + ConstantVariables.LOGIN
				+ "?id=" + idInputEditText.getText().toString() + "&passwd="
				+ passwdInputEditText.getText().toString();
		
		sp = getSharedPreferences(ConstantVariables.PREF_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("logged", "yes");
		editor.commit();

		Intent loginIntent = new Intent(Login.this, TabMain.class);

		startActivity(loginIntent);
		this.finish();

	}

	public void btnRegister(View view) {
		Intent regIntent = new Intent(Login.this, Register.class);
		startActivity(regIntent);
	}

	public void btnFindId(View view) {
		Toast.makeText(Login.this, "���̵� ã��", Toast.LENGTH_SHORT);
	}

	public void btnFindPw(View view) {
		Toast.makeText(Login.this, "��й�ȣ ã��", Toast.LENGTH_SHORT);
	}

	public void sendSMS(String smsNumber, String smsText) {
		PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0,
				new Intent("SMS_SENT_ACTION"), 0);
		PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0,
				new Intent("SMS_DELIVERED_ACTION"), 0);

		/**
		 * SMS�� �߼۵ɶ� ���� When the SMS massage has been sent
		 */
		registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					// ���� ����
					Toast.makeText(mContext, "���� �Ϸ�", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					// ���� ����
					Toast.makeText(mContext, "���� ����", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					// ���� ���� �ƴ�
					Toast.makeText(mContext, "���� ������ �ƴմϴ�", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					// ���� ����
					Toast.makeText(mContext, "����(Radio)�� �����ֽ��ϴ�",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					// PDU ����
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