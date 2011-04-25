package org.springframework.social.showcase.facebook;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.social.facebook.api.FacebookApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacebookPhotosController {

	private final Provider<FacebookApi> facebookApiProvider;

	@Inject
	public FacebookPhotosController(Provider<FacebookApi> facebookApiProvider) {
		this.facebookApiProvider = facebookApiProvider;
	}

	@RequestMapping(value="/facebook/albums", method=RequestMethod.GET)
	public String showAlbums(Model model) {
		model.addAttribute("albums", getFacebookApi().mediaOperations().getAlbums());
		return "facebook/albums";
	}
	
	@RequestMapping(value="/facebook/album/{albumId}", method=RequestMethod.GET)
	public String showAlbum(@PathVariable("albumId") String albumId, Model model) {
		model.addAttribute("album", getFacebookApi().mediaOperations().getAlbum(albumId));
		model.addAttribute("photos", getFacebookApi().mediaOperations().getPhotos(albumId));
		return "facebook/album";
	}
	
	private FacebookApi getFacebookApi() {
		return facebookApiProvider.get();
	}
	
}
