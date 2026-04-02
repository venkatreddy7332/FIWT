package com.adobe.aem.social.fiwt.core.servlets;

import com.adobe.aem.social.fiwt.core.services.PropertiesSearch;
import com.google.gson.JsonArray;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "fiwt/components/custom/propertySearch", methods = "GET")
public class PropertiesSearchServlet extends SlingSafeMethodsServlet {

    @Reference
    private PropertiesSearch propertiesSearch;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        Resource componentResource = request.getResource();
        Session session = request.getResourceResolver().adaptTo(Session.class);
        ValueMap componentValueMap = componentResource.getValueMap();
        String searchInput = request.getParameter("searchInput");
        String propertyType = request.getParameter("propertyType");
        String location = request.getParameter("location");
        Map<String,String> predicates=new HashMap<>();
        predicates.put("path", "/content");
        if(searchInput != null || propertyType != null || location != null){

            if( searchInput != null){
                predicates.put("2_property", "propertyTag");
                predicates.put("2_property.value",searchInput);
            }
            if( propertyType != null){
                predicates.put("3_property", "propertyType");
                predicates.put("3_property.value",propertyType);
            }
            if( location != null){
                predicates.put("4_property", "cityLocation");
                predicates.put("4_property.value",location);
            }

            predicates.put("property.and","true");
            predicates.put("p.limit", "-1");

        }
        JsonArray jsonArray = null;
        if(session != null) {
            jsonArray = propertiesSearch.getPropertiesJson(predicates, session, request);
        }
        response.setContentType("application/json");
        if (jsonArray != null) {
            response.getWriter().print(jsonArray);
        }else{
            response.getWriter().print("error while fetching data");
        }
    }
}
