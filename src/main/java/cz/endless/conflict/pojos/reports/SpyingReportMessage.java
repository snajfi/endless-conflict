package cz.endless.conflict.pojos.reports;

import java.util.Date;

/**
 * Created by dobeji1 on 16.01.2018.
 */

public class SpyingReportMessage {

    private Date date;
    private String message;

    public SpyingReportMessage(Date date, String message) {
        this.date = date;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
