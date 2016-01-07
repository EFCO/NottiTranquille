package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;

/**
 * Fee is one of two abstract decoration that are allowed for {@link Price}.
 *
 * <p>
 *      This abstract class is realized by {@link FixFee} and {@link PercentageFee}.
 * </p>
 *
 * @see Price
 * @see Decorator
 * @see FixFee
 * @see PercentageFee
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public abstract class Fee extends Decorator {

	/**
	 * Default constructor
	 */
	protected Fee() {
	}

    /**
     * Builder constructor
     */
    public Fee(Builder builder) {
        super(builder);
    }

    @Override
    public String toString() {
        return "Fee = " + super.toString();
    }

    /**
	 * Applies fee to {@link Price}'s value.
     *
     * @param price the Price's value
     * @return  the new Price's value
	 */
	protected abstract double applyFee(double price);

    protected static abstract class Builder<T extends Fee, B extends Builder<T, B>> extends Decorator.Builder<T, B> {

        /**
         * Sets Fee's value.
         *
         * @param fee the fee
         * @return the builder itself
         */
        public B setFee(double fee) {
            this.value = fee;
            return thisObject;
        }
    }
}