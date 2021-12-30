package material.graphs;

import material.Position;

/**
 *
 * @author jvelez
 */
public interface Vertex <V> extends Position <V> {
    V getValue();

    void setValue(V value);
}
