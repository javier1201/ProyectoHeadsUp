package com.example.Charadas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String WORDS_TABLE = "WORDS";
    public static final String COLUMN_ID = "_ID";

    public static final String[] categories = new String[] {"animals", "places", "sports", "films", "foods", "jobs", "kings", "publics", "things"};

    public static final String[][][] content = new String[][][] {
            {
                    {"Abejas", "Camello", "Pavo real", "Leon Marino"},
                    {"Ratón ciego", "Hamster", "Murciélagos", "Tiburón"},
                    {"Lagartos", "Mono de circo", "Lunar", "Diente de león"},
            },
            {
                    {"Bosque", "Administración", "Supermercado", "Escuela"},
                    {"Hospital", "Parque acuático", "Cuarto de baño", "Taller"},
                    {"Casa", "Experimento", "Montaña", "Aeropuerto"},
            },
            {
                    {"Barco", "Levantamiento de pesas", "Aeróbicos", "Penalización"},
                    {"Ping Pong", "Archery", "Breast Press", "Mano Bola"},
                    {"Elevación de potencia", "Aplicación de la ley", "Correr", "Volar en globo"},
            },
            {
                    {"Código Da Vinci", "Kaiser", "Ojo a ojo", "No camine en mis zapatos"},
                    {"Moto", "Boxeo", "Paseo en globo", "Doble ping"},
                    {"Juegos Paralímpicos", "Ronda 10", "Squash", "Esquí de nieve"},
            },
            {
                    {"Tomate verde", "Chips de pimiento", "Ensalada", "Chips"},
                    {"Pizza", "Ensalada de Coditos", "Aloe Vera", "Te"},
                    {"Huevo cocido", "Pollo al horno", "Chuleta", "Mango"},
            },
            {
                    {"Chef", "Electricista", "Neurocirujano", "Médico"},
                    {"Tutor", "Diseñador de ropa", "Diseñador de gafas", "Diseñador de interiores"},
                    {"Comerciante", "reportero", "alfarero", "técnico de quirófano"},
            },
            {
                    {"Jennifer Lopez", "Rihanna ", "Selena Gomez", "Lionel Messi"},
                    {"Taylor Switf", "Nicki Minaj", "Barack Obama", "Cristiano Ronaldo"},
                    {"Ed Sheeran", "Eminem", "Demi Lovato", "Johnny Depp"},
            },
            {
                    {"Triste", "Tablero", "Llave inglesa", "Bebida"},
                    {"Sorprendido", "Quiebra", "Cuello de camisa", "Vientre"},
                    {"Crisis de déficit hídrico", "Educación a distancia", "Tormento de conciencia", "Alianza"},
            },
            {
                    {"celular", "mapa", "manguera", "manguera"},
                    {"Pluma", "Panal", "computadora", "Goldon lleno de flores"},
                    {"Marco de fotos", "Llave francesa", "Grabadora de voz", "Silla inflable"},
            },
    };

    private static final String DB_NAME = "palabras.db";

    private static final int DB_VERSION = 1;

    private static final String DB_CREATE =
            "CREATE TABLE " + WORDS_TABLE + " ( " +
                    COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, WORD TEXT, CATEGORY TEXT, SCORE INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);

        int categoryIndex = 0;
        int score = 2;

        for(String category : categories) {
            score = 2;
            for(String[] scoredCategory : content[categoryIndex]) {

                for(String word : scoredCategory) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("WORD", word);
                    contentValues.put("CATEGORY", category);
                    contentValues.put("SCORE", score);
                    db.insert(DatabaseHelper.WORDS_TABLE, null, contentValues);
                }
                score += 2;

            }
            categoryIndex++;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
