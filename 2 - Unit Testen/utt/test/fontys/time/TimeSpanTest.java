package fontys.time;

import fontys.time.ITime;
import fontys.time.ITimeSpan;
import fontys.time.Time;
import fontys.time.TimeSpan;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ian
 */
public class TimeSpanTest {

    private static ITimeSpan ts;
    private static Time bt, et;

    @BeforeClass
    public static void before() {
        bt = new Time(2017, 9, 6, 11, 15);
        et = new Time(2017, 9, 7, 11, 15);
        ts = new TimeSpan(bt, et);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructConflictTest() {
        TimeSpan ts2 = new TimeSpan(et, bt);
    }

    @Test
    public void getBeginTimeTest() {
        Assert.assertEquals(bt, ts.getBeginTime());
    }

    @Test
    public void getEndTimeTest() {
        Assert.assertEquals(et, ts.getEndTime());
    }

    @Test
    public void lengthTest() {
        Assert.assertEquals(4, ts.length());
    }

    @Test
    public void setBeginTimeTest() {
        Time time = new Time(2017, 9, 5, 11, 15);
        ts.setBeginTime(time);
        Assert.assertEquals(time, ts.getBeginTime());
    }

    @Test
    public void setEndTimeTest() {
        Time time = new Time(2017, 9, 8, 11, 15);
        ts.setEndTime(time);
        Assert.assertEquals(time, ts.getEndTime());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEndTimeBugTest() {
        Time time = new Time(2017, 9, 5, 11, 15);
        ts.setEndTime(time);
        Assert.assertEquals(time, ts.getEndTime());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setBeginTimeBugTest() {
        Time time = new Time(2017, 9, 8, 11, 15);
        ts.setBeginTime(time);
        Assert.assertEquals(time, ts.getBeginTime());
    }

    @Test
    public void moveTest() {
        ts.move(10);
        Assert.assertEquals(25, ts.getBeginTime().getMinutes());
        Assert.assertEquals(25, ts.getEndTime().getMinutes());
    }

    @Test
    public void changeLengthTest() {
        ts.changeLengthWith(10);
        Assert.assertEquals(25, ts.getEndTime().getMinutes());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeLengthBugTest() {
        ts.changeLengthWith(-10);
    }

    @Test
    public void isPartOfTest() {
        Time bt2 = new Time(2017, 9, 6, 12, 15);
        Time et2 = new Time(2017, 9, 6, 15, 15);
        TimeSpan ts2 = new TimeSpan(bt2, et2);
        Assert.assertTrue(ts2.isPartOf(ts));

        ITime bt3 = new Time(2017, 8, 6, 12, 15);
        ITime et3 = new Time(2017, 8, 6, 15, 15);
        TimeSpan ts3 = new TimeSpan(bt3, et3);
        Assert.assertFalse(ts3.isPartOf(ts));

        ITime bt4 = new Time(2017, 10, 6, 12, 15);
        ITime et4 = new Time(2017, 10, 6, 15, 15);
        TimeSpan ts4 = new TimeSpan(bt4, et4);
        Assert.assertFalse(ts4.isPartOf(ts));
    }

    @Test
    public void unionWithTest() {
        ITime bt2 = new Time(2017, 9, 5, 11, 15);
        ITime et2 = new Time(2017, 9, 6, 11, 15);
        TimeSpan ts2 = new TimeSpan(bt2, et2);

        TimeSpan ts3 = (TimeSpan) ts.unionWith(ts2);
        TimeSpan ts4 = (TimeSpan) ts2.unionWith(ts);

        Assert.assertEquals(0, ts3.getBeginTime().compareTo(ts.getBeginTime()));
        Assert.assertEquals(0, ts3.getEndTime().compareTo(ts.getEndTime()));

        Assert.assertEquals(0, ts4.getBeginTime().compareTo(ts.getBeginTime()));
        Assert.assertEquals(0, ts4.getEndTime().compareTo(ts.getEndTime()));

        ITime bt5 = new Time(2000, 9, 5, 11, 15);
        ITime et5 = new Time(2000, 9, 6, 11, 15);
        TimeSpan ts5 = new TimeSpan(bt5, et5);
        Assert.assertNull(ts2.unionWith(ts5));
    }

    
    @Test
    public void unionWithBeginTest() {
        TimeSpan newTS = (TimeSpan) ts.unionWith(new TimeSpan(new Time(2017, 9, 7, 10, 15), new Time(2017, 9, 8, 11, 15)));
        Assert.assertEquals(10, newTS.getBeginTime().getHours());
    }

    @Test
    public void unionWithEndTest() {
        TimeSpan newTS = (TimeSpan) ts.unionWith(new TimeSpan(new Time(2017, 9, 7, 11, 15), new Time(2017, 9, 8, 12, 15)));
        Assert.assertEquals(12, newTS.getEndTime().getHours());
    }

    @Test
    public void unionWithOtherEndTest() {
        TimeSpan newTS = (TimeSpan) new TimeSpan(new Time(2017, 9, 7, 11, 15), new Time(2017, 9, 8, 12, 15)).unionWith(ts);
        Assert.assertEquals(12, newTS.getEndTime().getHours());
    }

    
    @Test
    public void intersectionWithBeginTest() {
        TimeSpan newTS = (TimeSpan) ts.intersectionWith(new TimeSpan(new Time(2017, 9, 7, 10, 15), new Time(2017, 9, 8, 11, 15)));
        Assert.assertEquals(10, newTS.getBeginTime().getHours());
    }

    @Test
    public void testIntersectionWith() throws Exception {
        Time btNull = new Time(2000, 1, 1, 1, 1);
        Time etNull = new Time(2000, 2, 2, 2, 2);
        TimeSpan tsNull = new TimeSpan(btNull, etNull);

        Assert.assertNull(tsNull.intersectionWith(ts));

        Time bt2 = new Time(2017, 9, 6, 11, 15);
        Time et2 = new Time(2017, 9, 7, 15, 15);
        TimeSpan ts2 = new TimeSpan(bt2, et2);

        TimeSpan ts3 = null;
        TimeSpan ts4 = null;

        try {
            ts3 = (TimeSpan) ts.intersectionWith(ts2);
        } catch (NullPointerException np) {

        }

        try {
            ts4 = (TimeSpan) ts2.intersectionWith(ts);
        } catch (NullPointerException np) {

        }

        TimeSpan tsExpect = new TimeSpan(bt2, et);

        if (ts3 != null) {
            Assert.assertEquals(0, ts3.getBeginTime().compareTo(tsExpect.getBeginTime()));
            Assert.assertEquals(0, ts3.getEndTime().compareTo(tsExpect.getEndTime()));
        }
        if (ts4 != null) {
            Assert.assertEquals(0, ts4.getBeginTime().compareTo(tsExpect.getBeginTime()));
            Assert.assertEquals(0, ts4.getEndTime().compareTo(tsExpect.getEndTime()));
        }
    }
}
