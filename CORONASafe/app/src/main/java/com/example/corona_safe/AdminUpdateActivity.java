package com.example.corona_safe;

import android.os.Bundle;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class AdminUpdateActivity extends AppCompatActivity {

    EditText editNIC, editUser, editEmail, editVacc, editVacST;
    SearchEvent searchUser;
    Button btnV0,btnV1,btnV2,btnV3, btnAddnew;
    CustomerDBHelper CustomersDB;
    String editVacst = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminupdate);

        editNIC = (EditText) findViewById(R.id.editNIC);
        editUser = (EditText) findViewById(R.id.editUser);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editVacc = (EditText) findViewById(R.id.editVacc);
        editVacST = (EditText) findViewById(R.id.editVacST);
        CustomersDB = new CustomerDBHelper(this);
        btnAddnew = (Button) findViewById(R.id.btnAddnew);
        //btnV0 = (Button) findViewById(R.id.btnV0);
        // btnV1 = (Button) findViewById(R.id.btnV1);
        // btnV2 = (Button) findViewById(R.id.btnV2);
        // btnV3 = (Button) findViewById(R.id.btnV3);


        btnAddnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = editUser.getText().toString();
                String mail = editEmail.getText().toString();
                String nic = editNIC.getText().toString();
                String vacc = editVacc.getText().toString();
                Integer  vacst = Integer.valueOf(editVacST.getText().toString());

                if(user.equals("")||mail.equals("")||nic.equals("")||vacc.equals(""))
                    Toast.makeText(AdminUpdateActivity.this, "Please enter all the fields", LENGTH_SHORT).show();
                else{
                    Boolean checkusermail = CustomersDB.checkusernameemail(user, mail);
                    if(checkusermail==true){
                        Boolean insert = CustomersDB.updateDataAsAdmin(user, nic, vacc, vacst);
                        if(insert==true){
                            Toast.makeText(AdminUpdateActivity.this, "Added successfully", LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),AdminHomeActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(AdminUpdateActivity.this, "Registration failed", LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AdminUpdateActivity.this, "username not matching", LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
