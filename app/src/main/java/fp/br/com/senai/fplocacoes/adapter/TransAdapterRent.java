package fp.br.com.senai.fplocacoes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fp.br.com.senai.fplocacoes.R;
import fp.br.com.senai.fplocacoes.models.TransporteRent;

public class TransAdapterRent extends BaseAdapter {

    private final List<TransporteRent> transRent;
    private final Context context;

    public TransAdapterRent(Context context, List<TransporteRent> transRent){
        this.transRent = transRent;
        this.context = context;
    }

    @Override
    public int getCount(){
        return transRent.size();
    }

    @Override
    public Object getItem(int position){
        return transRent.get(position);
    }

    @Override
    public long getItemId(int position){
        return transRent.get(position).getIdAlg();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TransporteRent transporte = transRent.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_principal_rent, null);

        TextView campoDtAluga = view.findViewById(R.id.item_dtAluga);
        campoDtAluga.setText(transporte.getDtAlugacao());

        TextView campoDtDevolve = view.findViewById(R.id.item_dtDevolu);
        campoDtDevolve.setText(transporte.getDtDevolucao());

        TextView campoPassagem = view.findViewById(R.id.item_pass);
        campoPassagem.setText(transporte.getPassageiros());

        TextView campoOrigem = view.findViewById(R.id.item_origem);
        campoOrigem.setText(transporte.getOrigem());

        TextView campoDestino = view.findViewById(R.id.item_destino);
        campoDestino.setText(transporte.getDestino());



        return view;
    }
}
