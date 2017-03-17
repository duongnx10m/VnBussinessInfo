package com.vn.company;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by duongnx on 1/10/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private TextView tvCount;
    private View groupResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        groupResult = findViewById(R.id.groupResult);
        if (groupResult != null) {
            groupResult.setVisibility(View.GONE);
            tvCount = (TextView) findViewById(R.id.tvCount);
            View btDelete = findViewById(R.id.btDelete);
            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    groupResult.setVisibility(View.GONE);
                }
            });
        }
    }

    public void pushFragment(Fragment fragment, boolean isAdded) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frgContent, fragment);
        if (isAdded)
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    public void changeMenu(Fragment fragment) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frgContent, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    public void setGroupResultCount(String text) {
        if (groupResult != null)
            tvCount.setText(text);
    }

    public void setGroupResultVisibility(int visiblity) {
        if (groupResult != null)
            groupResult.setVisibility(visiblity);
    }
}
