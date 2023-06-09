package com.example.baject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SuhuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText textInputan, textKonversi;
    Button buttonKonvHitung;
    Spinner spinnerAsal, spinnerTujuan;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_suhu);
//
//        spinnerAsal = findViewById(R.id.spinnerAsal);
//    }
//}

//================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suhu);

        // Inisiasi spinner untuk satuan suhu asal
        spinnerAsal = findViewById(R.id.spinnerAsal);

        // Inisiasi spinner untuk satuan suhu tujuan
        spinnerTujuan = findViewById(R.id.spinnerKonversi);

        // Inisiasi input field
        textInputan = findViewById(R.id.et_sample8);
        textKonversi = findViewById(R.id.et_sample10);

        // Inisiasi button untuk hitung konversi
        buttonKonvHitung = findViewById(R.id.buttonKonversi);
        buttonKonvHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitungKonversi();
            }
        });
    }

    // Method untuk menghitung konversi suhu
    private void hitungKonversi() {
        String suhuAsal = spinnerAsal.getSelectedItem().toString();
        String suhuTujuan = spinnerTujuan.getSelectedItem().toString();
        String inputan = textInputan.getText().toString();

        if (inputan.isEmpty()) {
            // Jika input field kosong, tampilkan pesan error
            Toast.makeText(this, "Masukkan suhu yang ingin dikonversi", Toast.LENGTH_SHORT).show();
            return;
        }

        // Konversi inputan menjadi double
        double suhu = Double.parseDouble(inputan);

        // Konversi suhu sesuai dengan satuan yang dipilih
        double hasilKonversi;

        spinnerAsal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                String selectedItem2 = spinnerTujuan.getSelectedItem().toString();

                if(selectedItem.equals(selectedItem2)) {
                    // ganti isian spinner
                    spinnerTujuan.setSelection((position + 1) % spinnerTujuan.getAdapter().getCount());
                }
            }

            @Override
            public void onNothingSelected(AdapterView parent) {

            }
        });

        spinnerTujuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                String selectedItem1 = spinnerAsal.getSelectedItem().toString();

                if(selectedItem.equals(selectedItem1)) {
                    // ganti isian spinner
                    spinnerAsal.setSelection((position + 1) % spinnerAsal.getAdapter().getCount());
                }
            }

            @Override
            public void onNothingSelected(AdapterView parent) {

            }
        });

        switch (suhuAsal) {
            case "Celcius":
                if (suhuTujuan.equals("Fahrenheit")) {
                    hasilKonversi = (suhu * 1.8) + 32;
                } else {
                    hasilKonversi = suhu + 273.15;
                }
                break;
            case "Fahrenheit":
                if (suhuTujuan.equals("Celsius")) {
                    hasilKonversi = (suhu - 32) * 1.8;
                } else {
                    hasilKonversi = (suhu - 32) * 5/9 + 273.15;
                }
                break;
            default:
                if (suhuTujuan.equals("Celsius")) {
                    hasilKonversi = suhu - 273.15;
                } else {
                    hasilKonversi = (suhu - 273.15) * 9/5 + 32;
                }
                break;
        }

        // Tampilkan hasil konversi pada output field
        textKonversi.setText(Double.toString(hasilKonversi));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}