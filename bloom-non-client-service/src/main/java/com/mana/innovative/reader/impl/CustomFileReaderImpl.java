package com.mana.innovative.reader.impl;

import com.mana.innovative.dto.client.payload.ItemsPayload;
import com.mana.innovative.reader.CustomSpecificFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomFileReaderImpl
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service( value = "customFileReaderImpl" )
public class CustomFileReaderImpl implements CustomSpecificFileReader< ItemsPayload > {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CustomFileReaderImpl.class );

    /**
     * Read excel file reader.
     *
     * @param file the file
     * @return the items payload
     */
    @Override
    public ItemsPayload readExcelFileReader( File file ) {
        String location = this.getClass( ).getCanonicalName( ) + "#readExcelFileReader()";
        logger.debug( "Starting " + location );
        // todo
        logger.debug( "Finishing " + location );
        return null;
    }

    /**
     * Read xML file.
     *
     * @param file the file
     * @return the items payload
     */
    @Override
    public ItemsPayload readXMLFile( final File file ) {

        String location = this.getClass( ).getCanonicalName( ) + "#readXMLFile()";
        logger.debug( "Starting " + location );
        ItemsPayload itemsPayload;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance( ItemsPayload.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller( );
//            jaxbUnmarshaller.setAdapter( new DateFormatAdapter( ) );
//            jaxbUnmarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
            itemsPayload = ( ItemsPayload ) jaxbUnmarshaller.unmarshal( file );
        } catch ( JAXBException exception ) {
            exception.printStackTrace( );
            logger.error( "An exception occurred while unmarshalling", exception );
            return null;
        }
        logger.debug( "Finishing " + location );
        return itemsPayload;
    }
}
