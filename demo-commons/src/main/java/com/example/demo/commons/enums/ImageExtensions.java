
package com.example.demo.commons.enums;


import java.util.HashSet;
import java.util.Set;

public enum ImageExtensions {

	BMP("bmp"), 
	GIF("gif"), 
	JPEG("jpeg"), 
	JPG("jpg"), 
	WEBP("webp"), 
	HEIF("heif"), 
	PNG("png"), 
	HEIC("heic"),
	PDF("pdf"),
	CSV("csv"),
	XLS("xls"),
	XLSX("xlsx"),
	DOCX("docx"),
	DOC("doc");

	final String value;

	ImageExtensions(String value) {
		this.value = value;
	}

	public static Set<String> getAllowedBillUploadExtensionSet() {
		Set<String> allowedBillUpload = new HashSet<>();
		allowedBillUpload.add(JPG.value);
		allowedBillUpload.add(JPEG.value);
		allowedBillUpload.add(PNG.value);
		allowedBillUpload.add(PDF.value);
		allowedBillUpload.add(XLSX.value);
		allowedBillUpload.add(XLS.value);
		allowedBillUpload.add(DOC.value);
		allowedBillUpload.add(DOCX.value);
		allowedBillUpload.add(CSV.value);
		return allowedBillUpload;
	}
	
	public static Set<String> getAllowedExpenseUploadExtensionSet() {
		Set<String> allowedBillUpload = new HashSet<>();
		allowedBillUpload.add(JPG.value);
		allowedBillUpload.add(JPEG.value);
		allowedBillUpload.add(PNG.value);
		allowedBillUpload.add(PDF.value);
		allowedBillUpload.add(XLSX.value);
		allowedBillUpload.add(XLS.value);
		return allowedBillUpload;
	}
}
