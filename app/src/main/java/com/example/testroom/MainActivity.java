package com.example.testroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.testroom.database.UserDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 10;

    private EditText edtmaSV;
    private EditText edthoTen;
    private EditText edtlop;
    private EditText edtdiemToan;
    private EditText edtdiemLi;
    private EditText edtdiemHoa;
    private EditText edtgioiTinh;
    private Button btADD;
    private RecyclerView rvUser;

    private UserAdapter userAdapter;
    private  List<User> mListUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();


        userAdapter = new UserAdapter(new UserAdapter.IClickItemUser() {
            @Override
            public void updateUser(User user) {
                clickUpdateUser(user);
            }
        });
        mListUser = new ArrayList<>();
        userAdapter.setData(mListUser);
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(this);
        rvUser.setLayoutManager(linearLayoutManager);
        rvUser.setAdapter(userAdapter);
        btADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
        loadData();
    }

    private void addUser() {

        String strmaSV = edtmaSV.getText().toString().trim();
        String strhoTen = edthoTen.getText().toString().trim();
        String strgioiTinh = edtgioiTinh.getText().toString().trim();
        String strlop = edtlop.getText().toString().trim();
        String strdiemToan = edtdiemToan.getText().toString().trim();
        String strdiemLi = edtdiemLi.getText().toString().trim();
        String strdiemHoa = edtdiemHoa.getText().toString().trim();

        if (TextUtils.isEmpty(strmaSV) || TextUtils.isEmpty(strhoTen)){
            return;
        }
        User user = new User(strmaSV,strhoTen,strgioiTinh,strlop,strdiemToan,strdiemLi,strdiemHoa);

        if (isUserExist(user)) {
            Toast.makeText(this,"ton tai user",Toast.LENGTH_SHORT).show();
            return;

        }
        

        UserDatabase.getInstance(this).userDAO().insertUser(user);
        Toast.makeText(this , "add success",Toast.LENGTH_SHORT).show();


        edtmaSV.setText("");
        edthoTen.setText("");
        edtlop.setText("");
        edtgioiTinh.setText("");
        edtdiemToan.setText("");
        edtdiemLi.setText("");
        edtdiemHoa.setText("");
        loadData();

    }

    private void initUi(){

        edtmaSV = findViewById(R.id.edt_maSV);
        edthoTen = findViewById(R.id.edt_hoTen);
        edtgioiTinh = findViewById(R.id.edt_gioiTinh);
        edtlop = findViewById(R.id.edt_lop);
        edtdiemToan = findViewById(R.id.edt_diemToan);
        edtdiemLi= findViewById(R.id.edt_diemLi);
        edtdiemHoa = findViewById(R.id.edt_diemHoa);
        btADD = findViewById(R.id.btAdd);
        rvUser = findViewById(R.id.rv_user);
    }
    private void loadData(){
        mListUser = UserDatabase.getInstance(this).userDAO().getListUser();
        userAdapter.setData(mListUser);

    }
    private boolean isUserExist(User user){
        List<User> list = UserDatabase.getInstance(this).userDAO().checkUser(user.getMaSV());
        return list!= null && !list.isEmpty();


    }
    public void clickUpdateUser(User user){
        Intent intent;
        intent = new Intent(MainActivity.this,UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user",user);
        intent.putExtras(bundle);
        startActivityForResult(intent,MY_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            loadData();
        }
    }
}