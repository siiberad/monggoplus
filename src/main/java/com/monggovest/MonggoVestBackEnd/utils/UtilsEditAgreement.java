package com.monggovest.MonggoVestBackEnd.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.monggovest.MonggoVestBackEnd.model.TransactionModel;
import org.springframework.stereotype.Component;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class UtilsEditAgreement {

    //CREATE PDF DOCUMENT FOR AGREEMENT

    public void writeAgree(OutputStream outputStream, TransactionModel transactionModel) throws Exception {

        Document document = new Document(PageSize.A4, 50f, 50f, 20f, 50f);
        PdfWriter.getInstance(document, outputStream);


        Font fontSpace = new Font(Font.FontFamily.HELVETICA, 6);
        Paragraph space = new Paragraph(" ");
        Paragraph spaceSmall = new Paragraph(" ", fontSpace);

        Image image = Image.getInstance ("src/main/resources/monggovest-logo.png");
        image.setAlignment (Element.ALIGN_RIGHT);
        image.scaleAbsolute(170f, 50f);

        Font fontJudul = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
        Paragraph judul = new Paragraph("SURAT PERJANJIAN KERJASAMA INVESTASI", fontJudul);
        judul.setAlignment(Element.ALIGN_CENTER);

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("EEEE, d MMMM yyyy");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
        Font fontDate = FontFactory.getFont(FontFactory.HELVETICA, 11);
        Paragraph tanggal = new Paragraph("" + df.format(date), fontDate);
        tanggal.setAlignment(Element.ALIGN_RIGHT);

        Paragraph no1 = new Paragraph("1.");
        Paragraph name1 = new Paragraph("Nama     : PT.MonggoVestPlus");
        Paragraph email1 = new Paragraph("Email     : mvestplus@gmail.com");
        Paragraph address1 = new Paragraph("Alamat   : Gedung 4 Highscope Indonesia, Jl. TB. Simatupang No. 8, Cilandak Barat, Cilandak, Jakarta Selatan, 12430");
        Paragraph pihak1 = new Paragraph("Untuk selanjutnya disebut sebagai PIHAK PERTAMA.");

        Paragraph no2 = new Paragraph("2.");
        Paragraph name2 = new Paragraph("Nama      : "+transactionModel.getUserModel().getUserFullName());
        Paragraph email2 = new Paragraph("Email      : "+transactionModel.getUserModel().getUserEmail());
        Paragraph contact2 = new Paragraph("Kontak    : "+transactionModel.getUserModel().getUserContact());
        Paragraph bank = new Paragraph("Nama Bank    : "+transactionModel.getBankModel().getBankName());
        Paragraph norek = new Paragraph("No Rekening    : "+transactionModel.getNoRekening());
        Paragraph address2 = new Paragraph("Alamat    : "+transactionModel.getUserModel().getUserAddress());
        Paragraph pihak2 = new Paragraph("Untuk selanjutnya disebut sebagai PIHAK KEDUA.");

        Paragraph par = new Paragraph("Para pihak terlebih dahulu menerangkan hal–hal sebagai berikut:");
        Font ketentuan = new Font(Font.FontFamily.HELVETICA, 11);
        Paragraph par1 = new Paragraph("1. Bahwa PIHAK PERTAMA adalah selaku MODAL INVESTASI  selanjutnya PIHAK KEDUA disebut sebagai INVESTOR .",ketentuan);
        par1.setAlignment(Element.ALIGN_JUSTIFIED);
        Paragraph par2 = new Paragraph("2. Bahwa PIHAK PERTAMA adalah Pengelola Dana Investasi yang menerima DANA INVESTASI dari PIHAK KEDUA.",ketentuan);
        par2.setAlignment(Element.ALIGN_JUSTIFIED);
        Paragraph par3 = new Paragraph("3. Bahwa Pihak Pertama dan Pihak Kedua setuju untuk saling mengikatkan diri dalam suatu perjanjian kerjasama Investasi dalam Peningkatan Modal Investasi sesuai dengan ketentuan hukum yang berlaku.",ketentuan);
        par3.setAlignment(Element.ALIGN_JUSTIFIED);
        Paragraph par4 = new Paragraph("4. Bahwa berdasarkan hal-hal tersebut di atas, kedua belah pihak menyatakan sepakat dan setuju untuk mengadakan Perjanjian Kerjasama ini yang dilaksanakan dengan ketentuan dan syarat-syarat sebagai berikut :",ketentuan);
        par4.setAlignment(Element.ALIGN_JUSTIFIED);

        Font fontJudulPasal = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font fontIsiPasal = new Font(Font.FontFamily.HELVETICA, 11);

        Paragraph pasal1 = new Paragraph("PASAL I\n" + "MAKSUD DAN TUJUAN", fontJudulPasal);
        pasal1.setAlignment(Element.ALIGN_CENTER);
        Paragraph pasal1Isi = new Paragraph(("PIHAK KEDUA dalam perjanjian ini memberi DANA INVESTASI kepada PIHAK PERTAMA sebesar Rp "+ 500000*transactionModel.getLotTaken())+",- dan PIHAK PERTAMA dengan ini telah menerima penyerahan DANA INVESTASI tersebut dari PIHAK KEDUA serta menyanggupi untuk melaksanakan pengelolaan DANA INVESTASI tersebut.", fontIsiPasal);
        pasal1Isi.setAlignment(Element.ALIGN_JUSTIFIED);

        Paragraph pasal2 = new Paragraph("PASAL II\n" + "RUANG LINGKUP", fontJudulPasal);
        pasal2.setAlignment(Element.ALIGN_CENTER);
        Paragraph pasal2Isi = new Paragraph(("PIHAK PERTAMA dengan ini berjanji dan mengikatkan diri untuk melaksanakan perputaran DANA INVESTASI pada Usaha Peningkatan Modal Investasi. PIHAK PERTAMA dengan ini berjanji dan mengikatkan diri untuk memberikan keuntungan "+transactionModel.getProductModel().getReturnOfInvestment()+"% per bulan dalam jangka waktu "+transactionModel.getProductModel().getContractPeriodInMonth()+" bulan."), fontIsiPasal);
        pasal2Isi.setAlignment(Element.ALIGN_JUSTIFIED);

        Paragraph pasal3 = new Paragraph("PASAL III\n" + "HAK DAN KEWAJIBAN PIHAK KEDUA", fontJudulPasal);
        pasal3.setAlignment(Element.ALIGN_CENTER);
        Paragraph pasal3Isi = new Paragraph(("Dalam Perjanjian Kerjasama ini, PIHAK KEDUA memiliki Hak dan Kewajiban sebagai berikut :\n" +
                "1. Memberikan DANA INVESTASI kepada PIHAK PERTAMA sebesar Rp"+ 500000*transactionModel.getLotTaken())+",-\n" +
                "2. Menerima hasil keuntungan atas pengelolaan DANA INVESTASI, sesuai dengan Pasal V.", fontIsiPasal);
        pasal3Isi.setAlignment(Element.ALIGN_JUSTIFIED);

        Paragraph pasal4 = new Paragraph("PASAL IV\n" + "HAK DAN KEWAJIBAN PIHAK PERTAMA", fontJudulPasal);
        pasal4.setAlignment(Element.ALIGN_CENTER);
        Paragraph pasal4Isi = new Paragraph("Dalam Perjanjian Kerjasama Investasi Modal ini, PIHAK PERTAMA memiliki Hak dan Kewajiban sebagai berikut :\n" +
                "1. Menerima DANA INVESTASI dari PIHAK KEDUA sebesar 2.5% dari Laba Ternak.\n" +
                "2. Memberikan bagian hasil keuntungan kepada PIHAK KEDUA, sesuai dengan Pasal V.", fontIsiPasal);
        pasal4Isi.setAlignment(Element.ALIGN_JUSTIFIED);


        Paragraph pasal5 = new Paragraph("PASAL V\n" + "PEMBAGIAN HASIL", fontJudulPasal);
        pasal5.setAlignment(Element.ALIGN_CENTER);
        Paragraph pasal5Isi = new Paragraph("Dalam Perjanjian Kerjasama Investasi Modal ini, kedua belah pihak sepakat didalam hal pembagian hasil investasi penyertaan dana  sebagai berikut :\n" +
                "1. Kedua belah pihak sepakat dan setuju bahwa perjanjian kerjasama ini dilakukan dengan  cara  pemberian keuntungan yang diperoleh dalam Usaha Peningkatan Modal Investasi sebagaimana Pasal II.\n" +
                "2. Bagi hasil yang dimaksud dalam ayat 1 diatas dilakukan dengan memperhitungkan biaya investasi sebagaimana tersebut dalam pasal II.\n" +
                "3. Bagi hasil yang dimaksud dalam ayat 2 di atas berlaku sampai dengan PIHAK KEDUA menarik kembali DANA INVESTASI yang telah diserahkan sesuai dengan perhitungan Pasal II.", fontIsiPasal);
        pasal5Isi.setAlignment(Element.ALIGN_JUSTIFIED);

        Paragraph pasal6 = new Paragraph("PASAL VI\n" + "KEADAAN MEMAKSA (FORCE MAJEUR)", fontJudulPasal);
        pasal6.setAlignment(Element.ALIGN_CENTER);
        Paragraph pasal6Isi = new Paragraph("1. Yang termasuk dalam Force Majeur adalah akibat dari kejadian-kejadian diluar kuasa dan kehendak dari kedua belah pihak diantaranya termasuk tidak terbatas bencana alam, banjir, badai, topan, gempa bumi, kebakaran, perang, huru-hara, pemberontakan, demonstrasi, pemogokan, kegagalan investasi.\n" +
                "2. Jika dalam pelaksanaan perjanjian ini terhambat ataupun tertunda baik secara keseluruhan ataupun sebagian yang dikarenakan hal-hal tersebut dalam ayat 1  diatas,  maka  PIHAK PERTAMA bersedia mengganti sejumlah Dana Investasi dari PIHAK KEDUA secara penuh apabila belum ada pembagian hasil keuntungan, atau pengembalian Dana Investasi dikurangi dengan pembagian hasil yang sudah terima oleh PIHAK KEDUA.\n" +
                "3. Pengembalian Dana Investasi sebagaimana tersebut dalam ayat 2, mengenai tata cara pengembaliannya akan diadakan musyawarah terlebih dahulu antara PIHAK KEDUA dan PIHAK PERTAMA mengenai proses atau jangka waktu pengembaliannya.", fontIsiPasal);
        pasal6Isi.setAlignment(Element.ALIGN_JUSTIFIED);

        Paragraph pasal7 = new Paragraph("PASAL VII\n" + "WANPRESTASI", fontJudulPasal);
        pasal7.setAlignment(Element.ALIGN_CENTER);
        Paragraph pasal7Isi = new Paragraph("1. Dalam hal salah satu PIHAK KEDUA telah melanggar kewajibannya yang tercantum dalam salah satu Pasal perjanjian ini, telah cukup bukti dan tanpa perlu dibuktikan lebih  lanjut, bahwa pihak yang melanggar tersebut telah melakukan tindakan Wanprestasi.\n" +
                "2. Pihak yang merasa dirugikan atas tindakan Wanprestasi tersebut dalam ayat 1 diatas, berhak meminta ganti kerugian dari pihak yang melakukan wanprestasi  tersebut  atas sejumlah kerugian yang dideritanya, kecuali dalam hal kerugian tersebut disebabkan  karena  adanya suatu  keadaan memaksa, seperti tercantum dalam Pasal VI.\n", fontIsiPasal);
        pasal7Isi.setAlignment(Element.ALIGN_JUSTIFIED);

        Paragraph pasal8 = new Paragraph("PASAL VIII\n" + "PERSELISIHAN", fontJudulPasal);
        pasal8.setAlignment(Element.ALIGN_CENTER);
        Paragraph pasal8Isi = new Paragraph("Bilamana dalam pelaksanaan perjanjian Kerjasama ini terdapat perselisihan antara  kedua belah pihak baik dalam pelaksanaannya ataupun dalam penafsiran salah satu Pasal dalam perjanjian ini, maka kedua belah pihak sepakat  untuk  sedapat mungkin menyelesaikan perselisihan tersebut dengan cara musyawarah. Apabila musyawarah telah dilakukan oleh kedua belah pihak, namun ternyata tidak  berhasil  mencapai suatu kemufakatan maka Para Pihak sepakat bahwa semua sengketa yang timbul  dari  perjanjian ini akan diselesaikan pada Kantor Kepaniteraan Pengadilan Negeri.", fontIsiPasal);
        pasal8Isi.setAlignment(Element.ALIGN_JUSTIFIED);

        Paragraph pasal9 = new Paragraph("PASAL IV\n" + "ATURAN PENUTUP", fontJudulPasal);
        pasal9.setAlignment(Element.ALIGN_CENTER);
        Paragraph pasal9Isi = new Paragraph("Hal-hal yang belum diatur atau belum cukup diatur dalam perjanjian ini apabila dikemudian hari dibutuhkan dan dipandang perlu akan ditetapkan tersendiri secara musyawarah dan selanjutnya akan ditetapkan dalam suatu ADDENDUM yang berlaku mengikat bagi kedua belah pihak, yang akan direkatkan dan merupakan bagian yang tidak terpisahkan dari Perjanjian ini.", fontIsiPasal);
        pasal9Isi.setAlignment(Element.ALIGN_JUSTIFIED);

        Paragraph penutup = new Paragraph("Demikianlah surat perjanjian kerjasama ini dibuat dalam rangkap 2 (dua), untuk masing-masing pihak, yang ditandatangani di atas kertas bermaterai cukup, yang masing-masing mempunyai kekuatan hukum yang sama dan berlaku sejak PIHAK KEDUA mencentang pada halama transaksi.", fontIsiPasal);
        penutup.setAlignment(Element.ALIGN_JUSTIFIED);


        document.open();

        document.add(image);
        document.add(space);

        document.add(judul);
        document.add(space);
        document.add(space);
        document.add(tanggal);
        document.add(spaceSmall);

        document.add(no1);
        document.add(name1);
        document.add(email1);
        document.add(address1);
        document.add(pihak1);

        document.add(space);

        document.add(no2);
        document.add(name2);
        document.add(email2);
        document.add(contact2);
        document.add(bank);
        document.add(norek);
        document.add(address2);
        document.add(pihak2);

        document.add(space);
        document.add(space);

        document.add(par);
        document.add(space);
        document.add(par1);
        document.add(space);
        document.add(par2);
        document.add(space);
        document.add(par3);
        document.add(space);
        document.add(par4);

        document.newPage();
        document.add(image);
        document.add(space);

        document.add(pasal1);
        document.add(spaceSmall);
        document.add(pasal1Isi);
        document.add(space);

        document.add(pasal2);
        document.add(spaceSmall);
        document.add(pasal2Isi);
        document.add(space);

        document.add(pasal3);
        document.add(spaceSmall);
        document.add(pasal3Isi);
        document.add(space);

        document.add(pasal4);
        document.add(spaceSmall);
        document.add(pasal4Isi);
        document.add(space);

        document.add(pasal5);
        document.add(spaceSmall);
        document.add(pasal5Isi);
        document.add(space);

        document.newPage();
        document.add(image);
        document.add(space);

        document.add(pasal6);
        document.add(spaceSmall);
        document.add(pasal6Isi);
        document.add(space);

        document.add(pasal7);
        document.add(spaceSmall);
        document.add(pasal7Isi);
        document.add(space);

        document.add(pasal8);
        document.add(spaceSmall);
        document.add(pasal8Isi);
        document.add(space);

        document.newPage();
        document.add(image);
        document.add(space);

        document.add(pasal9);
        document.add(spaceSmall);
        document.add(pasal9Isi);
        document.add(space);

        document.add(penutup);

		document.close();
    }
}




