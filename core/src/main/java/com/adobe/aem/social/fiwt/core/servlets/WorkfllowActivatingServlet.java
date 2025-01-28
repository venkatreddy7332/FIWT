package com.adobe.aem.social.fiwt.core.servlets;

import com.adobe.aem.social.fiwt.core.services.ServiceResourceResolver;
import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowService;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.Workflow;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.model.WorkflowModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths({"/bin/workflow/servlet.json"})
public class WorkfllowActivatingServlet extends SlingAllMethodsServlet {

    @Reference
    private WorkflowService workflowService;

    @Reference
    private ServiceResourceResolver serviceResourceResolver;

    private static final Logger log= LoggerFactory.getLogger(WorkfllowActivatingServlet.class);


    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {


        String payLoad = request.getParameter("payLoad");
        if(payLoad != null){
            try {

                Session session= request.getResourceResolver().adaptTo(Session.class);
               WorkflowSession workflowSession =  workflowService.getWorkflowSession(session);
              WorkflowModel model = workflowSession.getModel("/var/workflow/models/pageActivationModel");
              WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH",payLoad);
              Workflow ss = workflowSession.startWorkflow(model, workflowData);
              log.info("Workflow started for the payload : {}", payLoad);
            } catch (WorkflowException e) {
                throw new RuntimeException(e);
            }
        }else{
            log.info("Please ADD Pay Load to trigger Workflow");
        }
    }
}
