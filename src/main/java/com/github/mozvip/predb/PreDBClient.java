package com.github.mozvip.predb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.mozvip.predb.model.PreDBCategory;
import com.github.mozvip.predb.model.PreDBPost;
import com.github.mozvip.predb.model.PreDBPostDetails;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PreDBClient {

	private PreDBService service = null;
	private Map<PreDBCategory, String> catMap = new HashMap<>();
	private Map<String, PreDBCategory> invCatMap = new HashMap<>();

	public PreDBClient() {
		service = new Retrofit.Builder().baseUrl("http://predb.me").build().create(PreDBService.class);
		
		for (PreDBCategory category : PreDBCategory.values()) {
			String label = category.name().toLowerCase().replace('_', '-');
			catMap.put(category, label);
			invCatMap.put(label, category);
		}
		
	}

	public Collection<PreDBPost> getPostsForPageTagsCats(int page, String[] tags, PreDBCategory ... categories) throws IOException {

		String tag = null;
		if (tags != null) {
			tag = String.join(",", tags);
		}
		String cats = null;
		if (categories != null) {
			StringBuffer sb = new StringBuffer();
			for (PreDBCategory preDBCategory : categories) {
				sb.append(catMap.get(preDBCategory)).append(',');
			}
			cats = sb.toString();
		}
		
		Response<ResponseBody> response = service.getPostsForPageTagsCats(page, tag, cats).execute();
		HttpUrl url = response.raw().request().url();
		Document document = Jsoup.parse( response.body().string(), url.toString() );
		Elements elements = document.select(".post");
		List<PreDBPost> posts = new ArrayList<>();
		for (Element element : elements) {
			PreDBPost post = new PreDBPost();
			post.setId( element.attr("id"));
			post.setTime( element.select(".p-time").attr("data") );
			post.setTitle( element.select(".p-title").text() );
			
			List<PreDBCategory> postCategories = new ArrayList<>();
			Elements links = element.select(".p-cat a");
			for (Element link : links) {
				String href = link.attr("href");
				String category = href.substring( href.indexOf('=') + 1 );
				PreDBCategory preDBCategory = invCatMap.get( category );
				postCategories.add( preDBCategory );
			}
			
			post.setCategories((PreDBCategory[]) postCategories.toArray(new PreDBCategory[postCategories.size()]));

			posts.add( post );
		}
		return posts;
	}	
	
	public PreDBPostDetails getPostDetails( String id ) throws IOException {
		Response<ResponseBody> response = service.getPostDetails( id ).execute();
		HttpUrl url = response.raw().request().url();
		Document document = Jsoup.parse( response.body().string(), url.toString() );
		
		PreDBPostDetails details = new PreDBPostDetails();
		
		Elements terms = document.select("a.term");
		
		List<String> tags = new ArrayList<>();
		
		for (Element element : terms) {
			if (element.hasClass("t-g")) {
				details.setGroup( element.text() );
			} else if (element.hasClass("t-t")) {
				details.setTitle( element.text() );
			} else {
				tags.add( element.text() );
			}
		}
		
		details.setTags((String[]) tags.toArray(new String[tags.size()]));
		
		return details;
	}

}
