package com.adobe.aem.social.fiwt.core.services;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.Map;

@Component(service = PropertiesSearch.class)
public class PropertiesSearch {

    @Reference
    private QueryBuilder queryBuilder;

    String[] parameterNames = {"propertyTag", "propertyType", "price", "title", "description", "address", "area", "bedrooms", "restrooms", "other", "ownerName", "ownerContact", "ownerEmail", "cityLocation", "isNegotiable", "discount", "verifiedBy","propertyImage"};
    String resourceType="fiwt/components/custom/properties";
    public JsonArray getPropertiesJson(Map predicates, Session session, SlingHttpServletRequest request){
        JsonArray jsonArray = new JsonArray();
        if(predicates != null)
        predicates.put("type","nt:unstructured");
        predicates.put("1_property", "sling:resourceType");
        predicates.put("1_property.value",resourceType);
        predicates.put("orderby", "jcr:created");
        predicates.put("orderby.sort","dec");
        Query query =queryBuilder.createQuery(PredicateGroup.create(predicates),session);
        SearchResult searchResult = query.getResult();

        for(Hit hit:searchResult.getHits()){
            try {
                JsonObject jsonObject= new JsonObject();
                String path = hit.getPath();
                ValueMap valueMap=request.getResourceResolver().getResource(path).getValueMap();
                for(int j=0;j<this.parameterNames.length;j++) {
                    if(valueMap.get(this.parameterNames[j], String.class) != null) {
                        jsonObject.addProperty(this.parameterNames[j], valueMap.get(this.parameterNames[j], String.class));
                    }
                }
                jsonArray.add(jsonObject);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonArray;
    }
}
