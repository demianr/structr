/**
 * Copyright (C) 2010-2014 Morgner UG (haftungsbeschränkt)
 *
 * This file is part of Structr <http://structr.org>.
 *
 * Structr is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Structr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Structr.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.structr.websocket.command;


import org.structr.common.error.FrameworkException;
import org.structr.web.entity.dom.DOMNode;
import org.structr.web.entity.dom.ShadowDocument;
import org.structr.web.entity.dom.Template;
import org.structr.websocket.StructrWebSocket;
import org.structr.websocket.message.MessageBuilder;
import org.structr.websocket.message.WebSocketMessage;
import org.w3c.dom.DOMException;


/**
 * Create a shared component as a clone of the source node.
 *
 * This command will create a SYNC relationship: (source)&lt;-[:SYNC]-(component)
 *
 * @author Axel Morgner
 */
public class CreateComponentCommand extends AbstractCommand {

	static {

		StructrWebSocket.addCommand(CreateComponentCommand.class);
	}

	@Override
	public void processMessage(WebSocketMessage webSocketData) {

		String id                             = webSocketData.getId();

		if (id != null) {

			final DOMNode node = (DOMNode) getDOMNode(id);

			try {

				DOMNode clonedNode = (DOMNode) node.cloneNode(false);
				
				// Child nodes of a template must stay in page tree
				if (!(clonedNode instanceof Template)) {
				
					moveChildNodes(node, clonedNode);
				
				}

				ShadowDocument hiddenDoc = getOrCreateHiddenDocument();
				clonedNode.setProperty(DOMNode.ownerDocument, hiddenDoc);

				// Change page (owner document) of all children recursively
				for (DOMNode child : DOMNode.getAllChildNodes(clonedNode)) {
					child.setProperty((DOMNode.ownerDocument), hiddenDoc);
				}

				node.setProperty(DOMNode.sharedComponent, clonedNode);
				
			} catch (DOMException | FrameworkException ex) {

				// send DOM exception
				getWebSocket().send(MessageBuilder.status().code(422).message(ex.getMessage()).build(), true);

			}

		} else {

			getWebSocket().send(MessageBuilder.status().code(422).message("Cannot append node without id").build(), true);
		}

	}

	@Override
	public String getCommand() {

		return "CREATE_COMPONENT";

	}


}
