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
    private static final int MAX_REPETITION_DAYS = 31;

    /**
     *
     */
    private static final int MAX_REPETITION_WEEKS = 53;

    /**
     *
     */
    private static final int MAX_REPETITION_MONTHS = 31;

    /**
     *
     */
    private static final int MAX_REPETITION_YEARS = 31;

    /**
	 * Default constructor
	 */
	public PriceBean() {

	}

    private long id;

    private String priceType;

    private String repetitionType;

    private int times;

    private double value;

    private String startDate;

    private int occurrences;

    private String comment;

    private String endDate;

	private List<Day> days;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "PriceBean{" +
                "id=" + id + '\'' +
                "priceType='" + priceType + '\'' +
                ", repetitionType='" + repetitionType + '\'' +
                ", times=" + times +
                ", value=" + value +
                ", startDate='" + startDate + '\'' +
                ", occurrences=" + occurrences +
                ", endDate='" + endDate + '\'' +
                ", comment='" + comment + '\'' +
                ", days=" + days +
                '}';
    }

    @Override
	public boolean validate() throws NoSuchFieldException {

        boolean valid = true;

        if (StringUtils.isBlank(priceType)) {
            setErrorMessage(PriceBean.class.getDeclaredField("priceType"), "Price Type is required");
            valid = false;
        }

        if (StringUtils.isBlank(repetitionType)) {
            setErrorMessage(PriceBean.class.getDeclaredField("repetitionType"), "Repetition Type is required");
            valid = false;
        }

        if (!NumberUtils.isFromTo(times, 0, MAX_REPETITION_DAYS)) {
            setErrorMessage(PriceBean.class.getDeclaredField("times"), "Times must be in range 1, 7");
            valid = false;
        }

        return valid;
	}

    public int getMAX_REPETITION_DAY() {
        return MAX_REPETITION_DAYS;
    }

    public int getMAX_REPETITION_WEEK() {
        return MAX_REPETITION_WEEKS;
    }

    public int getMAX_REPETITION_MONTH() {
        return MAX_REPETITION_MONTHS;
    }

    public int getMAX_REPETITION_YEAR() {
        return MAX_REPETITION_YEARS;
    }
}