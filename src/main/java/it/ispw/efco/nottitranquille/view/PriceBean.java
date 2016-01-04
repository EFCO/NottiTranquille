package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.model.enumeration.Day;
import it.ispw.efco.nottitranquille.model.enumeration.RepetitionType;
import org.joda.time.Interval;

import java.util.*;

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
	private Set<Day> days;

	/**
	 * 
	 */
	private RepetitionType repetitionType;


	/**
	 * @return
	 */
	public Interval getInterval() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public int getTimes() {
		// TODO implement here
		return 0;
	}

	/**
	 * @return
	 */
	public int getOccurencies() {
		// TODO implement here
		return 0;
	}

	/**
	 * @return
	 */
	public Set<Day> getDays() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public RepetitionType getRepetitionType() {
		// TODO implement here
		return null;
	}

	/**
	 * @param interval
	 */
	public void setInterval(Interval interval) {
		// TODO implement here
	}

	/**
	 * @param times
	 */
	public void setTimes(int times) {
		// TODO implement here
	}

	/**
	 * @param occurencies
	 */
	public void setOccurencies(int occurencies) {
		// TODO implement here
	}

	/**
	 * @param days
	 */
	public void setDays(List<Day> days) {
		// TODO implement here
	}

	/**
	 * @param repetitionType
	 */
	public void setRepetitionType(RepetitionType repetitionType) {
		// TODO implement here
	}

	/**
	 * 
	 */
	public void validate() {
		// TODO implement here
	}

}