package com.adobe.aem.social.fiwt.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class GalleryImages {

    @ValueMapValue
    private String imgReference;

    @ValueMapValue
    private String alt;

    public String getImgReference() {
        return imgReference;
    }

    public String getAlt() {
        return alt;
    }
}
