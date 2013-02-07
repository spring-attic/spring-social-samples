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
package org.springframework.social.showcase.twitter;

import javax.inject.Inject;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TwitterMessageController {

	private final Twitter twitter;
	
	@Inject
	public TwitterMessageController(Twitter twitter) {
		this.twitter = twitter;
	}
	
	@RequestMapping(value="/twitter/messages", method=RequestMethod.GET)
	public String inbox(Model model) {
		model.addAttribute("directMessages", twitter.directMessageOperations().getDirectMessagesReceived());
		model.addAttribute("dmListType", "Received");
		model.addAttribute("messageForm", new MessageForm());
		return "twitter/messages";
	}

	@RequestMapping(value="/twitter/messages/sent", method=RequestMethod.GET)
	public String sent(Model model) {
		model.addAttribute("directMessages", twitter.directMessageOperations().getDirectMessagesSent());
		model.addAttribute("dmListType", "Sent");
		model.addAttribute("messageForm", new MessageForm());
		return "twitter/messages";
	}

	@RequestMapping(value="/twitter/messages", method=RequestMethod.POST)
	public String sent(MessageForm message) {
		twitter.directMessageOperations().sendDirectMessage(message.getTo(), message.getText());
		return "redirect:/twitter/messages";
	}
	
}
