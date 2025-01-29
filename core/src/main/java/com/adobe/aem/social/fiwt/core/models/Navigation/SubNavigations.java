package com.adobe.aem.social.fiwt.core.models.Navigation;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SubNavigations {

    @ValueMapValue
    private String linkURL;

    @ValueMapValue
    private String subNavLabel;

    public String getLinkURL() {
        return linkURL;
    }

    public String getSubNavLabel() {
        return subNavLabel;
    }
}
