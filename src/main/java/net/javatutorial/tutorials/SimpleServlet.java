package net.javatutorial.tutorials;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acme.common.EnvConfig;

import java.io.*;
import javax.servlet.*;
import java.net.URL;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class SimpleServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {


	    String resourceName = "probeFailed.png";
		try {
            resourceName = checkReadiness();
        } catch(Error er ) {
            //Unable to check readiness, resource for unable to check readiness is set
            System.out.println("Error getting readiness");
            System.out.println(er);
        } 
          catch (Exception e) {
            //Unable to check readiness, resource for unable to check readiness is set
            System.out.println("Exception getting readiness");
            System.out.println(e);
        }
        
	  	// set the content type to image/jpeg.
        response.setContentType("image/jpeg");  
          
        ServletOutputStream out;
          
        // Writing this image 
        // content as a response 
        out = response.getOutputStream(); 
         

        URL url = this.getClass()
        .getClassLoader()
        .getResource(resourceName);

        // path of the image
        FileInputStream fin = new FileInputStream(url.getFile());  
  
        // getting image in BufferedInputStream  
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(out);  
          
        int ch =0;  
        while((ch=bin.read())!=-1)  
        {  
            // display image
            bout.write(ch);  
        }  
          
        // close all classes
        bin.close();  
        fin.close();  
        bout.close();  
        out.close();  	
	}

    //Stub method for checking the readiness of the machine
    private String checkReadiness() {
        System.out.println("Getting readiness for machine: " + EnvConfig.configureEnvDiscovery());
        return "probeActive.png";
    }
	
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started");
	}

	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}
	
}
