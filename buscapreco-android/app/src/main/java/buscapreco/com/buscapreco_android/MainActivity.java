package buscapreco.com.buscapreco_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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


        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, obterCidades());
        AutoCompleteTextView clubes = (AutoCompleteTextView) findViewById(R.id.cidade);
        clubes.setAdapter(adp);
    }

    private ArrayList<Produto> GetSearchResults() {
        ArrayList<Produto> results = new ArrayList<Produto>();

        Produto item_details = new Produto();
        item_details.setDescricao("Cerveja Skol");
        item_details.setDetalhes("300 ml");

        Endereco endereco = new Endereco();
        endereco.setCidade("Araras");
        endereco.setLogradouro("Rua Paraná");
        endereco.setBairro("Pq Industrial");
        endereco.setUF("SP");
        endereco.setNumero("350");

        Loja l1 = new Loja();
        l1.setEndereco(endereco);
        l1.setPreco(4.78);
        l1.setDescricao("Boteco da Maria");

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

    private String [] obterCidades(){
        String[] cidades = new String[]{"Araras-SP", "Limeira-SP", "Campinas-SP"};
        return cidades;
    }

    private void configurarEnventoLista(){
        lista = (ListView) findViewById(R.id.listView);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lista.getItemAtPosition(position);
                Produto obj_produto = (Produto) o;
                Toast.makeText(MainActivity.this, "Produto selecionado: " + " " + obj_produto.getDescricao(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
