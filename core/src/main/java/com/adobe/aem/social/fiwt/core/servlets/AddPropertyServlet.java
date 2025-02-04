package com.adobe.aem.social.fiwt.core.servlets;


import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Random;

@Component(service = Servlet.class,
        property={"sling.servlet.paths=/bin/addProperty.json",
        "sling.servlet.extensions=html",
        "sling.servlet.methods=" + "POST"})

public class AddPropertyServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String pagePath = "/content/fiwt/lm/en/home/properties/propertyList";
        String componentSlingResourceType="fiwt/components/custom/properties";
        Random random = new Random();
        String randomNumber = String.format("%08d", 10000000 + random.nextInt(90000000));
        ResourceResolver resolver = request.getResourceResolver();
        Page page = resolver.adaptTo(PageManager.class).getPage(pagePath);
        if(page != null) {
            Resource idResource = page.adaptTo(Resource.class).getChild("jcr:content");
            int id = Integer.parseInt(idResource.getValueMap().get("id", String.class))+1;
            Resource propertiesListRootResource = resolver.getResource(pagePath + "/jcr:content/root");
            if (resolver != null && propertiesListRootResource != null) {

                Node pageRootNode = propertiesListRootResource.adaptTo(Node.class);
                try {
                    Node componentNode = pageRootNode.addNode("property" + id, "nt:unstructured");

                    String[] parameterNames = {"propertyTag", "propertyType", "price", "title", "description", "address", "area", "bedrooms", "restrooms", "other", "ownerName", "ownerContact", "ownerEmail", "cityLocation", "isNegotiable", "discount", "verifiedBy"};
                    componentNode.setProperty("pid", request.getParameter("propertyTag") + id);
                    componentNode.setProperty("sling:resourceType", componentSlingResourceType);

                    componentNode.setProperty( "propertyImage", "/content/dam/fiwt/makaan/property-1.jpg");
                    for (int i = 0; i < parameterNames.length; i++) {
                        componentNode.setProperty(parameterNames[i], getRequestParameter(request, parameterNames[i]));
                    }
                    idResource.adaptTo(ModifiableValueMap.class).put("id", String.valueOf(id));

                    resolver.commit();
                    response.getWriter().write("Submitted Property=====" + getRequestParameter(request, "title")+" ==With ID "+request.getParameter("propertyTag") + id);

                } catch (RepositoryException e) {
                    response.getWriter().write("error");
                    throw new RuntimeException(e);

                }


            } else {
                response.getWriter().write("error");
            }
        }

    }

    public String getRequestParameter(SlingHttpServletRequest request, String parameterName){


        String parameter;
        if(request.getRequestParameter(parameterName) != null){
            parameter= request.getParameter(parameterName);
        }else{
            parameter=" ";
        }
        return parameter;
    }
}
