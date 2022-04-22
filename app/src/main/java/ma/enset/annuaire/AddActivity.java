package ma.enset.annuaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity {

    AppDataBase database;
    EditText lastName;
    EditText firstName;
    EditText job;
    EditText phone;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database=AppDataBase.getInstance(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        lastName = (EditText) findViewById(R.id.lastNameInput);
        firstName = (EditText) findViewById(R.id.firstNameInput);
        job = (EditText) findViewById(R.id.jobInput);
        phone = (EditText) findViewById(R.id.phoneInput);
        email = (EditText) findViewById(R.id.emailInput);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
    public void save(View view){

        database.contactDAO().insert(new Contact(lastName.getText().toString(),firstName.getText().toString(),job.getText().toString(),phone.getText().toString(),email.getText().toString()));
        Toast.makeText(AddActivity.this,"Contact added successfully!",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(myIntent);
    }
    public void clear(View view)
    {
        lastName.setText("");
        firstName.setText("");
        job.setText("");
        phone.setText("");
        email.setText("");
    }

}