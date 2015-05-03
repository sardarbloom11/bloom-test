package com.mana.innovative.dto.client;

import com.mana.innovative.dto.adapter.DateFormatAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.Objects;

/**
 * The type Item.

 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "item", namespace = "http://localhost:8080/bloom-test/rest/items" )
public class Item {

    private Long itemId;
    private Double itemPrice;
    private Double quantity;
    private Double weight;

    private String itemPriceCurrency;
    private String itemName;
    private String itemType;
    private String itemSubType;
    private String boughtFrom;
    private String quantityType;
    private String weightedUnit;

    private Date boughtDate;

    /**
     * Gets item id.
     *
     * @return the item id
     */
    @XmlElement( name = "item_id", nillable = false )
    public Long getItemId( ) {

        return itemId;
    }

    /**
     * Sets item id.
     *
     * @param itemId the item id
     */
    public void setItemId( Long itemId ) {

        this.itemId = itemId;
    }

    /**
     * Gets item price.
     *
     * @return the item price
     */
    @XmlElement( name = "item_price", defaultValue = "0.00", nillable = false )
    public Double getItemPrice( ) {

        return itemPrice;
    }

    /**
     * Sets item price.
     *
     * @param itemPrice the item price
     */
    public void setItemPrice( Double itemPrice ) {

        this.itemPrice = itemPrice;
    }

    /**
     * Gets item price currency.
     *
     * @return the item price currency
     */
    @XmlElement( name = "item_price_currency", defaultValue = "Dollar", nillable = false )
    public String getItemPriceCurrency( ) {

        return itemPriceCurrency;
    }

    /**
     * Sets item price currency.
     *
     * @param itemPriceCurrency the item price currency
     */
    public void setItemPriceCurrency( String itemPriceCurrency ) {

        this.itemPriceCurrency = itemPriceCurrency;
    }

    /**
     * Gets item name.
     *
     * @return the item name
     */
    @XmlElement( name = "item_name", defaultValue = "Unknown", nillable = false )
    public String getItemName( ) {

        return itemName;
    }

    /**
     * Sets item name.
     *
     * @param itemName the item name
     */
    public void setItemName( String itemName ) {

        this.itemName = itemName;
    }

    /**
     * Gets item type.
     *
     * @return the item type
     */
    @XmlElement( name = "item_type", defaultValue = "Food", nillable = false )
    public String getItemType( ) {

        return itemType;
    }

    /**
     * Sets item type.
     *
     * @param itemType the item type
     */
    public void setItemType( String itemType ) {

        this.itemType = itemType;
    }

    /**
     * Gets item sub type.
     *
     * @return the item sub type
     */
    @XmlElement( name = "item_sub_type", defaultValue = "None", nillable = false )
    public String getItemSubType( ) {
        return itemSubType;
    }

    /**
     * Sets item sub type.
     *
     * @param itemSubType the item sub type
     */
    public void setItemSubType( String itemSubType ) {
        this.itemSubType = itemSubType;
    }

    /**
     * Gets bought from.
     *
     * @return the bought from
     */
    @XmlElement( name = "bought_from", defaultValue = "SomeStore", nillable = false )
    public String getBoughtFrom( ) {
        return boughtFrom;
    }

    /**
     * Sets bought from.
     *
     * @param boughtFrom the bought from
     */
    public void setBoughtFrom( String boughtFrom ) {
        this.boughtFrom = boughtFrom;
    }

    /**
     * Gets bought date.
     *
     * @return the bought date
     */
    @XmlElement( name = "bought_date", nillable = false )
    @XmlJavaTypeAdapter( value = DateFormatAdapter.class )
    public Date getBoughtDate( ) {
        return boughtDate;
    }

    /**
     * Sets bought date.
     *
     * @param boughtDate the bought date
     */
    public void setBoughtDate( final Date boughtDate ) {
        this.boughtDate = boughtDate;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    @XmlElement( name = "quantity", defaultValue = "0", nillable = false )
    public Double getQuantity( ) {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity( final Double quantity ) {
        this.quantity = quantity;
    }

    /**
     * Gets quantity type.
     *
     * @return the quantity type
     */
    @XmlElement( name = "quantity_type", defaultValue = "units", nillable = false )
    public String getQuantityType( ) {
        return quantityType;
    }

    /**
     * Sets quantity type.
     *
     * @param quantityType the quantity type
     */
    public void setQuantityType( String quantityType ) {
        this.quantityType = quantityType;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    @XmlElement( name = "weight", defaultValue = "0", nillable = false )
    public Double getWeight( ) {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight( final Double weight ) {
        this.weight = weight;
    }

    /**
     * Gets weighted unit.
     *
     * @return the weighted unit
     */
    @XmlElement( name = "weighted_unit", defaultValue = "ounces", nillable = false )
    public String getWeightedUnit( ) {
        return weightedUnit;
    }

    /**
     * Sets weighted unit.
     *
     * @param weightedUnit the weighted unit
     */
    public void setWeightedUnit( final String weightedUnit ) {
        this.weightedUnit = weightedUnit;
    }


    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Item ) ) return false;
        Item item = ( Item ) o;
        return Objects.equals( getItemId( ), item.getItemId( ) ) &&
                Objects.equals( getItemPrice( ), item.getItemPrice( ) ) &&
                Objects.equals( getQuantity( ), item.getQuantity( ) ) &&
                Objects.equals( getWeight( ), item.getWeight( ) ) &&
                Objects.equals( getItemPriceCurrency( ), item.getItemPriceCurrency( ) ) &&
                Objects.equals( getItemName( ), item.getItemName( ) ) &&
                Objects.equals( getItemType( ), item.getItemType( ) ) &&
                Objects.equals( getItemSubType( ), item.getItemSubType( ) ) &&
                Objects.equals( getBoughtFrom( ), item.getBoughtFrom( ) ) &&
                Objects.equals( getQuantityType( ), item.getQuantityType( ) ) &&
                Objects.equals( getWeightedUnit( ), item.getWeightedUnit( ) ) &&
                Objects.equals( getBoughtDate( ), item.getBoughtDate( ) );
    }

    /**
     * Returns a string representation of the object. In general, the {@code toString} method returns a string that
     * "textually represents" this object. The result should be a concise but informative representation that is easy
     * for a person to read.
     *
     * @return {@link String}a string representation of the object.
     */
    @Override
    public String toString( ) {
        return "Item{" +
                "itemId=" + itemId +
                ", itemPrice=" + itemPrice +
                ", quantity=" + quantity +
                ", weight=" + weight +
                ", itemPriceCurrency='" + itemPriceCurrency + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemSubType='" + itemSubType + '\'' +
                ", boughtFrom='" + boughtFrom + '\'' +
                ", quantityType='" + quantityType + '\'' +
                ", weightedUnit='" + weightedUnit + '\'' +
                ", boughtDate=" + boughtDate +
                '}';
    }
}
