package alcongdalcong_p.tab2;

import alcongdalcong_p.user.TabMain;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alcongdalcong_p.R;

public class Tab2_CM extends Fragment {

	static final String TAG = "Tab2_CM";

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tab2_cm, container, false);

	
		return view;
	}

	private void startNowPage() {
		TabMain.tabPosition = 1;
		startActivity(new Intent(getActivity(), TabMain.class));
		getActivity().finish();

	}

}
