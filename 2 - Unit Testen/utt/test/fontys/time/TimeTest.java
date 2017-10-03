package fontys.time;

import fontys.time.DayInWeek;
import fontys.time.ITime;
import fontys.time.Time;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Ian
 */
public class TimeTest {

    private ITime time;

    @Before
    public void setup() {
        time = new Time(2017, 9, 18, 10, 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructMonthTest() {
        Time timeTest = new Time(2017, 0, 18, 10, 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructDayTest() {
        Time timeTest = new Time(2017, 9, 0, 10, 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructHourTest() {
        Time timeTest = new Time(2017, 9, 18, 24, 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructMinuteTest() {
        Time timeTest = new Time(2017, 9, 18, 10, -1);
    }

    @Test
    public void getYearTest() {
        Assert.assertEquals(2017, time.getYear());
    }

    @Test
    public void getMonthTest() {
        Assert.assertEquals(9, time.getMonth());
    }

    @Test
    public void getDayTest() {
        Assert.assertEquals(18, time.getDay());
    }

    @Test
    public void getHourTest() {
        Assert.assertEquals(10, time.getHours());
    }

    @Test
    public void getMinuteTest() {
        Assert.assertEquals(15, time.getMinutes());
    }
    
    @Test
    public void getDayMondayTest() {
        Assert.assertEquals(DayInWeek.MON, time.plus(60 * 24 * 0).getDayInWeek());
    }
    
    @Test
    public void getDayTuesdayTest() {
        Assert.assertEquals(DayInWeek.TUE, time.plus(60 * 24 * 1).getDayInWeek());
    }
    
    @Test
    public void getDayWednesdayTest() {
        Assert.assertEquals(DayInWeek.WED, time.plus(60 * 24 * 2).getDayInWeek());
    }
    
    @Test
    public void getDayThursdayTest() {
        Assert.assertEquals(DayInWeek.THU, time.plus(60 * 24 * 3).getDayInWeek());
    }
    
    @Test
    public void getDayFridayTest() {
        Assert.assertEquals(DayInWeek.FRI, time.plus(60 * 24 * 4).getDayInWeek());
    }
    
    @Test
    public void getDaySaturdayTest() {
        Assert.assertEquals(DayInWeek.SAT, time.plus(60 * 24 * 5).getDayInWeek());
    }
    
    @Test
    public void getDaySundayTest() {
        Assert.assertEquals(DayInWeek.SUN, time.plus(60 * 24 * 6).getDayInWeek());
    }

    @Test
    public void plusTest() {
        Time ntime = (Time) time.plus(10);
        Assert.assertEquals(25, ntime.getMinutes());
    }

    @Test
    public void differenceTest() {
        Assert.assertEquals(-300, time.difference(new Time(2017, 9, 18, 5, 15)));
    }

    @Test
    public void compareToTest() {
        Assert.assertEquals(0, time.compareTo(new Time(2017, 9, 18, 10, 15)));
    }

}
