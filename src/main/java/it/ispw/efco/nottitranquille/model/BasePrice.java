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
     * Builder constructor
     */
    protected BasePrice(Builder builder) {
        super(builder);
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
	public double showPrice() {
		return this.priceValue;
	}

    public static final class Builder extends Price.Builder<BasePrice, Builder> {
        protected BasePrice createObject() {
            return new BasePrice(this);
        }
        protected Builder thisObject() {
            return this;
        }
    }
}