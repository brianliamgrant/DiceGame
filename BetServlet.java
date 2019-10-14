package controller;

import java.io.IOException;
import java.text.NumberFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dice;
import model.Game;

/**
 * Servlet implementation class BetServlet
 */
@WebServlet(description = "Receives the request from the index.jsp and forwards it to BetPage.jsp to initialize the game", 
urlPatterns = { "/BetServlet" })
public class BetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Game g = new Game();
		g.setBalance(Double.parseDouble(request.getParameter("currBalance")));
		g.setRollCounter(Integer.parseInt(request.getParameter("counter")));
		double balance = g.getBalance();
		
		//define the url string of the page the user should view first
		String url = "";
		if(balance > 0) {
			g.updateRollCounter();
			url = "/BetPage.jsp";
		} else {
			url = "/EndGame.jsp";
		}
		int counter = g.getRollCounter();
		
		//pass the request and response object to the results jsp
		request.setAttribute("balance", balance);
		request.setAttribute("counter", counter);
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
