package com.imademethink_sikulix_automation;

import java.awt.Rectangle;
import java.io.File;
import java.util.Iterator;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import cucumber.api.java.en.When;

public class StepDefSikuliBasedAutomation{
	
	WebDriver objWebdriver = null;

	// ---------------------------------------------- Screen class features -----------------------------------------

	public void PatternClassTest1(){
		// image matching using pattern class with CUSTOM SIMILARITY percentage
		Settings.MinSimilarity 				= 0.50;        // 0.7 default
		Settings.WaitScanRate 				= 0.3f; 		  	// 0.3f/sec default
		Settings.ObserveScanRate 		= 0.3f;  		// 0.3f/sec default

		// manually open patterncompare/mainimage.png full screen using MS photo viewer
		try{
			Screen screen 					= new Screen(0);
			Pattern pattern 					= new Pattern("patterncompare//partialimage.png").similar(0.8f);
			Region expectedRegion	= screen.exists(pattern);
			if(null != expectedRegion) {expectedRegion.rightClick(pattern);}
			else {System.out.println("ERROR :: Either required similarity is very high or image to be compared does not exists");}
		}catch(Exception e){}
	}

	public void PatternClassTest2(){
		// image matching using pattern class type 2
		// get matching score and use Match class as well
		// manually open patterncompare/mainimage.png full screen using MS photo viewer

		try{
			Screen screen 					= new Screen(0);
			Region  smallRegion 	    	= new Screen(0).setRect(600, 500, 222, 111);
			smallRegion.highlight(2.5f);
			String partialImage 			= "patterncompare\\partialimage.png";
			//screen.find(new Pattern(partialImage).exact()).rightClick();
			Region sampleRegion  		= screen;
			// manuall open patterncompare/mainimage.png full screen/ reduced scale using MS photo viewer
			Double matchingScore 		= sampleRegion.find(new Pattern(partialImage)).getScore();
			System.out.println("INFO :: ------------------------ matching score  ------------------------" + matchingScore);
			Match matchedResult = sampleRegion.find(new Pattern(partialImage));
			if (null != matchedResult){
				matchedResult.highlight(2.5f);
				matchedResult.rightClick();
			}else {System.out.println("ERROR :: Either required similarity is very high or image to be compared does not exists");}
		}catch(Exception e){}
	}
		
	// ---------------------------------------------- Region class features -----------------------------------------

	public void RegionClassTest1(){
		Settings.MinSimilarity 			= 0.50;        // 0.7 default
		Settings.WaitScanRate 			= 0.3f; 		  	// 0.3f/sec default
		Settings.ObserveScanRate 	= 0.3f;  		// 0.3f/sec default
		
		// current region attributes
		Region currentRegion = new Screen(0);
		System.out.println("INFO :: ------------------------ H ------------------------" + currentRegion.getH());
		System.out.println("INFO :: ------------------------ W ------------------------" + currentRegion.getW());
		System.out.println("INFO :: ------------------------ X ------------------------" + currentRegion.getX());
		System.out.println("INFO :: ------------------------ Y ------------------------" + currentRegion.getY());
		
		App.open("C:\\Program Files (x86)\\Microsoft Office\\Office12\\EXCEL.EXE  ScreenClassTestAssets\\simple_tests.xlsx");
		delay(2500);
		App.focus("EXCEL");

		// use full region of current screen
		Region fullRegion         = new Screen(0);
		// can highlight region used
		fullRegion.highlight(2.5f); 			// 2.5 seconds
		delay(2000);
		
		// use small region of current screen
		Region smallRegion 		= new Screen(0).setRect(250, 150, 200, 100);
		// can highlight region used
		smallRegion.highlight(3);
		delay(2000);
		// can highlight region used with colour
		smallRegion.highlight(2.5f, "green");
		delay(2000);
		
		App.close("EXCEL");
	}

