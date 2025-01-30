package com.adobe.aem.social.fiwt.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductCategoryModel {

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String id;

    @ChildResource(name = "categories")
    private List<CategoriesCards> categories;

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public List<CategoriesCards> getCategories() {
        return categories;
    }
}
