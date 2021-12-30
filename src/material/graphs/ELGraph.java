
package material.graphs;

import java.util.*;

/**
 *
 * @author mayte
 * @param <V>
 * @param <E>
 */
public class ELGraph<V,E> implements Graph<V,E> {

    public ELGraph() {
        this.vertex = new HashSet<>();
        this.edges = new HashSet<>();
    }

    private class ELVertex<V> implements Vertex<V>{

        private V value;

        public ELVertex(V value) {
            this.value = value;
        }

        @Override
        public V getElement() {
            return value;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }
    }

    private class ELEdge<E> implements Edge<E>{
        private E value;
        private Vertex<V> start, end;

        public ELEdge(E value, ELVertex<V> start, ELVertex<V> end) {
            this.value = value;
            this.start = start;
            this.end = end;
        }

        @Override
        public E getElement() {
            return value;
        }

        @Override
        public E getValue() {
            return value;
        }

        @Override
        public void setValue(E value) {
            this.value = value;
        }

        public Vertex<V> getStart() {
            return start;
        }

        public void setStart(ELVertex<V> start) {
            this.start = start;
        }

        public Vertex<V> getEnd() {
            return end;
        }

        public void setEnd(ELVertex<V> end) {
            this.end = end;
        }
    }

    private HashSet<Vertex<V>> vertex;
    private HashSet<Edge<E>> edges;

    @Override
    public Collection<? extends Vertex<V>> vertices() {
        //Debemos devolver un conjunto inmutable!
        return Collections.unmodifiableSet(vertex); // MIRAR
    }

    @Override
    public Collection<? extends Edge<E>> edges() {
        return Collections.unmodifiableSet(edges);
    }

    @Override
    public Collection<? extends Edge<E>> incidentEdges(Vertex<V> v) {
        ArrayList<ELEdge<E>> edgeArrayList = new ArrayList<>();
        for(Edge e : edges){
            ELEdge<E> edge = (ELEdge<E>) e;
            if((edge.getStart() == v) || (edge.getEnd() == v)){
                edgeArrayList.add(edge);
            }
        }
        return Collections.unmodifiableList(edgeArrayList);
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        ELEdge<E> edge = (ELEdge<E>) e;
        ELVertex<V> vertex = (ELVertex<V>) v;
        if(edge.getStart() == vertex){
            return edge.getEnd();
        }else if(edge.getEnd() == vertex){
            return edge.getStart();
        }
        return null;
    }

    @Override
    public List<Vertex<V>> endVertices(Edge<E> edge) {
        ELEdge<E> e = (ELEdge<E>) edge;
        ArrayList<Vertex<V>> v = new ArrayList<>();
        v.add(e.getEnd());
        return v;
    }

    @Override
    public Edge<E> areAdjacent(Vertex<V> v1, Vertex<V> v2) {
        for(Edge<E> e : edges){
            ELEdge<E> edge = (ELEdge<E>) e;
            if(((edge.getStart() == v1) && (edge.getEnd() == v2)) || ((edge.getStart() == v2) && (edge.getEnd() == v1))){
                return e;
            }
        }
        return null;
    }

    @Override
    public V replace(Vertex<V> vertex, V vertexValue) {
        ELVertex<V> v = (ELVertex<V>) vertex;
        V oldValue = v.getValue();
        v.setValue(vertexValue);
        return oldValue;
    }

    @Override
    public E replace(Edge<E> edge, E edgeValue) {
        ELEdge<E> e = (ELEdge<E>) edge;
        E oldValue = e.getValue();
        e.setValue(edgeValue);
        return oldValue;
    }

    @Override
    public Vertex<V> insertVertex(V value) {
        ELVertex<V> newVertex = new ELVertex<>(value);
        vertex.add(newVertex);
        return newVertex;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeValue) {
        for(Edge<E> e : edges){
            ELEdge<E> edge = (ELEdge<E>) e;
            if ((edge.getStart() == v1 && edge.getEnd() == v2) || (edge.getStart() == v2 && edge.getEnd() == v1)){
                edge.setValue(edgeValue);
                return edge;
            }
        }

        Edge<E> edge = new ELEdge<>(edgeValue,(ELVertex<V>) v1,(ELVertex<V>)v2);
        edges.add(edge);
        //TODO
        for(Edge<E> e: edges){
            ELEdge<E> aux = (ELEdge<E>) e;
            System.out.println("Value: " + aux.getValue() + " start: " + aux.getStart() + " end: " + aux.getEnd() );
        }
        return edge;
    }

    @Override
    public V removeVertex(Vertex<V> vertex) {
        ELVertex<V> v = (ELVertex<V>) vertex;
        for(Edge<E> e : edges){
            ELEdge<E> edge = (ELEdge<E>) e;
            if(edge.getStart() == v || edge.getEnd() == v){
                edges.remove(edge);
            }
        }
        this.vertex.remove(v);
        return v.getValue();
    }

    @Override
    public E removeEdge(Edge<E> edge) {
        ELEdge<E> e = (ELEdge<E>) edge;
        edges.remove(e);
        return e.getElement();
    }
    
}
