package dev.hwo.domain.specification;

public abstract class EspecificacionAbstract<T> implements Especificacion<T> 
{
    
    public abstract boolean isSatisfiedBy (T t);

    public Especificacion<T> and(final Especificacion<T> especificacion) {
        return new AndEspecificacion<T>(this, especificacion);
    }

}
