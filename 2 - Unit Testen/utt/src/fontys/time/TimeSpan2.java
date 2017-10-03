package fontys.time;

/**
 * Created by linux on 29-9-15.
 * @author Guus
 */
public class TimeSpan2 implements ITimeSpan{
    private ITime bt;
    private long duration;



    public TimeSpan2(ITime bt,ITime et) {
        if (bt.compareTo(et) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        this.bt = bt;
        this.duration = (bt.difference(et));
    }
    @Override
    public ITime getBeginTime() {
        return bt;
    }


    @Override
    public ITime getEndTime() {
        return bt.plus((int)duration);
    }

    @Override
    public int length() {

        return -(int)duration;
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        if (beginTime.compareTo(bt.plus((int)duration)) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + bt.plus((int)duration));
        }
        bt = beginTime;
    }

    @Override
    public void setEndTime(ITime endTime) {
        if (endTime.compareTo(bt) > 0) {
            throw new IllegalArgumentException("end time "
                    + duration + " must be later then begin time " + bt);
        }

        duration = bt.difference(endTime);
        //duration = endTime.compareTo(bt);
    }

    @Override
    public void move(int minutes) {
        bt = bt.plus(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("length of period must be positive");
        }

        duration += minutes;
    }

    @Override
    public boolean isPartOf(ITimeSpan timeSpan) {
        return (getBeginTime().compareTo(timeSpan.getBeginTime()) <= 0
                && getEndTime().compareTo(timeSpan.getEndTime()) >= 0);
    }

    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
        if (bt.compareTo(timeSpan.getEndTime()) < 0 || bt.plus((int)duration).compareTo(timeSpan.getBeginTime()) > 0) {
            return null;
        }

        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) > 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (bt.plus((int)duration).compareTo(timeSpan.getEndTime()) < 0) {
            endtime = bt.plus((int)duration);
        } else {
            endtime = timeSpan.getEndTime();
        }

        return new TimeSpan2(begintime, endtime);

    }

    @Override
    public ITimeSpan intersectionWith(ITimeSpan timeSpan) {

        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) < 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (bt.plus((int)duration).compareTo(timeSpan.getEndTime()) > 0) {
            endtime = bt.plus((int)duration);
        } else {
            endtime = timeSpan.getEndTime();
        }

        if (begintime.compareTo(endtime) <= 0) {
            return null;
        }

        return new TimeSpan2(begintime, endtime);
    }
}