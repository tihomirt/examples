/*
 * Copyright 2016 Tihomir Turzai
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package main;
import java.io.BufferedInputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Example for using apache pdf reader  
 *
 */
public class PdfReader {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testPDFReader() throws Exception {
		// page with example pdf document
		driver.get("http://www.vandevenbv.nl/dynamics/modules/SFIL0200/view.php?fil_Id=5515");

		URL url = new URL(driver.getCurrentUrl());
		BufferedInputStream fileToParse = new BufferedInputStream(url.openStream());

		PDDocument document = null;
		try{
			document = PDDocument.load(fileToParse);
			String output = new PDFTextStripper().getText(document);
			System.out.println(output);
		}finally{

			if( document != null )
			{
				document.close();
			}
		}
	}
}