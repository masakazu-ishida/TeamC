package jp.co.shiftw.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.shiftw.service.CartAddService;

/**
 * Servlet implementation class CartAddController
 */
@WebServlet("/CartAddController")
public class CartAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartAddController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		String path = "/CartListController";

		HttpSession session = request.getSession(true);

		String userid = (String) session.getAttribute("userid");

		int item = Integer.parseInt(request.getParameter("itemId"));
		int amon = Integer.parseInt(request.getParameter("amount"));

		String strDate = request.getParameter("date");
		java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date expected = null;

		try {
			expected = dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}

		Date date = expected;

		CartAddService.CartAdd(userid, item, amon, date);

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
