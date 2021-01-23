//package com.example.agendamu.adapter;
//
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.agendamu.R;
//import com.example.agendamu.model.EventModel;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
//    private ArrayList<EventModel> ScheduleList;
//    private AppCompatActivity parentActivity;
//
//    //    Inner Class
//    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
//        public TextView textName, textLecturer, textDay, textTime;
//        public String uid;
//
//        public ScheduleViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textName = itemView.findViewById(R.id.textName);
//            textLecturer = itemView.findViewById(R.id.textLecturer);
//            textDay = itemView.findViewById(R.id.textDay);
//            textTime = itemView.findViewById(R.id.textTime);
//        }
//    }
//
//    public ScheduleAdapter(ArrayList<EventModel> ScheduleList, AppCompatActivity parentActivity) {
//        this.ScheduleList = ScheduleList;
//        this.parentActivity = parentActivity;
//    }
//
//    @NonNull
//    @Override
//    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.Schedule_list_card, parent, false);
//        final ScheduleViewHolder ScheduleViewHolder = new ScheduleViewHolder(v);
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(parentActivity, ScheduleDetailActivity.class);
//                intent.putExtra("uid", ScheduleViewHolder.uid);
//                System.out.println("EDITING " + ScheduleViewHolder.uid);
//                parentActivity.startActivity(intent);
//            }
//        });
//
//        return ScheduleViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final ScheduleViewHolder holder, int position) {
//        final EventModel currentSchedule = this.ScheduleList.get(position);
//        holder.textName.setText(currentSchedule.getName());
//        holder.textDay.setText(parentActivity.getResources().getStringArray(R.array.day)[currentSchedule.getDay()]);
//        holder.textTime.setText(parentActivity.getResources().getStringArray(R.array.time)[currentSchedule.getStart()] + " - " + parentActivity.getResources().getStringArray(R.array.time)[currentSchedule.getEnd()]);
//        holder.uid = currentSchedule.getId();
//
//        DatabaseReference dblecturer;
//        dblecturer = FirebaseDatabase.getInstance().getReference("lecturers").child(currentSchedule.getLecturer_id()).child("name");
//        dblecturer.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                holder.textLecturer.setText(snapshot.getValue(String.class));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return this.ScheduleList.size();
//    }
//
//}
