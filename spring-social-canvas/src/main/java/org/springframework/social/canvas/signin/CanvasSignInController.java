package org.springframework.social.canvas.signin;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

public class CanvasSignInController extends ProviderSignInController {

	public CanvasSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository, SignInAdapter signInAdapter) {
		super(connectionFactoryLocator, usersConnectionRepository, signInAdapter);
	}

	public RedirectView oauth2Callback(@PathVariable String providerId, @RequestParam("code") String code, NativeWebRequest request) {
		super.oauth2Callback(providerId, code, request);
		return new RedirectView("http://apps.facebook.com/springsocialcanvas/");
	}
}
