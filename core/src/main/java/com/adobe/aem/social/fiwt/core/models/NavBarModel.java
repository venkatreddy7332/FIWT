package com.adobe.aem.social.fiwt.core.models;

import com.adobe.aem.social.fiwt.core.models.Navigation.Navigations;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavBarModel {

    @ValueMapValue
    private String logoReference;

    @ValueMapValue
    private String logoAlt;

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String id;

    @ValueMapValue
    private String btnText;

    @ValueMapValue
    private String btnLinkURL;

    @ValueMapValue
    private String LogoLinkURL;

    @ChildResource(name = "navigations")
    private List<Navigations> navigationsList;

    public String getLogoReference() {
        return logoReference;
    }

    public String getLogoAlt() {
        return logoAlt;
    }

    public String getHeading() {
        return heading;
    }

    public String getId() {
        return id;
    }

    public List<Navigations> getNavigationsList() {
        return navigationsList;
    }

    public String getBtnText() {
        return btnText;
    }

    public String getBtnLinkURL() {
        return btnLinkURL;
    }

    public String getLogoLinkURL() {
        return LogoLinkURL;
    }
}
