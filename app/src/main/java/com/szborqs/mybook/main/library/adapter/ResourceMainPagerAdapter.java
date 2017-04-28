package com.szborqs.mybook.main.library.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * viewpager+fragment  Adapter
 * @author liyongde
 * @version 2015-3-20  下午6:34:09
 */
public class ResourceMainPagerAdapter extends FragmentStatePagerAdapter {
	private List<Fragment> fragmentsList;

	public ResourceMainPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public ResourceMainPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.fragmentsList = fragments;
	}

	@Override
	public Fragment getItem(int arg0) {
		return fragmentsList.get(arg0);
	}

	@Override
	public int getCount() {
		return fragmentsList.size();
	}
}
