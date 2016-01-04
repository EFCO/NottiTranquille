package it.ispw.efco.nottitranquille.model;

/**
 * FixDiscount is one of two concrete {@link Price}'s {@link Decorator}.
 * <br>
 * It applies a fix {@link Discount} to {@link Price}'s value.
 *
 * @see Price
 * @see Decorator
 * @see Discount
 * @see PercentageDiscount
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class FixDiscount extends Discount {

    /**
     * Default constructor
     */
    protected FixDiscount() {
    }

    /**
     * {@inheritDoc}
     */
	public double showPrice() {
		return this.applyDiscount(super.showPrice());
	}

    /**
     * {@inheritDoc}
     */
	@Override
	protected double applyDiscount(double price) {
		return price - discount;
	}

    public static final class Builder extends Discount.Builder<FixDiscount, Builder> {

        protected FixDiscount createObject() {
            return new FixDiscount();
        }

        protected Builder thisObject() {
            return this;
        }
    }
}