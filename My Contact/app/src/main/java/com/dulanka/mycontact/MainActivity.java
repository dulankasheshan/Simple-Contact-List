package com.dulanka.mycontact;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener {

    FloatingActionButton addContact;
    MyAdapter adapter;
    SearchView searchView;
    List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addContact = findViewById(R.id.addContactBtn);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);


        //search contact
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });


        //contact detail list
        items = new ArrayList<>();
        items.add(new Item("Dulanka Sheshan", "078 2347635", R.drawable.a, "123, Galle Road, Colombo", "dulanka@gmail.com"));
        items.add(new Item("Nimal Perera", "071 4567890", R.drawable.b, "456, Kandy Road, Kandy", null));
        items.add(new Item("Sunil Fernando", "072 9876543", R.drawable.c, "789, Negombo Road, Negombo", "sunil@gmail.com"));
        items.add(new Item("Kamal Wijesinghe", "077 1234567", R.drawable.d, "321, Havelock Road, Colombo", "kamal@gmail.com"));
        items.add(new Item("Amara Silva", "075 2345678", R.drawable.e, "654, Nawala Road, Colombo", null));
        items.add(new Item("Ruwan Gunasekara", "076 3456789", R.drawable.f, "987, Dehiwala Road, Dehiwala", "ruwan@gmail.com"));
        items.add(new Item("Saman Kumara", "078 4567890", R.drawable.g, "147, Galle Road, Matara", "saman@gmail.com"));
        items.add(new Item("Nimali Ranatunga", "079 5678901", R.drawable.h, "258, Bandaranaike Mawatha, Colombo", null));
        items.add(new Item("Kusum Senanayake", "070 6789012", R.drawable.i, "369, Galle Road, Panadura", "kusum@gmail.com"));
        items.add(new Item("Mala Perera", "071 2345678", R.drawable.d, "147, High Level Road, Nugegoda", null));
        items.add(new Item("Anura Jayasinghe", "072 3456789", R.drawable.g, "258, Bauddhaloka Mawatha, Colombo", null));
        items.add(new Item("Tharindu Silva", "073 4567890", R.drawable.a, "369, Havelock Road, Colombo", "tharindu@gmail.com"));
        items.add(new Item("Dilanka Wickramasinghe", "074 5678901", R.drawable.b, "741, Main Street, Kandy", null));
        items.add(new Item("Chathuri Ratnayake", "075 6789012", R.drawable.f, "852, Nawala Road, Colombo", "chathuri@gmail.com"));
        items.add(new Item("Hasitha Perera", "076 7890123", R.drawable.e, "963, Galle Road, Moratuwa", null));
        items.add(new Item("Ruwanthi Senarath", "077 8901234", R.drawable.c, "174, Hikkaduwa Road, Galle", "ruwanthi@gmail.com"));
        items.add(new Item("Kasun Jayawardena", "078 9012345", R.drawable.g, "285, Colombo Road, Kurunegala", "kasun@gmail.com"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, items, this);
        recyclerView.setAdapter(adapter);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // add new contact
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_contact);

                EditText addName = dialog.findViewById(R.id.addName);
                EditText addContactNumber = dialog.findViewById(R.id.addContact);
                EditText addAddress = dialog.findViewById(R.id.addAddress);
                EditText addEmail = dialog.findViewById(R.id.addEmail);

                Button saveContact = dialog.findViewById(R.id.saveContactBtn);

                saveContact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = addName.getText().toString();
                        String contactNum = addContactNumber.getText().toString();
                        String address = addAddress.getText().toString();
                        String email = addEmail.getText().toString();

                        if (name.isEmpty()) {
                            Toast.makeText(MainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (contactNum.isEmpty()) {
                            Toast.makeText(MainActivity.this, "Please enter contact number", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        items.add(new Item(name, contactNum, R.drawable.d, address, email));
                        adapter.notifyItemInserted(items.size() - 1);
                        recyclerView.scrollToPosition(items.size() - 1);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    //search contact
    private void filterList(String text) {
        List<Item> filteredList = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(text.toLowerCase()) ||
                    item.getContactNum().toLowerCase().contains(text.toLowerCase()) ||
                    (item.getEmail() != null && item.getEmail().toLowerCase().contains(text.toLowerCase())) ||
                    (item.getAddress() != null && item.getAddress().toLowerCase().contains(text.toLowerCase()))) {
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No contacts found", Toast.LENGTH_SHORT).show();
        }
        adapter.updateList(filteredList);
    }

    @Override
    public void onItemClick(Item item) {
        Intent i = new Intent(getApplicationContext(), Single_contact_page.class);
        i.putExtra("IMAGE", item.getImage());
        i.putExtra("NAME", item.getName());
        i.putExtra("NUMBER", item.getContactNum());
        i.putExtra("ADDRESS", item.getAddress());
        i.putExtra("EMAIL", item.getEmail());
        startActivity(i);
    }
}
