package com.adobe.aem.social.fiwt.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.*;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PropertySearchModel {

    @ValueMapValue
    private String id;

    @ValueMapValue
    private String btnId;

    @ValueMapValue
    private String btnText;

    @ValueMapValue
    private String inputId;

    @ValueMapValue
    private String locationId;

    @ValueMapValue
    private String placeholder;

    @ValueMapValue
    private String propertyTypeId;

    @ValueMapValue
    private String type;

    @ChildResource(name = "typeOptions")
    private List<Map> typeOptions;

    @ChildResource(name = "locationOptions")
    private List<Map> locationOptions;

    @ValueMapValue
    private String locationsPath;

    @ValueMapValue
    private String propertiesPath;

    @Self
    private Resource resource;

    @PostConstruct
    public void init(){

        if (type.equalsIgnoreCase("dynamic")){
            typeOptions= getSelectOptionsWithPagePath(resource,propertiesPath);
            locationOptions=getSelectOptionsWithPagePath(resource,locationsPath);
        }else {
            typeOptions= getSelectOptionsMultifield(resource, "typeOptions");
            locationOptions=getSelectOptionsMultifield(resource, "locationOptions");
        }

    }

    private List<Map> getSelectOptionsMultifield(Resource resource, String childNodeName) {
        List <Map> list = new ArrayList<>();
        Resource optionsResource = resource.getChild(childNodeName);
        if(optionsResource != null) {
            Iterator<Resource> itemsResource = optionsResource.listChildren();

            while (itemsResource.hasNext()){
                Map<String,String> map = new HashMap<>();
                ValueMap valueMap = itemsResource.next().getValueMap();
                map.put("text",valueMap.get("text", String.class));
                map.put("value",valueMap.get("value", String.class));
                list.add(map);
            }
        }
       return list;
    }

    private List<Map> getSelectOptionsWithPagePath(Resource resource, String propertiesPath) {
        List <Map> list=new ArrayList<>();
        Page page= getPage(resource,propertiesPath);
        Iterator<Page> childPages = page.listChildren();
        while(childPages.hasNext()){
           Map<String,String> map= new HashMap<>();
           Page childPage = childPages.next();
           map.put("text", childPage.getTitle());
           map.put("value", childPage.getName());
           list.add(map);
        }
        return list;
    }

    private Page getPage(Resource resource, String pagePath) {
        PageManager pageManager = resource.getResourceResolver().adaptTo(PageManager.class);
        Page page = pageManager.getContainingPage(pagePath);
        return page;
    }


    public String getId() {
        return id;
    }

    public String getBtnId() {
        return btnId;
    }

    public String getBtnText() {
        return btnText;
    }

    public String getInputId() {
        return inputId;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getPropertyTypeId() {
        return propertyTypeId;
    }

    public String getType() {
        return type;
    }

    public List<Map> getTypeOptions() {
        return typeOptions;
    }

    public List<Map> getLocationOptions() {
        return locationOptions;
    }

    public String getLocationsPath() {
        return locationsPath;
    }

    public String getPropertiesPath() {
        return propertiesPath;
    }
}
