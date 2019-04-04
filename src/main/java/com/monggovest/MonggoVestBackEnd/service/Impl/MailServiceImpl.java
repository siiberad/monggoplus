package com.monggovest.MonggoVestBackEnd.service.Impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.itextpdf.text.Document;
import com.monggovest.MonggoVestBackEnd.model.TransactionModel;
import com.monggovest.MonggoVestBackEnd.repository.TransactionRepository;
import com.monggovest.MonggoVestBackEnd.service.MailService;
import com.monggovest.MonggoVestBackEnd.utils.UtilsEditAgreement;
import com.monggovest.MonggoVestBackEnd.utils.UtilsEditInvoice;
//import com.sun.corba.se.spi.activation.Server;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


@Service("mailService")
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	UtilsEditInvoice utilsEditInvoice;

	@Autowired
	UtilsEditAgreement utilsEditAgreement;

	@Override
	public void sendEmail(Object object) {

		TransactionModel transactionModel = (TransactionModel) object;

		MimeMessagePreparator preparator = getContentWithAttachementMessagePreparator(transactionModel);

		try {
			//SEND EMAIL
			mailSender.send(preparator);
			System.out.println("Email Send .....");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}

	}

	private MimeMessagePreparator getContentWithAttachementMessagePreparator(final TransactionModel transactionModel) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			ByteArrayOutputStream outputStream = null;

			public void prepare(MimeMessage mimeMessage) throws Exception {

				outputStream = new ByteArrayOutputStream();


				//ByteArrayOutputStream TO BYTE[]
				utilsEditInvoice.writeInv(outputStream, transactionModel);
				byte[] inv = outputStream.toByteArray();

				utilsEditAgreement.writeAgree(outputStream, transactionModel);
				byte[] agree = outputStream.toByteArray();

				//GENERATE BYTEARRAY TO PDF
				DataSource invDataSource = new  ByteArrayDataSource(inv,"application/pdf");
				MimeBodyPart attachmentInv = new MimeBodyPart();
				attachmentInv.setDataHandler(new DataHandler(invDataSource));

				DataSource agreeDataSource = new  ByteArrayDataSource(agree,"application/pdf");
				MimeBodyPart attachmentAgree = new MimeBodyPart();
				attachmentAgree.setDataHandler(new DataHandler(agreeDataSource));

				//SETING EMAIL
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setSubject("This is you Invoice and Agreement Letter");
				helper.setFrom("mvestplus@gmail.com");
				helper.setTo(transactionModel.getUserModel().getUserEmail());


				//TAMBAH 2 HARI
				Date currentDate = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(currentDate);
				c.add(Calendar.DATE, 2);
				DateFormat df = new SimpleDateFormat("HH:MM EEEE, d MMMM yyyy");
				Date currentDatePlus = c.getTime();
				df.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));

				//EMAIL
				String content = "Dear " + transactionModel.getUserModel().getUserFullName() + "\n"
						+ "Thank you for placing order.\n"
						+ "Your order id is -> " + transactionModel.getTrxInvoiceNum() + ".\n"
						+ "You should finish Transaction before : " + df.format(currentDatePlus)  ;

				helper.setText(content);
				//give ATTACHMENT INVOICE TO EMAIL
				helper.addAttachment("invoice-" + transactionModel.getUserModel().getUserFullName() + ".pdf", invDataSource);
				//give ATTACHMENT AGREEMENT TO EMAIL
				helper.addAttachment("agreement-" + transactionModel.getUserModel().getUserFullName() + ".pdf", agreeDataSource);


				//SAVE PDF AGREEMENT TO DATABASES
				transactionModel.setPdf(agree);

			}


		};
		return preparator;
	}

}
