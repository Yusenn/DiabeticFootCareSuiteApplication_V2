package com.example.diabeticfootulcerv1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Base64.Decoder;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> ListRecyclerItem;


    public HistoryAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        ListRecyclerItem = listRecyclerItem;
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDate;
        private TextView tvCondition;
        private TextView tvAccuracy;
        private TextView tvId;
        private ImageView imageView;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvAccuracy = itemView.findViewById(R.id.tvAccuracy);
            tvCondition = itemView.findViewById(R.id.tvCondition);
            tvId = itemView.findViewById(R.id.tvId);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE:
            default:
                View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_history,parent,false);
                return new ItemViewHolder((layoutView));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        int viewType = getItemViewType(i);
        switch (viewType)
        {
            case TYPE:
            default:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                HistoryModel historyModel = (HistoryModel) ListRecyclerItem.get(i);
                itemViewHolder.tvCondition.setText(historyModel.getCondition());
                itemViewHolder.tvAccuracy.setText(historyModel.getAccuracy());
                itemViewHolder.tvDate.setText(historyModel.getDatetime());
                itemViewHolder.tvId.setText(historyModel.getFeetID());

                byte[] decodedString = Base64.decode(historyModel.getImage(),Base64.NO_WRAP);
                InputStream inputStream  = new ByteArrayInputStream(decodedString);
                Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
                itemViewHolder.imageView.setImageBitmap(bitmap);




        }
    }

    @Override
    public int getItemCount() {
        return ListRecyclerItem.size();
    }
}
