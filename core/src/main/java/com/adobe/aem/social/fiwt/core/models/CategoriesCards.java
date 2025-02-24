package com.adobe.aem.social.fiwt.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CategoriesCards {

    @ValueMapValue
    private String name;

    @ValueMapValue
    private int totalProperties;

    @ValueMapValue
    private String imgReference;

    private String alt;

    public String getName() {
        alt=name.toLowerCase();
       name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    public int getTotalProperties() {
        return totalProperties;
    }

    public String getImgReference() {
        return imgReference;
    }
}
