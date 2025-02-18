package com.colak.commondefinitions.exceptions;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CoreException extends RuntimeException {

    private final String localizationKey;

    private final Object[] arguments;

}
