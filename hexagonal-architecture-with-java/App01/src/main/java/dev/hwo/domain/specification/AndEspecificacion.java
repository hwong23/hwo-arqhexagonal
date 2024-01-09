package dev.hwo.domain.specification;

public final class AndEspecificacion<T> extends EspecificacionAbstract<T>
{
    private final Especificacion<T> spec1;
    private final Especificacion<T> spec2;

    public AndEspecificacion (final Especificacion<T> s1, final Especificacion<T> s2) {
        this.spec1 = s1;
        this.spec2 = s2;
    }

    public boolean isSatisfiedBy (final T t) {
        return spec1.isSatisfiedBy(t) && spec2.isSatisfiedBy(t);
    }
}
