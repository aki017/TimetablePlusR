package info.aki017.TimetablePlusR;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GetXMLDialog extends DialogFragment {

	public static GetXMLDialog newInstance(int title) {
		GetXMLDialog frag = new GetXMLDialog();
		Bundle args = new Bundle();
		args.putInt("title", title);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);

		this.setStyle(STYLE_NO_TITLE, android.R.style.Theme_Dialog);


		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		// return super.onCreateView(inflater, container, savedInstanceState);

		View v = inflater.inflate(R.layout.getxml_dialog, container, false);

		return v;
	}

}
