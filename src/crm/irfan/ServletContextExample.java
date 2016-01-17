package crm.irfan;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
 
public class ServletContextExample implements ServletContextListener{
    ServletContext context;
    private int count = 0;
    public void contextInitialized(ServletContextEvent contextEvent) {
        System.out.println("Context Created");
        context = contextEvent.getServletContext();
        // set variable to servlet context
        context.setAttribute("TEST", "TEST_VALUE");
        
        while (true) {
			System.out.println(System.currentTimeMillis());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(++count == 100){
				break;
			}
		}
    }
    public void contextDestroyed(ServletContextEvent contextEvent) {
        context = contextEvent.getServletContext();
        System.out.println("Context Destroyed");
    }
}
