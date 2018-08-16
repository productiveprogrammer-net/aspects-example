package net.productiveprogrammer.service;

import net.productiveprogrammer.exception.CouldNotMatchSignatureException;

import java.util.List;

public interface MethodParser {

    List<String> parse(final String longName) throws CouldNotMatchSignatureException;
}
