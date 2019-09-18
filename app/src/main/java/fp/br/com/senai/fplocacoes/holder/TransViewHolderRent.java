package fp.br.com.senai.fplocacoes.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fp.br.com.senai.fplocacoes.MainActivityAluga;
import fp.br.com.senai.fplocacoes.R;
import fp.br.com.senai.fplocacoes.adapter.TransAdapterRent;
import fp.br.com.senai.fplocacoes.adapter.TransRecyclerAdpterRent;
import fp.br.com.senai.fplocacoes.models.TransporteRent;

public class TransViewHolderRent extends RecyclerView.ViewHolder{

    private final TransRecyclerAdpterRent adapter;
    private TextView campoDtAluga;
    private TextView campoDtDevolve;
    private TextView campoPassa;
    private TextView campoOrigem;
    private TextView campoDestino;
    private Long rentId;


    public TransViewHolderRent(View itemView, TransRecyclerAdpterRent adapter) {
        super(itemView);
        this.adapter = adapter;

        campoDtAluga = itemView.findViewById(R.id.item_dtAluga);
        campoDtDevolve = itemView.findViewById(R.id.item_dtDevolu);
        campoPassa = itemView.findViewById(R.id.item_pass);
        campoOrigem = itemView.findViewById(R.id.item_origem);
        campoDestino = itemView.findViewById(R.id.item_destino);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Activity context = (Activity) view.getContext();
                final Intent intent = new Intent(context, MainActivityAluga.class);
                intent.putExtra("transpId", rentId);
                context.startActivityForResult(intent, 1);
            }
        });
    }

    public void preencherRent(TransporteRent transpRent){
        rentId = transpRent.getIdAlg();
        campoDtAluga.setText(transpRent.getDtAlugacao());
        campoDtDevolve.setText(transpRent.getDtDevolucao());
        campoPassa.setText(transpRent.getPassageiros());
        campoOrigem.setText(transpRent.getOrigem());
        campoDestino.setText(transpRent.getDestino());
    }
}
