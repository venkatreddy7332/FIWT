package com.adobe.aem.social.fiwt.core.servlets;


import com.day.cq.dam.api.AssetManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;

@Component(service = Servlet.class,
        property={"sling.servlet.paths=/bin/uploadimage",
                "sling.servlet.extensions=html",
                "sling.servlet.methods=" + "POST"})
public class FileUploadServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("application/json");
            InputStream is =request.getPart("file").getInputStream();
        String type = request.getPart("file").getContentType();
       String name = request.getPart("file").getSubmittedFileName();
            AssetManager assetManager = request.getResourceResolver().adaptTo(AssetManager.class);
            assetManager.createAsset("/content/dam/fiwt/"+name,is,type,true);
            request.getResourceResolver().commit();
            response.getWriter().print("helo");
    }
}

