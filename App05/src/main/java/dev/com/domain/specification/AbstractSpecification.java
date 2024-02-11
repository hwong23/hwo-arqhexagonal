package dev.com.domain.specification;

public abstract sealed class AbstractSpecification<T> implements Specification<T> permits
        AndSpecification,
        CIDRSpecification,
        CoberturaAmountSpecification,
        CoberturaAvailabilitySpecification,
        PrimaTypeSpecification
{

    public abstract boolean isSatisfiedBy(T t);

    public Specification<T> and(final Specification<T> specification) {
        return new AndSpecification<T>(this, specification);
    }
}
