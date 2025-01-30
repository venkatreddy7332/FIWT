package com.adobe.aem.social.fiwt.core.services.Workflow;

import com.adobe.aem.social.fiwt.core.services.ServiceResourceResolver;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;

@Component(service = WorkflowProcess.class, property = {
        "process.label= VV Work flow Venkat"
}
)
public class PageActivationProcess implements WorkflowProcess{

    @Reference
    private Replicator replicator;

    @Reference
    private ServiceResourceResolver serviceResourceResolver;

    final static Logger log = LoggerFactory.getLogger(PageActivationProcess.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {

        log.info("in page activation workflow process");
        String payload=workItem.getWorkflow().getWorkflowData().getPayload().toString();
        ResourceResolver resolver = serviceResourceResolver.getResourceResolver();
        Session session = resolver.adaptTo(Session.class);
        try {
            replicator.replicate(session, ReplicationActionType.ACTIVATE,payload);
            log.info("Replicated page : "+payload);
        } catch (ReplicationException e) {
            throw new RuntimeException(e);
        }

    }
}
