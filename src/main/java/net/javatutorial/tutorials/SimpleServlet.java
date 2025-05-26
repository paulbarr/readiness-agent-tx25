package net.javatutorial.tutorials;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.acme.common.EnvConfig;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.URL;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class SimpleServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {

	String resourceName = "accessDenied.png";
		
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
	
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started on machine: " + EnvConfig.configureEnvDiscovery());
	}

	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}
	
}
