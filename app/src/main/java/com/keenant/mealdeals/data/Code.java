package com.keenant.mealdeals.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lombok.Getter;

public class Code {
    @Getter final int code;
    @Getter final Date date;

    public Code(int code, Date date) {
        this.code = code;
        this.date = date;
    }

    public boolean isValid() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        return fmt.format(date).equals(fmt.format(new Date()));
    }
}
