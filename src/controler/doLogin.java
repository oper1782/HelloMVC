package controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerService;

/**
 * Servlet implementation class doLogin
 */
@WebServlet(name = "DoLogin", urlPatterns = { "/doLogin" })
public class doLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerID = request.getParameter("customerID");
		//perform business logic.
		
		CustomerService service = new CustomerService();
		Customer customer = service.findCustomer(customerID);
		request.setAttribute("customer", customer);
		
		// we can iterate over Lists using forEach in JSTL
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer("id006","Kim","kim@hansung.ac.kr"));
		customers.add(new Customer("id007","Lee","lee@hansung.ac.kr"));
		customers.add(new Customer("id008","Pakr","park@hansung.ac.kr"));
		request.setAttribute("customerList", customers);
		String page =null;
		if(customer ==null)
			page = "/view/error.jsp";
		
		else
			page = "/view/success.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}


}
