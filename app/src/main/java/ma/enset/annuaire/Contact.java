package ma.enset.annuaire;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "LastName")
    String lastName;
    @ColumnInfo(name = "FirstName")
    String firstName;
    @ColumnInfo(name = "Job")
    String job;
    @ColumnInfo(name = "Phone")
    String phone;
    @ColumnInfo(name = "Email")
    String email;
    @Ignore
    public Contact() {
    }

    public Contact(Integer ID, String lastName, String firstName, String job, String phone, String email) {
        this.ID = ID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.job = job;
        this.phone = phone;
        this.email = email;
    }
    @Ignore
    public Contact(String lastName, String firstName, String job, String phone, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.job = job;
        this.phone = phone;
        this.email = email;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
