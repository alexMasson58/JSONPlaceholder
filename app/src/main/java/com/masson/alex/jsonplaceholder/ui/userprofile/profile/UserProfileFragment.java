package com.masson.alex.jsonplaceholder.ui.userprofile.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.masson.alex.jsonplaceholder.R;
import com.masson.alex.jsonplaceholder.viewmodel.UserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserProfileFragment extends Fragment implements UserProfilePresenter.View {

    public static final String USERPROFILE_EXTRA = "USERPROFILE_EXTRA";
    public static final String PRESENTER_STATE = "PRESENTER_STATE";
    private UserProfilePresenter presenter;
    private UserViewModel uvm;

    @BindView(R.id.id)
    protected TextView id;

    @BindView(R.id.name)
    protected TextView name;

    @BindView(R.id.username)
    protected TextView username;

    @BindView(R.id.email)
    protected TextView email;

    @BindView(R.id.website)
    protected TextView website;


    @BindView(R.id.phone)
    protected TextView phone;

    @BindView(R.id.street)
    protected TextView street;

    @BindView(R.id.suite)
    protected TextView suite;

    @BindView(R.id.cityZip)
    protected TextView cityZip;

    @BindView(R.id.compagny_name)
    protected TextView compagny;

    @BindView(R.id.catchphrase)
    protected TextView catchPhrase;

    @BindView(R.id.bs)
    protected TextView bs;

    public static UserProfileFragment newInstance() {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PRESENTER_STATE, presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        uvm = (UserViewModel) getArguments().getSerializable(USERPROFILE_EXTRA);
        return inflater.inflate(R.layout.fragment_user_profile, container, false);


        //here is your list array

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);


        presenter = new UserProfilePresenter(this, uvm);

        //If restoring from state, load the list from the bundle
        if (savedInstanceState != null) {
            presenter = savedInstanceState.getParcelable(PRESENTER_STATE);
        }
        presenter.bind(this);

    }

    @Override
    public void displayUserProfile(UserViewModel userViewModel) {
        setId(uvm.getId()+"");
        setName(uvm.getName());
        setUsername(uvm.getUsername());
        setEmail(uvm.getEmail());
        setWebsite(uvm.getWebsite());
        setPhone(uvm.getPhone());
        setStreet(uvm.getAddress().getStreet());
        setSuite(uvm.getAddress().getSuite());
        setCityZip(uvm.getAddress().getCity(),uvm.getAddress().getZipcode());
        setCompagny(uvm.getCompany().getName());
        setCatchPhrase(uvm.getCompany().getCatchPhrase());
        setBs(uvm.getCompany().getBs());
        //TODO : add onclikcinteraction
    }

    void setId(String id){
        this.id.setText(id);
    }

    void setName(String name) {
        this.name.setText(name);
    }

    void setUsername(String username) {
        this.username.setText(username);
    }

    void setEmail(String email) {
        this.email.setText("(" + email + ")");
    }

    void setWebsite(String website) {
        this.website.setText(website);
    }

    void setPhone(String phone) {
        this.phone.setText(phone);
    }

    void setStreet(String street) {
        this.street.setText(street);
    }

    void setSuite(String suite) {
        this.suite.setText(suite);
    }

    void setCityZip(String city, String zip) {
        this.cityZip.setText(city + " (" + zip + ")");
    }

    void setCompagny(String compagny) {
        this.compagny.setText(compagny);
    }

    void setCatchPhrase(String catchPhrase) {
        this.catchPhrase.setText(catchPhrase);
    }

    void setBs(String bs) {
        this.bs.setText(bs);
    }

    @Override
    public void displayErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
