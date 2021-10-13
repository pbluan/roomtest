package com.example.testroom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testroom.database.UserDatabase;

public class UpdateActivity extends AppCompatActivity {

    private EditText edtmaSV;
    private EditText edthoTen;
    private EditText edtlop;
    private EditText edtdiemToan;
    private EditText edtdiemLi;
    private EditText edtdiemHoa;
    private EditText edtgioiTinh;
    private Button btUpdate;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        edtmaSV = findViewById(R.id.edt_maSV);
        edthoTen = findViewById(R.id.edt_hoTen);
        edtgioiTinh = findViewById(R.id.edt_gioiTinh);
        edtlop = findViewById(R.id.edt_lop);
        edtdiemToan = findViewById(R.id.edt_diemToan);
        edtdiemLi= findViewById(R.id.edt_diemLi);
        edtdiemHoa = findViewById(R.id.edt_diemHoa);
        btUpdate = findViewById(R.id.btUpdate);

        mUser = (User) getIntent().getExtras().get("object_user");
        if(mUser != null){

            edtmaSV.setText(mUser.getMaSV());
            edthoTen.setText(mUser.getHoTen());
            edtgioiTinh.setText(mUser.getGioiTinh());
            edtlop.setText(mUser.getLop());
            edtdiemToan.setText(mUser.getDiemToan());
            edtdiemLi.setText(mUser.getDiemLi());
            edtdiemHoa.setText(mUser.getDiemHoa());
        }
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

    }

    private void updateUser() {

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

        mUser.setMaSV(strmaSV);
        mUser.setHoTen(strhoTen);
        mUser.setGioiTinh(strgioiTinh);
        mUser.setLop(strlop);
        mUser.setDiemToan(strdiemToan);
        mUser.setDiemLi(strdiemLi);
        mUser.setDiemHoa(strdiemHoa);
        UserDatabase.getInstance(this).userDAO().updateUser(mUser);
        Toast.makeText(this,"up thanh cong",Toast.LENGTH_SHORT).show();
        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK,intentResult);
        finish();

    }
}