package jp.co.sars.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginTest
 */
@WebServlet("/Login2")
public class LoginTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginTestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = "/WEB-INF/login.jsp";

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(true);
		String resultPath = (String) session.getAttribute("path");
		String resultItemIdStr = (String) session.getAttribute("pendingItemId");
		String resultAmountStr = (String) session.getAttribute("pendingAmount");

		int resultItemId = 0;
		int resultAmount = 0;

		if (resultItemIdStr != null && !resultItemIdStr.isEmpty()) {
			resultItemId = Integer.parseInt(resultItemIdStr);
		}
		if (resultAmountStr != null && !resultAmountStr.isEmpty()) {
			resultAmount = Integer.parseInt(resultAmountStr);
		}

		request.setAttribute("path", resultPath);
		request.setAttribute("itemId", resultItemId);
		request.setAttribute("amount", resultAmount);
		RequestDispatcher rd = request.getRequestDispatcher(path);

		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
