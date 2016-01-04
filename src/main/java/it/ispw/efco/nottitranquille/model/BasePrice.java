package it.ispw.efco.nottitranquille.model;

/**
 * BasePrice is the concrete component of {@link Price}.
 *
 * @see Price
 * @see Decorator
 * @see it.ispw.efco.nottitranquille.model.Price.Builder
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class BasePrice extends Price {

	/**
	 * Default constructor
	 */
	protected BasePrice() {
	}

    /**
	 * {@inheritDoc}
	 */
    @Override
	public double showPrice() {
		return this.price;
	}

    public static final class Builder extends Price.Builder<BasePrice, Builder> {
        protected BasePrice createObject() {
            return new BasePrice();
        }
        protected Builder thisObject() {
            return this;
        }
    }
}