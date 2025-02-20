package com.adobe.aem.social.fiwt.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SocialMediaLinks {

    @ValueMapValue
    private String socialMediaFaClass;

    @ValueMapValue
    private String socialMediaHref;

    public String getSocialMediaFaClass() {
        return socialMediaFaClass;
    }

    public String getSocialMediaHref() {
        return socialMediaHref;
    }
}