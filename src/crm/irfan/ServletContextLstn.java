package crm.irfan;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import crm.irfan.entity.Calisan;

/**
 * Application Lifecycle Listener implementation class ServletContextLstn
 *
 */
@WebListener
public class ServletContextLstn implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ServletContextLstn() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  {
    	ServletContext sc = event.getServletContext();
    	
    	@SuppressWarnings("unchecked")
		ArrayList<Calisan> calisan = (ArrayList<Calisan>) sc.getAttribute("calisan");
    	if(calisan == null){
    		System.out.println("No calisan mevcut");
    		calisan = new ArrayList<Calisan>();
    		sc.setAttribute("calisan", calisan);
    	}
    }
	
}
