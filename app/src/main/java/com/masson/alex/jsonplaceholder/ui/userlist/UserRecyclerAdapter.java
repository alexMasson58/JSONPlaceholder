package com.masson.alex.jsonplaceholder.ui.userlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.masson.alex.jsonplaceholder.R;
import com.masson.alex.jsonplaceholder.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alex on 25/03/2018.
 */

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {

    public interface ItemClickListener {
        void onItemClicked(int position);
    }

    private final ItemClickListener listener;
    private final List<UserViewModel> userList = new ArrayList<>();

    public UserRecyclerAdapter(ItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_user, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        UserViewModel uvm = userList.get(position);
        holder.setId(uvm.getId() + "");
        holder.setName(uvm.getName());
        holder.setUsername(uvm.getUsername());
        holder.setEmail(uvm.getEmail());
        holder.setWebsite(uvm.getWebsite());
        holder.setPhone(uvm.getPhone());
        holder.setStreet(uvm.getAddress().getStreet());
        holder.setSuite(uvm.getAddress().getSuite());
        holder.setCityZip(uvm.getAddress().getCity(), uvm.getAddress().getZipcode());
        holder.setCompagny(uvm.getCompany().getName());
        holder.setCatchPhrase(uvm.getCompany().getCatchPhrase());
        holder.setBs(uvm.getCompany().getBs());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClicked(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public void setUserList(List<UserViewModel> userList) {
        this.userList.clear();
        this.userList.addAll(userList);
        this.notifyDataSetChanged();
    }

    public List<UserViewModel> getUserList() {
        return userList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;

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

        protected ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        void setId(String id) {
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


    }
}
