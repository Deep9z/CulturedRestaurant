package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

public class PayByCard extends AppCompatActivity {

    CardForm cardForm;
    TextView txtDes;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_by_card);

        cardForm =  findViewById(R.id.cardform);
        txtDes = findViewById(R.id.payment_amount);
        btnPay = findViewById(R.id.btn_pay);


        Intent intent = getIntent();
        final String numOfPeople = intent.getStringExtra("numOfPeople");
        final String cost = intent.getStringExtra("cost");
        final String pricePerPerson = intent.getStringExtra("pricePerPerson");
        final String IDString = intent.getStringExtra("TableNumber");
        final String ToGoString = intent.getStringExtra("ToGo");


       // tvCost.setText(String.valueOf("Total $" + cost));
        //tvNumOfPeople.setText(String.valueOf("Number of Payers " + numOfPeople));
        //tvPricePerPerson.setText(String.valueOf("Price per person: $" + pricePerPerson));

        txtDes.setText(String.format("$%s", pricePerPerson));
        btnPay.setText(String.format("Pay %s",txtDes.getText()));


        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {

                Toast.makeText(getApplicationContext(),"Payment Sent",Toast.LENGTH_SHORT).show();
                Intent receiptIntent = new Intent(PayByCard.this, Receipt.class);

                receiptIntent.putExtra("numOfPeople", numOfPeople);
                receiptIntent.putExtra("cost", cost);
                receiptIntent.putExtra("pricePerPerson", pricePerPerson);
                receiptIntent.putExtra("TableNumber", IDString);
                receiptIntent.putExtra("ToGo", ToGoString);
                startActivity(receiptIntent);
                finish();
            }
        });
    }
}
