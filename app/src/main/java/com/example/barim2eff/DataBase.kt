package com.example.barim2eff

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Vent(val id: Int, val numV: Int, val tc: String, val qte: Int)

class DataBase(context: Context) : SQLiteOpenHelper(context, "DB", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS tbl (" +
                    "id INTEGER PRIMARY KEY, " +
                    "numV INTEGER, " +
                    "tc TEXT, " +
                    "qte INTEGER" +
                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS tbl")
        onCreate(db)
    }

    fun rechercher(numV: String): Vent? {
        val db = readableDatabase
        val cursor: Cursor = db.query(
            "tbl", null, "numV=?", arrayOf(numV), null, null, null
        )
        return if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val numV = cursor.getInt(cursor.getColumnIndexOrThrow("numV"))
            val tc = cursor.getString(cursor.getColumnIndexOrThrow("tc"))
            val qte = cursor.getInt(cursor.getColumnIndexOrThrow("qte"))
            Vent(id, numV, tc, qte)
        } else {
            null
        }.also {
            cursor.close()
            db.close()
        }
    }

    fun ajouterVent( numV: Int, tc: String, qte: Int): Long {
        val db = writableDatabase
        val content = ContentValues().apply {
            put("numV", numV)
            put("tc", tc)
            put("qte", qte)
        }
        val result = db.insert("tbl", null, content)
        db.close()
        return result
    }
}
