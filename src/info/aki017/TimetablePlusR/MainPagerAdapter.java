package info.aki017.TimetablePlusR;

import info.aki017.TimetablePlusR.TimetableItem.Direction;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

public class MainPagerAdapter extends FragmentPagerAdapter{
    public MainPagerAdapter(FragmentManager fm) {
		super(fm);
	}
    private List<String> names = new LinkedList<String>();
    private List<Fragment> pages = new LinkedList<Fragment>();

    /**
     * Pagerに登録したViewの数を返す
     * return int Size
     */
    @Override
    public int getCount() {
        return pages.size();
    }

    /**
     * ページを破棄する。
     * postion番目のViweを削除するために利用
     * @param container: 削除するViewのコンテナ
     * @param position : インスタンス削除位置
     * @param object   : instantiateItemメソッドで返却したオブジェクト
     */
    @Override
    public void destroyItem(View collection, int position, Object view) {
        //ViewPagerに登録していたTextViewを削除する
        ((ViewPager) collection).removeView((TextView) view);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return names.get(position);
    }
 
    private void addTab(String name,Fragment fragment)
    {
    	names.add(name);
    	pages.add(fragment);
    }
	/**
	 * タブを追加する
	 * 
	 * @param host TabHost
	 * @param name タブに表示する名前
	 * @param timatable 時刻表のデータ
	 * @param myclass 表示するActivityのクラス
	 */
	public void addTab(String name, Direction direction,String station) {
		TimetableFragment fragment = new TimetableFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable("Direction", direction);
        arguments.putString("Station", station);
        fragment.setArguments(arguments);
        addTab(name,fragment);
	}
	@Override
    public Fragment getItem(int position) {
		Trace.e("item @"+position+" - "+pages.get(position).toString());
        return pages.get(position);
    }
	
}
