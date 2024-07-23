package ecom.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
    
    public static ExtentReports generateExtentReport() {
        ExtentReports extentReport = new ExtentReports();
        
        // Set up the report location
        File extentReportFile = new File(System.getProperty("user.dir") + "\\TestOutput\\extentReport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        
        // Configure the report
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Demoblaze Ecom Test Automation Results Report");
        sparkReporter.config().setDocumentTitle("Ecom Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
        
        extentReport.attachReporter(sparkReporter);
        
        // Load configuration properties
        Properties configProp = new Properties();
        File configPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\ecom\\config\\config.properties");
        
        try (FileInputStream fisConfigProp = new FileInputStream(configPropFile)) {
            if (configPropFile.exists()) {
                configProp.load(fisConfigProp);
                extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
                extentReport.setSystemInfo("Browser Name", configProp.getProperty("browser"));
                extentReport.setSystemInfo("Email", configProp.getProperty("validUsername"));
                extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
            } else {
                System.err.println("Config file does not exist at: " + configPropFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Set system information
        extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReport.setSystemInfo("Username", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
        ExtentReports extent = new ExtentReports();
        ExtentObserver reporter = null;
		extent.attachReporter(reporter);

        return extent;
    }
}
