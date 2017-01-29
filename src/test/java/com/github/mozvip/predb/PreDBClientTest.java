package com.github.mozvip.predb;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.github.mozvip.predb.model.PreDBCategory;
import com.github.mozvip.predb.model.PreDBPost;
import com.github.mozvip.predb.model.PreDBPostDetails;

public class PreDBClientTest {
	
	private PreDBClient predb = new PreDBClient();

	@Test
	public void testGetMoviesHD() throws Exception {
		Collection<PreDBPost> resultsForPageTagsCats = predb.getPostsForPageTagsCats(1, null, PreDBCategory.MOVIES_HD);
		for (PreDBPost preDBPost : resultsForPageTagsCats) {
			PreDBPostDetails postDetails = predb.getPostDetails(  preDBPost.getId() );
			Assert.assertNotNull( postDetails.getRlsName() );
		}
	}

}