	public void RegionClassTest2(){
		Settings.MinSimilarity 			= 0.50;        // 0.7 default
		Settings.WaitScanRate 			= 0.3f; 		  	// 0.3f/sec default
		Settings.ObserveScanRate 	= 0.3f;  		// 0.3f/sec default
		Settings.OcrTextSearch         = true;
		
		App.open("C:\\Program Files (x86)\\Microsoft Office\\Office12\\EXCEL.EXE  ScreenClassTestAssets\\simple_tests.xlsx");
		delay(2500);
		App.focus("EXCEL");

		// find text using Region class object (full screen)
		Region fullRegion         = new Screen(0);
		try{
			Match singleMatch 	= null;
			//Iterator<Match> matchedIterate = fullRegion.findAllText("imademethink");
			Iterator<Match> matchedIterate = fullRegion.findAllText("simple living");
			if(matchedIterate.hasNext()){
				singleMatch = matchedIterate.next();
				System.out.println("INFO :: ------------------------ Find given string ---X---------------------" + singleMatch.getX());
				System.out.println("INFO :: ------------------------ Find given string ---Y---------------------" + singleMatch.getY());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		App.close("Excel");
	}

	public void RegionClassTest3(){
		// to click, right click, double click using region class object		

		App.open("C:\\Program Files (x86)\\Microsoft Office\\Office12\\EXCEL.EXE  ScreenClassTestAssets\\simple_tests.xlsx");
		delay(2500);
		App.focus("EXCEL");

		Region fullRegion         = new Screen(0);

		fullRegion.setX(100);
		fullRegion.setY(150);
		fullRegion.click();
		delay(1800);
		fullRegion.type("imademethink 100 200" + Key.ENTER);

		fullRegion.setX(150);
		fullRegion.setY(248);
		fullRegion.doubleClick();
		delay(2000);
		fullRegion.type("imademethink 150 250" + Key.ENTER);

		fullRegion.setX(200);
		fullRegion.setY(300);
		fullRegion.rightClick();
		delay(2500);
		fullRegion.type(Key.ESC);

		App.close("EXCEL");
	}

	@SuppressWarnings("deprecation")
	public void RegionClassTest4(){
		// screen capture and save using region class
		Region currentRegion         = new Screen(0);
		currentRegion.saveScreenCapture("captured_image");

		// screen capture and save using region class with rectangle
		currentRegion = new Screen(0).setRect(50, 60, 200, 100);
		currentRegion.saveScreenCapture("captured_image");
		// screen capture and save using region class with set size
		currentRegion.setSize(300, 400);
		currentRegion.saveScreenCapture("captured_image");

		App.open("C:\\Program Files (x86)\\Microsoft Office\\Office12\\EXCEL.EXE  ScreenClassTestAssets\\simple_tests.xlsx");
		delay(2500);
		App.focus("EXCEL");

		// drag and drop using region class
		Region tempRegionSrc = new Screen(0).setRect(100, 200, 50, 50);
		Region tempRegionDst = new Screen(0).setRect(300, 400, 90, 90);
		Settings.DelayAfterDrag  = 2.2f;
		Settings.DelayBeforeDrop = 2.2f;
		try{currentRegion.dragDrop(tempRegionSrc, tempRegionDst);}
		catch(Exception e){}
		
		App.close("EXCEL");
	}

	public void RegionClassTest5(){
		// screen capture column wise and row wise
		Region currentRegion         = new Screen(0);
		try{
			int totalColn = 7;
			for (int n=0; n< totalColn; n++){
				Region tempRegion = currentRegion.getCol(n, totalColn);
				tempRegion.saveScreenCapture("captured_image");				
			}
			//			int totalRows = 7;
			//			for (int n=0; n< totalRows; n++){
			//				Region tempRegion = currentRegion.getRow(n, totalRows);
			//             tempRegion.saveScreenCapture("captured_image\\captured_with_row__" + n  + ".png");
			//			}
		}catch(Exception e){}
	}
	
	// ---------------------------------------------- Screen class features -----------------------------------------
	public void ScreenClassTest1(){
		// general setting
		Screen mainScreen 				= new Screen(); 			//Screen(0) is implied
		Settings.OcrTextRead 			= true;
		Settings.OcrTextSearch 			= true;
		Settings.OcrLanguage  			= "eng";
		Settings.MinSimilarity  			= 0.75;        // 0.7 default
		Settings.MoveMouseDelay 	= 1.1f;   		// 0.5f sec default
		Settings.WaitScanRate 			= 0.3f; 			// 0.3f/sec default
		Settings.ObserveScanRate 	= 0.3f;			// 0.3f/sec default

		// general screen object attributes
		System.out.println("INFO :: --------------- mainScreen H ------------------------" + mainScreen.getH());
		System.out.println("INFO :: --------------- mainScreen W ------------------------" + mainScreen.getW());
		System.out.println("INFO :: --------------- mainScreen X ------------------------" + mainScreen.getX());
		System.out.println("INFO :: --------------- mainScreen Y ------------------------" + mainScreen.getY());
		
		// manuall open the excel file full screen mode ScreenClassTestAssets folder
		System.out.println("INFO :: --------------- text() start-------------");
		System.out.println(mainScreen.text().toString());
		System.out.println("\nINFO :: --------------- text() finish---------\n");
	}

	public void ScreenClassTest2(){
		// open a windows application
		// steps are similar to open linux application
		
		// just open excel
		//App.open("C:\\Program Files (x86)\\Microsoft Office\\Office12\\EXCEL.EXE");
		// open ms excel with mentoned xls file
		App.open("C:\\Program Files (x86)\\Microsoft Office\\Office12\\EXCEL.EXE  ScreenClassTestAssets\\simple_tests.xlsx");
		delay(2500);
		App.focus("EXCEL");
		
		// set mouse pointer location @ x,y --> then click -> then key in escape
		Screen mainScreen 				= new Screen(); 			//Screen(0) is implied
		mainScreen.setLocation(new Location(310, 210));
		mainScreen.click();
		delay(1500);
		mainScreen.type(Key.ESC);

		// set mouse pointer location @ x,y with offset --> then right click -> then key in escape
		mainScreen.setLocation(new Location(200, 150)).offset(-30, -20);
		mainScreen.rightClick();
		delay(1500);
		mainScreen.type(Key.ESC);

		// set mouse pointer location @ x,y --> then double click -> then key in escape
		mainScreen.setLocation(new Location(500, 148));
		mainScreen.doubleClick();
		delay(1500);
		mainScreen.type(Key.ESC);

		// close excel app
		App.close("EXCEL");
	}

	public void ScreenClassTest3(){
		Screen mainScreen = new Screen(0);
		// screen capture - full
		mainScreen.capture().save("captured_image", "screen_capture_full.png");
		// screen capture  - partial
		mainScreen.capture(new Rectangle(50, 10,400,300)).save("captured_image", "screen_capture_rectangle.png");
	}

	public void ScreenClassTest4(){
		// other operations on screen object e.g. wait, type, click
		
		initChrome();
		objWebdriver.get("http://www.phptravels.net/login");
		delay(5000);
		try{
			Screen mainScreen 						= new Screen(0);
			String demoWebSiteImages		= "ScreenClassTestAssets\\phptravellogin"; 
			String demoUserIconImg 			= demoWebSiteImages + "\\user_icon.png";
			String demoUserEmailImg 			= demoWebSiteImages + "\\user_email.png";
			String demoUserPasswordImg 	= demoWebSiteImages + "\\user_password.png";
			String demoLoginsubmitImg 		= demoWebSiteImages + "\\user_submit.png";
			
			// assuming matching image is found (with acceptable tolerance) 
			mainScreen.wait(demoUserIconImg);
			// assuming matching image is found (with acceptable tolerance) 
			mainScreen.type(demoUserEmailImg,"dummy@dummy.com");
			// assuming matching image is found (with acceptable tolerance) 
			mainScreen.type(demoUserPasswordImg,"dummy1234");
			// assuming matching image is found (with acceptable tolerance) 
			mainScreen.click(demoLoginsubmitImg);			
		}catch(Exception e){}
		quitChrome();
	}
	
	@When("^Sikulix automation using Screen class$")
	public void Sikulix_automation_using_Screen_class(){
		System.out.println("INFO :: ------------------------Sikulix_automation_using_Screen_class");
		ScreenClassTest1();
		ScreenClassTest2();
		ScreenClassTest3();
		ScreenClassTest4();
	}
	
	@When("^Sikulix automation using Region class$")
	public void Sikulix_automation_using_Region_class(){
		System.out.println("INFO :: ------------------------Sikulix_automation_using_Region_class");
		RegionClassTest1();
		RegionClassTest2();
		RegionClassTest3();
		RegionClassTest4();
		RegionClassTest5();
	}

	@When("^Sikulix automation using Pattern class$")
	public void Sikulix_automation_using_Pattern_class(){
		System.out.println("INFO :: ------------------------Sikulix_automation_using_Pattern_class");
		PatternClassTest1();
		PatternClassTest2();
	}
	
	public void initChrome(){
		String browserDriverBasePath = new File("").getAbsoluteFile().toString() + 
				File.separator + "src" +
				File.separator + "test" +
				File.separator + "browserDriver" +
				File.separator;
		
	    String browserDriverBasePathChrome 	= browserDriverBasePath + "chromedriver.exe";
		File chromeDriverExecutable 					= new File(browserDriverBasePathChrome);
	    chromeDriverExecutable.setExecutable(true);
		System.setProperty("webdriver.chrome.driver",browserDriverBasePathChrome);
		ChromeOptions objChromeOptions 		= new ChromeOptions();
		objChromeOptions.addArguments("--incognito");
		DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
		chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, objChromeOptions);
		objWebdriver 											= new ChromeDriver(chromeCapabilities);
		objWebdriver.manage().window().setPosition(new Point(05,05));
		objWebdriver.manage().window().setSize(new Dimension(1350,750));
	}

	public void quitChrome(){
		if(null == objWebdriver) return;
		objWebdriver.close();
		objWebdriver.quit();
	}
	
	public void delay(int milliSec){
		try{Thread.sleep(milliSec);}catch(Exception d){}
	}
	
}

//		System.out.println("INFO :: ------------------------ sdsdcfd ------------------------");