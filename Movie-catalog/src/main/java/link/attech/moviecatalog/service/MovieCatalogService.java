package link.attech.moviecatalog.service;

import link.attech.moviecatalog.response.BaseResponse;

public interface MovieCatalogService {
    BaseResponse getCatalog(String userId);
}
