package ma.enset.annuaire;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<Contact> contactsList;
    private Context context;
    private AppDataBase database;
    private SelectedContact selectedContact;
    private MainActivity mainActivity;
    private Context ctx;

    public ContactsAdapter(List<Contact> contactsList, Activity context, SelectedContact selectedContact, MainActivity mainActivity) {
        this.contactsList = contactsList;
        this.mainActivity=mainActivity;
        this.selectedContact=selectedContact;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       ctx=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recycler_view,parent,false);
        return new ViewHolder(view,ctx);

    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact=contactsList.get(position);
        database=AppDataBase.getInstance(context);

        holder.id.setText(String.valueOf(contact.getID()));
        holder.name.setText(contact.getFirstName()+" "+contact.getLastName());
        holder.job.setText(contact.getJob());
        holder.phone.setText(contact.getPhone());
        holder.email.setText(contact.getEmail());
    }


    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView id, name, email,job,phone;
        ImageButton btCall;
        Context context;
        public ViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            id=itemView.findViewById(R.id.ID);
            name=itemView.findViewById(R.id.Name);
            job=itemView.findViewById(R.id.Job);
            phone=itemView.findViewById(R.id.Tel);
            email=itemView.findViewById(R.id.Email);
            btCall=itemView.findViewById(R.id.phone_btn);
            this.context=context;
            itemView.setOnLongClickListener(this::onLongClick);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedContact.selectedContact(contactsList.get(getAdapterPosition()));
                }
            });

        }
        @Override
        public boolean onLongClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            String contactName =    contactsList.get(getAdapterPosition()).getFirstName()
                                    + " "
                                    + contactsList.get(getAdapterPosition()).getLastName();
            builder.setTitle("Confirmation :")
                .setMessage("You want to delete "+contactName+ " from contacts ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int idContact = Integer.parseInt(id.getText().toString());
                        Contact contactToDelete = new Contact();
                        int contactPos = getAdapterPosition();
                        contactToDelete.setID(idContact);
                        if(contactToDelete!=null){
                            contactsList.remove(contactToDelete);
                            database.contactDAO().delete(idContact);
                            mainActivity.RefreshListView(database.contactDAO().getAll());
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
            AlertDialog dialog  = builder.create();
            dialog.show();
            return true;

        }
    }

    public interface SelectedContact{
        void selectedContact(Contact contact);
    }
}
