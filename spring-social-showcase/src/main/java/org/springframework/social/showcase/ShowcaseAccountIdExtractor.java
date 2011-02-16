package org.springframework.social.showcase;

import java.io.Serializable;
import java.security.Principal;

import org.springframework.social.web.connect.AccountIdExtractor;
import org.springframework.web.context.request.WebRequest;


public class ShowcaseAccountIdExtractor implements AccountIdExtractor {

	public Serializable extractAccountId(WebRequest request) {
		return request.getUserPrincipal().getName();
	}

}
