package com.example.harryaung.wizard;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Harry on 1/22/2016.
 */
public class WizardFragment extends Fragment {

    private View rootView;
    private int color_code;

    public static WizardFragment newInstance(int color_code) {
        WizardFragment fragment = new WizardFragment();
        fragment.color_code = color_code;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = new Wizard_SurfaceView(getActivity(),color_code);
        }
        return rootView;
    }
}
