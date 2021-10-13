package com.example.testroom;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> mListUser;
    private IClickItemUser iClickItemUser;

    public UserAdapter(IClickItemUser iClickItemUser) {
        this.iClickItemUser = iClickItemUser;
    }



    public interface IClickItemUser{
        void updateUser(User user);
    }
    public void setData(List<User> list){
        this.mListUser = list;
        notifyDataSetChanged();
    }
    @NonNull

    @Override
    public UserViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  UserAdapter.UserViewHolder holder, int position) {
        User user = mListUser.get(position);
        if (user == null){
            return;
        }

        holder.tvmaSV.setText(user.getMaSV());
        holder.tvhoTen.setText(user.getHoTen());
        holder.tvgioiTinh.setText(user.getGioiTinh());
        holder.tvlop.setText(user.getLop());
        holder.tvdiemToan.setText(user.getDiemToan());
        holder.tvdiemLi.setText(user.getDiemLi());
        holder.tvdiemHoa.setText(user.getDiemHoa());
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemUser.updateUser(user);
            }
        });

    }

    @Override
    public int getItemCount(){
        if (mListUser != null) {
            return mListUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView tvmaSV;
        private TextView tvhoTen;
        private TextView tvgioiTinh;
        private TextView tvlop;
        private TextView tvdiemToan;
        private TextView tvdiemLi;
        private TextView tvdiemHoa;
        private Button btnUpdate;

        public UserViewHolder(@NonNull  View itemView) {
            super(itemView);

            tvmaSV = itemView.findViewById(R.id.tv_maSV);
            tvhoTen = itemView.findViewById(R.id.tv_hoTen);
            tvgioiTinh = itemView.findViewById(R.id.tv_gioiTinh);
            tvlop = itemView.findViewById(R.id.tv_lop);
            tvdiemToan = itemView.findViewById(R.id.tv_diemToan);
            tvdiemLi = itemView.findViewById(R.id.tv_diemLi);
            tvdiemHoa= itemView.findViewById(R.id.tv_diemHoa);
            btnUpdate = itemView.findViewById(R.id.btn_update);
        }
    }
}
