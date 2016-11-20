package buscapreco.com.buscapreco_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView codigoBarras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codigoBarras = (TextView) findViewById(R.id.codigoBarras);
        eventoRealizarPesquisa();

        ImageButton btnLerCodigoBrras = (ImageButton) findViewById(R.id.lerCodigoBrras);
        btnLerCodigoBrras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lerCodigoBarras();
            }
        });
    }

    private ArrayList<ItemDetails> GetSearchResults() {
        ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();

        ItemDetails item_details = new ItemDetails();
        item_details.setName("Pizza");
        item_details.setItemDescription("Você já se perguntou o que é, de fato, um texto? Geralmente, entendemos o texto como um conjunto de frases, ou seja, algo que foi feito para ser lido. Mas a definição de texto não é tão simples quanto parece.");
        item_details.setPreco(310.00);
        item_details.setImageNumber(1);
        item_details.setQtdelojas(3);
        results.add(item_details);

        item_details = new ItemDetails();
        item_details.setName("Burger");
        item_details.setItemDescription("Beef Burger");
        item_details.setPreco(0.80);
        item_details.setImageNumber(2);
        item_details.setQtdelojas(1);
        results.add(item_details);

        item_details = new ItemDetails();
        item_details.setName("O contexto pode ser explícito, quando é expresso por palavras");
        item_details.setItemDescription("Agora, vamos imaginar outra situação: você está no meio de uma floresta e ouve alguém gritar: “Madeira!”");
        item_details.setPreco(250.00);
        item_details.setImageNumber(3);
        item_details.setQtdelojas(10);
        results.add(item_details);

        item_details = new ItemDetails();
        item_details.setName("Burger");
        item_details.setItemDescription("Chicken Burger");
        item_details.setPreco(350.78);
        item_details.setImageNumber(4);
        item_details.setQtdelojas(2);
        results.add(item_details);

        item_details = new ItemDetails();
        item_details.setName("Burger");
        item_details.setItemDescription("Fish Burger");
        item_details.setPreco(310.05);
        item_details.setImageNumber(5);
        item_details.setQtdelojas(1);
        results.add(item_details);

        item_details = new ItemDetails();
        item_details.setName("Mango");
        item_details.setItemDescription("Mango Juice");
        item_details.setPreco(250.10);
        item_details.setImageNumber(6);
        results.add(item_details);

        return results;
    }

    public void lerCodigoBarras() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Posicione o código de barras do produto.");
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    public void eventoRealizarPesquisa() {
        codigoBarras.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                boolean handled = false;
                if (actionId == KeyEvent.KEYCODE_HOME) {
                    pesquisar();

                    handled = true;
                }
                return handled;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            String contents = result.getContents();
            if (contents != null) {
                codigoBarras.setText(contents);
                pesquisar();
            }
        }
    }

    private void pesquisar() {
        ArrayList<ItemDetails> image_details = GetSearchResults();

        final ListView lv1 = (ListView) findViewById(R.id.listView);
        lv1.setAdapter(new ItemListBaseAdapter(this, image_details));

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                ItemDetails obj_itemDetails = (ItemDetails) o;
                Toast.makeText(MainActivity.this, "Produto selecionado: " + " " + obj_itemDetails.getName(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
