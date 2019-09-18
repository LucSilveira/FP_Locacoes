package fp.br.com.senai.fplocacoes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import fp.br.com.senai.fplocacoes.adapter.TransAdapter;
import fp.br.com.senai.fplocacoes.models.TranspDao;
import fp.br.com.senai.fplocacoes.models.Transporte;

public class MainActivity2 extends AppCompatActivity {

    private ListView listaTransportes2;
    private Button add2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listaTransportes2 = findViewById(R.id.listcars2Id);

        listaTransportes2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                Transporte transporte = (Transporte) listaTransportes2.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity2.this, MainActivityCars.class);
                intent.putExtra("transporte", transporte);
                startActivity(intent);
            }
        });

        carregarLista2();

        add2 = findViewById(R.id.btnAdd2);
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivityCars.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listaTransportes2);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Transporte transporte = (Transporte) listaTransportes2.getItemAtPosition(info.position);

        MenuItem deletar = menu.add("deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Transporte transporte = (Transporte) listaTransportes2.getItemAtPosition(info.position);
                TranspDao dao = new TranspDao(MainActivity2.this);
                dao.deletar(transporte);
                dao.close();
                carregarLista2();
                return false;
            }
        });

    }

    public void carregarLista2(){
        TranspDao dao = new TranspDao(this);
        List<Transporte> trens = dao.buscaTransporte();
        TransAdapter adapter = new TransAdapter(MainActivity2.this, trens);
        listaTransportes2.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista2();
    }

}
