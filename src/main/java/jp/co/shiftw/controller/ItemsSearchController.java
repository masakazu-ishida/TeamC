package jp.co.shiftw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.service.ItemsSearchService;

/**
 * Servlet implementation class ItemsSearchController
 */
@WebServlet("/ItemsSearchController")
public class ItemsSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemsSearchController() {
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
		doGet(request, response);

		String path = "/WEB-INF/admin/main.jsp";

		request.setCharacterEncoding("UTF-8");

		String categoryId = request.getParameter("categoryId");
		String name = request.getParameter("name");

		int Id = Integer.parseInt(categoryId);

		ItemsDTO dto = ItemsSearchService.findByCond(categoryId, name);

	}

}
