package com.mana.innovative.dao;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Item;
import junit.framework.Assert;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * This class is for testing given {@link ItemDAO#updateItem(Item, boolean)}
 * <p/>
 * Please uncomment the following lines to enable Spring Integration Test the 2nd line requires location on Context
 * Config Files for beans and properties extra, the 1st one is to enble Spring for the Class
 *
 * @ RunWith(value = SpringJUnit4ClassRunner.class | MockitoWithJunitRunner.Class)
 * @ ContextConfiguration(location {"loc1"."loc2"})
 * @ TransactionConfiguration   <--- Only If required
 * @ Transactional              <--- Only If required
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/dbConfig-test.xml"})
@TransactionConfiguration
@Transactional
public class WhenUpdateAnItemTestItemDAOUpdateItem {


    @Resource
    private ItemDAO itemDAO;

    @Resource
    private SessionFactory sessionFactory;

    private Item dummyItem;


    /**
     * This method is to initialize Objects and configuration files before testing test method
     */
    @Before
    public void setUp() throws Exception {


        dummyItem = new Item();
        dummyItem.setItemId(TestConstants.ONE);
//        dummyItem.setItemName(TestConstants.TEST_VALUE);
//        dummyItem.setItemPriceCurrency(TestConstants.TEST_PRICE_CURRENCY);
        dummyItem.setItemType(TestConstants.UPDATED_TEST_VALUE);
//        dummyItem.setItemPrice(TestConstants.THREE);

    }

    @Test
    public void testItemDAONotNull() {

        Assert.assertNotNull(itemDAO);
    }

    /**
     * todo This method is to test the behavior of ...
     */
    @Test
    @Rollback(value = true)
    @Transactional(propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED)
    public void testItemDAOUpdate() throws Exception {

//        dummyItem.setItemPrice(TestConstants.UPDATED_ITEM_PRICE);
        dummyItem.setItemName(TestConstants.UPDATED_TEST_VALUE);
        dummyItem.setItemPriceCurrency(TestConstants.UPDATED_TEST_VALUE);

        DAOResponse<Item> itemDAOResponse = itemDAO.updateItem(dummyItem, TestConstants.IS_ERROR);

        Assert.assertTrue(itemDAOResponse.isRequestSuccess());
        Assert.assertTrue(itemDAOResponse.isUpdate());
        Assert.assertFalse(itemDAOResponse.isCreate());
        Assert.assertFalse(itemDAOResponse.isDelete());

        dummyItem = itemDAOResponse.getResults().get(TestConstants.ZERO);
//        dummyItem = itemDAO.getItemByItemId(dummyItem.getItemId(), TestConstants.IS_ERROR).getResults().get
//                (TestConstants.ZERO);
        System.out.println(dummyItem);

        Assert.assertNotNull(dummyItem);
        Assert.assertEquals("Values are not equal", TestConstants.UPDATED_TEST_VALUE, dummyItem.getItemName());
        Assert.assertEquals("Values are not equal", TestConstants.UPDATED_TEST_VALUE, dummyItem.getItemPriceCurrency());
        Assert.assertEquals("Values are not equal", TestConstants.UPDATED_TEST_VALUE, dummyItem.getItemType());
        Assert.assertEquals("Values are not equal", 2.0, dummyItem.getItemPrice());
    }


    /**
     * This method is to release objects and shut down OR close any connections after Test is completed before testing
     * test method
     */
    @After
    @AfterTransaction
    public void tearDown() throws Exception {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery(" from Item where itemId=:item_id");
        query.setLong("item_id", TestConstants.ONE);
        List<Item> items = query.list();
        if (items.isEmpty()) {
            Assert.assertTrue(" List is not Empty, Hib created a dummy Row ", items.isEmpty());
        } else if (items.size() == TestConstants.ONE) {
            Item item = items.get(TestConstants.ZERO);
            Assert.assertNotNull(item);
            Assert.assertEquals(" Data modified by Hibernate", TestConstants.TEST_PRICE_CURRENCY, item
                    .getItemPriceCurrency());
        } else {
            Assert.fail(" Unique result expected but got duplicate data");
        }

    }
}
