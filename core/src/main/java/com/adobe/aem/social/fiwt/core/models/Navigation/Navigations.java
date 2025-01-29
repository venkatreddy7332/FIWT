package com.adobe.aem.social.fiwt.core.models.Navigation;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Navigations {

    @ValueMapValue
    private String navLabel;

    @ValueMapValue
    private String linkURL;

    @ChildResource(name = "subNavigations")
    private List<SubNavigations> subNavigations;

    public String getNavLabel() {
        return navLabel;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public List<SubNavigations> getSubNavigations() {
        return subNavigations;
    }
}

