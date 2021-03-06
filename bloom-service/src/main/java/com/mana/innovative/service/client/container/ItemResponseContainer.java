package com.mana.innovative.service.client.container;

import com.mana.innovative.dto.client.payload.ItemsPayload;
import com.mana.innovative.service.container.ResponseContainer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * The type Item response container.
 *
 * @param <T>   the type parameter
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "response", namespace = "http://localhost:8443/bloom-test/rest/" )
@XmlSeeAlso( value = { ItemsPayload.class, ResponseContainer.class } )
public class ItemResponseContainer < T > extends ResponseContainer< T > {
}