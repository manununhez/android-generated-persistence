package py.com.cuatroqstudios.persistenceapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import py.com.cuatroqstudios.persistenceapp.R;
import py.com.cuatroqstudios.persistenceapp.activities.UserActivity;
import py.com.cuatroqstudios.persistenceapp.activities.UserFormActivity;
import py.com.cuatroqstudios.persistenceapp.models.User;
import py.com.cuatroqstudios.persistenceapp.sharedmanager.helper.MySharedPreferencesHelper;

/**
 * Created by manuel on 7/14/16.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User> userList;
    private Context context;
    private int REQUEST_CODE = 1;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPass;
        private ImageView ivDelete, ivEdit;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvPass = (TextView) view.findViewById(R.id.tvPass);
            ivEdit = (ImageView) view.findViewById(R.id.ivEdit);
            ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
        }
    }


    public UserAdapter(Context context, List<User> userList) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final User user = userList.get(position);
        holder.tvName.setText(user.getNombre());
        holder.tvPass.setText(user.getPassword());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserFormActivity.class);
                ((UserActivity) context).startActivityForResult(intent, REQUEST_CODE);
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySharedPreferencesHelper.removeValue(context, "nombre");
                MySharedPreferencesHelper.removeValue(context, "password");
                userList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
