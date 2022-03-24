package com.example.canvas_for_drawing.data.data_methods

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Environment
import androidx.core.graphics.applyCanvas
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

class SaveBitmap @Inject constructor() {
    fun save(
        onSizeChanged: OnSizeChanged,
        pathList: MutableList<Path>,
        paintList: MutableList<Paint>,
        activeLayer: Int
    ): Boolean {
        val myDir = File( //объявляем каталог
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
            "CustomSV"
        )
        myDir.mkdirs() //создаем его
        val filename = "myCanvas.jpg" //имя создаваемого файла
        val file = File(myDir, filename) //объявляем файл
        if (file.exists()) {//проверяем, существует ли файл с таким названием в этой дериктории
            file.delete()//если да, то удаляем его
        }
        file.createNewFile()//создаем файл
        val bitmap = //создаем картинку
            Bitmap.createBitmap(
                onSizeChanged.w,
                onSizeChanged.h,
                Bitmap.Config.ARGB_8888
            )
        bitmap.applyCanvas {//формируем изображение для сохранения
            if (pathList.isNotEmpty() && pathList.size == paintList.size) {
                var i = 1
                this.drawColor(Color.WHITE)
                while (i <= activeLayer) {
                    this.drawPath(pathList[i - 1], paintList[i - 1]) //рисуем массив
                    i++
                }
            } else {
                return false
            }
        }
        val out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        return try {
            out.flush()
            out.close()
            true
        } catch (ex: IOException) {
            println(ex.message)
            false
        }
    }
}