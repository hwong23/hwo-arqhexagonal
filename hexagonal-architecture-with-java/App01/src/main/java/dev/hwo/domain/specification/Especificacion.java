package dev.hwo.domain.specification;

public interface Especificacion<T> 
{

    boolean isSatisfiedBy(T t);

    Especificacion<T> and(Especificacion<T> specification);
}