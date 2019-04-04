package com.monggovest.MonggoVestBackEnd.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.monggovest.MonggoVestBackEnd.model.TransactionModel;
import org.springframework.stereotype.Component;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class UtilsEditInvoice{

    //CREATE PDF DOCUMENT FOR INVOICE

    public void writeInv(OutputStream outputStream, TransactionModel transactionModel) throws Exception {
        Document document = new Document(PageSize.A4.rotate(), 100f, 100f, 50f, 0f);
        PdfWriter.getInstance(document, outputStream);

        Image image = Image.getInstance ("src/main/resources/monggovest-logo.png");
        image.setAlignment (Element.ALIGN_LEFT);
        image.scaleAbsolute(350f, 100f);

        PdfPTable irdTable = new PdfPTable(2);
        irdTable.setWidthPercentage(100);
        irdTable.setWidths(new float[] { 5,3 });
        irdTable.addCell(getIRDCell("Invoice No"));
        irdTable.addCell(getIRDCell("Invoice Date"));
        irdTable.addCell(getIRDCell(transactionModel.getTrxInvoiceNum()));
        irdTable.addCell(getIRDCell(getPdfDate()));

        PdfPTable irhTable = new PdfPTable(3);
        irhTable.setWidthPercentage(100);

        irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
        irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
        irhTable.addCell(getIRHCell("Invoice", PdfPCell.ALIGN_RIGHT));
        irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
        irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
        PdfPCell invoiceTable = new PdfPCell (irdTable);
        invoiceTable.setBorder(0);
        irhTable.addCell(invoiceTable);

        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD);
        fs.addFont(font);
        Phrase bill = fs.process("Bill To : ");
        Paragraph name = new Paragraph("Name    : "+transactionModel.getUserModel().getUserFullName());
        name.setIndentationLeft(15);
        Paragraph email = new Paragraph("Email    : "+transactionModel.getUserModel().getUserEmail());
        email.setIndentationLeft(15);
        Paragraph contact = new Paragraph("Contact    : "+transactionModel.getUserModel().getUserContact());
        contact.setIndentationLeft(15);
        Paragraph trx = new Paragraph("Status    : "+ transactionModel.getStatus());
        trx.setIndentationLeft(15);

        Paragraph space = new Paragraph(" ");

        PdfPTable billTable = new PdfPTable(4);
        billTable.setWidthPercentage(100);
        billTable.setWidths(new float[] { 3,2,2,3 });
        billTable.setSpacingBefore(30.0f);
        billTable.addCell(getBillHeaderCell("Product Name"));
        billTable.addCell(getBillHeaderCell("Unit Price per Lot"));
        billTable.addCell(getBillHeaderCell("Jumlah Lot"));
        billTable.addCell(getBillHeaderCell("Amount"));

        billTable.addCell(getBillRowCell(transactionModel.getProductModel().getProductName()));
        billTable.addCell(getBillRowCell("Rp.500.000"));
        billTable.addCell(getBillRowCell(""+transactionModel.getLotTaken()));
        billTable.addCell(getBillRowCell(("Rp. "+500000*transactionModel.getLotTaken())+""));

        PdfPTable validity = new PdfPTable(1);
        validity.setWidthPercentage(100);
        PdfPCell summaryL = new PdfPCell (validity);
        summaryL.setColspan (3);
        summaryL.setPadding (1.0f);
        billTable.addCell(summaryL);

        PdfPTable accounts = new PdfPTable(2);
        accounts.setWidthPercentage(100);
        accounts.addCell(getAccountsCell("Tax"));
        accounts.addCell(getAccountsCellR("0.0%"));
        accounts.addCell(getAccountsCell("Total"));
        accounts.addCell(getAccountsCellR(("Rp. "+500000*transactionModel.getLotTaken())+""));
        PdfPCell summaryR = new PdfPCell (accounts);
        summaryR.setColspan (3);
        billTable.addCell(summaryR);

        PdfPTable describer = new PdfPTable(1);
        describer.setWidthPercentage(100);
        describer.addCell(getdescCell(" "));
        describer.addCell(getdescCell("+82123 0000 0000 || MONGGO VEST PLUS || Invest Barokah"));

        // ----------------------------------------------------------

        document.open();

        document.add(image);
        document.add(irhTable);
        document.add(bill);
        document.add(name);
        document.add(email);
        document.add(contact);
        document.add(trx);
        document.add(billTable);
        document.add(space);
        document.add(describer);
        document.add(validity);

        document.close();
    }

    public static PdfPCell getIRHCell(String text, int alignment) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPCell getIRDCell(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        return cell;
    }
    private String getPdfDate () {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
        String strDate = dateFormat.format(date);

        PdfPCell cell = new PdfPCell (new Paragraph (strDate));
        cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
        cell.setPadding (2.0f);
        return strDate;
    }

    public static PdfPCell getBillHeaderCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        return cell;
    }

    public static PdfPCell getBillRowCell(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    public static PdfPCell getAccountsCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthTop(0);
        cell.setPadding(5.0f);

        return cell;
    }
    public static PdfPCell getAccountsCellR(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthTop(0);
        cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
        cell.setPadding (5.0f);
        cell.setPaddingRight(20.0f);
        return cell;
    }

    public static PdfPCell getdescCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setBorder(0);
        return cell;
    }

}
