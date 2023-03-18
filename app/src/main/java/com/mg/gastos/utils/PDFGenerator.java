package com.mg.gastos.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.text.TextPaint;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.mg.gastos.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFGenerator {
    public static void generate(Context context) {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        TextPaint title = new TextPaint();
        TextPaint description = new TextPaint();
        Bitmap bitmap, bitmapScale;

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(816, 1054, 1).create();
        PdfDocument.Page page1 = pdfDocument.startPage(pageInfo);

        Canvas canvas = page1.getCanvas();

//        Resources res = context.getResources();
//        int id = R.drawable.ic_logo;
//        bitmap = BitmapFactory.decodeResource(res, id);
//
//        bitmapScale = Bitmap.createScaledBitmap(bitmap, 80, 80, false);
//        canvas.drawBitmap(bitmapScale, 368, 20, paint);

        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setTextSize(20);
        canvas.drawText("titleText", 10, 150, title);

        description.setTypeface(Typeface.DEFAULT);
        title.setTextSize(14);

        canvas.drawText("description", 10, 250, description);

        pdfDocument.finishPage(page1);
        String path = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + File.separator;
        String fileName = "archivo.pdf";

        File file = new File(path, fileName);

        Log.i("PDFGenerator", file.getAbsolutePath());

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        pdfDocument.close();

        viewFile(file, context);
    }

    private static void viewFile(File file, Context context) {
        String mimeType = "application/pdf";

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), mimeType);
        Uri apkURI = FileProvider.getUriForFile(
                context,
                context.getApplicationContext()
                        .getPackageName() + ".provider", file);
        intent.setDataAndType(apkURI, mimeType);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(intent);
    }
}