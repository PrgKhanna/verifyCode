package com.vc.mappers;

public interface IMapper {
	<S, D> D map(S sourceObject, Class<D> destinationClass);
}
