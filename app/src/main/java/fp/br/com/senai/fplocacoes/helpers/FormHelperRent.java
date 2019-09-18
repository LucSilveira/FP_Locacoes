package fp.br.com.senai.fplocacoes.helpers;

import android.widget.EditText;
import android.widget.ImageView;

import fp.br.com.senai.fplocacoes.MainActivityAluga;
import fp.br.com.senai.fplocacoes.R;
import fp.br.com.senai.fplocacoes.models.TransporteRent;

public class FormHelperRent {

    public EditText dtAlugacao;
    public EditText dtDevolucao;
    public EditText passageiros;
    public EditText origem;
    public EditText destino;
    private ImageView img;
    public TransporteRent transRent;

    public void setImgSelect(ImageView img){
        this.img = img;
    }

    public ImageView getImgSelect() {
        return img;
    }

    public FormHelperRent(MainActivityAluga forms){
        dtAlugacao = forms.findViewById(R.id.setAluga);
        dtDevolucao = forms.findViewById(R.id.setDevolu);
        passageiros = forms.findViewById(R.id.setPassageiros);
        origem = forms.findViewById(R.id.setOrigem);
        destino = forms.findViewById(R.id.setDestino);
        img = forms.findViewById(R.id.imgDoCar);
        transRent = new TransporteRent();
    }

    public TransporteRent pegaTransporteAlugado(){
        transRent.setDtAlugacao(dtAlugacao.getText().toString());
        transRent.setDtDevolucao(dtDevolucao.getText().toString());
        transRent.setPassageiros(passageiros.getText().toString());
        transRent.setOrigem(origem.getText().toString());
        transRent.setDestino(destino.getText().toString());
        transRent.setImgCarro(img.getTag().toString());
        return transRent;
    }

    public void preencherFormu(TransporteRent transportRent){
        dtAlugacao.setText(transportRent.getDtAlugacao());
        dtDevolucao.setText(transportRent.getDtDevolucao());
        passageiros.setText(transportRent.getPassageiros());
        origem.setText(transportRent.getOrigem());
        destino.setText(transportRent.getDestino());
        img.setTag(transportRent.getImgCarro());
        this.transRent = transportRent;
    }
}
