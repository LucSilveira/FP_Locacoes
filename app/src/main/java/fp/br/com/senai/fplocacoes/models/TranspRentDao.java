package fp.br.com.senai.fplocacoes.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 46923597811 on 04/05/2018.
 */

public class TranspRentDao extends SQLiteOpenHelper{

    public TranspRentDao(Context context){ super(context, "TransportesLS", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alugados(idAlg INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "dtAluga TEXT, dtDevolve TEXT, psgs TEXT, origem TEXT, destino TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS TransportesLS";
        db.execSQL(sql);
    }

    public void inserirAlg(TransporteRent transpRent){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(transpRent);
        dados.put("caminhoDaImagem", transpRent.getImgCarro());
        db.insert("Veiculo", null, dados);
    }

    @NonNull
    private ContentValues getContentValues(TransporteRent transRent){
        ContentValues dados = new ContentValues();
        dados.put("dtAluga", transRent.getDtAlugacao());
        dados.put("dtDevolve", transRent.getDtDevolucao());
        dados.put("psgs", transRent.getPassageiros());
        dados.put("origem", transRent.getOrigem());
        dados.put("destino", transRent.getDestino());
        return dados;
    }

    public List<TransporteRent> buscaTransporteAlugado(){
        String sql2 = "SELECT * FROM Alugados";
        SQLiteDatabase Db = getReadableDatabase();
        Cursor c = Db.rawQuery(sql2, null);
        List<TransporteRent> transRent = new ArrayList<>();
        while(c.moveToNext()){
            TransporteRent tranRent = new TransporteRent();
            tranRent.setIdAlg(c.getLong(c.getColumnIndex("idAlg")));
            tranRent.setDtAlugacao(c.getString(c.getColumnIndex("dtAluga")));
            tranRent.setDtDevolucao(c.getString(c.getColumnIndex("dtDevolve")));
            tranRent.setPassageiros(c.getString(c.getColumnIndex("psgs")));
            tranRent.setOrigem(c.getString(c.getColumnIndex("origem")));
            tranRent.setDestino(c.getString(c.getColumnIndex("destino")));
            transRent.add(tranRent);
        }
        c.close();
        return transRent;
    }


}
