/**
 * Copyright (C) 2010-2015 Morgner UG (haftungsbeschränkt)
 *
 * This file is part of Structr <http://structr.org>.
 *
 * Structr is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Structr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Structr.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.structr.core.property;

import org.structr.common.SecurityContext;
import org.structr.core.GraphObject;
import org.structr.core.converter.PropertyConverter;
import org.structr.core.converter.RelationshipStartNodeConverter;

//~--- classes ----------------------------------------------------------------

/**
 * A property that returns the start node of a relationship.
 *
 * @author Axel Morgner
 */
public class StartNodeProperty<T> extends AbstractPrimitiveProperty<T> {

	public StartNodeProperty(String name) {

		super(name);

	}

	//~--- methods --------------------------------------------------------

	@Override
	public PropertyConverter<T, ?> databaseConverter(SecurityContext securityContext) {

		return databaseConverter(securityContext, null);

	}

	@Override
	public PropertyConverter<T, ?> databaseConverter(SecurityContext securityContext, GraphObject entity) {

		return new RelationshipStartNodeConverter(securityContext, entity);

	}

	@Override
	public PropertyConverter<?, T> inputConverter(SecurityContext securityContext) {

		return null;

	}

	@Override
	public Object fixDatabaseProperty(Object value) {

		return null;

	}

	@Override
	public String typeName() {

		return null;

	}

	@Override
	public Class valueType() {
		return null;
	}

	@Override
	public Integer getSortType() {
		return null;
	}
}
