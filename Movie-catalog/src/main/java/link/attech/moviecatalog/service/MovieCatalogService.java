package link.attech.moviecatalog.service;

import link.attech.moviecatalog.request.UserRequest;
import link.attech.moviecatalog.response.BaseResponse;
import link.attech.moviecatalog.response.UserResponse;

public interface MovieCatalogService {
    BaseResponse getCatalog();
}
