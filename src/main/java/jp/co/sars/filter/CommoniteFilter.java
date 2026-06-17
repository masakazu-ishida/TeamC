package jp.co.sars.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CommoniteFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {

		try {

			//出力時の文字化け対策はJSPにゆだねる。フィルターとしてはリクエストのみ
			request.setCharacterEncoding("UTF-8");

			filter.doFilter(request, response);

		} catch (ServletException e) {

			//致命的エラーは全てここで捕まえて処理する
			e.printStackTrace();
			throw e;
		}

	}

}
