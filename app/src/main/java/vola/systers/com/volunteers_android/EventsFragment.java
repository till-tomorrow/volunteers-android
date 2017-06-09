package vola.systers.com.volunteers_android;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;


/*
 * User can see all events either in list view or maps view in this fragment.
 *
 * @author divyapandilla
 * @since 2017-06-09
 */


public class EventsFragment extends Fragment {

    private TabLayout mTabLayout;

    private int[] mTabsIcons = {
            R.drawable.listicon,
            R.drawable.mapicon};


    public EventsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_fragment, container, false);

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getFragmentManager());
        if (viewPager != null)
            viewPager.setAdapter(pagerAdapter);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        if (mTabLayout != null) {
            mTabLayout.setupWithViewPager(viewPager);

            for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = mTabLayout.getTabAt(i);
                if (tab != null)
                    tab.setCustomView(pagerAdapter.getTabView(i));
            }

            mTabLayout.getTabAt(0).getCustomView().setSelected(true);
        }
        return rootView;
    }



    private class MyPagerAdapter extends FragmentPagerAdapter {

        public final int PAGE_COUNT = 2;

        private final String[] mTabsTitle = {"ListView", "MapView"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public View getTabView(int position) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(mTabsTitle[position]);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            icon.setImageResource(mTabsIcons[position]);
            return view;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int pos) {
            switch (pos) {

                case 0:
                    return PageFragment.newInstance(1);

                case 1:
                    return PageFragment.newInstance(2);

            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabsTitle[position];
        }
    }
}