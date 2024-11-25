package com.ongc.liferay.vigilance.util;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Scanner;

public class OngcFileUtils {

	public static String getMimeType(String fileUrl) throws java.io.IOException {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String type = fileNameMap.getContentTypeFor(fileUrl);

		return type;
	}

	public static boolean isPDF(File file) throws FileNotFoundException {
		Scanner input = new Scanner(new FileReader(file));
		while (input.hasNextLine()) {
			final String checkline = input.nextLine();
			if (checkline.contains("%PDF-")) {
				// a match!
				return true;
			}
		}
		return false;
	}

	public static boolean isImage(File file) throws FileNotFoundException {
		boolean flag = false;
		Scanner input = new Scanner(new FileReader(file));
		DataInputStream ins = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));

		try {
			////system.out.println("isImage isImage"+ins.readInt());
			if (ins.readInt() == 0xffd8ffe0) {
				flag = true;
			}else {
				flag = false;

			}

		} catch (Exception e) {

		}
		return flag;
	}
}
