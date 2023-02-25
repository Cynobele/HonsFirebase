package uk.ac.abertay.honsfirebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;


public class OperatorLessonFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated_view = inflater.inflate(R.layout.oper_lesson_frag, container, false);


        return inflated_view;
    }


}