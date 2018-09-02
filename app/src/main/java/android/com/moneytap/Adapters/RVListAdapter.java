package android.com.moneytap.Adapters;

import android.com.moneytap.Models.Page;
import android.com.moneytap.R;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavan Nagaraju on 02-Sep-18.
 */

public class RVListAdapter extends RecyclerView.Adapter<RVListAdapter.ViewHolder> {
    private final List<Page> mList;
    private Context mContext;
    public RVListAdapter(Context context , List<Page> list){
        mContext = context;
        mList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view,null);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).load(mList.get(position).getThumbnail().getSource())
                .into(holder.imgItem);
        holder.tvItem.setText(mList.get(position).getTerms().getDescription().toString());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvItem;
        private final ImageView imgItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
            imgItem = (ImageView) itemView.findViewById(R.id.img_item);
        }
    }
}
