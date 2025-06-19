package jp.co.shiftw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.service.ItemsSearchService;

/**
 * Servlet implementation class ItemDetailController
 */
@WebServlet("/ItemsDetailController")
public class ItemsDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemsDetailController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		String path = "/WEB-INF/main/Items_detail.jsp";

		request.setCharacterEncoding("UTF-8");

		String Id = request.getParameter("itemId");

		int unique = Integer.parseInt(Id);

		ItemsDTO dto = ItemsSearchService.findByItemId(unique);

		request.setAttribute("itemId", unique);
		request.setAttribute("dto", dto);

		RequestDispatcher rd = request.getRequestDispatcher(path);

		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
