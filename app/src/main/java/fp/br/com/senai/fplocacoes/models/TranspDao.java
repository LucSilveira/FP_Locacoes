package fp.br.com.senai.fplocacoes.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fp.br.com.senai.fplocacoes.models.Transporte;

/**
 * Created by 46923597811 on 25/04/2018.
 */

public class TranspDao extends SQLiteOpenHelper{

    public TranspDao(Context context){ super(context, "TransportesLS", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Veiculo(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "modelo TEXT, placa TEXT, marca TEXT, ano TEXT, cor TEXT, tipoCarro TEXT, lugares TEXT, caminhoDaImagem TEXT)";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS TransportesLS";
        db.execSQL(sql);
    }

    public void inserir(Transporte transp){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(transp);
        dados.put("caminhoDaImagem", transp.getImgCarro());
        db.insert("Veiculo", null, dados);
    }

    @NonNull
    private ContentValues getContentValues(Transporte transp){
        ContentValues dados = new ContentValues();
        dados.put("modelo", transp.getModelo());
        dados.put("placa", transp.getPlaca());
        dados.put("marca", transp.getMarca());
        dados.put("ano", transp.getAno());
        dados.put("cor", transp.getCor());
        dados.put("tipoCarro", transp.getTipoCarro());
        dados.put("lugares", transp.getLugares());
        dados.put("caminhoDaImagem", transp.getImgCarro());
        return dados;
    }

    public List<Transporte> buscaTransporte(){
        String sql = "SELECT * FROM Veiculo";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Transporte> trans = new ArrayList<Transporte>();
        while(c.moveToNext()){
            Transporte tran = new Transporte();
            tran.setId(c.getLong(c.getColumnIndex("id")));
            tran.setModelo(c.getString(c.getColumnIndex("modelo")));
            tran.setPlaca(c.getString(c.getColumnIndex("placa")));
            tran.setMarca(c.getString(c.getColumnIndex("marca")));
            tran.setAno(c.getString(c.getColumnIndex("ano")));
            tran.setCor(c.getString(c.getColumnIndex("cor")));
            tran.setTipoCarro(c.getString(c.getColumnIndex("tipoCarro")));
            tran.setLugares(c.getString(c.getColumnIndex("lugares")));
            tran.setImgCarro(c.getString(c.getColumnIndex("caminhoDaImagem")));
            trans.add(tran);
        }
        c.close();
        return trans;
    }

    public Transporte localizar(Long transId){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM Veiculo WHERE id = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(transId)});
        c.moveToFirst();
        Transporte transRetornado = new Transporte();
        transRetornado.setId(c.getLong(c.getColumnIndex("id")));
        transRetornado.setModelo(c.getString(c.getColumnIndex("modelo")));
        transRetornado.setPlaca(c.getString(c.getColumnIndex("placa")));
        transRetornado.setMarca(c.getString(c.getColumnIndex("marca")));
        transRetornado.setAno(c.getString(c.getColumnIndex("ano")));
        transRetornado.setCor(c.getString(c.getColumnIndex("cor")));
        transRetornado.setTipoCarro(c.getString(c.getColumnIndex("tipoCarro")));
        transRetornado.setLugares(c.getString(c.getColumnIndex("lugares")));
        transRetornado.setImgCarro(c.getString(c.getColumnIndex("caminhoDaImagem")));
        db.close();
        return transRetornado;
    }

    public void deletar (Transporte trans){
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {String.valueOf(trans.getId())};
        db.delete("Veiculo", "id = ?", parametros);
    }

    public void alterar(Transporte trans){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(trans);
        String[] parametros = {trans.getId().toString()};
        db.update("Veiculo", dados, "id = ?", parametros);
    }

    public Transporte buscaTransporteId(Long transpId) {

        String sql = "SELECT * FROM Veiculo WHERE id like" + transpId + "";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Transporte> transId = new ArrayList<Transporte>();
        Transporte tranpt = new Transporte();

        while(c.moveToNext()){
            tranpt.setId(c.getLong(c.getColumnIndex("id")));
            tranpt.setModelo(c.getString(c.getColumnIndex("modelo")));
            tranpt.setPlaca(c.getString(c.getColumnIndex("placa")));
            tranpt.setMarca(c.getString(c.getColumnIndex("marca")));
            tranpt.setAno(c.getString(c.getColumnIndex("ano")));
            tranpt.setCor(c.getString(c.getColumnIndex("cor")));
            tranpt.setTipoCarro(c.getString(c.getColumnIndex("tipoCarro")));
            tranpt.setLugares(c.getString(c.getColumnIndex("lugares")));
            tranpt.setImgCarro(c.getString(c.getColumnIndex("caminhoDaImagem")));
            transId.add(tranpt);
        }
        c.close();
        return tranpt;
    }
}
