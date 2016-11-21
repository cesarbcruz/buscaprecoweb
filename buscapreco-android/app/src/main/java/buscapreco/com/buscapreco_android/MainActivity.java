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

    private ArrayList<Produto> produtos;
    private ListView lista;
    private TextView codigoBarras;
    private TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codigoBarras = (TextView) findViewById(R.id.codigoBarras);
        titulo = (TextView) findViewById(R.id.titulo);
        configuraEventoPesquisar();
        configurarEnventoLista();

        if (savedInstanceState != null) {
            produtos = savedInstanceState.getParcelableArrayList("produtos");
            if (produtos != null) {
                carregarProdutos();
            }
        }

        ImageButton btnLerCodigoBrras = (ImageButton) findViewById(R.id.lerCodigoBrras);
        btnLerCodigoBrras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lerCodigoBarras();
            }
        });
    }

    private ArrayList<Produto> GetSearchResults() {
        ArrayList<Produto> results = new ArrayList<Produto>();

        Produto item_details = new Produto();
        item_details.setName("Pizza");
        item_details.setItemDescription("Você já se perguntou o que é, de fato, um texto? Geralmente, entendemos o texto como um conjunto de frases, ou seja, algo que foi feito para ser lido. Mas a definição de texto não é tão simples quanto parece.");
        item_details.setPreco(3101.00);
        //item_details.setImagem(1);
        item_details.setQtdelojas(3);
        results.add(item_details);

        item_details = new Produto();
        item_details.setName("Burger");
        item_details.setItemDescription("Beef Burger");
        item_details.setPreco(0.80);
        //item_details.setImagem(2);
        item_details.setQtdelojas(1);
        results.add(item_details);

        item_details = new Produto();
        item_details.setName("O contexto pode ser explícito, quando é expresso por palavras");
        item_details.setItemDescription("Agora, vamos imaginar outra situação: você está no meio de uma floresta e ouve alguém gritar: “Madeira!”");
        item_details.setPreco(250.00);
        //item_details.setImagem(3);
        item_details.setQtdelojas(10);
        results.add(item_details);

        item_details = new Produto();
        item_details.setName("Burger");
        item_details.setItemDescription("Chicken Burger");
        item_details.setPreco(350.78);
        //item_details.setImagem(4);
        item_details.setQtdelojas(2);
        results.add(item_details);

        item_details = new Produto();
        item_details.setName("Burger");
        item_details.setItemDescription("Fish Burger");
        item_details.setPreco(310.05);
        //item_details.setImagem(5);
        item_details.setQtdelojas(1);
        results.add(item_details);

        item_details = new Produto();
        item_details.setName("Mango");
        item_details.setItemDescription("Mango Juice");
        item_details.setPreco(250.10);
        //item_details.setImagem(6);
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

    public void configuraEventoPesquisar() {
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

    private void pesquisar(){
        produtos = GetSearchResults();
        carregarProdutos();
    }

    private void carregarProdutos() {
        if(produtos==null){
            return;
        }

        if(produtos.size() == 1){
            titulo.setText(produtos.size()+" produto encontrado");
        }else if(produtos.size() > 1){
            titulo.setText(produtos.size()+" produtos encontrados");
        }else{
            titulo.setText("nenhum produto encontrado");
        }

        lista.setAdapter(new ItemListBaseAdapter(this, produtos));
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        savedState.putParcelableArrayList("produtos", produtos);
    }

    private void configurarEnventoLista(){
        lista = (ListView) findViewById(R.id.listView);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lista.getItemAtPosition(position);
                Produto obj_produto = (Produto) o;
                Toast.makeText(MainActivity.this, "Produto selecionado: " + " " + obj_produto.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
