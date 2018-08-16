package net.productiveprogrammer.service;

import net.productiveprogrammer.exception.CouldNotMatchSignatureException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class MethodParserImpl implements MethodParser {

    private static final String METHOD_PATTERN = "^.*\\((.*)\\)$";

    @Override
    public List<String> parse(final String longName) throws CouldNotMatchSignatureException {
        var pattern = Pattern.compile(METHOD_PATTERN);
        var matcher = pattern.matcher(longName);
        if (matcher.matches()) {
            return Arrays.stream(matcher.group(1).split(",")).filter(str -> !StringUtils.isEmpty(str))
                    .collect(Collectors.toList());
        }
        throw new CouldNotMatchSignatureException("Could not match signature");
    }
}
