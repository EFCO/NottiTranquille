package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.Bean;
import it.ispw.efco.nottitranquille.NumberUtils;
import it.ispw.efco.nottitranquille.StringUtils;
import it.ispw.efco.nottitranquille.model.enumeration.Day;

import java.util.List;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class PriceBean extends Bean {

    /**
     *
     */
    public static final int MAX_REPETITION_DAYS = 8;

    /**
     *
     */
    public static final int MAX_REPETITION_WEEKS = 53;

    /**
     *
     */
    public static final int MAX_REPETITION_MONTHS = 13;

    /**
     *
     */
    public static final int MAX_REPETITION_YEARS = 100;

    /**
     *
     */
    public static final int MAX_REPETITION_WEEKENDS = 53;

    /**
     *
     */
    public static final int MAX_REPETITION_WORKDAYS = 6;

    /**
     *
     */
    public static final int MAX_REPETITION_NO_WORKDAYS = 3;


    /**
	 * Default constructor
	 */
	public PriceBean() {

	}

    private String priceType;

    private String repetitionType;

    private int times;

    private int value = 22;

    private String startDate;

    private int occurrences;

    private String endDate;

	private List<Day> days;

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getRepetitionType() {
        return repetitionType;
    }

    public void setRepetitionType(String repetitionType) {
        this.repetitionType = repetitionType;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getMaxRepetitionDays() {
        return MAX_REPETITION_DAYS;
    }

    public int getMaxRepetitionWeeks() {
        return MAX_REPETITION_WEEKS;
    }

    public int getMaxRepetitionMonths() {
        return MAX_REPETITION_MONTHS;
    }

    public int getMaxRepetitionYears() {
        return MAX_REPETITION_YEARS;
    }

    public int getMaxRepetitionWeekends() {
        return MAX_REPETITION_WEEKENDS;
    }

    public int getMaxRepetitionWorkdays() {
        return MAX_REPETITION_WORKDAYS;
    }

    public int getMaxRepetitionNoWorkdays() {
        return MAX_REPETITION_NO_WORKDAYS;
    }

    @Override
    public String toString() {
        return "PriceBean{" +
                "priceType='" + priceType + '\'' +
                ", repetitionType='" + repetitionType + '\'' +
                ", times=" + times +
                ", value=" + value +
                ", startDate='" + startDate + '\'' +
                ", occurrences=" + occurrences +
                ", endDate='" + endDate + '\'' +
                ", days=" + days +
                '}';
    }

    @Override
	public boolean validate() throws NoSuchFieldException {

        boolean valid = true;

        if (StringUtils.isBlank(this.priceType)) {
            setErrorMessage(PriceBean.class.getDeclaredField("priceType"), "Price Type is required");
            valid = false;
        }

        if (StringUtils.isBlank(repetitionType)) {
            setErrorMessage(PriceBean.class.getDeclaredField("repetitionType"), "Repetition Type is required");
            valid = false;
        }

        if (NumberUtils.isFromTo(this.times, 0, MAX_REPETITION_DAYS)) {
            setErrorMessage(PriceBean.class.getDeclaredField("times"), "Times must be in range 1, 7");
            valid = false;
        }

        return valid;
	}

}