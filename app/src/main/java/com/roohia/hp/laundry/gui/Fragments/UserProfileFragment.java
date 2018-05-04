package com.roohia.hp.laundry.gui.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.roohia.hp.laundry.Controller.UserProfileController;
import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.activities.HomeActivity;
import com.roohia.hp.laundry.gui.interfaces.SubmitUserInterface;
import com.roohia.hp.laundry.model.database.DBHandler;
import com.roohia.hp.laundry.model.dbo.User;
import com.roohia.hp.laundry.model.utils.AlertUtils;
import com.roohia.hp.laundry.model.utils.CodeUtils;


public class UserProfileFragment extends Fragment implements View.OnClickListener, SubmitUserInterface, AdapterView.OnItemSelectedListener {
    Context mContext;
    EditText etUsername, etFullName, etAddress, etEmail, etContact;
    Button btnClose, btnSubmit;
    boolean submissionFlag = false;

    public UserProfileFragment() {
    }

    public static UserProfileFragment newInstance(Context context) {
        Bundle args = new Bundle();
        UserProfileFragment fragment = new UserProfileFragment();
        fragment.mContext = context;
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_user_profile_fragment, container, false);


        etUsername = (EditText) view.findViewById(R.id.et_username);
        etFullName = (EditText) view.findViewById(R.id.et_fullname);
        etAddress = (EditText) view.findViewById(R.id.et_address);
        etEmail = (EditText) view.findViewById(R.id.et_email);
        etContact = (EditText) view.findViewById(R.id.et_contact);
        btnClose = (Button) view.findViewById(R.id.btn_close);
        btnSubmit = (Button) view.findViewById(R.id.btn_submit);
        btnClose.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        User user = DBHandler.getInstance().getCurrentUser();
        if(user != null) {
            etUsername.setText(user.getUserName());
            etFullName.setText(user.getFullName());
            etAddress.setText(user.getAddress());
            etEmail.setText(user.getUserEmail());
            etContact.setText(user.getContact());
        }


        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                ((HomeActivity) getActivity()).removeTopFragmentFromBackStack();
                break;

            case R.id.btn_submit:
                if (etFullName.getText().toString().isEmpty()) {
                    AlertUtils.showAlertDialog(mContext, getString(R.string.fullname_missing_prompt), null);
                } else if (etEmail.getText().toString().isEmpty()) {
                    AlertUtils.showAlertDialog(mContext, getString(R.string.email_missing_prompt), null);
                } else if (etAddress.getText().toString().isEmpty()) {
                    AlertUtils.showAlertDialog(mContext, getString(R.string.address_missing_prompt), null);
                }else if (etContact.getText().toString().isEmpty()) {
                    AlertUtils.showAlertDialog(mContext, getString(R.string.contact_missing_prompt), null);
                }else {
                    if (CodeUtils.getInstance().checkNetworkConnectivity(mContext)) {
                        ((HomeActivity) getActivity()).showProgressDialog(getString(R.string.saving_profile_prompt));

                        UserProfileController.getInstance().submitUserInfo(mContext,etUsername.getText().toString(), etFullName.getText().toString(), etEmail.getText().toString(),etAddress.getText().toString(),etContact.getText().toString(),this);
                    } else {
                        AlertUtils.showAlertDialog(mContext, getString(R.string.no_internet_on_submission_message), null);
                    }
                }
                break;


        }

    }

    @Override
    public void onSubmissionSuccessful() {
        ((HomeActivity) getActivity()).hideProgressDialog();
        AlertUtils.showAlertDialog(getContext(), getString(R.string.profile_success_prompt),  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((HomeActivity)getActivity()).clearBackStack();
            }
        });
    }

    @Override
    public void onSubmissionFailed(String code, String message) {
        ((HomeActivity) getActivity()).hideProgressDialog();
        AlertUtils.showAlertDialog(getContext(), getString(R.string.profile_failure_prompt), null);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (submissionFlag)
            btnSubmit.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
