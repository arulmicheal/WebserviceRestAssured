package com.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public abstract class Reporter {

	public static ExtentHtmlReporter html;
	public static ExtentReports extent;
	public static ExtentTest test, suiteTest;
	public String testCaseName, testNodes, testDescription, category, authors;


	public void startResult() {
		html = new ExtentHtmlReporter("./reports/result.html");
		html.setAppendExisting(true);		
		extent = new ExtentReports();		
		extent.attachReporter(html);	
	}


	public ExtentTest startTestModule(String testCaseName, String testDescription) {
		suiteTest = extent.createTest(testCaseName, testDescription);
		return suiteTest;
	}



	public ExtentTest startTestCase(String testNodes) {
		test = 	suiteTest.createNode(testNodes);
		return test;
	}


	/**
	 * Step report for the execution
	 * @param desc description of the step
	 * @param status status of the step
	 */
	public void reportStep(String desc, String status)  {

		if(status.equalsIgnoreCase("PASS")) {
			test.pass(desc);			
		}else if (status.equalsIgnoreCase("FAIL")) {
			test.fail(desc);
			throw new RuntimeException();
		}else if (status.equalsIgnoreCase("WARNING")) {
			test.warning(desc);
		}else if (status.equalsIgnoreCase("INFO")) {
			test.info(desc);
		}						
	}

	public void endResult() {
		extent.flush();
	}	

}
