package com.mana.innovative.service.common.container;


import com.mana.innovative.dto.common.payload.SidebarImagePayload;
import com.mana.innovative.service.container.ResponseContainer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * The type Sidebar image response container.
 *
 * @param <T>   the type parameter
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlRootElement()
@XmlSeeAlso( { SidebarImagePayload.class, ResponseContainer.class } )
public class SidebarImageResponseContainer < T > extends ResponseContainer< T > {
}
