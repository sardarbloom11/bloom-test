package com.mana.innovative.domain.common.email;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * The type Custom event.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "custom_events" )
public class CustomEvent {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CustomEvent.class );

    /**
     * The Custom event id.
     */
    @Id
    @GeneratedValue
    @Column( name = "custom_event_id", unique = true, nullable = false )
    private long customEventId;

    /**
     * The Attachment.
     */
    @Column( name = "has_attachment" )
    private boolean attachment;

    /**
     * The Is scheduler.
     */
    @Column( name = "use_scheduler" )
    private boolean isScheduler;

    /**
     * The Time of event.
     */
    @Column( name = "time_of_event" )
    private String timeOfEvent;

    /**
     * The Event date.
     */
    @Column( name = "event_date", columnDefinition = "Timestamp" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date eventDate;

    /**
     * The Subject.
     */
    @Column( name = "subject" )
    private String subject;

    /**
     * The Body.
     */
    @Column( name = "body" )
    private String body;

    /**
     * The Attachment location.
     */
    @Column( name = "attachment_location" )
    private String attachmentLocation;

    /**
     * The Event name.
     */
    @Column( name = "event_name" )
    private String eventName;

    /**
     * The Receivers.
     */
    @Column( name = "receivers" )
    private String receivers;

    /**
     * The Cc receivers.
     */
    @Column( name = "cc_receivers" )
    private String ccReceivers;

    /**
     * The Bcc receivers.
     */
    @Column( name = "bcc_receivers" )
    private String bccReceivers;

    /**
     * The Optional data.
     */
    @Column( name = "optional_data" )
    private String optionalData;


    /**
     * Gets custom event id.
     *
     * @return the custom event id
     */
    public long getCustomEventId( ) {
        return customEventId;
    }

    /**
     * Sets custom event id.
     *
     * @param customEventId the custom event id
     */
    public void setCustomEventId( final long customEventId ) {
        this.customEventId = customEventId;
    }

    /**
     * Is attachment.
     *
     * @return the boolean
     */
    public boolean isAttachment( ) {
        return attachment;
    }

    /**
     * Sets attachment.
     *
     * @param attachment the attachment
     */
    public void setAttachment( final boolean attachment ) {
        this.attachment = attachment;
    }

    /**
     * Is scheduler.
     *
     * @return the boolean
     */
    public boolean isScheduler( ) {
        return isScheduler;
    }

    /**
     * Sets is scheduler.
     *
     * @param isScheduler the is scheduler
     */
    public void setIsScheduler( final boolean isScheduler ) {
        this.isScheduler = isScheduler;
    }

    /**
     * Gets time of event.
     *
     * @return the time of event
     */
    public String getTimeOfEvent( ) {
        return timeOfEvent;
    }

    /**
     * Sets time of event.
     *
     * @param timeOfEvent the time of event
     */
    public void setTimeOfEvent( String timeOfEvent ) {
        this.timeOfEvent = timeOfEvent;
    }

    /**
     * Gets event date.
     *
     * @return the event date
     */
    public Date getEventDate( ) {
        return eventDate;
    }

    /**
     * Sets event date.
     *
     * @param timeOfEvent the time of event
     */
    public void setEventDate( final Date timeOfEvent ) {
        this.eventDate = timeOfEvent;
    }

    /**
     * Gets subject.
     *
     * @return the subject
     */
    public String getSubject( ) {
        return subject;
    }

    /**
     * Sets subject.
     *
     * @param subject the subject
     */
    public void setSubject( final String subject ) {
        this.subject = subject;
    }

    /**
     * Gets body.
     *
     * @return the body
     */
    public String getBody( ) {
        return body;
    }

    /**
     * Sets body.
     *
     * @param body the body
     */
    public void setBody( final String body ) {
        this.body = body;
    }

    /**
     * Gets attachment location.
     *
     * @return the attachment location
     */
    public String getAttachmentLocation( ) {
        return attachmentLocation;
    }

    /**
     * Sets attachment location.
     *
     * @param attachmentLocation the attachment location
     */
    public void setAttachmentLocation( final String attachmentLocation ) {
        this.attachmentLocation = attachmentLocation;
    }

    /**
     * Gets event name.
     *
     * @return the event name
     */
    public String getEventName( ) {
        return eventName;
    }

    /**
     * Sets event name.
     *
     * @param eventName the event name
     */
    public void setEventName( final String eventName ) {
        this.eventName = eventName;
    }

    /**
     * Gets receivers.
     *
     * @return the receivers
     */
    public String getReceivers( ) {
        return receivers;
    }

    /**
     * Sets receivers.
     *
     * @param receivers the receivers
     */
    public void setReceivers( final String receivers ) {
        this.receivers = receivers;
    }

    /**
     * Gets cc receivers.
     *
     * @return the cc receivers
     */
    public String getCcReceivers( ) {
        return ccReceivers;
    }

    /**
     * Sets cc receivers.
     *
     * @param ccReceivers the cc receivers
     */
    public void setCcReceivers( final String ccReceivers ) {
        this.ccReceivers = ccReceivers;
    }

    /**
     * Gets bcc receivers.
     *
     * @return the bcc receivers
     */
    public String getBccReceivers( ) {
        return bccReceivers;
    }

    /**
     * Sets bcc receivers.
     *
     * @param bccReceivers the bcc receivers
     */
    public void setBccReceivers( final String bccReceivers ) {
        this.bccReceivers = bccReceivers;
    }

    /**
     * Gets optional data.
     *
     * @return the optional data
     */
    public String getOptionalData( ) {
        return optionalData;
    }

    /**
     * Sets optional data.
     *
     * @param optionalData the optional data
     */
    public void setOptionalData( final String optionalData ) {
        this.optionalData = optionalData;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     *
     * @return the boolean
     */
    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof CustomEvent ) ) return false;

        CustomEvent that = ( CustomEvent ) o;

        if ( getCustomEventId( ) != that.getCustomEventId( ) ) return false;
        if ( isAttachment( ) != that.isAttachment( ) ) return false;
        if ( isScheduler( ) != that.isScheduler( ) ) return false;
        if ( getEventDate( ) != null ? !getEventDate( ).equals( that.getEventDate( ) ) : that.getEventDate( ) != null )
            return false;
        if ( getSubject( ) != null ? !getSubject( ).equals( that.getSubject( ) ) : that.getSubject( ) != null )
            return false;
        if ( getBody( ) != null ? !getBody( ).equals( that.getBody( ) ) : that.getBody( ) != null ) return false;
        if ( getAttachmentLocation( ) != null ? !getAttachmentLocation( ).equals( that.getAttachmentLocation( ) ) : that.getAttachmentLocation( ) != null )
            return false;
        if ( getEventName( ) != null ? !getEventName( ).equals( that.getEventName( ) ) : that.getEventName( ) != null )
            return false;
        if ( getReceivers( ) != null ? !getReceivers( ).equals( that.getReceivers( ) ) : that.getReceivers( ) != null )
            return false;
        if ( getCcReceivers( ) != null ? !getCcReceivers( ).equals( that.getCcReceivers( ) ) : that.getCcReceivers( ) != null )
            return false;
        if ( getBccReceivers( ) != null ? !getBccReceivers( ).equals( that.getBccReceivers( ) ) : that.getBccReceivers( ) != null )
            return false;
        return !( getOptionalData( ) != null ? !getOptionalData( ).equals( that.getOptionalData( ) ) : that.getOptionalData( ) != null );

    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "CustomEvent{" +
                "customEventId=" + customEventId +
                ", attachment=" + attachment +
                ", isScheduler=" + isScheduler +
                ", timeOfEvent=" + eventDate +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", attachmentLocation='" + attachmentLocation + '\'' +
                ", eventName='" + eventName + '\'' +
                ", receivers='" + receivers + '\'' +
                ", ccReceivers='" + ccReceivers + '\'' +
                ", bccReceivers='" + bccReceivers + '\'' +
                ", optionalData='" + optionalData + '\'' +
                '}';
    }

}