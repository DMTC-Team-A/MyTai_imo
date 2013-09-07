package com.example.mytai_imo.utils;

import java.util.GregorianCalendar;

/**
 * 体重
 */
public class Weight {
    private final long dateEpoch;
    private final double weight;

    //region コンストラクタ
    /**
     * 時刻と体重を指定して初期化する。
     * @param weight
     * @param date
     */
    public Weight(final double weight, final Long dateEpoch) {
        this.weight = weight;
        this.dateEpoch = dateEpoch;
    }

    /**
     * 体重のみを指定して初期化する。時刻は初期化された時間になる。
     * @param weight
     */
    public Weight(final double weight) {
        this(weight, App.getMinimumTime(new GregorianCalendar()));
    }
    //endregion

    //region アクセサ
    public double getWeight() {
        return weight;
    }

    public long getDateEpoch(){
        return dateEpoch;
    }
    //endregion
}
