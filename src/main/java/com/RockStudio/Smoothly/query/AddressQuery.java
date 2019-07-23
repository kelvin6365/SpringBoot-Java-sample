package com.RockStudio.Smoothly.query;

import com.RockStudio.Smoothly.model.Address;
import com.RockStudio.Smoothly.service.AddressService;
import graphql.relay.*;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.relay.Connection;
import io.leangen.graphql.execution.relay.Page;
import io.leangen.graphql.execution.relay.generic.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressQuery {
    @Autowired
    private AddressService addressService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressQuery.class);

    @GraphQLMutation(name = "createAddress")
    public Address createAddress(
            @GraphQLArgument(name = "name")   @Valid String name
    ){
        Address a = addressService.create(name);
        System.out.println(a);
        return a;
    }

    public static class ExtendedPage<N> implements Page<N> {

        private final List<Edge<N>> edges;
        private final PageInfo pageInfo;
        private final long totalCount;

        ExtendedPage(List<Edge<N>> edges, PageInfo pageInfo, long totalCount) {
            this.edges = edges;
            this.pageInfo = pageInfo;
            this.totalCount = totalCount;
        }

        @Override
        public List<Edge<N>> getEdges() {
            return edges;
        }

        @Override
        public PageInfo getPageInfo() {
            return pageInfo;
        }

        public long getTotalCount() {
            return totalCount;
        }
    }

    public static class ExtendedConnection<E extends Edge> implements Connection<E> {

        private final List<E> edges;
        private final PageInfo pageInfo;
        private final long totalCount;

        ExtendedConnection(List<E> edges, PageInfo pageInfo, long count) {
            this.edges = edges;
            this.pageInfo = pageInfo;
            this.totalCount = count;
        }

        @Override
        public List<E> getEdges() {
            return edges;
        }

        @Override
        public PageInfo getPageInfo() {
            return pageInfo;
        }

        public long getTotalCount() {
            return totalCount;
        }
    }

    public static class ExtendedEdge<T> extends DefaultEdge<T> {


        ExtendedEdge(T node, ConnectionCursor cursor) {
            super(node, cursor);
        }

    }


    @GraphQLQuery(name = "allAdress")
    public ExtendedConnection<ExtendedEdge<Address>> getExtended(@GraphQLArgument(name = "first") int first, @GraphQLArgument(name = "after") String after) {
        List<Address> books = addressService.getAll().stream().skip( Integer.parseInt(after)*first).limit(first).collect(Collectors.toList());
        long count = addressService.getAll().size();
        long offset = Long.parseLong(after);

        return PageFactory.createOffsetBasedConnection(books, addressService.getAll().size(), offset*first,
                (node, cursor) -> new ExtendedEdge<>(node, cursor), (edges, info) -> new ExtendedConnection<>(edges, info, count));
    }





}
