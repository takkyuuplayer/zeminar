package com.takkyuuplayer.zeminar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * Hello World: document constructor.
 */
public class BunkenPDF {

	/** The resulting text file with info about a PDF. */
	public static final String RESULT = "_result.pdf";

	/**
	 * Main method.
	 * 
	 * @param args
	 *            no arguments needed
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void main(String[] args) throws DocumentException,
			IOException {

		if (args.length > 0) {
			for (String file : args) {
				File file2 = new File(file);
				if (!file2.exists())
					continue;

				addBlankPages(file2);
			}
		}
	}

	public static void addBlankPages(File originFile) throws IOException,
			DocumentException {
		// add blank pages.
		PdfReader reader = new PdfReader(originFile.getName());
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
				originFile.getAbsolutePath() + RESULT));

		for (int page = reader.getNumberOfPages(); page > 0; page--) {
			stamper.insertPage(page + 1, reader.getPageSizeWithRotation(page));
		}
		stamper.close();
	}

}