package ma.enset.annuaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactsAdapter.SelectedContact{
    RecyclerView recyclerView;
    AppDataBase db;
    List<Contact> contacts;
    LinearLayoutManager linearLayoutManager;

    Contact contact1=new Contact("HACHMI","Mohamed Amine","Software engineer","0612345678","aminehcm0@gmail.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = AppDataBase.getInstance(this);
        //db.contactDAO().insert(contact1);

        contacts= db.contactDAO().getAll();
        ContactsAdapter adapter = new ContactsAdapter(contacts,this,this,this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        contacts.clear();
        contacts.addAll(db.contactDAO().getAll());
        adapter.notifyDataSetChanged();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String textQuery) {
                RefreshListView((ArrayList<Contact>) db.contactDAO().findByName("%"+textQuery+"%"));
                return false;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void selectedContact(Contact contact) {
        Intent myIntent = new Intent(MainActivity.this, EditActivity.class);
        myIntent.putExtra("ID",String.valueOf(contact.getID()));
        startActivity(myIntent);
    }
    void RefreshListView(List<Contact> contacts){
        ContactsAdapter adapter = new ContactsAdapter(contacts,this,this,this);
        recyclerView.setAdapter(adapter);
    }
}