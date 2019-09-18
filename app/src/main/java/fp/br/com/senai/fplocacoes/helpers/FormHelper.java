package fp.br.com.senai.fplocacoes.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import fp.br.com.senai.fplocacoes.MainActivityCars;
import fp.br.com.senai.fplocacoes.R;
import fp.br.com.senai.fplocacoes.models.Transporte;

public class FormHelper {

    public EditText modelo;
    public EditText placa;
    public EditText marca;
    public EditText ano;
    public EditText cor;
    public EditText tipoCarro;
    public EditText lugares;
    public Transporte transp;
    private ImageView imgSelect;

    public void setImgSelect(ImageView imgSelect){
        this.imgSelect = imgSelect;
    }

    public ImageView getImgSelect() {
        return imgSelect;
    }

    public FormHelper(MainActivityCars form){
        modelo = form.findViewById(R.id.setModelo);
        placa = form.findViewById(R.id.setPlaca);
        marca = form.findViewById(R.id.setMarca);
        ano = form.findViewById(R.id.setAno);
        cor = form.findViewById(R.id.setCor);
        tipoCarro = form.findViewById(R.id.setTipo);
        lugares = form.findViewById(R.id.setLugares);
        imgSelect = form.findViewById(R.id.imgDoCar);
        transp = new Transporte();
    }

    public Transporte pegaTransporte(){
        transp.setModelo(modelo.getText().toString());
        transp.setPlaca(placa.getText().toString());
        transp.setMarca(marca.getText().toString());
        transp.setAno(ano.getText().toString());
        transp.setCor(cor.getText().toString());
        transp.setTipoCarro(tipoCarro.getText().toString());
        transp.setLugares(lugares.getText().toString());
        transp.setImgCarro(imgSelect.getTag().toString());
        return transp;
    }

    public void preencherForm (Transporte transp){
    modelo.setText(transp.getModelo());
    placa.setText(transp.getPlaca());
    marca.setText(transp.getMarca());
    ano.setText(transp.getAno());
    cor.setText(transp.getCor());
    tipoCarro.setText(transp.getTipoCarro());
    lugares.setText(transp.getLugares());

        String caminhoImg = transp.getImgCarro();
        Bitmap imgCarregada = BitmapFactory.decodeFile(caminhoImg);
        imgSelect.setImageBitmap(imgCarregada);
    this.transp = transp;
    }

}
