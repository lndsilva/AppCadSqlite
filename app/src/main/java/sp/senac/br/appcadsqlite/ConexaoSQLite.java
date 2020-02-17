package sp.senac.br.appcadsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_CLIENTE = "dbClientes.sql";


    private static final String TABELA_CLIENTES = "tbClientes";

    private static final String COLUNA_CODIGO = "codigo";
    private static final String COLUNA_NOME_COMPLETO = "nome";
    private static final String COLUNA_TELEFONE = "telefone";
    private static final String COLUNA_EMAIL = "email";


    public ConexaoSQLite(@Nullable Context context) {
        super(context, BANCO_CLIENTE, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CRIA_TABELA = "CREATE TABLE " + TABELA_CLIENTES + "(" +
                COLUNA_CODIGO + " INTEGER PRIMARY KEY," +
                COLUNA_NOME_COMPLETO + " TEXT," +
                COLUNA_TELEFONE + " TEXT," +
                COLUNA_EMAIL + " TEXT)";

        db.execSQL(CRIA_TABELA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void adicionarClientes(Clientes clientes) {

        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_COMPLETO, clientes.getNome());
        values.put(COLUNA_TELEFONE, clientes.getTelefone());
        values.put(COLUNA_EMAIL, clientes.getEmail());

        db.insert(TABELA_CLIENTES, null, values);
        db.close();
    }

    public void excluirClientes(Clientes clientes) {

        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABELA_CLIENTES, COLUNA_CODIGO + " = ?", new String[]{String.valueOf(clientes.getCodigo())});
        db.close();

    }

    public Clientes buscarClientes(int codigo) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABELA_CLIENTES, new String[]{COLUNA_CODIGO, COLUNA_NOME_COMPLETO, COLUNA_TELEFONE, COLUNA_EMAIL},
                COLUNA_CODIGO + "= ?", new String[]{String.valueOf(codigo)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Clientes clientes = new Clientes(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return clientes;

    }

    public void atualizarClientes(Clientes clientes) {

        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_COMPLETO, clientes.getNome());
        values.put(COLUNA_TELEFONE, clientes.getTelefone());
        values.put(COLUNA_EMAIL, clientes.getEmail());

        db.update(TABELA_CLIENTES, values, COLUNA_CODIGO + " = ? ", new String[]{String.valueOf(clientes.getCodigo())});
        db.close();

    }

    public List<Clientes> listarTodosClientes() {

        List<Clientes> listarClientes = new ArrayList<Clientes>();

        String query = "SELECT * FROM " + TABELA_CLIENTES;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Clientes clientes = new Clientes();
                clientes.setCodigo(Integer.parseInt(cursor.getString(0)));
                clientes.setNome(cursor.getString(1));
                clientes.setTelefone(cursor.getString(2));
                clientes.setEmail(cursor.getString(3));

                listarClientes.add(clientes);

            } while (cursor.moveToFirst());
        }


        return listarClientes;
    }
}
