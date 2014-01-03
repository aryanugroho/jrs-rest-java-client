package com.jaspersoft.jasperserver.jaxrs.client.builder.permissions;

import com.jaspersoft.jasperserver.dto.permissions.RepositoryPermission;
import com.jaspersoft.jasperserver.dto.permissions.RepositoryPermissionListWrapper;
import com.jaspersoft.jasperserver.jaxrs.client.builder.OperationResult;
import com.jaspersoft.jasperserver.jaxrs.client.builder.RequestBuilder;

import javax.ws.rs.client.WebTarget;

public class PermissionsRequestBuilder<RequestType, ResponseType>
        extends RequestBuilder<RequestType, ResponseType> {

    private static final String URI = "/permissions";

    public PermissionsRequestBuilder(Class<ResponseType> responseClass) {
        super(responseClass);
        this.setPath(URI);
    }

    private PermissionsRequestBuilder(WebTarget webTarget, Class<ResponseType> responseClass) {
        super(responseClass);
        this.setTarget(webTarget);
    }

    public RequestBuilder<RequestType, RepositoryPermission> permissionRecipient(PermissionRecipient recipient, String name) {
        String protocol = recipient.getProtocol();
        String uri = protocol + ":%2F" + name;
        this.addMatrixParam("recipient", uri);
        return new PermissionsRequestBuilder<RequestType, RepositoryPermission>(this.getPath(), RepositoryPermission.class);
    }

    @Override
    public OperationResult<ResponseType> put(RequestType entity) {
        if (entity instanceof RepositoryPermissionListWrapper)
            setContentType("application/collection+json");
        return super.put(entity);
    }

    @Override
    public OperationResult<ResponseType> post(RequestType entity) {
        if (entity instanceof RepositoryPermissionListWrapper)
            setContentType("application/collection+json");
        return super.post(entity);
    }
}