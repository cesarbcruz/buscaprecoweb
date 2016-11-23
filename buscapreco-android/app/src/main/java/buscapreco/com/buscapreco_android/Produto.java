package buscapreco.com.buscapreco_android;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cesar on 20/11/16.
 */
public class Produto implements Parcelable{


    private String descricao;
    private String detalhes;
    private List<Loja> lojas = new ArrayList<Loja>();
    private String imagem;

    public Produto(){
        imagem="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";
    }

    protected Produto(Parcel in) {
        descricao = in.readString();
        detalhes = in.readString();
        imagem = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(descricao);
        dest.writeString(detalhes);
        dest.writeString(imagem);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public List<Loja> getLojas() {
        return lojas;
    }

    public void setLojas(List<Loja> lojas) {
        this.lojas = lojas;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public double getMenorPreco() {
        if(lojas==null || lojas.isEmpty()){
            return 0D;
        }
        Collections.sort(lojas);
        return lojas.get(0).getPreco();
    }

}
