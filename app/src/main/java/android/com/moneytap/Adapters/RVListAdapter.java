package android.com.moneytap.Adapters;

import android.com.moneytap.Activities.WebViewActivity;
import android.com.moneytap.Models.Page;
import android.com.moneytap.R;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(mList.get(position).getThumbnail() != null && mList.get(position).getThumbnail().getSource() != null) {
            Picasso.with(mContext)
                    .load(mList.get(position).getThumbnail().getSource())
                    .placeholder(R.drawable.placeholder)
                    .into(holder.imgItem);
        }
        if(mList.get(position).getTerms() != null && mList.get(position).getTerms().getDescription() != null) {
            String desc = "";
            for(int i=0;i<mList.get(position).getTerms().getDescription().size();i++){
                desc = desc + " " +mList.get(position).getTerms().getDescription().get(i);
            }
            holder.tvItem.setText(desc);
        }
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("pageId",mList.get(position).getPageid());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvItem;
        private ImageView imgItem;
        private LinearLayout llItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
            imgItem = (ImageView) itemView.findViewById(R.id.img_item);
            llItem = (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }
}
