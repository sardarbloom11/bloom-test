/**
 * @author Bloom This Class TabsResponseContainer.java is for Created at Aug 19, 2012 6:29:44 PM
 */
package com.mana.innovative.service.common.container;

import com.mana.innovative.dto.common.payload.TabsPayload;
import com.mana.innovative.service.container.ResponseContainer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * The type Tab response container.
 *
 * @param <T>   the type parameter Created by Bloom/Rono on $date $time.
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "response", namespace = "http://localhost:8080/rest/Bloom/tab" )
@XmlSeeAlso( { TabsPayload.class, ResponseContainer.class } )
public class TabResponseContainer < T > extends ResponseContainer< T > {

}
