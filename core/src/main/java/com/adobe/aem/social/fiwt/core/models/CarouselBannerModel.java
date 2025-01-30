package com.adobe.aem.social.fiwt.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.*;

import javax.annotation.PostConstruct;
import java.util.*;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CarouselBannerModel {

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String carouselReference;

    @Self
    private Resource currentResource;

    private List<Map> breadcrumbList;

    @ValueMapValue
    private String type;

    @ChildResource(name = "carouselImgs")
    private List<CarouselImages> carouselImgs;

    @ValueMapValue
    private String id;

    @ValueMapValue
    private String text;

    @PostConstruct
    public void init(){
        if(currentResource != null) {
            ResourceResolver resourceResolver = currentResource.getResourceResolver();
            PageManager pagemgr = resourceResolver.adaptTo(PageManager.class);
            Page currentPage = pagemgr.getContainingPage(currentResource.getPath());
            if (currentPage != null) {
                if(heading == null){
                   heading = currentPage.getNavigationTitle();
                   if(heading == null){
                       heading = currentPage.getTitle();
                   }
                }
                if (carouselReference == null){
                    Resource featuredImageResource = currentPage.adaptTo(Resource.class).getChild("jcr:content/cq:featuredimage");
                    carouselReference = featuredImageResource.getValueMap().get("fileReference", String.class);
                }

                breadcrumbList = new ArrayList<>();
                breadcrumbList.add(getPageDeatils(currentPage, currentPage));
                Page page = currentPage;
                for(int i=0 ; i<2 ;i++) {
                    Page parentPage = page.getParent();
                    breadcrumbList.add(getPageDeatils(parentPage, currentPage));
                    page= parentPage;
                }
                Collections.reverse(breadcrumbList);
            }
        }

    }

    public String getHeading() {
        return heading;
    }

    public String getCarouselReference() {
        return carouselReference;
    }

    public Map<String,String> getPageDeatils(Page page, Page currentPage){
        Map<String, String> map = new HashMap<>();
        map.put("name",page.getName().toUpperCase());
        map.put("path",page.getPath());
        if(page == currentPage){
            map.put("active","text-body active");
        }else{
            map.put("active","");
        }
        return map;
    }

    public List<Map> getBreadcrumbList() {
        return breadcrumbList;
    }

    public String getType() {
        return type;
    }

    public List<CarouselImages> getCarouselImgs() {
        return carouselImgs;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
