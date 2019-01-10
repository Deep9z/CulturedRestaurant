package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class MainActivity extends AppCompatActivity {

    //Creates static variables, that will not changem for referencing in the database.
    private static final String NAME_KEY = "Name";
    private static final String PASSWORD_KEY = "Password";
    private static final String ID_KEY = "ID";

    //used for requests
    public static int TABLE_NUM;

    String testStringCheck;//String to test if the area was reached in the unit tests

    public FirebaseFirestore db = FirebaseFirestore.getInstance();//Point db to the root directory of the database
    CollectionReference mLoginReference = db.collection("Users");//Creates a document reference to the "Users" collection

    public Button mLoginButton;//Initializes the method for the login button, of class Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginButton = (Button) findViewById(R.id.bLogin);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.


        //Creates an function (onClickListener) that will listen to when the button is clicked, and execute the code below.
        mLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Creates variables to store the text from the username and password of the login screen
                EditText usernameView = (EditText) findViewById(R.id.etUsername);
                EditText passwordView = (EditText) findViewById(R.id.etPassword);

                //Create variables to store the user-inputted text from the username and password fields
                final String usernameText = usernameView.getText().toString();
                final String passwordText = passwordView.getText().toString();

                //Check if the username or password are empty
                if(usernameText.isEmpty() || passwordText.isEmpty())
                {
                    //Display a Toast message telling the user that the login username or password was incorrect
                    Toast.makeText(getApplicationContext(),"Try again: Incorrect username or password",Toast.LENGTH_SHORT).show();
                    return;
                }

                //Creates variable that will create a reference to the specified username, which is used to login (For example, <username: Customer06>, which is from the database
                DocumentReference userLogin = db.collection("Users").document(usernameText);

                //Try to get data from the database
                userLogin.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    //If the user is able to get data from the database
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {   //Check if the document exists in the database
                            //Stores the data from the database document into the created strings

                            String usernameCheck = documentSnapshot.getString(NAME_KEY);
                            String passwordCheck = documentSnapshot.getString(PASSWORD_KEY);
                            String classificationCheck = documentSnapshot.getString("Classification");

                            //Login works by authenticating the username and password, then diverting where the user goes
                            //by the actual name of the username. This is for all users, except for customers, whom only check
                            //if the password is a space, as this is the unifier for all of the customer's data.
                            //If the username and password equal what is in the database
                            if (passwordText.equals(passwordCheck)) {
                                if(classificationCheck.equals("Customer"))//If the Customer logs in, as the password is always a single space (" ")
                                {
                                    Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();

                                    int customerID = documentSnapshot.getLong("ID").intValue();

                                    //made public static variable for table num for requests activity
                                    TABLE_NUM = customerID;

                                    String IDString = Integer.toString(customerID);

                                    Intent customerIntent = new Intent(MainActivity.this, goActivity.class);
                                    customerIntent.putExtra("TableNumber", IDString);
                                    startActivity(customerIntent);

                                    finish();
                                }
                                else if(classificationCheck.equals("Manager"))//If the manager logs in, using his name as the condition
                                {
                                    Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();

                                    Intent managerIntent = new Intent(MainActivity.this, managerActivity.class);
                                    startActivity(managerIntent);
                                }
                                else if(classificationCheck.equals("Waitstaff"))//If the wait staff logs in, using their names as the condition
                                {
                                    Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();

                                    int customerID = documentSnapshot.getLong("ID").intValue();

                                    //made public static variable for table num for requests activity
                                    TABLE_NUM = customerID;

                                    String IDString = Integer.toString(customerID);

                                    Intent waitstaffIntent = new Intent(MainActivity.this, waitstaffActivity.class);
                                    waitstaffIntent.putExtra("TableNumber", IDString);
                                    startActivity(waitstaffIntent);
                                }
                                else if(classificationCheck.equals("Kitchen"))//If the kitchen staff logs in, using their name as the condition
                                {
                                    Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();


                                    Intent kitchenstaffActivity = new Intent(MainActivity.this, kitchenstaffActivity.class);
                                    startActivity(kitchenstaffActivity);
                                }
                            } else//If the username and/or password are not equal to what is in the database
                            {
                                //Display a Toast message telling the user that the login username or password was incorrect
                                Toast.makeText(getApplicationContext(), "Try again: Incorrect username or password", Toast.LENGTH_SHORT).show();
                            }
                        } else//If the username does not exist in the database
                        {
                            Toast.makeText(getApplicationContext(), "Try again: Incorrect username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
