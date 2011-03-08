package org.springframework.social.movies.netflix;

public class QueueItem {

	private final String id;

	private final String title;

	private final String releaseYear;

	public QueueItem(String id, String title, String releaseYear) {
		this.title = title;
		this.releaseYear = releaseYear;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public String getTitle() {
		return title;
	}

}
