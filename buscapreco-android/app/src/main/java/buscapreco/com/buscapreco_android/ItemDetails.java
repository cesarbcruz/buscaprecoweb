package buscapreco.com.buscapreco_android;

/**
 * Created by cesar on 20/11/16.
 */
public class ItemDetails {


    private String name;
    private String itemDescription;
    private double preco;
    private int qtdelojas;
    private int imageNumber;

    public int getQtdelojas() { return qtdelojas;}

    public void setQtdelojas(int qtdelojas) {this.qtdelojas = qtdelojas;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {this.itemDescription = itemDescription;}

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

}
