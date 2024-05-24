package com.dulanka.mycontact;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Item> items;
    private SelectListener listener;

    // Constructor
    public MyAdapter(Context context, List<Item> items, SelectListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item item = items.get(holder.getAdapterPosition());

        holder.nameView.setText(item.getName());
        holder.contactNumView.setText(item.getContactNum());
        holder.imageView.setImageResource(item.getImage());

        Button editBtn = holder.cardView.findViewById(R.id.editButton);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_contact);

                EditText addName = dialog.findViewById(R.id.addName);
                EditText addContactNumber = dialog.findViewById(R.id.addContact);
                EditText addAddress = dialog.findViewById(R.id.addAddress);
                EditText addEmail = dialog.findViewById(R.id.addEmail);

                Button saveContact = dialog.findViewById(R.id.saveContactBtn);
                TextView textTitle = dialog.findViewById(R.id.addContactTxt);

                textTitle.setText("Update Contact");
                saveContact.setText("Update");

                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Item currentItem = items.get(adapterPosition);

                    addName.setText(currentItem.getName());
                    addContactNumber.setText(currentItem.getContactNum());
                    addAddress.setText(currentItem.getAddress());
                    addEmail.setText(currentItem.getEmail());

                    saveContact.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String name = addName.getText().toString();
                            String contactNum = addContactNumber.getText().toString();
                            String address = addAddress.getText().toString();
                            String email = addEmail.getText().toString();

                            if (name.isEmpty()) {
                                Toast.makeText(context, "Please enter name", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (contactNum.isEmpty()) {
                                Toast.makeText(context, "Please enter contact number", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            items.set(adapterPosition, new Item(name, contactNum, items.get(position).getImage(), address, email));
                            notifyItemChanged(adapterPosition);
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Delete Contact")
                            .setMessage("Are you sure you want to delete?")
                            .setIcon(R.drawable.baseline_delete_24)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    items.remove(adapterPosition);
                                    notifyItemRemoved(adapterPosition);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.show();
                }
                return true;
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onItemClick(items.get(adapterPosition));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateList(List<Item> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
    }
}
