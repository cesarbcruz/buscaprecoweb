package buscapreco.com.buscapreco_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnLerCodigoBrras = (ImageButton) findViewById(R.id.lerCodigoBrras);
        btnLerCodigoBrras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lerCodigoBarras();
            }
        });
    }

    public void lerCodigoBarras(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Posicione o código de barras do produto.");
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            String contents = result.getContents();
            if (contents != null) {
                TextView codigoBarras = (TextView) findViewById(R.id.codigoBarras);
                codigoBarras.setText(contents);
            }
        }
    }
}
