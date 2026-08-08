package io.agora.media;

/**
 * Extended interface for objects that can be both serialized (marshalled) and
 * deserialized (unmarshalled) to and from a {@link ByteBuf}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see Packable
 * @see ByteBuf
 */
public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
