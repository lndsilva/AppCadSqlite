package sp.senac.br.appcadsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editCodigo, editNomeCompleto, editTelefone, editEmail;

    Button btnLimpar, btnSalvar, btnExcluir;

    ListView listViewClientes;

    ConexaoSQLite db = new ConexaoSQLite(this);

    ArrayAdapter<String> adaptador;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editCodigo = findViewById(R.id.editCodigo);
        editNomeCompleto = findViewById(R.id.editNomeCompleto);
        editTelefone = findViewById(R.id.editTelefone);
        editEmail = findViewById(R.id.editEmail);

        btnLimpar = findViewById(R.id.btnLimpar);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnLimpar = findViewById(R.id.btnSalvar);

        listViewClientes = findViewById(R.id.lstClientes);

        listarClientes();

        ArrayAdapter<String> adaptador;
        ArrayList<String> arrayList;


        db.adicionarClientes(new Clientes("Senac Largo Treze", "37373900", "sac@sp.senac.br"));
        db.adicionarClientes(new Clientes("João Antonio", "39696900", "jantonio@sp.senac.br"));
        db.adicionarClientes(new Clientes("Maria do Carmo", "55228877", "mariacarmo@sp.senac.br"));
        db.adicionarClientes(new Clientes("Zeca", "88996633", "zeca@sp.senac.br"));

        Toast.makeText(getApplicationContext(), "Cliente Adicionado!!!", Toast.LENGTH_SHORT).show();

       /* Clientes clientes = new Clientes();
        clientes.setCodigo(9);
        db.excluirClientes(clientes);

        Toast.makeText(getApplicationContext(), "Cliente excluido!!!", Toast.LENGTH_SHORT).show();*/
/*
         Clientes clientes = db.buscarClientes(4);

        Log.d(" Cliente selecionado: ",
                " Código " + clientes.getCodigo() +
                        " Nome: " + clientes.getNome() +
                        " Telefone: " + clientes.getTelefone() +
                        " E-mail: " + clientes.getEmail());

        Toast.makeText(getApplicationContext(), "Cliente selecionado!!!", Toast.LENGTH_SHORT).show();*/

      /*  Clientes clientes = new Clientes();
        clientes.setCodigo(4);
        clientes.setNome("Maria do Carmo Domingues Silva");
        clientes.setTelefone("55228877");
        clientes.setEmail("maria.carmo@sp.senac.br");

        db.atualizarClientes(clientes);

        Toast.makeText(getApplicationContext(), "Cliente atualizado!!!", Toast.LENGTH_SHORT).show();*/
    }

    public void listarClientes() {

        List<Clientes> clientes = db.listarTodosClientes();

        arrayList = new ArrayList<String>();

        adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);

        listViewClientes.setAdapter(adaptador);

    }
}
