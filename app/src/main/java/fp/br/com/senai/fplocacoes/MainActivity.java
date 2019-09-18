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

public class MainActivity extends AppCompatActivity {

    private ListView listaTransportes;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaTransportes = findViewById(R.id.listcarsId);

        listaTransportes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                Transporte transporte = (Transporte) listaTransportes.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, MainActivityCars.class);
                intent.putExtra("transporte", transporte);
                startActivity(intent);
            }
        });

        carregarLista();

        add = findViewById(R.id.btnAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivityCars.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listaTransportes);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Transporte transporte = (Transporte) listaTransportes.getItemAtPosition(info.position);

        MenuItem deletar = menu.add("deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Transporte transporte = (Transporte) listaTransportes.getItemAtPosition(info.position);
                TranspDao dao = new TranspDao(MainActivity.this);
                dao.deletar(transporte);
                dao.close();
                carregarLista();
                return false;
            }
        });

    }

    public void carregarLista(){
        TranspDao dao = new TranspDao(this);
        List<Transporte> trens = dao.buscaTransporte();
        TransAdapter adapter = new TransAdapter(MainActivity.this, trens);
        listaTransportes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
