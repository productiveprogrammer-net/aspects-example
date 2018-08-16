package net.productiveprogrammer.service;

import net.productiveprogrammer.dao.ArgumentDao;
import net.productiveprogrammer.exception.ArgumentsDoNotMatchException;

import java.util.List;

public interface ArgumentMatcher {

    List<ArgumentDao> match(final List<String> params, final List<String> args) throws ArgumentsDoNotMatchException;
}
