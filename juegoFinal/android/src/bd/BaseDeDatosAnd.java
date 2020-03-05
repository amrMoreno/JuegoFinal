package bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BaseDeDatosAnd implements BaseDeDatos {
    private BaseDeDatosOpenHelper openHelper;

    public BaseDeDatosAnd(Context c) {
        openHelper = new BaseDeDatosOpenHelper(c, 1);
    }

    @Override
    public int cargar() {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        Cursor c = db.query("movimientos",
                null, null, null,
                null, null, null);
        if (c.moveToFirst()) {//False si no hay ninguna fila, true si hay una
            //Caso en que ya haya una fila
            return c.getInt(c.getColumnIndex("pasos"));
        } else {
            //Si no hay puntuaciones guardadas, empiezo desde 0 puntos
            return 0;
        }
    }

    @Override
    public void guardar(int nuevaPuntuacion) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        Cursor c = db.query("movimientos",
                null, null, null,
                null, null, null);

        ContentValues cv = new ContentValues();
        cv.put("pasos", nuevaPuntuacion);

        if (c.moveToFirst()) { //False si no hay ninguna fila, true si hay una
            //Caso en que ya haya una fila
            //Siempre voy a tener solo una fila, por tanto, cuando actualizo
            //puedo dejar whereClause y whereArgs a null. Me va a actualizar
            //todas las filas, es decir, la única que existe.
            db.update("movimientos", cv, null,
                    null);
        } else {
            //Caso en que la tabla esté vacía
            db.insert("movimientos", null, cv);
        }
        c.close();
        db.close();
    }}
