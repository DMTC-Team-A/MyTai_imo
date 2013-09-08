package com.example.mytai_imo.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 体重の記録を管理するデータベース。
 */
public class WeightDatabase {
    static final String DATABASE_NAME = "weight.db";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "weight";
    static final String COL_Date = "date";
    static final String COL_Weight = "weight";
    static final String[] COLS = new String[]{ COL_Date, COL_Weight };
    private static WeightDatabase ourInstance = new WeightDatabase();
    private SQLiteOpenHelper helper;

    private WeightDatabase() {
    }

    public static WeightDatabase getInstance() {
        return ourInstance;
    }
    
    public void ResetDatabase() {
    	execTransaction(new ITransaction<Integer>() {
			@Override
			public Integer Action(SQLiteDatabase db) {
				return db.delete(TABLE_NAME, null, null);
			}
		});
    }
    
    public void InputTestData() {
    	double[] doubles = new double[] { 0.1,-0.1,0.1,-0.1,0.1,-0.1,0.1,-0.1,0.1,-0.1,0.1,-0.1 };
    	
    	Weight[] weights = new Weight[doubles.length];
    	for(int i = 0; i < doubles.length; i++) {
    		Calendar today = new GregorianCalendar();
    		today.add(Calendar.DATE, i);
    		weights[i] = new Weight(doubles[i], today.getTimeInMillis());
    	}
    	
    	for(Weight weight : weights) {
    		this.InsertOrUpdateWeight(weight);
    	}
    }

    private static ContentValues toContentValues(final Weight weight) {
        ContentValues values = new ContentValues(2);
        values.put(COL_Date, weight.getDateEpoch());
        values.put(COL_Weight, weight.getWeight());
        return values;
    }

    private static Weight getRowAsWeight(Cursor cursor) {
        return new Weight(cursor.getDouble(1), cursor.getLong(0));
    }

    public static void Initialize(Context context) {
    	ourInstance = new WeightDatabase(context);
    }
    
    private WeightDatabase(Context context) {
    	helper = new WeightDatabaseHelper(context);
    }

    /**
     * デリゲートを渡してデータベースを変更する。
     *
     * @param transaction 実行するトランザクション処理のデリゲート
     */
    private <T> T execTransaction(final ITransaction<T> transaction) {
        T result = null;
        final SQLiteDatabase db = helper.getWritableDatabase();

        db.beginTransaction();
        try {
            result = transaction.Action(db);
        }
        catch (SQLiteConstraintException exception) {
            //TODO エラーが起こった時の処理を追加
            exception.getMessage();
        }
        finally {
            db.endTransaction();
        }
        helper.close();

        return result;
    }

    private <T> T readTransaction(final ITransaction<T> transaction) {
        T result = null;
        final SQLiteDatabase db = helper.getReadableDatabase();

        result = transaction.Action(db);

        helper.close();

        return result;
    }

    /**
     * 体重を渡して、それをデータベースへ挿入する。既に存在する時刻の場合は上書きする。
     *
     * @param weight 体重
     */
    public long InsertOrUpdateWeight(final Weight weight) {
        return execTransaction(new ITransaction<Long>() {
            @Override
            public Long Action(SQLiteDatabase db) {
                //return db.insertWithOnConflict(TABLE_NAME, null, toContentValues(weight), SQLiteDatabase.CONFLICT_REPLACE);
                return db.insert(TABLE_NAME, null, toContentValues(weight));
            }
        });
    }

    /**
     * N回前に記録した体重のデータを取得する。
     * @param n
     * @return N回前に記録した体重のデータ
     */
    public Float getWeightDataBefore(final int n) {
        return readTransaction(new ITransaction<Float>() {
            @Override
            public Float Action(SQLiteDatabase db) {
                Cursor cursor = db.query(TABLE_NAME, COLS, null, null, null, null, COL_Date + " desc");

                cursor.moveToFirst();
                cursor.moveToPosition(n);
                return cursor.getFloat(1);
            }
        });
    }

    public ArrayList<Weight> getWeightDataRange(final Calendar start, final Calendar end) {
        return readTransaction(new ITransaction<ArrayList<Weight>>() {
            @Override
            public ArrayList<Weight> Action(SQLiteDatabase db) {
                Cursor cursor = db.query(TABLE_NAME, COLS, "? between ? and ?",
                        new String[]{ COL_Date, String.valueOf(App.getMinimumTime(start)), String.valueOf(App.getMaximumTime(end)) }, null, null, null);

                return toArrayListFromCursor(cursor);
            }
        });
    }

    public ArrayList<Weight> getAllWeightData() {
        return readTransaction(new ITransaction<ArrayList<Weight>>() {
            @Override
            public ArrayList<Weight> Action(SQLiteDatabase db) {
                Cursor cursor = db.query(TABLE_NAME, COLS, null, null, null, null, null);
                return toArrayListFromCursor(cursor);
            }
        });
    }

    public static ArrayList<Weight> toArrayListFromCursor(Cursor cursor) {
        ArrayList<Weight> result = new ArrayList<Weight>();

        if(cursor.moveToFirst()) {
            do {
                result.add(toWeight(cursor));
            } while(cursor.moveToNext());
        }

        return result;
    }

    public static Weight toWeight(Cursor cursor) {
        return new Weight(cursor.getFloat(1), cursor.getLong(0));
    }

    /**
     * データベースを操作するデリゲート。
     */
    private interface ITransaction<T> {
        public T Action(SQLiteDatabase db);
    }

    private class WeightDatabaseHelper extends SQLiteOpenHelper {
        public WeightDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION,
                    new DatabaseErrorHandler() {
                        @Override
                        public void onCorruption(SQLiteDatabase db) {
                            //TODO デフォルトのエラー処理
                            db.toString();
                        }
                    });
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    "create table " + TABLE_NAME + " ( "
                            + COL_Date + " integer primary key, "
                            + COL_Weight + " real not null"
                            + " );"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
