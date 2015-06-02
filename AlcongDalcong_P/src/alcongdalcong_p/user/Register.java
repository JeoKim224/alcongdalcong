package alcongdalcong_p.user;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alcongdalcong_p.R;

public class Register extends Activity {

	String user_birth;

	EditText idEditText;
	EditText pwdEditText;
	EditText pwdCheckEditText;
	EditText nameEditText;
	EditText phoneEditText;
	Button birthButton;
	boolean isBirthInput = false;
	boolean idDupCheck = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);

		idEditText = (EditText) findViewById(R.id.edit_input_id);
		pwdEditText = (EditText) findViewById(R.id.edit_input_pw);
		pwdCheckEditText = (EditText) findViewById(R.id.edit_input_checkpw);
		nameEditText = (EditText) findViewById(R.id.edit_input_name);
		phoneEditText = (EditText) findViewById(R.id.edit_input_phone);
		birthButton = (Button) findViewById(R.id.btn_input_birth);
	}

	public void btnDupCheck(View view) {
		// ��񿡼� �ߺ��Ǵ� ���̵� �ִ��� ������ üũ�ؼ� ��� ���� ��� �Ұ��� �˷��ֱ�
	}

	public void btnRegDone(View view) {
		final String id = idEditText.getText().toString();
		final String password = pwdEditText.getText().toString();
		String passwordCheck = pwdCheckEditText.getText().toString();
		final String name = nameEditText.getText().toString();
		final String phone = phoneEditText.getText().toString();

		if (!id.isEmpty() && !password.isEmpty() && !passwordCheck.isEmpty()
				&& !name.isEmpty() && !phone.isEmpty()) {
			// ��й�ȣ�� ��й�ȣȮ���̶� ������ üũ
			if (password.equals(passwordCheck)) {
				// ���̵� �ߺ�üũ ���� Ȯ��
				if (idDupCheck) { // üũ������ ȸ�� ����~~
					// ��� �����ϴ� ���� ����

					if (isBirthInput) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								try {
									URL url = new URL(
											ConstantVariables.SERVER_ADDR
													+ ConstantVariables.REGISTER
													+ "ID="
													+ URLEncoder.encode(id,
															"UTF-8")
													+ "&PASSWD="
													+ URLEncoder.encode(
															password, "UTF-8")
													+ "&NAME="
													+ URLEncoder.encode(name,
															"UTF-8")
													+ "&PHONE="
													+ URLEncoder.encode(phone,
															"UTF-8")
													+ "&CODE="
													+ URLEncoder.encode(id,
															"UTF-8")
													+ "&POSITION="
													+ URLEncoder.encode(
															"Parent", "UTF-8")
													+ "&B_DAY="
													+ URLEncoder
															.encode(user_birth,
																	"UTF-8"));
									url.openStream();
								} catch (Exception e) {
									e.getMessage();
									// e.printStackTrace();
								}
							}
						}).start();

						Toast.makeText(this, "ȸ�� ���� �Ǿ����ϴ�:)\n�ٽ� �α��� ���ּ���!",
								Toast.LENGTH_SHORT).show();

						Intent i = new Intent(Register.this, Login.class);
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
					} else {
						Toast.makeText(this, "������ �Է����ּ���", Toast.LENGTH_SHORT)
								.show();
					}
				} else { // üũ ��������
					Toast.makeText(this, "���̵� �ߺ� üũ ���ּ���", Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				Toast.makeText(this, "�Է��Ͻ� ��й�ȣ�� �ٸ��ϴ�.", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(this, "��� ������ �Է����ּ���.", Toast.LENGTH_SHORT).show();
		}
		// db�� user�� �⺻ data�� ������.
	}

	// ���� �߷� ��ư ������ �� pop-up
	public void btnBirInput(View view) {
		Calendar c = Calendar.getInstance();
		int cyear = c.get(Calendar.YEAR) - 10;
		cyear = cyear - 10;
		int cmonth = c.get(Calendar.MONTH);
		int cday = c.get(Calendar.DAY_OF_MONTH);
		DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
			// onDateSet method
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				String s_year = String.valueOf(year).substring(2, 4);
				String s_month = String.valueOf(monthOfYear + 1);
				String s_day = String.valueOf(dayOfMonth);
				if (Integer.parseInt(s_year) < 10)
					s_year = "0" + s_year;
				if (monthOfYear + 1 < 10)
					s_month = "0" + s_month;
				if (dayOfMonth < 10)
					s_day = "0" + s_day;

				user_birth = s_year + "/" + s_month + "/" + s_day;
				birthButton.setText(user_birth);
				isBirthInput = true;
			}
		};
		DatePickerDialog alert = new DatePickerDialog(this, mDateSetListener,
				cyear, cmonth, cday);
		alert.show();
	}
}