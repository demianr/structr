package org.structr.schema.export;

import java.util.Map;
import org.structr.common.error.FrameworkException;
import org.structr.core.app.App;
import org.structr.core.entity.AbstractSchemaNode;
import org.structr.core.entity.SchemaProperty;
import org.structr.schema.SchemaHelper.Type;
import org.structr.schema.json.JsonStringProperty;

/**
 *
 * @author Christian Morgner
 */
public class StructrStringProperty extends StructrPropertyDefinition implements JsonStringProperty {

	public StructrStringProperty(final StructrTypeDefinition parent, final String name) {
		super(parent, name);
	}

	@Override
	public String getType() {
		return "string";
	}

	@Override
	void deserialize(final Map<String, Object> source) {
		super.deserialize(source);
	}

	@Override
	void deserialize(final SchemaProperty property) {
		super.deserialize(property);
	}

	@Override
	SchemaProperty createDatabaseSchema(final App app, final AbstractSchemaNode schemaNode) throws FrameworkException {

		final SchemaProperty property = super.createDatabaseSchema(app, schemaNode);

		property.setProperty(SchemaProperty.propertyType, Type.String.name());

		return property;
	}
}
