package app.web.pavelk.speech4.speech;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum Os {

    WIN32("windows 10"), LINUX("linux");

    public final String osName;

    public static final Map<String, Os> OS_MAP = Stream.of(Os.values()).collect(Collectors.toUnmodifiableMap(Os::getOsName, Function.identity()));

}
