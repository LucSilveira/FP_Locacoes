package fp.br.com.senai.fplocacoes;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

import fp.br.com.senai.fplocacoes.helpers.FormHelper;
import fp.br.com.senai.fplocacoes.models.TranspDao;
import fp.br.com.senai.fplocacoes.models.TranspRentDao;
import fp.br.com.senai.fplocacoes.models.Transporte;

public class MainActivityCars extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{


    public static final int GALERIA_CODE = 1;
    private final String ZERO = "0";
    private final String BARRA = "/";
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_YEAR);
    final int Ano = c.get(Calendar.YEAR);
    EditText ano;

    private TranspDao dao;
    private Button cadast;
    private ImageView carrinho;
    private FormHelper helper;
    private EditText modelo;
    private EditText placa;
    private EditText marca;
    private EditText cor;
    private EditText type;
    private EditText lugars;
    private ImageView Img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cars);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ano = (EditText) findViewById(R.id.setAno);

        modelo = findViewById(R.id.setModelo);
        placa = findViewById(R.id.setPlaca);
        marca = findViewById(R.id.setMarca);
        cor = findViewById(R.id.setCor);
        type = findViewById(R.id.setTipo);
        lugars = findViewById(R.id.setLugares);
        Img = findViewById(R.id.imgDoCar);

        helper = new FormHelper(this);
        cadast = findViewById(R.id.btnEnviar);
        dao = new TranspDao(this);

        carrinho = findViewById(R.id.imgDoCar);
        //carrinho.setOnClickListener(this);
        carrinho = helper.getImgSelect();

        final FormHelper helper = new FormHelper(MainActivityCars.this);

        Bundle extras = getIntent().getExtras();
        Long transpId = (extras != null)? extras.getLong("transpId"): null;

        /*TranspDao transDao = new TranspDao(MainActivityCars.this);
        Transporte transpt = transDao.buscaTransporteId(transpId);
        String modelo = transpt.getModelo();
        String placa = transpt.getPlaca();
        String marca = transpt.getMarca();
        String ano = transpt.getAno();
        String cor = transpt.getCor();
        String tipo = transpt.getTipoCarro();
        String lugar = transpt.getLugares();
        String carImg = transpt.getImgCarro();

        Bitmap imgRetorna = (BitmapFactory.decodeFile(carImg));
        carrinho.setImageBitmap(imgRetorna);*/

        if(transpId == null){
            Transporte trans = new Transporte();
        }else {
            Transporte trans = dao.localizar(transpId);
            helper.preencherForm(trans);
        }

        carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALERIA_CODE);
            }
        });

        cadast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modelo.getText().length() == 0 && placa.getText().length() == 0 && marca.getText().length() == 0 && ano.getText().length() == 0
                        && cor.getText().length() == 0 && lugars.getText().length() == 0 && type.getText().length() == 0 && Img.getTag() == null){
                    Toast.makeText(getApplicationContext(), "campos vazios!", Toast.LENGTH_LONG).show();

                }else if(modelo.getText().length() == 0 && placa.getText().length() != 0 && marca.getText().length() != 0 && ano.getText().length() != 0
                        && cor.getText().length() != 0 && lugars.getText().length() != 0 && type.getText().length() != 0 && Img.getTag() != null){
                    Toast.makeText(getApplicationContext(), "insira o modelo do veiculo!", Toast.LENGTH_LONG).show();

                }else if(modelo.getText().length() != 0 && placa.getText().length() == 0 && marca.getText().length() != 0 && ano.getText().length() != 0
                        && cor.getText().length() != 0 && lugars.getText().length() != 0 && type.getText().length() != 0 && Img.getTag() != null){
                    Toast.makeText(getApplicationContext(), "insira a placa do veiculo", Toast.LENGTH_LONG).show();

                }else if(modelo.getText().length() != 0 && placa.getText().length() != 0 && marca.getText().length() == 0 && ano.getText().length() != 0
                        && cor.getText().length() != 0 && lugars.getText().length() != 0 && type.getText().length() != 0 && Img.getTag() != null){
                    Toast.makeText(getApplicationContext(), "insira a marca do veiculo", Toast.LENGTH_LONG).show();

                }else if(modelo.getText().length() != 0 && placa.getText().length() != 0 && marca.getText().length() != 0 && ano.getText().length() == 0
                        && cor.getText().length() != 0 && lugars.getText().length() != 0 && type.getText().length() != 0 && Img.getTag() != null){
                    Toast.makeText(getApplicationContext(), "insira o ano do veiculo", Toast.LENGTH_LONG).show();

                }else if(modelo.getText().length() != 0 && placa.getText().length() != 0 && marca.getText().length() != 0 && ano.getText().length() != 0
                        && cor.getText().length() == 0 && lugars.getText().length() != 0 && type.getText().length() != 0 && Img.getTag() != null){
                    Toast.makeText(getApplicationContext(), "insira a cor do veiculo", Toast.LENGTH_LONG).show();

                }else if(modelo.getText().length() != 0 && placa.getText().length() != 0 && marca.getText().length() != 0 && ano.getText().length() != 0
                        && cor.getText().length() != 0 && lugars.getText().length() == 0 && type.getText().length() != 0 && Img.getTag() != null){
                    Toast.makeText(getApplicationContext(), "insira a quantidade de lugares ocupados", Toast.LENGTH_LONG).show();

                }else if(modelo.getText().length() != 0 && placa.getText().length() != 0 && marca.getText().length() != 0 && ano.getText().length() != 0
                        && cor.getText().length() != 0 && lugars.getText().length() != 0 && type.getText().length() != 0 && Img.getTag() == null){
                    Toast.makeText(getApplicationContext(), "insira a imagem do carro", Toast.LENGTH_LONG).show();

                }else{
                    Transporte transporte = helper.pegaTransporte();
                    TranspDao dao = new TranspDao(MainActivityCars.this);
                    dao.inserir(transporte);
                    dao.close();
                    Intent inte = new Intent(MainActivityCars.this, MainActivityListCars.class);
                    startActivity(inte);
                    finish();
                }


            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setAno:
                obtenerFecha();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED);

        } else{
            // A permissão foi negada. Precisa ver o que deve ser desabilitado
        }
        return;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == GALERIA_CODE){
            Uri uri = data.getData();
            String[] diretorios = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(uri, diretorios, null,null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(diretorios[0]);
            String caminhoDaImagem = c.getString(columnIndex);
            c.close();
            Bitmap imagemRetornada= BitmapFactory.decodeFile(caminhoDaImagem);
            carrinho.setImageBitmap(imagemRetornada);
            carrinho.setTag(caminhoDaImagem);

        }
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
        getMenuInflater().inflate(R.menu.main_activity_cars, menu);
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

    private void obtenerFecha(){

        DatePickerDialog rcFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String diaFt = (dayOfMonth < 10)? ZERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFt = (month < 10)? ZERO + String.valueOf(month):String.valueOf(month);
                ano.setText(diaFt + BARRA + mesFt + BARRA + year);
            }
        }, Ano, mes, dia);

        rcFecha.show();

    }

}
