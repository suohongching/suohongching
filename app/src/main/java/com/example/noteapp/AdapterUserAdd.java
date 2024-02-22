package com.example.noteapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.databinding.RowNoteaddBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterUserAdd extends RecyclerView.Adapter<AdapterUserAdd.HolderUserAddNote>{

    private Context context;
    private ArrayList<ModelUserNoteAdd> noteAddArrayList;

    private RowNoteaddBinding binding;

    public AdapterUserAdd(Context context, ArrayList<ModelUserNoteAdd> noteAddArrayList) {
        this.context = context;
        this.noteAddArrayList = noteAddArrayList;
    }

    @NonNull
    @Override
    public HolderUserAddNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = RowNoteaddBinding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderUserAddNote(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUserAdd.HolderUserAddNote holder, int position) {

        ModelUserNoteAdd model = noteAddArrayList.get(position);
        String id = model.getId();
        String note = model.getNote();
        String uid = model.getUid();
        long timestamp = model.getTimestamp();

        holder.noteTv.setText(note);

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete")
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(context, "Deleting...", Toast.LENGTH_SHORT).show();
                                deleteNote(model, holder);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

    }

    private void deleteNote(ModelUserNoteAdd model, HolderUserAddNote holder) {

        String id = model.getId();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Notes");
        ref.child(id)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(context, "Delete Successfully...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public int getItemCount() {
        return noteAddArrayList.size();
    }

    class HolderUserAddNote extends RecyclerView.ViewHolder{

        TextView noteTv;
        ImageButton deleteBtn;

        public HolderUserAddNote(@NonNull View itemView) {
            super(itemView);

            noteTv = binding.noteTv;
            deleteBtn = binding.deleteBtn;
        }
    }
}
