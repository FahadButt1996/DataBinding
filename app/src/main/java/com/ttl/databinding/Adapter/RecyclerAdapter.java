package com.ttl.databinding.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ttl.databinding.Interfaces.ClickHandler;
import com.ttl.databinding.Model.EidGreetingsModel;
import com.ttl.databinding.databinding.SportDataBinding;

import java.util.List;

/**
 * Created by fahad.waqar on 26-May-18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder>{

    private Context context;
    private List<EidGreetingsModel> mList;
    private LayoutInflater inflater;

    public RecyclerAdapter(Context context, List<EidGreetingsModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(inflater == null){
            inflater = LayoutInflater.from(parent.getContext());
        }
        SportDataBinding dataBinding = SportDataBinding.inflate(inflater , parent ,false);
        return new ViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        EidGreetingsModel model = mList.get(position);
        holder.bind(model);

        final SportDataBinding dataBinding = holder.getmDataBinding();
        dataBinding.setHandler(new ClickHandler() {
            @Override
            public void onNameClick() {
                Toast.makeText(context, "You click on " + mList.get(position).text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
