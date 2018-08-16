package net.productiveprogrammer.service;

import net.productiveprogrammer.dao.ArgumentDao;
import net.productiveprogrammer.exception.ArgumentsDoNotMatchException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ArgumentMatcherImpl implements ArgumentMatcher {

    @Override
    public List<ArgumentDao> match(final List<String> params, final List<String> args) throws ArgumentsDoNotMatchException {
        if (params.size() != args.size()){
            throw new ArgumentsDoNotMatchException("Params and args have different sizes");
        }
        var arguments = new LinkedList<ArgumentDao>();
        for (int i = 0; i < args.size(); i++){
            var argumentDao = new ArgumentDao();
            argumentDao.setParameter(params.get(i));
            argumentDao.setValue(args.get(i));
            arguments.add(argumentDao);
        }
        return arguments;
    }
}
