import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import webapp.domain.CirclePoint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** 
* CirclePoint Tester. 
* 
* @author <Authors name> 
* @since <pre>May 31, 2022</pre> 
* @version 1.0 
*/

public class CirclePointTest {
    public CirclePointTest() throws NoSuchMethodException {
        try {
            testCheckCoordinates();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final CirclePoint circlePoint = new CirclePoint("owner", 1.0, 1.0, 1.0);

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: equals(Object o)
    *
    */
    @Test
    public void testEquals() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: hashCode()
    *
    */
    @Test
    public void testHashCode() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: toString()
    *
    */
    @Test
    public void testToString() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getId()
    *
    */
    @Test
    public void testGetId() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: setId(int id)
    *
    */
    @Test
    public void testSetId() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getX()
    *
    */
    @Test
    public void testGetX() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: setX(double x)
    *
    */
    @Test
    public void testSetX() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getY()
    *
    */
    @Test
    public void testGetY() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: setY(double y)
    *
    */
    @Test
    public void testSetY() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getR()
    *
    */
    @Test
    public void testGetR() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: setR(double r)
    *
    */
    @Test
    public void testSetR() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: isCoordsStatus()
    *
    */
    @Test
    public void testIsCoordsStatus() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: setCoordsStatus(boolean coordsStatus)
    *
    */
    @Test
    public void testSetCoordsStatus() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getBornDate()
    *
    */
    @Test
    public void testGetBornDate() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: setBornDate(Date bornDate)
    *
    */
    @Test
    public void testSetBornDate() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getOwner()
    *
    */
    @Test
    public void testGetOwner() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: setOwner(String owner)
    *
    */
    @Test
    public void testSetOwner() throws Exception {
        //TODO: Test goes here...
    }

    /**
    *
    * Method: checkCoordinates(@NotNull double x, @NotNull double y, @NotNull double r)
    *
    */
    @Test
    public void testCheckCoordinates() throws Exception {
        try {
            Method method = CirclePoint.class.getDeclaredMethod("checkCoordinates", double.class, double.class, double.class);
            method.setAccessible(true);
            assertFalse((Boolean) method.invoke(circlePoint, 1.0, 1.0, 1.0));
            assertFalse(((Boolean) method.invoke(circlePoint, -1.0, 1.0, 1.0)));
            assertFalse(((Boolean) method.invoke(circlePoint, 1.0, -1.0, 1.0)));
            assertTrue(((Boolean) method.invoke(circlePoint, 0.0, 0.0, 0.0)));
        } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        }
    }
} 
