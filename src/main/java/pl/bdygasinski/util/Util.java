package pl.bdygasinski.util;

import java.util.Objects;
import java.util.function.Consumer;

public class Util {
    public static <T> void setIfNotNull(T value, Consumer<T> setter) {
        if (Objects.nonNull(value)) {
            setter.accept(value);
        }
    }
}
