package com.example.testroom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String maSV;
    private String hoTen;
    private String gioiTinh;
    private String lop;
    private String diemToan;
    private String diemLi;
    private String diemHoa;

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getDiemToan() {
        return diemToan;
    }

    public void setDiemToan(String diemToan) {
        this.diemToan = diemToan;
    }

    public String getDiemLi() {
        return diemLi;
    }

    public void setDiemLi(String diemLi) {
        this.diemLi = diemLi;
    }

    public String getDiemHoa() {
        return diemHoa;
    }

    public void setDiemHoa(String diemHoa) {
        this.diemHoa = diemHoa;
    }

    public User( String maSV,String hoTen,String gioiTinh,String lop , String diemToan, String diemLi , String diemHoa) {

        this.maSV = maSV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.lop = lop;
        this.diemToan = diemToan;
        this.diemLi = diemLi;
        this.diemHoa= diemHoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
