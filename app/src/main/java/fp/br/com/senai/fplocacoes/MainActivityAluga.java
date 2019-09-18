package fp.br.com.senai.fplocacoes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import fp.br.com.senai.fplocacoes.helpers.FormHelperRent;
import fp.br.com.senai.fplocacoes.models.TranspDao;
import fp.br.com.senai.fplocacoes.models.TranspRentDao;
import fp.br.com.senai.fplocacoes.models.Transporte;
import fp.br.com.senai.fplocacoes.models.TransporteRent;

public class MainActivityAluga extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TranspRentDao dao;
    private Button alugar;
    private FormHelperRent helperRent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_aluga);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helperRent = new FormHelperRent(this);
        alugar = findViewById(R.id.btnEnviar);
        dao = new TranspRentDao(this);

        final FormHelperRent help = new FormHelperRent(this);

        Bundle extras = getIntent().getExtras();
        Long transRentId = (extras != null)? extras.getLong("transpRentId"): null;

        if(transRentId == null){
            Transporte trans = new Transporte();
        }else{
            Intent inte = new Intent(MainActivityAluga.this, MainActivityListAlugar.class);
            startActivity(inte);
        }


        alugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransporteRent trnsRent = helperRent.pegaTransporteAlugado();
                TranspRentDao dao = new TranspRentDao(MainActivityAluga.this);
                Intent inte = new Intent(MainActivityAluga.this, MainActivityRecibo.class);
                startActivity(inte);
                if(trnsRent.getIdAlg() == null){
                    dao.inserir(trnsRent);
                }
                dao.close();
                finish();
            }
        });
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_aluga, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
