package dev.hwo.domain.specification;

public final class CIDRMinimoEspecificacion extends EspecificacionAbstract<Integer>
{
    final static public int CIDR_MINIMO_PERMITIDO = 8;

    @Override
    public boolean isSatisfiedBy (Integer cidr) {
        return cidr > CIDR_MINIMO_PERMITIDO;
    }
}
