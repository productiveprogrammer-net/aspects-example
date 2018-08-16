package net.productiveprogrammer.service;

import net.productiveprogrammer.exception.CouldNotMatchSignatureException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MethodParserImplTest {

    private static final String ONE_ARGUMENT =
            "public net.productiveprogrammer.model.HelloMessage net.productiveprogrammer.controller.HelloController.sayHello(java.lang.String)";

    private static final String TWO_ARGUMENTS =
            "public net.productiveprogrammer.model.HelloMessage net.productiveprogrammer.controller.HelloController.sayHello(java.lang.String,int)";

    private MethodParser underTest = new MethodParserImpl();

    @Test
    public void shouldParseOneArgument() throws CouldNotMatchSignatureException {
        final List<String> results = underTest.parse(ONE_ARGUMENT);
        assertThat(results).isNotNull();
        assertThat(results).hasSize(1);
        assertThat(results).first().isEqualTo("java.lang.String");
    }

    @Test
    public void shouldParseTwoArguments() throws CouldNotMatchSignatureException {
        final List<String> results = underTest.parse(TWO_ARGUMENTS);
        assertThat(results).isNotNull();
        assertThat(results).hasSize(2);
        assertThat(results).first().isEqualTo("java.lang.String");
        assertThat(results.get(1)).isEqualTo("int");
    }
}
