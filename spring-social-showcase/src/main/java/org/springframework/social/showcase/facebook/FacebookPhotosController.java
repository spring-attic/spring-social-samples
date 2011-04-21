package org.springframework.social.showcase.facebook;

import javax.inject.Inject;

import org.springframework.social.facebook.api.FacebookApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacebookPhotosController {

	private final FacebookApi facebookApi;

	@Inject
	public FacebookPhotosController(FacebookApi facebookApi) {
		this.facebookApi = facebookApi;
	}

	@RequestMapping(value="/facebook/albums", method=RequestMethod.GET)
	public String showAlbums(Model model) {
		model.addAttribute("albums", facebookApi.mediaOperations().getAlbums());
		return "facebook/albums";
	}
	
	@RequestMapping(value="/facebook/album/{albumId}", method=RequestMethod.GET)
	public String showAlbum(@PathVariable("albumId") String albumId, Model model) {
		model.addAttribute("album", facebookApi.mediaOperations().getAlbum(albumId));
		model.addAttribute("photos", facebookApi.mediaOperations().getPhotos(albumId));
		return "facebook/album";
	}
	
}
