package com.adobe.aem.social.fiwt.core.servlets;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "fiwt/components/custom/propertyList", extensions = "json")
public class PropertyListServlet extends SlingSafeMethodsServlet {

    @Reference
    private QueryBuilder queryBuilder;

    String[] parameterNames = {"propertyTag", "propertyType", "price", "title", "description", "address", "area", "bedrooms", "restrooms", "other", "ownerName", "ownerContact", "ownerEmail", "cityLocation", "isNegotiable", "discount", "verifiedBy","propertyImage"};

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        Resource componentResource = request.getResource();
        Session session = request.getResourceResolver().adaptTo(Session.class);
        ValueMap componentValueMap = componentResource.getValueMap();
        String[] variables={"path"};
        String resourceType="fiwt/components/custom/properties";
        Map<String,String> predicates=new HashMap<>();
        predicates.put("type","nt:unstructured");
        if(componentValueMap.get("path", String.class) != null) {
            predicates.put("path", componentValueMap.get("propertyListPath", String.class));
        }else{
            predicates.put("path", "/content");
        }
        predicates.put("1_property", "sling:resourceType");
        predicates.put("1_property.value",resourceType);
        if(request.getParameter("filter") != null){
            predicates.put("2_property", "propertyTag");
            predicates.put("2_property.value",request.getParameter("filter"));
            predicates.put("property.and","true");
        }
        if(componentValueMap.get("propertyCards", String.class) != null) {
            predicates.put("p.limit", componentValueMap.get("propertyCards", String.class));
        }else{
            predicates.put("p.limit", "9");
        }
        predicates.put("orderby", "jcr:created");
        predicates.put("orderby.sort","dec");


        Query query =queryBuilder.createQuery(PredicateGroup.create(predicates),session);
        SearchResult searchResult = query.getResult();
        JsonArray jsonArray = new JsonArray();
        for(Hit hit:searchResult.getHits()){
            try {
                JsonObject jsonObject= new JsonObject();
                String path = hit.getPath();
                ValueMap valueMap=request.getResourceResolver().getResource(path).getValueMap();
                for(int j=0;j<this.parameterNames.length;j++) {
                    jsonObject.addProperty(this.parameterNames[j], valueMap.get(this.parameterNames[j], String.class));
                }
                jsonArray.add(jsonObject);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }
        response.setContentType("application/json");
        response.getWriter().print(jsonArray);
    }
}
