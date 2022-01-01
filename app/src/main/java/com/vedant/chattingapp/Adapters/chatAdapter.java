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

public class chatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<MessageModel> messageModels;
    Context mContext;

    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public chatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == SENDER_VIEW_TYPE) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.sample_, parent, false);
            return new senderView(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.sample_reciever, parent, false);
            return new ReceiverView(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MessageModel messageModel = messageModels.get(position);

        if (holder.getClass() == senderView.class) {
            ((senderView) holder).SenderMsg.setText(messageModel.getMessage());

        } else {
            ((ReceiverView) holder).receiverMsg.setText(messageModel.getMessage());
        }

    }

    @Override
    public int getItemViewType(int position) {

        if (messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())) {
            return SENDER_VIEW_TYPE;
        } else {
            return RECEIVER_VIEW_TYPE;
        }

    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    /**
     * VIEW_HOLDER-1
     */

    public static class ReceiverView extends RecyclerView.ViewHolder {

        TextView receiverMsg, ReceiverTime;

        public ReceiverView(@NonNull View itemView) {
            super(itemView);
            receiverMsg = itemView.findViewById(R.id.textReciever);
//            ReceiverTime = itemView.findViewById(R.id.recieverTime);

        }
    }

    /**
     * VIEW_HOLDER-2
     */

    public static class senderView extends RecyclerView.ViewHolder {

        TextView SenderMsg, senderTime;

        public senderView(@NonNull View itemView) {
            super(itemView);
            SenderMsg = itemView.findViewById(R.id.sendermsg);
//          senderTime = itemView.findViewById(R.id.sendertime);
        }
    }

}
