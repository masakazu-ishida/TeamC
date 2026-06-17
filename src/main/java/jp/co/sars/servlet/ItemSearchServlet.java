package jp.co.sars.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.sars.dto.ItemsDTO;
import jp.co.sars.service.ItemSearchService;

/**
 * Servlet implementation class itemSearchServlet
 */
@WebServlet(name = "itemSearch", urlPatterns = { "/itemSearch" })
public class ItemSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = "/WEB-INF/itemSearch.jsp";
		HttpSession session = request.getSession();

		Object userId = session.getAttribute("userId");

		request.setAttribute("userId", userId);

		ItemSearchService itemSearchService = new ItemSearchService();

		String pr1 = request.getParameter("keyword");
		String pr2 = request.getParameter("category");

		String category;
		if (pr2.equals("1")) {
			category = "すべて";
		} else if (pr2.equals("2")) {
			category = "帽子";
		} else {
			category = "鞄";
		}

		List<ItemsDTO> itemList = itemSearchService.execute(pr1, pr2);

		request.setAttribute("keyword", pr1);
		request.setAttribute("category", category);
		request.setAttribute("itemList", itemList);
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
