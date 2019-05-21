package py.com.cuatroqstudios.persistenceapp.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import py.com.cuatroqstudios.persistenceapp.R;
import py.com.cuatroqstudios.persistenceapp.adapters.UserAdapter;
import py.com.cuatroqstudios.persistenceapp.models.User;
import py.com.cuatroqstudios.persistenceapp.sharedmanager.helper.MySharedPreferencesHelper;

public class UserActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private List<User> userList = new ArrayList<>();
    private static int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("User");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

      setupRecyclerView();

        loadUserData();
    }

    private void setupRecyclerView() {
        mAdapter = new UserAdapter(this, userList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);
    }

    private void loadUserData() {
        Toast.makeText(UserActivity.this, "Retrieving data from sharedPreferences", Toast.LENGTH_SHORT).show();

        String nombre = MySharedPreferencesHelper.getValue(this, "nombre");
        String password = MySharedPreferencesHelper.getValue(this, "password");
        User user = new User(nombre == null ? "" : nombre, password == null ? "" : password);
        userList.add(user);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                userList.clear();
                loadUserData();
            }
        }
    }


}
