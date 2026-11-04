package com.example.mynoesantara;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mynoesantara.models.Prospek;
import java.util.List;

public class ProspekAdapter extends RecyclerView.Adapter<ProspekAdapter.ViewHolder> {
    private List<Prospek> list;

    public ProspekAdapter(List<Prospek> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prospek, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Prospek prospek = list.get(position);
        holder.tvNama.setText(prospek.getNama());
        holder.tvPerusahaan.setText(prospek.getNamaPerusahaan() != null ? prospek.getNamaPerusahaan() : "Tanpa Perusahaan");
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvPerusahaan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvPerusahaan = itemView.findViewById(R.id.tvPerusahaan);
        }
    }
}