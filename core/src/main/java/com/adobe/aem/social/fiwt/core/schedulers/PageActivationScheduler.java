package com.adobe.aem.social.fiwt.core.schedulers;


import com.adobe.aem.social.fiwt.core.configs.PageActivationSchedulerConfig;
import com.adobe.aem.social.fiwt.core.services.ServiceResourceResolver;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;

@Component(service = Runnable.class,immediate=true)
@Designate(ocd= PageActivationSchedulerConfig.class)
public class PageActivationScheduler  implements Runnable{

    private String schedulerName;

    private String cronExpression;

    private boolean enable;

    private String pagePath;

    final static Logger log = LoggerFactory.getLogger(PageActivationScheduler.class);

    @Reference
    private Scheduler scheduler;

    @Reference
    private ServiceResourceResolver serviceResourceResolver;

    @Reference
    private Replicator replicator;

    @Activate
    @Modified
    public void activate(PageActivationSchedulerConfig config){
        this.schedulerName=config.schedulerName();
        this.cronExpression= config.cronExpression();
        this.enable=config.enable();
        this.pagePath=config.pagePath();

        if(this.enable && scheduler != null){
            ScheduleOptions options = scheduler.EXPR(this.cronExpression);
            options.canRunConcurrently(false);
            scheduler.schedule(this,options);
            log.info(this.schedulerName+" is Scheduled");
        }else{
            deactivate();
            log.info(this.schedulerName+" Scheduler is Disabled");
        }


    }

    @Deactivate
    public void deactivate(){
        scheduler.unschedule(this.schedulerName);
        log.info(this.schedulerName+" Schedular Unscheduled");
    }

    @Override
    public void run() {
    log.info("Scheduler is started");
    ResourceResolver resolver = serviceResourceResolver.getResourceResolver();
    Session session = resolver.adaptTo(Session.class);
    PageManager pageManager = resolver.adaptTo(PageManager.class);
    assert pageManager != null;
    Page page = pageManager.getPage(pagePath);
        if(session !=null && page != null){
            try {
                replicator.replicate(session, ReplicationActionType.ACTIVATE,pagePath);
                log.info(pagePath+ " : Page is replicated Successfully with page Activation scheduler");
            } catch (ReplicationException e) {
                throw new RuntimeException(e);
            }
        }else{
            log.info(pagePath+" : Page not found to replicate with page Activation scheduler");
        }

    }
}
