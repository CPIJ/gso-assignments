package fontys.time;

import fontys.time.ITime;
import fontys.time.Time;
import fontys.time.TimeSpan2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TimeSpan3Test {

    private ITime bt;
    private ITime et;
    private TimeSpan2 t;

    @Before
    public void before() {
        bt = new Time(1970, 1, 1, 1, 1);
        et = new Time(1970, 2, 1, 1, 1);
        t = new TimeSpan2(bt, et);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructConflictTest() {
        ITime bt2 = new Time(1970, 3, 1, 1, 1);
        ITime et2 = new Time(1970, 2, 1, 1, 1);
        TimeSpan2 t2 = new TimeSpan2(bt2, et2);
    }

    @Test
    public void getBeginTimeTest() {
        Assert.assertEquals(t.getBeginTime(), bt);
    }

    @Test
    public void getEndTimeTest() {
        Assert.assertEquals(t.getEndTime().compareTo(et), 0);
    }

    @Test
    public void lengthTest() {
        Assert.assertEquals(t.length(), et.difference(bt));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setBeginTimeBugTest() {

        ITime bt2 = new Time(1970, 1, 1, 1, 0);
        ITime bt3 = new Time(1975, 1, 1, 1, 0);

        t.setBeginTime(bt2);
        t.setBeginTime(bt3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEndTimeBugTest() {

        ITime et2 = new Time(1971, 3, 3, 13, 12);
        ITime et3 = new Time(1964, 2, 25, 05, 30);

        t.setEndTime(et2);
        t.setEndTime(et3);
    }

    @Test
    public void moveTest() {
        t.move(20);

        ITime bt2 = new Time(1970, 1, 1, 1, 21);
        ITime et2 = new Time(1970, 2, 1, 1, 21);
        TimeSpan2 ts2 = new TimeSpan2(bt2, et2);
        
        Assert.assertEquals(0, t.getBeginTime().compareTo(ts2.getBeginTime()));

    }

    @Test(expected = IllegalArgumentException.class)
    public void changeLengthBugTest() {
        ITime et2 = new Time(1970, 2, 1, 1, 21);
        t.changeLengthWith(20);

        Assert.assertEquals(21, t.getEndTime().getMinutes());
        t.changeLengthWith(-5);
    }

    @Test
    public void isPartOfTest() {
        ITime bt2 = new Time(1970, 1, 1, 1, 2);
        ITime et2 = new Time(1970, 1, 1, 1, 5);
        TimeSpan2 ts2 = new TimeSpan2(bt2, et2);

        Assert.assertTrue(ts2.isPartOf(t));
    }

    @Test
    public void unionWithTest() {
        ITime bt2 = new Time(1970, 1, 1, 1, 2);
        ITime et2 = new Time(1970, 1, 1, 1, 3);

        TimeSpan2 ts2 = new TimeSpan2(bt2, et2);

        TimeSpan2 ts3 = (TimeSpan2) t.unionWith(ts2);
        TimeSpan2 ts5 = (TimeSpan2) ts2.unionWith(t);

        ITime bt5 = new Time(1990, 1, 31, 1, 1);
        ITime et5 = new Time(1990, 3, 1, 1, 1);
        TimeSpan2 ts6 = new TimeSpan2(bt5, et5);

        int TS3 = ts3.getBeginTime().compareTo(ts3.getEndTime());
        int T = t.getBeginTime().compareTo(t.getEndTime());
        int TS5 = ts5.getBeginTime().compareTo(ts5.getEndTime());

        Assert.assertEquals(TS3, T);
        Assert.assertEquals(TS5, T);
        Assert.assertNull(ts2.unionWith(ts6));
    }

    @Test
    public void testIntersectionWith() {
        ITime btNull = new Time(1970, 1, 1, 1, 1);
        ITime btNull2 = new Time(2000, 1, 1, 1, 1);
        ITime etNull = new Time(1970, 2, 2, 2, 3);
        ITime etNull2 = new Time(2000, 2, 2, 2, 2);

        TimeSpan2 tis = new TimeSpan2(btNull, etNull);
        TimeSpan2 tis2 = new TimeSpan2(btNull2, etNull2);

        Assert.assertNull(tis2.intersectionWith(tis));

        ITime bt2 = new Time(1970, 1, 1, 1, 2);
        ITime et2 = new Time(1970, 2, 1, 1, 15);
        TimeSpan2 ts2 = new TimeSpan2(bt2, et2);
        TimeSpan2 ts3 = null;
        TimeSpan2 ts4 = null;

        ts3 = (TimeSpan2) t.intersectionWith(ts2);
        ts4 = (TimeSpan2) ts2.intersectionWith(t);

        TimeSpan2 ts5 = new TimeSpan2(bt2, et);

        if (ts3 != null) {
            int TsActual = ts3.getBeginTime().compareTo(ts3.getEndTime());
            int TsExpected = ts5.getBeginTime().compareTo(ts5.getEndTime());

            Assert.assertEquals(TsActual, TsExpected);
        }

    }
}
