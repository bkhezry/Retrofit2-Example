package ir.bkhezry.retrofit2.Service;


import java.util.List;

import ir.bkhezry.retrofit2.Model.Question;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ir.bkhezry.retrofit2.Model.Movie;

/**
 * Created by bkhezry on 2/20/2016.
 */
public interface APIService {
    @GET("questions")
    Call<Question> getQuestionsService(@Query("page") int page,
                                       @Query("pagesize") int pagesize,
                                       @Query("order") String order,
                                       @Query("sort") String sort,
                                       @Query("tagged") String tagged,
                                       @Query("site") String site);

    @GET("movies.json")
    Call<List<Movie>> getMoviesService();
}
