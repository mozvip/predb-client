package com.github.mozvip.predb;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PreDBService {
	
	@GET("/")
	Call<ResponseBody> getPostsForPageTagsCats(@Query("page") int page, @Query("tag") String tag, @Query("cats") String categories);
	
	@GET("/")
	Call<ResponseBody> getPostsForPageCats(@Query("page") int page, @Query("cats") String categories);

	@GET("/")
	Call<ResponseBody> getPostsForPageTags(@Query("page") int page, @Query("tag") String tag);

	@GET("/?jsload=1")
	Call<ResponseBody> getPostDetails(@Query("post") String post);

}
