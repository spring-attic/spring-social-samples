package org.springframework.social.movies.netflix;

import java.util.List;

public interface NetFlixApi {

	List<QueueItem> getDiscQueue();

}
