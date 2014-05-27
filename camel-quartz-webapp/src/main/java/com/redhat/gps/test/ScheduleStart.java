package com.redhat.gps.test;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.*;

/**
 * Servlet implementation class ScheduleStart
 */
public class ScheduleStart extends HttpServlet {
	private static final long serialVersionUID = 1934873489673124172L;
	Logger logger = Logger.getLogger(ScheduleStart.class);

    /**
     * Default constructor. 
     */
    public ScheduleStart() {
    	logger.info("ScheduleStart Constructor called");

    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		logger.info("Init method called.............");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("doGet method called.............");
	}

}
