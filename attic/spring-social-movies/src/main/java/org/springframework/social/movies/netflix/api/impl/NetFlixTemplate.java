/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.movies.netflix.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.movies.netflix.api.CatalogTitle;
import org.springframework.social.movies.netflix.api.NetFlixApi;
import org.springframework.social.movies.netflix.api.NetFlixUserProfile;
import org.springframework.social.movies.netflix.api.QueueItem;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;
import org.springframework.web.client.RestTemplate;

public class NetFlixTemplate extends AbstractOAuth1ApiBinding implements NetFlixApi {

	private final String userBaseUrl;

	public NetFlixTemplate(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
		super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		registerNetflixModule(getRestTemplate());
		this.userBaseUrl = getUserBaseUrl();
	}
	
	public NetFlixUserProfile getUserProfile() {
		return getRestTemplate().getForObject(userBaseUrl + "?output=json", NetFlixUserProfile.class);
	}
	
	public List<CatalogTitle> searchForTitles(String searchTerm) {
		Map<String, Object> resultMap = getRestTemplate().getForObject(SEARCH_TITLES_URL, Map.class, searchTerm);
		Map<String, Object> titlesMap = (Map<String, Object>) resultMap.get("catalog_titles");
		List<Map<String, Object>> titlesList = (List<Map<String, Object>>) titlesMap.get("catalog_title");
		List<CatalogTitle> titles = new ArrayList<CatalogTitle>();
		for (Map<String, Object> titleMap : titlesList) {
			String id = String.valueOf(titleMap.get("id"));
			String title = ((Map<String, String>) titleMap.get("title")).get("regular");
			String releaseYear = String.valueOf(titleMap.get("release_year"));
			titles.add(new CatalogTitle(id, title, releaseYear));
		}
		return titles;
	}

	public List<QueueItem> getDiscQueue() {
		Map<String, Object> resultMap = getRestTemplate().getForObject(userBaseUrl + QUEUE_PATH, Map.class);
		Map<String, Object> queueMap = (Map<String, Object>) resultMap.get("queue");
		List<Map<String, Object>> queueItemMaps = (List<Map<String, Object>>) queueMap.get("queue_item");
		List<QueueItem> queueItems = new ArrayList<QueueItem>();
		for (Map<String, Object> queueItemMap : queueItemMaps) {
			List<Map<String, String>> links = ((List<Map<String, String>>) queueItemMap.get("link"));
			String itemId = findItemId(links);
			String title = String.valueOf(((Map) queueItemMap.get("title")).get("regular"));
			String boxArtUrl = String.valueOf(((Map) queueItemMap.get("box_art")).get("small"));
			String releaseYear = String.valueOf(queueItemMap.get("release_year"));
			queueItems.add(new QueueItem(itemId, title, releaseYear, boxArtUrl));
		}
		return queueItems;
	}

	private String findItemId(List<Map<String, String>> linkList) {
		for (Map<String, String> linkMap : linkList) {
			if(linkMap.get("rel").equals("http://schemas.netflix.com/catalog/title")) {
				return linkMap.get("href");
			}
		}
		return "";
	}
	
	private String getUserBaseUrl() {
		Map<String, Map<String, Map<String, String>>> result = getRestTemplate().getForObject(CURRENT_USER_URL, Map.class);
		return result.get("resource").get("link").get("href");
	}
	
	private void registerNetflixModule(RestTemplate restTemplate) {
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		for (HttpMessageConverter<?> converter : converters) {
			if(converter instanceof MappingJacksonHttpMessageConverter) {
				MappingJacksonHttpMessageConverter jsonConverter = (MappingJacksonHttpMessageConverter) converter;
				ObjectMapper objectMapper = new ObjectMapper();				
				objectMapper.registerModule(new NetFlixModule());
				jsonConverter.setObjectMapper(objectMapper);
			}
		}
	}

	private static final String SEARCH_TITLES_URL = "http://api-public.netflix.com/catalog/titles?term={term}&max_results=5&output=json";
	private static final String CURRENT_USER_URL = "http://api-public.netflix.com/users/current?output=json";
	private static final String QUEUE_PATH = "/queues/disc?output=json";
}
