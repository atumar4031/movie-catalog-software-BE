package link.attech.moviecatalog.controller;


import link.attech.moviecatalog.response.BaseResponse;
import link.attech.moviecatalog.service.MovieCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/movieCatalog")
public class movieController {

    @Autowired
    private MovieCatalogService movieCatalogService;

    @GetMapping
    public BaseResponse getCatalog(){
        return movieCatalogService.getCatalog();
    }

}
