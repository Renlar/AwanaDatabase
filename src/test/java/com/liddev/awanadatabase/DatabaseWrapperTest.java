package com.liddev.awanadatabase;

import javax.swing.DefaultListModel;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertSame;
import static junit.framework.TestCase.assertTrue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Renlar <liddev.com>
 */
public class DatabaseWrapperTest {
    static DatabaseWrapper wrapper;
    
    public DatabaseWrapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Record.loadMasterData();
        wrapper = DatabaseWrapper.get();
    }
    
    @AfterClass
    public static void tearDownClass() {
        wrapper.closeDatabase();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGet() {
        System.out.println("checkGet");
        assertNotNull("Should not be null as wrapper was started by setUp method.", wrapper);
        assertEquals("Should return the same wrapper instance fore every call.", DatabaseWrapper.get(), wrapper);
    }
    
    @Test
    public void testDeleteCreateRecord() {
        System.out.println("deleteCreateRecord");
        Record r = wrapper.newRecord();
        TestCase.assertNotNull("Should not be null after calling newRecord() in DatabaseWrapper", r);
        if(wrapper.getRecord(r.getID()).equals(r)){
            wrapper.deleteRecord(r);
            assertNull("Should be null as record was deleted", wrapper.getRecord(r.getID()));
        }else{
            System.out.println("Record creation and return are inconsistent.");
        }
    }

    @Test
    public void testDeleteListing() {
        System.out.println("deleteCreateListing");
        Record r = wrapper.newRecord();
        Listing l = r.createListing();
        assertNotNull(l);
        assertSame(r.getID(), l.getID());
        wrapper.deleteListing(l);
        assertNull("Record should be null as wrapper should have been deleted the record associated with the listing.", wrapper.getRecord(l.getID()));
    }

    @Test
    public void testGetRecord() {
        System.out.println("getRecord");
        Record expected = wrapper.newRecord();
        int ID = expected.getID();
        Record result = wrapper.getRecord(ID);
        assertEquals("Should return the newly created record.", expected, result);
    }

    @Test
    public void testSaveRecord() {
        System.out.println("saveRecord");
        int ID = wrapper.newRecord().getID() + 1;
        Record expected = new Record(ID);
        wrapper.saveRecord(expected);
        Record result = wrapper.getRecord(ID);
        assertEquals("Should return the saved record.", expected, result);
        
    }

    @Test
    public void testNewRecord() {
        System.out.println("newRecord");
        Record result = wrapper.newRecord();
        Record expResult = wrapper.getRecord(result.getID());
        assertEquals("Should save to database and return the record", expResult, result);
    }

    @Test
    public void testGetRecordListingsAsDefaultListModel() {
        System.out.println("getRecordListingsAsDefaultListModel");
        DefaultListModel<Listing> result = wrapper.getRecordListingsAsDefaultListModel();
        assertNotNull("Should always return a list even if no records have been created yet", result);
    }

    @Test
    public void testReconnectDatabase() {
        System.out.println("reconnectDatabase");
        Record r = wrapper.newRecord();
        wrapper.reconnectDatabase();
        assertEquals("Should return same object after database reconnects.", wrapper.getRecord(r.getID()), r);
        
    }

    @Test
    public void testRunStatement() {
        System.out.println("runStatement");
        //TODO: create tests for run statement command.
    }

    @Test
    public void testExecuteQuery() throws Exception {
        System.out.println("executeQuery");
        //TODO: create tests for execute query command.
    }

    @Test
    public void testPrintResultSet() {
        System.out.println("printResultSet");
        //TODO: create tests for print result set command.
    }

    @Test
    public void testCloseDatabase() {
        System.out.println("closeDatabase");
        Record r = wrapper.newRecord();
        wrapper.closeDatabase();
        assertNull(wrapper.getRecord(r.getID()));
    }

    @Test
    public void testTableExists() {
        wrapper.closeDatabase();
        wrapper = null;
        System.gc();
        wrapper = DatabaseWrapper.get();
        boolean result = wrapper.tableExists(wrapper.dataTable);
        assertTrue("Table should exist.", result);
        wrapper.runStatement("DROP TABLE " + wrapper.dataTable);
        result = wrapper.tableExists(wrapper.dataTable);
        assertFalse(result);
        wrapper.closeDatabase();
        wrapper = null;
        System.gc();
        wrapper = DatabaseWrapper.get();
    }

    @Test
    public void testGetColumnProperties() {
        System.out.println("getColumnProperties");
        //TODO: test properties method.
    }

    public void testSetDefaultValue() {
        System.out.println("setDefaultValue");
        //TODO: write test if this method is needed
    }

    /*@Test
    public void testGetDefaultValue() {
        System.out.println("getDefaultValue");
        int rowNumber = 1;
        ResultSet results = executeQuery();
        String expResult = null;
        String result = wrapper.getDefaultValue(rowNumber, results);
        assertEquals(expResult, result);
    }

    @Test
    public void testResultSetContainsNameDataTypeAndDefaultValue() {
        System.out.println("resultSetContainsNameDataTypeAndDefaultValue");
        String fieldName = "";
        String fieldDataType = "";
        String defaultValue = "";
        ResultSet results = null;
        int expResult = 0;
        int result = wrapper.resultSetContainsNameDataTypeAndDefaultValue(fieldName, fieldDataType, defaultValue, results);
        assertEquals(expResult, result);
    }

    @Test
    public void testResultSetContainsNameAndDataType() {
        System.out.println("resultSetContainsNameAndDataType");
        String fieldName = "";
        String fieldDataType = "";
        ResultSet results = null;
        int expResult = 0;
        int result = wrapper.resultSetContainsNameAndDataType(fieldName, fieldDataType, results);
        assertEquals(expResult, result);
    }

    @Test
    public void testResultSetContainsName() {
        System.out.println("resultSetContainsName");
        String fieldName = "";
        ResultSet results = null;
        int expResult = 0;
        int result = wrapper.resultSetContainsName(fieldName, results);
        assertEquals(expResult, result);
    }*/
    
}
