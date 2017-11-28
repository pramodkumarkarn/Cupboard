package co.edureka.cupboardapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

      private SQLiteDatabase sqLiteDatabase;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button insertButton = (Button) findViewById(R.id.insertButton);
        Button readButton = (Button) findViewById(R.id.readButton);
        Button updateButton = (Button) findViewById(R.id.updateButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);

        insertButton.setOnClickListener(this);
        readButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        CupboardSQLiteOpenHelper cupboardSQLiteOpenHelper = new CupboardSQLiteOpenHelper(this);
        sqLiteDatabase = cupboardSQLiteOpenHelper.getWritableDatabase();
    }

   @Override
            public void onClick(View v){
            switch(v.getId()) {
                case R.id.insertButton:
                insertRecords();
                break;
                case R.id.readButton:
                    readRecords();
                    break;
                case R.id.updateButton:
                    updateRecords();
                    break;
                case R.id.deleteButton:
                    deleteRecords();
                    break;
            }
        }

    private void insertRecords(){


            Book book1 = new Book("Advanced Android", "Mortensen", 3000);
            Book book2 = new Book("Advanced IOS", "Cutler", 3000);
            Book book3 = new Book("Advanced Java", "Thuleen", 3000);

            long rowId1 = cupboard().withDatabase(sqLiteDatabase).put(book1);
            if (rowId1 != -1){
                Toast.makeText(this, "Insert Success " + rowId1, Toast.LENGTH_LONG).show();
            }
        long rowId2 = cupboard().withDatabase(sqLiteDatabase).put(book2);
        if (rowId2 != -1){
            Toast.makeText(this, "Insert Success " + rowId2, Toast.LENGTH_LONG).show();
        }
        long rowId3 = cupboard().withDatabase(sqLiteDatabase).put(book3);
        if (rowId3 != -1){
            Toast.makeText(this, "Insert Success " + rowId3, Toast.LENGTH_LONG).show();
        }

    }
private void readRecords(){

        List<Book> books = cupboard().withDatabase(sqLiteDatabase).query(Book.class).list();

        if (books != null && books.size() > 0){
            for(Book book : books) {
                Toast.makeText(this, book.name + " " + book.author + " " + book.price, Toast.LENGTH_LONG).show();
            }
        }
}


private void updateRecords() {
    ContentValues values = new ContentValues(1);
    values.put("price", 5000);
    int numRowsUpdated = cupboard().withDatabase(sqLiteDatabase).update(Book.class, values, "name = ?","Advanced SOMETHING" );

    if (numRowsUpdated != -1){
        Toast.makeText(this, numRowsUpdated + " rows updated!", Toast.LENGTH_LONG).show();
    }
}

private void deleteRecords(){
    int numRowsDeleted = cupboard().withDatabase(sqLiteDatabase).delete(Book.class, "name = ?","Advanced SOMETHING");
    Toast.makeText(this, numRowsDeleted + " row deleted!", Toast.LENGTH_LONG).show();

    }
}

