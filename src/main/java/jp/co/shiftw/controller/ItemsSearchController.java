package jp.co.shiftw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.shiftw.dto.CategoriesDTO;
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		String path = "/WEB-INF/main/Search_result.jsp";

		request.setCharacterEncoding("UTF-8");

		String categoryId = request.getParameter("categoryId");
		String name = request.getParameter("name");
		String pageNumber = request.getParameter("pageNumber");

		int Id = Integer.parseInt(categoryId);
		int Number = Integer.parseInt(pageNumber);

		//List<ItemsDTO> dto = ItemsSearchService.findByCond(Id, name);
		List<ItemsDTO> page = ItemsSearchService.findByCondForPaging(Id, name, Number);
		CategoriesDTO cate = ItemsSearchService.findById(Id);

		request.setAttribute("keyword", name);
		request.setAttribute("category", cate);
		request.setAttribute("pageNumber", Number);
		request.setAttribute("ItemsDTO", page);
		//request.setAttribute("pagedto", page);

		RequestDispatcher rd = request.getRequestDispatcher(path);

		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
