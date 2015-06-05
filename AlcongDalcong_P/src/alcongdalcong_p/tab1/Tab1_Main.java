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
 * < Activity Class: Tab1_Main>******************************* ChildStae_Page에서
 * 날짜별 어플 사용 통계량을 보여줄 때, 날짜 정보를 다루는 class*************
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

		// // 전역 변수 !!
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
		// // 자녀들의 간략한 정보를 보여주는 리스트
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

		// 자녀 추가 버튼 눌렀을 때
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
				// dialog.setTitle("자녀 추가");
				//
				// dialog.setButton2("보내기", new
				// DialogInterface.OnClickListener() {
				//
				// public void onClick(DialogInterface dialog, int which) {
				// String childName = etInsertName.getText().toString();
				// String childPhoneNum = etInsertPhone.getText()
				// .toString();
				// gcmManager.sendFamilyCodeMessage(childPhoneNum, name,
				// phone, familyCode);
				// Toast.makeText(getActivity(),
				// childName + "에게  자녀 신청을 보냈습니다 :)",
				// Toast.LENGTH_LONG).show();
				// }
				// });
				//
				// dialog.setButton("취소", new DialogInterface.OnClickListener()
				// {
				// public void onClick(DialogInterface dialog, int which) {
				//
				// dialog.dismiss();
				// }
				// });
				// dialog.show();
				Toast.makeText(getActivity(), "자녀추가", Toast.LENGTH_SHORT)
						.show();
			}
		});

		// 배우자 추가하기
		Button add_partner = (Button) view.findViewById(R.id.add_partner);
		add_partner.setOnClickListener(new Button.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				Toast.makeText(getActivity(), "서비스 준비 중이에용 :D",
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
				 * tv.setText("배우자이름"); etInsertName = (EditText) layout
				 * .findViewById(R.id.et_alert_child_name); etInsertPhone =
				 * (EditText) layout .findViewById(R.id.et_alert_child_phone);
				 * 
				 * builder = new AlertDialog.Builder(mContext);
				 * builder.setView(layout); dialog = builder.create();
				 * dialog.setTitle("배우자 추가");
				 * 
				 * dialog.setButton2("보내기", new
				 * DialogInterface.OnClickListener() {
				 * 
				 * public void onClick(DialogInterface dialog, int which) {
				 * String childName = etInsertName.getText().toString(); String
				 * childPhoneNum = etInsertPhone.getText() .toString();
				 * gcmManager.sendFamilyCodeMessage(childPhoneNum, name, phone,
				 * familyCode); Toast.makeText(getActivity(), childName +
				 * "에게  배우자 신청을 보냈습니다 :)", Toast.LENGTH_LONG).show(); } });
				 * 
				 * dialog.setButton("취소", new DialogInterface.OnClickListener()
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

		// 메시지@@@!

	}

	// ListView의 List item을 눌렀을 때
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {

		/* 자녀 신청 후 수락되지 않은 자녀를 눌렀을 때 */
		// Intent intent = new Intent(getActivity(), ChildState_Page.class);
		// intent.putExtra("childIndex", index);
		// startActivity(intent);
	}

	// ListView의 List를 (이름-상태-이미지)모양으로 만들기
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
	// // 각 아이템에 정보 출력
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