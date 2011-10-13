package org.springframework.social.showcase.jsp;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

public class SocialConnectedTag extends RequestContextAwareTag {
	//tag attribute
	private String provider;

	@Override
	protected int doStartTagInternal() throws Exception {
    	if (supportsProvider(provider) && getConnectionRepository().findConnections(provider).size() > 0) {
            return EVAL_BODY_INCLUDE;        	
        }
        
        return SKIP_BODY;
	}
    
	public void setProvider(String provider) {
		this.provider = provider;
	}

	private ConnectionRepository getConnectionRepository() {
		WebApplicationContext applicationContext = getRequestContext().getWebApplicationContext();
		return applicationContext.getBean(ConnectionRepository.class);
	}
	
	private boolean supportsProvider(String provider) {
		return provider.equals("facebook") || provider.equals("twitter");
	}
}