package com.adobe.aem.social.fiwt.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PropertyListModel {

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String propertyListPath;

    @ValueMapValue
    private String propertyCards;

    @ValueMapValue
    private String browseMoreBtnText;

    @ValueMapValue
    private String browseMoreBtnHref;

    @ChildResource(name = "filters")
    private List<FilterData> filters;

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public String getPropertyListPath() {
        return propertyListPath;
    }

    public String getPropertyCards() {
        return propertyCards;
    }

    public String getBrowseMoreBtnText() {
        return browseMoreBtnText;
    }

    public String getBrowseMoreBtnHref() {
        return browseMoreBtnHref;
    }

    public List<FilterData> getFilters() {
        return filters;
    }
}
