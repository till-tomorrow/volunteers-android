package vola.systers.com.volunteers_android;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/*
 * @author divyapandilla
 * @since 2017-06-09
 */

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPageNo;

    public static PageFragment newInstance(int pageNo) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNo = getArguments().getInt(ARG_PAGE);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        if(mPageNo == 1)
        {
            TextView textView = (TextView) view;
            textView.setText("LIST VIEW");
        }
        else if(mPageNo == 2)
        {
            TextView textView = (TextView) view;
            textView.setText("MAP VIEW" );
        }

        return view;
    }
}