package com.mygdx.game

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import java.util.stream.DoubleStream

class Alerta : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder:AlertDialog.Builder=AlertDialog.Builder(activity)
        builder.setMessage("Seguro que deseas Salir ")
        builder.setTitle("Hasta pronto")
        builder.setNegativeButton("No",(object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog!!.dismiss()
            }
        }))
        builder.setPositiveButton("Si",(object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
               System.exit(0)
            }
        }))
     return  builder.create()
    }

}