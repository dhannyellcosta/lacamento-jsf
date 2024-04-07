package br.com.lancamento.filter;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.lancamento.entidades.Pessoa;
import br.com.lancamento.jpautil.JPAUtil;

@WebFilter(urlPatterns = "/*")
public class FilterAutenticacao implements Filter {

	@Inject
	private JPAUtil jpaUtil;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		Pessoa usuarioLogado = (Pessoa) session.getAttribute("usuarioLogado");
		boolean resourceRequest = req.getRequestURI()
				.startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/")
				|| req.getRequestURI().startsWith(req.getContextPath() + "/resources/");

		String url = req.getServletPath();

		if (!resourceRequest && !url.equalsIgnoreCase("/login.jsf") && usuarioLogado == null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsf?faces-redirect=true");
			dispatcher.forward(request, response);
			return;
		} else if(url.contains("admin/pessoa") && !usuarioLogado.getPerfil().equals("admin")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsf?faces-redirect=true");
			dispatcher.forward(request, response);
			return;
		}  else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		jpaUtil.getEntityManager();
	}

}
