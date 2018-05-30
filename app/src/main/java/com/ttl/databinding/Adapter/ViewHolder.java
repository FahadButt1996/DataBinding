package com.ttl.databinding.Adapter;

import android.support.v7.widget.RecyclerView;

import com.ttl.databinding.Model.EidGreetingsModel;
import com.ttl.databinding.databinding.SportDataBinding;

/**
 * Created by fahad.waqar on 26-May-18.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private SportDataBinding mDataBinding;

    public ViewHolder(SportDataBinding db) {
        super(db.getRoot());
        this.mDataBinding = db ;
    }

    public void bind(EidGreetingsModel model){
        this.mDataBinding.setViewModel(model);
    }

    public SportDataBinding getmDataBinding(){
        return mDataBinding;
    }
}
