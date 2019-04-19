package com.sjl.objectbox.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sjl.objectbox.ObjectBox;
import com.sjl.objectbox.R;
import com.sjl.objectbox.model.UserBean;

import java.util.List;

/**
 * UserAdapter
 *
 * @author 沈建林
 * @date 2019/4/19
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<UserBean> list;

    public UserAdapter(List<UserBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.tvId.setText(list.get(i).id+"");
        viewHolder.tvUsername.setText(list.get(i).username);
        viewHolder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectBox.get().boxFor(UserBean.class).remove(list.get(viewHolder.getAdapterPosition()));
                list.clear();
                list.addAll(ObjectBox.get().boxFor(UserBean.class).getAll());
                notifyDataSetChanged();
            }
        });
        viewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean userBean = list.get(viewHolder.getAdapterPosition());
                userBean.username = userBean.username+"(update)";
                ObjectBox.get().boxFor(UserBean.class).put(userBean);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvUsername;
        Button btnUpdate;
        Button btnDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvUsername = itemView.findViewById(R.id.tv_username);
            btnUpdate = itemView.findViewById(R.id.btn_update);
            btnDel = itemView.findViewById(R.id.btn_del);
        }
    }
}
