/*
 * Copyright 2013 the original author or authors.
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
package org.springframework.social.showcase.facebook;

import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacebookPhotosController {

	private final Facebook facebook;

	@Inject
	public FacebookPhotosController(Facebook facebook) {
		this.facebook = facebook;
	}

	@RequestMapping(value="/facebook/albums", method=RequestMethod.GET)
	public String showAlbums(Model model) {
		model.addAttribute("albums", facebook.mediaOperations().getAlbums());
		return "facebook/albums";
	}
	
	@RequestMapping(value="/facebook/album/{albumId}", method=RequestMethod.GET)
	public String showAlbum(@PathVariable("albumId") String albumId, Model model) {
		model.addAttribute("album", facebook.mediaOperations().getAlbum(albumId));
		model.addAttribute("photos", facebook.mediaOperations().getPhotos(albumId));
		return "facebook/album";
	}
	
}
