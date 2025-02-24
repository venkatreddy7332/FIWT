package com.adobe.aem.social.fiwt.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterLinks {

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String link;

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

}
