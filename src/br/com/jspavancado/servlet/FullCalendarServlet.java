package br.com.jspavancado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/buscarCalendarioDatas")
public class FullCalendarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FullCalendarServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String datas = "[{ \"title\": \"JDev Treinamento\", \"start\": \"2017-02-01\" }, { \"title\": \"Java Jedai\", \"start\": \"2017-02-10\" }]";
		
		response.setStatus(200);
		response.getWriter().write(datas);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
