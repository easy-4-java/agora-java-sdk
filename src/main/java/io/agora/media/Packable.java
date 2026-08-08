package io.agora.media;

/**
 * Interface for objects that can be serialized (marshalled) into a {@link ByteBuf}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see ByteBuf
 * @see PackableEx
 */
public interface Packable {
    ByteBuf marshal(ByteBuf out);
}
