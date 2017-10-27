package es.randomco.randomapp.domain.mappers;

public interface Mapper<M, P> {

    P map(M model);

    M inverseMap(P model);
}