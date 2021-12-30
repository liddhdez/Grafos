package material.graphs;

import material.Position;

/**
 *
 * @author jvelez
 */
public interface Edge <E> extends Position <E> {

    E getValue();

    void setValue(E value);

}
