package com.mana.innovative.dto.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The type Sort.
 * <p/>
 * Created by Bloom/Rono on 2/11/2016.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "sort" )
public class Sort {

	private List< String > params;
	private Map< String, String > keyValueMap = new HashMap<>( );

	@XmlElement( name = "params" )
	public List< String > getParams( ) {
		return params;
	}

	public void setParams( List< String > params ) {
		this.params = params;
		this.setKeyValueMap( params );
	}

	@XmlTransient
	public Map< String, String > getKeyValueMap( ) {
		return keyValueMap;
	}

	private void setKeyValueMap( List< String > params ) {
		for ( String param : params ) {
			if ( param != null ) {
				String temp[] = param.split( "=" );
				if ( temp.length == 2 ) {
					this.getKeyValueMap( ).put( temp[ 0 ], temp[ 1 ] );
				}
				//todo give warnng for invalid sort options
			}
		}
	}

	@Override
	public int hashCode( ) {
		return Objects.hash( params );
	}

	@Override
	public boolean equals( final Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass( ) != o.getClass( ) ) return false;
		Sort that = ( Sort ) o;
		return Objects.equals( params, that.params );
	}

	@Override
	public String toString( ) {
		return "Sort{" +
				"params=" + params +
				", keyValueMap=" + keyValueMap +
				'}';
	}
}
