package com.sjl.objectbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sjl.objectbox.adapter.UserAdapter;
import com.sjl.objectbox.model.UserBean;
import com.sjl.objectbox.model.UserBean_;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<UserBean> list = new ArrayList<>();
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        findViewById(R.id.btn_insert).setOnClickListener(v -> insert());
        findViewById(R.id.btn_query).setOnClickListener(v -> query());
        findViewById(R.id.btn_query_filter).setOnClickListener(v -> queryFilter());

        userAdapter = new UserAdapter(list);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void queryFilter() {
        list.clear();
        Query<UserBean> query = ObjectBox.get().boxFor(UserBean.class).query()
                .contains(UserBean_.username,"1")
                .build();
        list.addAll(query.find());
        userAdapter.notifyDataSetChanged();
    }

    private void query() {
        list.clear();
        list.addAll(ObjectBox.get().boxFor(UserBean.class).getAll());
        userAdapter.notifyDataSetChanged();
    }

    private int cnt = 0;

    private void insert() {
        UserBean userBean = new UserBean();
        userBean.username = "user" + cnt++;
        ObjectBox.get().boxFor(UserBean.class).put(userBean);
        query();
    }
}
