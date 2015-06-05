package alcongdalcong_p.tab1;

import java.util.ArrayList;

import com.example.alcongdalcong_p.R;

import alcongdalcong_p.options.MessagePage;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/***********************
 * < Activity Class: Tab1_Main>******************************* ChildStae_Page����
 * ��¥�� ���� ��� ��跮�� ������ ��, ��¥ ������ �ٷ�� class*************
 ********************************************************************************/

public class Tab1_Main extends Fragment implements OnItemClickListener {

	View view;

	ArrayAdapter<String> adapter;
	ListView childListView;
	TextView childNameTextView, childStateTextView;
	EditText etInsertName, etInsertPhone;
	ImageView childStateImageView = null;
	Button ApplySendButton, ApplyCancelButton;
	int numOfChild;
	String familyCode, name, phone;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_tab1_main, container, false);

		// // ���� ���� !!
		// GlobalVariables globalVariable = (GlobalVariables) getActivity()
		// .getApplicationContext();
		//
		// numOfChild = globalVariable.getNumberOfChild();
		//
		// child = globalVariable.getChildList();
		// name = globalVariable.getName();
		// phone = globalVariable.getPhone();
		// familyCode = globalVariable.getFamilyCode();
		//
		// gcmManager = new GCMManager();
		//
		// // �ڳ���� ������ ������ �����ִ� ����Ʈ
		// childList = new ArrayList<ChildState>();
		// childList.clear();
		//
		// for (int i = 0; i < numOfChild; i++) {
		// childList.add(child[i]);
		// }
		//
		// ChildArrayAdapter arrayAdapter = new
		// ChildArrayAdapter(getActivity());
		// arrayAdapter.notifyDataSetChanged();
		// childListView = (ListView) view.findViewById(R.id.child_list);
		// childListView.setAdapter(arrayAdapter);
		// childListView.setOnItemClickListener(this);

		// �ڳ� �߰� ��ư ������ ��
		Button add_child = (Button) view.findViewById(R.id.add_child);
		add_child.setOnClickListener(new Button.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {

				// Context mContext = getActivity();
				// AlertDialog.Builder builder;
				// AlertDialog dialog;
				// LayoutInflater aler_inflater = (LayoutInflater) mContext
				// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				//
				// View layout = aler_inflater.inflate(
				// R.layout.tab1_add_child_dialog, (ViewGroup) view
				// .findViewById(R.id.layout_add_child_alert));
				// etInsertName = (EditText) layout
				// .findViewById(R.id.et_alert_child_name);
				// etInsertPhone = (EditText) layout
				// .findViewById(R.id.et_alert_child_phone);
				//
				// builder = new AlertDialog.Builder(mContext);
				// builder.setView(layout);
				// dialog = builder.create();
				// dialog.setTitle("�ڳ� �߰�");
				//
				// dialog.setButton2("������", new
				// DialogInterface.OnClickListener() {
				//
				// public void onClick(DialogInterface dialog, int which) {
				// String childName = etInsertName.getText().toString();
				// String childPhoneNum = etInsertPhone.getText()
				// .toString();
				// gcmManager.sendFamilyCodeMessage(childPhoneNum, name,
				// phone, familyCode);
				// Toast.makeText(getActivity(),
				// childName + "����  �ڳ� ��û�� ���½��ϴ� :)",
				// Toast.LENGTH_LONG).show();
				// }
				// });
				//
				// dialog.setButton("���", new DialogInterface.OnClickListener()
				// {
				// public void onClick(DialogInterface dialog, int which) {
				//
				// dialog.dismiss();
				// }
				// });
				// dialog.show();
				Toast.makeText(getActivity(), "�ڳ��߰�", Toast.LENGTH_SHORT)
						.show();
			}
		});

		// ����� �߰��ϱ�
		Button add_partner = (Button) view.findViewById(R.id.add_partner);
		add_partner.setOnClickListener(new Button.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				Toast.makeText(getActivity(), "���� �غ� ���̿��� :D",
						Toast.LENGTH_LONG).show();
				/*
				 * Context mContext = getActivity(); AlertDialog.Builder
				 * builder; AlertDialog dialog; LayoutInflater aler_inflater =
				 * (LayoutInflater) mContext
				 * .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				 * 
				 * View layout = aler_inflater.inflate(
				 * R.layout.tab1_add_child_dialog, (ViewGroup) view
				 * .findViewById(R.id.layout_add_child_alert)); TextView tv =
				 * (TextView) layout.findViewById(R.id.add_child_name);
				 * tv.setText("������̸�"); etInsertName = (EditText) layout
				 * .findViewById(R.id.et_alert_child_name); etInsertPhone =
				 * (EditText) layout .findViewById(R.id.et_alert_child_phone);
				 * 
				 * builder = new AlertDialog.Builder(mContext);
				 * builder.setView(layout); dialog = builder.create();
				 * dialog.setTitle("����� �߰�");
				 * 
				 * dialog.setButton2("������", new
				 * DialogInterface.OnClickListener() {
				 * 
				 * public void onClick(DialogInterface dialog, int which) {
				 * String childName = etInsertName.getText().toString(); String
				 * childPhoneNum = etInsertPhone.getText() .toString();
				 * gcmManager.sendFamilyCodeMessage(childPhoneNum, name, phone,
				 * familyCode); Toast.makeText(getActivity(), childName +
				 * "����  ����� ��û�� ���½��ϴ� :)", Toast.LENGTH_LONG).show(); } });
				 * 
				 * dialog.setButton("���", new DialogInterface.OnClickListener()
				 * { public void onClick(DialogInterface dialog, int which) {
				 * 
				 * dialog.dismiss(); } }); dialog.show();
				 */
			}
		});

		/*
		 * Button btnCheckAddiction = (Button)
		 * view.findViewById(R.id.btn_message);
		 * btnCheckAddiction.setOnClickListener(message);
		 */

		return view;

		// �޽���@@@!

	}

	// ListView�� List item�� ������ ��
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {

		/* �ڳ� ��û �� �������� ���� �ڳฦ ������ �� */
		// Intent intent = new Intent(getActivity(), ChildState_Page.class);
		// intent.putExtra("childIndex", index);
		// startActivity(intent);
	}

	// ListView�� List�� (�̸�-����-�̹���)������� �����
	// public class ChildArrayAdapter extends ArrayAdapter<ChildState> {
	//
	// private final LayoutInflater mInflater;
	//
	// public ChildArrayAdapter(Context context) {
	// super(context, android.R.layout.activity_list_item, childList);
	// mInflater = (LayoutInflater) context
	// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// }
	//
	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	// View view;
	// if (convertView == null) {
	// view = mInflater
	// .inflate(R.layout.tab1_row_child, parent, false);
	// } else {
	// view = convertView;
	// }
	// // �� �����ۿ� ���� ���
	// ChildState item = getItem(position);
	// ((TextView) view.findViewById(R.id.tv_child_name)).setText(item
	// .getName());
	// ((TextView) view.findViewById(R.id.tv_child_state)).setText("\""
	// + item.getState() + "\"");
	// try {
	// ((ImageView) view.findViewById(R.id.img_child_state))
	// .setImageResource(item.getResourceStateImage());
	// } catch (NullPointerException e) {
	// e.printStackTrace();
	// }
	//
	// return view;
	// }
	//
	// }

	private OnClickListener message = new Button.OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(getActivity(), MessagePage.class);
			startActivity(intent);
		}
	};

}