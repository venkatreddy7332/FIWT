package com.adobe.aem.social.fiwt.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModel {

    @ValueMapValue
    private String aboutHeading;

    @ValueMapValue
    private String address;

    @ValueMapValue
    private String buttonText;

    @ValueMapValue
    private String copyRight;

    @ValueMapValue
    private String email;

    @ValueMapValue
    private String galleryHeading;

    @ValueMapValue
    private String id;

    @ValueMapValue
    private String inputBoxId;

    @ValueMapValue
    private String newsLetterHeading;

    @ValueMapValue
    private String newsletterSubHeading;

    @ValueMapValue
    private String phone;

    @ValueMapValue
    private String placeHolderText;

    @ValueMapValue
    private String quickLinkHeading;

    @ValueMapValue
    private String textIsRich;

    @ChildResource(name = "socialMediaLinks")
    private List<SocialMediaLinks> socialMediaLinks;

    @ChildResource(name = "quickLinks")
    private List<QuickLinks> quickLinks;

    @ChildResource(name = "galleryImages")
    private List<GalleryImages> galleryImages;

    @ChildResource(name = "footerLinks")
    private List<FooterLinks> footerLinks;

    public String getAboutHeading() {
        return aboutHeading;
    }

    public String getAddress() {
        return address;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public String getEmail() {
        return email;
    }

    public String getGalleryHeading() {
        return galleryHeading;
    }

    public String getId() {
        return id;
    }

    public String getInputBoxId() {
        return inputBoxId;
    }

    public String getNewsLetterHeading() {
        return newsLetterHeading;
    }

    public String getNewsletterSubHeading() {
        return newsletterSubHeading;
    }

    public String getPhone() {
        return phone;
    }

    public String getPlaceHolderText() {
        return placeHolderText;
    }

    public String getQuickLinkHeading() {
        return quickLinkHeading;
    }

    public String getTextIsRich() {
        return textIsRich;
    }

    public List<SocialMediaLinks> getSocialMediaLinks() {
        return socialMediaLinks;
    }

    public List<QuickLinks> getQuickLinks() {
        return quickLinks;
    }

    public List<GalleryImages> getGalleryImages() {
        return galleryImages;
    }

    public List<FooterLinks> getFooterLinks() {
        return footerLinks;
    }

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

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public class QuickLinks {

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
}
