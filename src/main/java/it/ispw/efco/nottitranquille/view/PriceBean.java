package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.model.enumeration.Day;
import it.ispw.efco.nottitranquille.model.enumeration.RepetitionType;
import org.joda.time.Interval;

import java.util.List;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class PriceBean {

	/**
	 * Default constructor
	 */
	public PriceBean() {
	}

	/**
	 * 
	 */
	private Interval interval;

	/**
	 * 
	 */
	private int times;

	/**
	 * 
	 */
	private int occurrencies;

	/**
	 * 
	 */
	private List<Day> days;

	/**
	 * 
	 */
	private RepetitionType repetitionType;


	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getOccurrencies() {
		return occurrencies;
	}

	public void setOccurrencies(int occurrencies) {
		this.occurrencies = occurrencies;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

	public RepetitionType getRepetitionType() {
		return repetitionType;
	}

	public void setRepetitionType(RepetitionType repetitionType) {
		this.repetitionType = repetitionType;
	}

	/**
	 * 
	 */
	public void validate() {
		// TODO implement here
	}

}