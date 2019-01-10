package com.harrowedtale.culturedrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class payActivity extends AppCompatActivity {

    TextView tvPreTax;
    TextView tvNumberOfPeople;
    Spinner sNumOfPeople;
    TextView tvCouponCode;
    EditText etCouponCode;
    TextView tvTip;
    EditText etTip;
    TextView tvTipAmount;
    TextView tvTax;
    TextView tvTotal;
    EditText etManagerComp;
    Button bCash;
    Button bCard;

    float cost;
    String[] peopleCount  = new String [] {"1","2","3","4",};
    String stringTotal;
    String tipPercent;
    String Coupon;


    public static String MAN_COMPED = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Intent intent = getIntent();
        final String IDString = intent.getStringExtra("TableNumber");
        final String ToGoString = intent.getStringExtra("ToGo");

        tvPreTax = findViewById(R.id.tvPreTax);
        tvNumberOfPeople = findViewById(R.id.tvNumOfPeople);
        sNumOfPeople = findViewById(R.id.sNumOfPeople);
        tvCouponCode = findViewById(R.id.tvCouponCode);
        etCouponCode = findViewById(R.id.etCouponCode);
        tvTip = findViewById(R.id.tvTip);
        etTip = findViewById(R.id.etTip);
        tvTipAmount = findViewById(R.id.tvTipAmount);
        tvTax = findViewById(R.id.tvTax);
        tvTotal = findViewById(R.id.tvTotal);
        bCash = findViewById(R.id.bPayCash);
        bCard = findViewById(R.id.bPayCard);


        etManagerComp = findViewById(R.id.etManagerComp);

        etTip.setSelection(etTip.getText().length());

        //calculating total cost
        double tempCost = 0;
        ArrayList<Double> tempHolder = MakeCart.getAllPrices();
        for (int i = 0; i < tempHolder.size(); i++){
            tempCost += tempHolder.get(i);
        }

        cost = (float)tempCost;

        NumberFormat nf_us = NumberFormat.getCurrencyInstance(Locale.US);
        tvPreTax.setText(String.valueOf("Total before tax and tip: " + nf_us.format(cost)));


        tipPercent = etTip.getText().toString();

        float floatTip = (Float.parseFloat(tipPercent)/100) * cost;
        //String stringTip = String.format(Locale.US, "%.2f", floatTip);


        tvTipAmount.setText(String.valueOf("Tip Amount: " +  nf_us.format(floatTip)));

        float floatTax = (float)((8.25/100) * cost);
        final String stringTax = String.format(Locale.US, "%.2f", floatTax);
        tvTax.setText(String.valueOf("Tax Amount: $" + stringTax));

        float floatTotal = cost + floatTax + floatTip;
        stringTotal = String.format(Locale.US, "%.2f",floatTotal);
        tvTotal.setText(String.valueOf("Total Amount: $" + stringTotal));


        //textwatcher for coupon
        etCouponCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                float floatTip = (float)20.00;

                if (!tipPercent.isEmpty()) {
                    floatTip = (Float.parseFloat(tipPercent) / 100) * cost;
                    String stringTip = String.format(Locale.US, "%.2f", floatTip);
                    tvTipAmount.setText(String.valueOf("Tip Amount: $" + stringTip));
                }

                float floatTax = (float)((8.25/100) * cost);
                String stringTax = String.format(Locale.US, "%.2f", floatTax);
                tvTax.setText(String.valueOf("Tax Amount: $" + stringTax));

                if(etCouponCode.getText().toString().equals("1234")){

                    float priceOff = 4;
                    float priceOffCost = cost - priceOff;
                    float floatTotal = priceOffCost + floatTax + floatTip;

                    stringTotal = String.format(Locale.US, "%.2f",floatTotal);
                    tvTotal.setText(String.valueOf("Total Amount: $" + stringTotal));

                    Toast.makeText(getApplicationContext(),"Coupon Code Applied", Toast.LENGTH_SHORT).show();
                }
                else {

                    float floatTotal = cost + floatTax + floatTip;
                    stringTotal = String.format(Locale.US, "%.2f",floatTotal);
                    tvTotal.setText(String.valueOf("Total Amount: $" + stringTotal));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //listen for changes in tip textfield
        etTip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                tipPercent = etTip.getText().toString();
                if(tipPercent.matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter tip amount",Toast.LENGTH_SHORT).show();
                    return;
                }


                float floatTip = (Float.parseFloat(tipPercent)/100) * cost;
                String stringTip = String.format(Locale.US, "%.2f", floatTip);
                tvTipAmount.setText(String.valueOf("Tip Amount: $" + stringTip));

                float floatTax = (float)((8.25/100) * cost);
                String stringTax = String.format(Locale.US, "%.2f", floatTax);
                tvTax.setText(String.valueOf("Tax Amount: $" + stringTax));

                if(etCouponCode.getText().toString().equals("1234")){


                    float priceOff = (float)(.5 * cost);
                    float priceOffCost = cost - priceOff;
                    float floatTotal = priceOffCost + floatTax + floatTip;

                    stringTotal = String.format(Locale.US, "%.2f",floatTotal);
                    tvTotal.setText(String.valueOf("Total Amount: $" + stringTotal));

                }
                else {

                    float floatTotal = cost + floatTax + floatTip;
                    stringTotal = String.format(Locale.US, "%.2f",floatTotal);
                    tvTotal.setText(String.valueOf("Total Amount: $" + stringTotal));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        etManagerComp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(etManagerComp.getText().toString().equals("COMPED")){

                    MAN_COMPED = "True";
                    float floatTotal = (float)0.00;
                    stringTotal = String.format(Locale.US, "%.2f",floatTotal);
                    tvTotal.setText(String.valueOf("Total Amount: $" + stringTotal));

                    Toast.makeText(payActivity.this, "Order Comped", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this,android.R.layout.simple_spinner_item, peopleCount);
        sNumOfPeople.setAdapter(adapter);


        //todo change coupon code
        Coupon = etCouponCode.getText().toString();
        if(Coupon.equals("1234")){
            float price_off = 4;
            cost = cost - price_off;

        }

        bCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tipPercent.matches(""))
                {

                    Toast.makeText(getApplicationContext(),"Enter tip amount",Toast.LENGTH_SHORT).show();
                    return;
                }

                final String numOfPeople = sNumOfPeople.getSelectedItem().toString();


                float ppp = (Float.parseFloat(stringTotal)/Integer.valueOf(numOfPeople));
                String pricePerPerson = String.format(Locale.US,"%.2f",ppp);


                Intent receiptIntent = new Intent(payActivity.this, Receipt.class);

                receiptIntent.putExtra("numOfPeople", numOfPeople);
                receiptIntent.putExtra("cost", stringTotal);
                receiptIntent.putExtra("pricePerPerson", pricePerPerson);
                receiptIntent.putExtra("TableNumber", IDString);
                receiptIntent.putExtra("ToGo", ToGoString);
                startActivity(receiptIntent);
                finish();
            }
        });

        bCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tipPercent.matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter tip amount",Toast.LENGTH_SHORT).show();
                    return;
                }

                final String numOfPeople = sNumOfPeople.getSelectedItem().toString();

                float ppp = (Float.parseFloat(stringTotal)/Integer.valueOf(numOfPeople));
                String pricePerPerson = String.format(Locale.US,"%.2f",ppp);


                //TODO write all receipt values to card activity then write them again to receipt activity (their might be a better way to do this)
                Intent cardIntent = new Intent(payActivity.this, PayByCard.class);
                cardIntent.putExtra("numOfPeople", numOfPeople);
                cardIntent.putExtra("cost", stringTotal);
                cardIntent.putExtra("pricePerPerson", pricePerPerson);
                cardIntent.putExtra("TableNumber", IDString);
                cardIntent.putExtra("ToGo", ToGoString);
                startActivity(cardIntent);
                finish();

            }
        });
    }
}
