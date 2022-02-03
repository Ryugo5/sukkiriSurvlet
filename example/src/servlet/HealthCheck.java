package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mdel.HealthCheckLogic;
import mdel.health;


/**
 * Servlet implementation class HealthCheck
 */
@WebServlet("/HealthCheck")
public class HealthCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HealthCheck() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException,
    IOException {
    	//フォワード
    			RequestDispatcher dispatcher = 
    					request.getRequestDispatcher
    					("/WEB-INF/jsp/healthCheck.jsp");
    			dispatcher.forward(request, response);
    			
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stu
		
		//リクエストパラメータを取得
		String weight = request.getParameter("weight");
		String height = request.getParameter("height");
		
		//入力値をプロパティに設定
		health health = new health();
		health.setHeight(Double.parseDouble(height));
		health.setWeight(Double.parseDouble(weight));
		
		//健康診断結果を実行し結果を設定
		HealthCheckLogic healthCheckLogic = new HealthCheckLogic();
		healthCheckLogic.execute(health);
		
		//リクエストスコープに保存
		request.setAttribute("health", health);
		
		//フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher
				("/WEB-INF/jsp/HealthCheckResult.jsp");
		dispatcher.forward(request,  response);
		doGet(request, response);
	}

}
