package ma.enset.annuaire;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDAO {

        @Query("SELECT * FROM Contact")
        List<Contact> getAll();

        @Insert
        void insert(Contact contact);

        @Query("SELECT * FROM Contact WHERE FirstName LIKE :keyword OR LastName LIKE :keyword")
        List<Contact> findByName(String keyword);

        @Query("SELECT * FROM Contact WHERE ID= :ID")
        Contact findByID(int ID);

        @Query("DELETE FROM Contact WHERE ID= :ID")
        void delete(int ID);

        @Update
        void update(Contact contact);

        @Query("DELETE FROM Contact")
        void clear();
}
