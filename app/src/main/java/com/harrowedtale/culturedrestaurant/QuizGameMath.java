package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizGameMath extends AppCompatActivity{
    public Button Answer1;//Initializes the method for the first button, of class Button
    public Button Answer2;//Initializes the method for the second button, of class Button
    public Button Answer3;//Initializes the method for the third button, of class Button
    public Button Answer4;//Initializes the method for the fourth button, of class Button
    public Button Answer5;//Initializes the method for the fifth button, of class Button
    public Button homeButton;//Initializes the method for the fifth button, of class Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game5_math);

        Intent intent = getIntent();
        final String IDString = intent.getStringExtra("TableNumber");
        final String ToGoString = intent.getStringExtra("ToGo");


        //TODO: Get background from Anderson and add it into the xml
        Answer1 = (Button) findViewById(R.id.bMAnswer1);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        Answer2 = (Button) findViewById(R.id.bMAnswer2);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        Answer3 = (Button) findViewById(R.id.bMAnswer3);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        Answer4 = (Button) findViewById(R.id.bMAnswer4);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        Answer5 = (Button) findViewById(R.id.bMAnswer5);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
        homeButton  = (Button) findViewById(R.id.bQuizHome);//Assigns homeButton to the button to send the customer back tot he main menu


        //Detects which button the customer selects, starting that game

        Answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);//Set the button to go invisible
                // try{ view.setVisibility(View.GONE);
                //     Thread.sleep(3000);
                // }
                // catch (InterruptedException ex) {android.util.Log.d("CulturedRestaurant", ex.toString());}

                setContentView(R.layout.activity_quiz_game5_math2);

                Answer1 = (Button) findViewById(R.id.bMAnswer12);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
                Answer2 = (Button) findViewById(R.id.bMAnswer22);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
                Answer3 = (Button) findViewById(R.id.bMAnswer32);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
                Answer4 = (Button) findViewById(R.id.bMAnswer42);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
                Answer5 = (Button) findViewById(R.id.bMAnswer52);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
                homeButton  = (Button) findViewById(R.id.bQuizHome);//Assigns homeButton to the button to send the customer back tot he main menu

                Answer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Intent GameIntent = new Intent(GameMenu.this, MemoryGame.class);
                        //startActivity(GameIntent);
                        view.setVisibility(View.GONE);//Set the button to go invisible

                    }
                });

                Answer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Intent GameIntent = new Intent(GameMenu.this, MemoryGame.class);
                        //startActivity(GameIntent);
                        view.setVisibility(View.GONE);//Set the button to go invisible

                    }
                });

                Answer3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Intent GameIntent = new Intent(GameMenu.this, ClickerGameActivity.class);
                        //startActivity(GameIntent);
                        view.setVisibility(View.GONE);//Set the button to go invisible
                        // try{ Thread.sleep(3000);}
                        // catch (InterruptedException ex) {android.util.Log.d("CulturedRestaurant", ex.toString());}
                        setContentView(R.layout.activity_quiz_game5_math3);

                        Answer1 = (Button) findViewById(R.id.bMAnswer13);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
                        Answer2 = (Button) findViewById(R.id.bMAnswer23);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
                        Answer3 = (Button) findViewById(R.id.bMAnswer33);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
                        Answer4 = (Button) findViewById(R.id.bMAnswer43);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
                        Answer5 = (Button) findViewById(R.id.bMAnswer53);//Assigns the method button to the button created in the .xml file. It is type casted to type Button.
                        homeButton  = (Button) findViewById(R.id.bQuizHome);//Assigns homeButton to the button to send the customer back tot he main menu

                        Answer1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Intent GameIntent = new Intent(GameMenu.this, MemoryGame.class);
                                //startActivity(GameIntent);
                                view.setVisibility(View.GONE);//Set the button to go invisible

                            }
                        });

                        Answer2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Intent GameIntent = new Intent(GameMenu.this, MemoryGame.class);
                                //startActivity(GameIntent);
                                view.setVisibility(View.GONE);//Set the button to go invisible

                            }
                        });

                        Answer3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Intent GameIntent = new Intent(GameMenu.this, ClickerGameActivity.class);
                                //startActivity(GameIntent);
                                view.setVisibility(View.GONE);//Set the button to go invisible

                            }
                        });

                        Answer4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Intent GameIntent = new Intent(GameMenu.this, QuizGame.class);
                                // startActivity(GameIntent);
                                view.setVisibility(View.GONE);//Set the button to go invisible

                            }
                        });

                        Answer5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Intent GameIntent = new Intent(GameMenu.this, SpinBottle.class);
                                // startActivity(GameIntent);
                                view.setVisibility(View.GONE);//Set the button to go invisible

                            }
                        });

                        homeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent customerIntent = new Intent(QuizGameMath.this, customerActivity.class);
                                customerIntent.putExtra("TableNumber", IDString);
                                customerIntent.putExtra("ToGo", ToGoString);
                                startActivity(customerIntent);
                            }
                        });
                    }
                });

                Answer4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Intent GameIntent = new Intent(GameMenu.this, QuizGame.class);
                        // startActivity(GameIntent);
                        view.setVisibility(View.GONE);//Set the button to go invisible

                    }
                });

                Answer5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Intent GameIntent = new Intent(GameMenu.this, SpinBottle.class);
                        // startActivity(GameIntent);
                        view.setVisibility(View.GONE);//Set the button to go invisible

                    }
                });

                homeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent customerIntent = new Intent(QuizGameMath.this, customerActivity.class);
                        customerIntent.putExtra("TableNumber", IDString);
                        customerIntent.putExtra("ToGo", ToGoString);
                        startActivity(customerIntent);
                    }
                });
            }
        });

        Answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent GameIntent = new Intent(GameMenu.this, MemoryGame.class);
                //startActivity(GameIntent);
                view.setVisibility(View.GONE);//Set the button to go invisible

            }
        });

        Answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent GameIntent = new Intent(GameMenu.this, ClickerGameActivity.class);
                //startActivity(GameIntent);
                view.setVisibility(View.GONE);//Set the button to go invisible

            }
        });

        Answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent GameIntent = new Intent(GameMenu.this, QuizGame.class);
                // startActivity(GameIntent);
                view.setVisibility(View.GONE);//Set the button to go invisible

            }
        });

        Answer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent GameIntent = new Intent(GameMenu.this, SpinBottle.class);
                // startActivity(GameIntent);
                view.setVisibility(View.GONE);//Set the button to go invisible

            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent customerIntent = new Intent(QuizGameMath.this, customerActivity.class);
                customerIntent.putExtra("TableNumber", IDString);
                customerIntent.putExtra("ToGo", ToGoString);
                startActivity(customerIntent);
            }
        });
    }

}
