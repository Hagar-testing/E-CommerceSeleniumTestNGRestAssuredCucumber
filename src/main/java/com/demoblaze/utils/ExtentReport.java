package com.demoblaze.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

    private static ExtentReports report;
    private static ExtentTest test;
    final static ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();

    public static void initReports() {
        report = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/extend-report/TestNGExtentReports.html");
        report.attachReporter(spark);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Extent Report");
        spark.config().setReportName("Demoblaze Report");
    }

    public static void createTest(String testCaseName) {
        if(threadLocal.get() != null) {
            test = report.createTest(testCaseName);
            threadLocal.set(test);
        }
    }

    public static void removeTest(String testCaseName) {
        report.removeTest(testCaseName);
    }

    public static void info(String message) {
        if (test != null) {
            threadLocal.get().info(message);
        }
    }

    public static void info(Markup m) {
        if(threadLocal.get() != null) threadLocal.get().info(m);
    }

    public static void pass(String message) {
        if(threadLocal.get() != null) threadLocal.get().pass(message);
    }

    public static void pass(Markup m) {
        if(threadLocal.get() != null) threadLocal.get().pass(m);
    }

    public static void fail(String message) {
        if(threadLocal.get() != null) threadLocal.get().fail(message);
    }

    public static void fail(Markup m) {
        if(threadLocal.get() != null) threadLocal.get().fail(m);
    }

    public static void fail(Throwable t) {
        if(threadLocal.get() != null) threadLocal.get().fail(t);
    }

    public static void fail(Media media) {
        if(threadLocal.get() != null) threadLocal.get().fail(media);
    }

    public static void skip(String message) {
        if(threadLocal.get() != null) threadLocal.get().skip(message);
    }

    public static void skip(Markup m) {
        if(threadLocal.get() != null) threadLocal.get().skip(m);
    }

    public static void skip(Throwable t) {
        if(threadLocal.get() != null) threadLocal.get().skip(t);
    }

    public static void flushReports() {
        if(threadLocal.get() != null) report.flush();
    }

}