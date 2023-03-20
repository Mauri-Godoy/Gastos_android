package com.mg.gastos.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.text.TextPaint;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mg.gastos.R;
import com.mg.gastos.entity.Movement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class PDFGenerator {

    public static void generatePDFDocument(Context context) {
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

    public static void generatePdfTable(Context context, List<Movement> list) throws DocumentException {

        if (list.isEmpty()) {
            Toast.makeText(context, "No hay datos para generar el documento.", Toast.LENGTH_SHORT).show();
            return;
        }

        String title = "Historial de movimientos".toUpperCase();
        BaseColor secondaryColor = new BaseColor(125, 119, 215);

        Font fontTitle = new Font();
        fontTitle.setStyle(Font.BOLD);
        fontTitle.setSize(16);

        Paragraph pTitle = new Paragraph(title.concat("\n\n"), fontTitle);

        Font fontWhite = new Font();
        fontWhite.setStyle(Font.BOLD);
        fontWhite.setColor(BaseColor.WHITE);

        int paddingCell = 2;

        Phrase pCategory = new Phrase("Categoría", fontWhite);
        PdfPCell categoryCell = new PdfPCell(pCategory);
        categoryCell.setBackgroundColor(secondaryColor);
        categoryCell.setPadding(paddingCell);

        Phrase pDescription = new Phrase("Descripción", fontWhite);
        PdfPCell descriptionCell = new PdfPCell(pDescription);
        descriptionCell.setBackgroundColor(secondaryColor);
        descriptionCell.setPadding(paddingCell);

        Phrase pDate = new Phrase("Fecha", fontWhite);
        PdfPCell dateCell = new PdfPCell(pDate);
        dateCell.setBackgroundColor(secondaryColor);
        dateCell.setPadding(paddingCell);

        Phrase pAmount = new Phrase("Monto", fontWhite);
        PdfPCell amountCell = new PdfPCell(pAmount);
        amountCell.setBackgroundColor(secondaryColor);
        amountCell.setPadding(paddingCell);

        PdfPTable historyTable = new PdfPTable(4);
        historyTable.setWidthPercentage(100);
        historyTable.addCell(categoryCell);
        historyTable.addCell(descriptionCell);
        historyTable.addCell(dateCell);
        historyTable.addCell(amountCell);

        list.forEach(movement -> {
            historyTable.addCell(movement.getCategory().getName());
            historyTable.addCell(movement.getDescription());
            historyTable.addCell(DateUtils.parseToTableFormat(movement.getDate()));
            historyTable.addCell((movement.isNegativeAmount() ? "-" : "") + movement.getAmount());
        });

        PdfPCell totalCell = new PdfPCell(new Phrase("TOTAL", fontWhite));
        totalCell.setBackgroundColor(secondaryColor);
        PdfPCell emptyCell = new PdfPCell(new Phrase(""));
        emptyCell.setBackgroundColor(secondaryColor);
        PdfPCell totalValueCell = new PdfPCell(new Phrase(String.valueOf(list.stream().mapToDouble(movement ->
                movement.isNegativeAmount() ? (movement.getAmount() * -1) : movement.getAmount()
        ).sum()),fontWhite));
        totalValueCell.setBackgroundColor(secondaryColor);

        historyTable.addCell(totalCell);
        historyTable.addCell(emptyCell);
        historyTable.addCell(emptyCell);
        historyTable.addCell(totalValueCell);

        String path = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + File.separator;
        String fileName = title.concat("_" + LocalDateTime.now().toString()).concat(".pdf");
        File file = new File(path, fileName);
        Document document = new Document();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PdfWriter.getInstance(document, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        document.add(pTitle);
        document.add(historyTable);
        document.addCreationDate();
        document.addCreator(context.getString(R.string.app_name));
        document.addTitle(title);
        document.close();
        viewFile(file, context);
    }
}
