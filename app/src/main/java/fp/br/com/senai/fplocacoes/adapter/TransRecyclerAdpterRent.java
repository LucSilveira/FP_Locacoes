package fp.br.com.senai.fplocacoes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fp.br.com.senai.fplocacoes.R;
import fp.br.com.senai.fplocacoes.holder.TransViewHolderRent;
import fp.br.com.senai.fplocacoes.models.TransporteRent;

/**
 * Created by 46923597811 on 04/05/2018.
 */

public class TransRecyclerAdpterRent extends RecyclerView.Adapter{

    private final Context context;
    private final List<TransporteRent> trans;

    public TransRecyclerAdpterRent(Context context, List<TransporteRent> trans){
        this.context = context;
        this.trans = trans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.lista_principal_rent, parent, false);
        TransViewHolderRent holderRent = new TransViewHolderRent(view, this);
        return holderRent;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TransViewHolderRent meuHolder = (TransViewHolderRent) holder;
        TransporteRent tran = trans.get(position);
        meuHolder.preencherRent(tran);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
