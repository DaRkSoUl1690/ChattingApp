package com.vedant.chattingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.vedant.chattingapp.Models.MessageModel;
import com.vedant.chattingapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class chatAdapter extends RecyclerView.Adapter  {

    ArrayList<MessageModel> messageModels;
    Context context;

    int SENDER_VIEW_TYPE =1;
    int RECIEVER_VIEW_TYPE =2;



    public chatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == SENDER_VIEW_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_,parent,false);
            return new senderView(view);
        }
       else {
           View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever,parent,false);
           return new RecieverView(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    MessageModel messageModel = messageModels.get(position);

    if(holder.getClass() == senderView.class)
    {
        ((senderView)holder).sendermsg.setText(messageModel.getMessage());
    }
    else {
        ((RecieverView)holder).receiverMsg.setText(messageModel.getMessage());
    }

    }

    @Override
    public int getItemViewType(int position) {

        if(messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid()))
        {
            return SENDER_VIEW_TYPE;
        }
        else
        {
            return RECIEVER_VIEW_TYPE;
        }

    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class RecieverView extends RecyclerView.ViewHolder {

        TextView receiverMsg,receivertime;
        public RecieverView(@NonNull View itemView) {
            super(itemView);
            receiverMsg = itemView.findViewById(R.id.textReciever);
            receivertime = itemView.findViewById(R.id.recieverTime);

        }
    }


    public class senderView extends RecyclerView.ViewHolder {

        TextView sendermsg,senderTime;
        public senderView(@NonNull View itemView) {
            super(itemView);
            sendermsg = itemView.findViewById(R.id.sendermsg);
            senderTime = itemView.findViewById(R.id.sendertime);
        }
    }

}
