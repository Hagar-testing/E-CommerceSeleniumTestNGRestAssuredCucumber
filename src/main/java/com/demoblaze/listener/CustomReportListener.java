package com.demoblaze.listener;

import com.demoblaze.utils.ExtentReport;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;

public class CustomReportListener implements EventListener {


	private void runStarted(TestRunStarted event) {
		System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSS");
		ExtentReport.initReports();
	};

	private void runFinished(TestRunFinished event) {
		ExtentReport.flushReports();
	};

	@Override
	public void setEventPublisher(EventPublisher eventPublisher) {
		eventPublisher.registerHandlerFor(TestRunStarted.class, this::runStarted);
		eventPublisher.registerHandlerFor(TestRunFinished.class, this::runFinished);
	}


}