package org.cedj.app.web.rest.conference.model;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.cedj.app.domain.conference.model.Duration;
import org.cedj.app.domain.conference.model.Session;
import org.cedj.app.web.rest.core.LinkableRepresenatation;
import org.cedj.app.web.rest.core.ResourceLink;

@XmlRootElement(name = "session", namespace = "urn:ced:session")
public class SessionRepresentation extends LinkableRepresenatation {

    private Session session;
    private UriBuilder uriBuilder;

    private Date start;
    private Date end;

    public SessionRepresentation() {
        session = new Session();
    }

    public SessionRepresentation(Session session, UriBuilder uriBuilder) {
        this.session = session;
        this.uriBuilder = uriBuilder;
    }

    @XmlElement
    public String getTitle() {
        return session.getTitle();
    }

    public void setTitle(String title) {
        session.setTitle(title);
    }

    @XmlElement
    public String getOutline() {
        return session.getOutline();
    }

    public void setOutline(String outline) {
        session.setOutline(outline);
    }

    @XmlElement
    public Date getStart() {
        return session.getDuration().getStart();
    }

    public void setStart(Date date) {
        start = date;
        setDurationIfBothDatesSet();
    }

    @XmlElement
    public Date getEnd() {
        return session.getDuration().getEnd();
    }

    public void setEnd(Date date) {
        end = date;
        setDurationIfBothDatesSet();
    }

    private void setDurationIfBothDatesSet() {
        if (start != null && end != null) {
            session.setDuration(new Duration(start, end));
        }
    }

    public List<ResourceLink> getLinks() {
        List<ResourceLink> links = super.getLinks();
        if (uriBuilder != null) {
            links.add(new ResourceLink("self", uriBuilder.path(session.getId()).build()));
        }
        return links;
    }

    public Session toSession() {
        return session;
    }
}