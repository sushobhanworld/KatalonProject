import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.Iterator
import java.util.List


import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory


		String homePage = "http://www.zlti.com"
		String url = ""
		HttpURLConnection huc = null
		int respCode = 200
		
		WebDriver driver= DriverFactory.getWebDriver()

		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(homePage)
		
		List<WebElement> links = driver.findElements(By.tagName("a"))
		
		Iterator<WebElement> it = links.iterator()
		
		while(it.hasNext()){
			
			url = it.next().getAttribute("href")
			
			println(url)
		
			if(url == null || url.isEmpty()){
				println("URL is either not configured for anchor tag or it is empty")
				continue
			}
			
			if(!url.startsWith(homePage)){
				println("URL belongs to another domain, skipping it.")
				continue
			}
			
			try {
				huc = (HttpURLConnection)(new URL(url).openConnection())
				
				huc.setRequestMethod("HEAD")
				
				huc.connect();
				
				respCode = huc.getResponseCode()
				
				if(respCode >= 400){
					println(url+" is a broken link")
				}
				else{
					println(url+" is a valid link");
				}
					
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		driver.quit()

