package buscapreco.com.buscapreco_android;

/**
 * Created by cesar on 23/11/16.
 */
public class Loja implements Comparable {
    private String descricao;
    private double preco;
    private Endereco endereco;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    @Override
    public int compareTo(Object another) {

        if(this.getPreco()>((Loja)another).getPreco()){
            return 1;
        }

        if(this.getPreco()<((Loja)another).getPreco()){
            return -1;
        }

        return 0;
    }
}
