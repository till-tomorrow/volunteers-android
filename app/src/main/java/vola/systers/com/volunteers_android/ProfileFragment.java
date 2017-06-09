package vola.systers.com.volunteers_android;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * @author divyapandilla
 * @since 2017-06-09
 */


public class ProfileFragment extends Fragment {


    public ProfileFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);

        return rootView;
    }


}
