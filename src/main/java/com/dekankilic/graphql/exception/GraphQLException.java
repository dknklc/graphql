package com.dekankilic.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class GraphQLException extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof UserNotFoundException)
            return toGraphQLError(ex);
        else if (ex instanceof Exception)
            return toGraphQLError(ex);
        return super.resolveToSingleError(ex, env);
    }

    private GraphQLError toGraphQLError(Throwable throwable) {
        return GraphQLError.newError().message(throwable.getMessage()).errorType(ErrorType.DataFetchingException).build();
    }
}
