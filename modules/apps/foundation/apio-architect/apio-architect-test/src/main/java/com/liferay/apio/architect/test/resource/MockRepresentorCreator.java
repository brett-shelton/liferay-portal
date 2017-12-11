/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.apio.architect.test.resource;

import com.liferay.apio.architect.resource.Representor;
import com.liferay.apio.architect.resource.identifier.StringIdentifier;
import com.liferay.apio.architect.test.resource.model.FirstEmbeddedModel;
import com.liferay.apio.architect.test.resource.model.RootModel;
import com.liferay.apio.architect.test.resource.model.SecondEmbeddedModel;
import com.liferay.apio.architect.test.resource.model.ThirdEmbeddedModel;

import java.util.Date;
import java.util.Optional;

/**
 * Provides methods that create {@link Representor} objects for {@link
 * RootModel}, {@link FirstEmbeddedModel}, {@link SecondEmbeddedModel} or {@link
 * ThirdEmbeddedModel}.
 *
 * <p>
 * This class shouldn't be instantiated.
 * </p>
 *
 * @author Alejandro Hernández
 */
public class MockRepresentorCreator {

	/**
	 * Creates a mock {@code Representor} for {@code FirstEmbeddedModel}.
	 *
	 * @return the mock {@code Representor} for {@code FirstEmbeddedModel}
	 */
	public static Representor<FirstEmbeddedModel, StringIdentifier>
		createFirstEmbeddedModelRepresentor() {

		Representor.Builder<FirstEmbeddedModel, StringIdentifier> builder =
			new Representor.Builder<>(StringIdentifier.class);

		return builder.types(
			"Type"
		).identifier(
			model -> model::getId
		).addBinary(
			"binary", __ -> null
		).addBoolean(
			"boolean", __ -> true
		).addEmbeddedModel(
			"embedded", SecondEmbeddedModel.class,
			__ -> Optional.of(() -> "first")
		).addLink(
			"link", "www.liferay.com"
		).addLinkedModel(
			"linked", SecondEmbeddedModel.class,
			__ -> Optional.of(() -> "second")
		).addNumber(
			"number", __ -> 42
		).addRelatedCollection(
			"relatedCollection", SecondEmbeddedModel.class,
			model -> (StringIdentifier)model::getId
		).addString(
			"string", __ -> "A string"
		).build();
	}

	/**
	 * Creates a mock {@code Representor} for {@code RootModel}.
	 *
	 * @param  activateNulls whether to add {@code null} empty values
	 * @return the mock {@code Representor} for {@code RootModel}
	 */
	public static Representor<RootModel, StringIdentifier>
		createRootModelRepresentor(boolean activateNulls) {

		Representor.Builder<RootModel, StringIdentifier> builder =
			new Representor.Builder<>(StringIdentifier.class);

		Representor.Builder<RootModel, StringIdentifier>.FirstStep
			firstStepBuilder = builder.types(
				"Type 1", "Type 2"
			).identifier(
				model -> model::getId
			).addBinary(
				"binary1", __ -> null
			).addBinary(
				"binary2", __ -> null
			).addBoolean(
				"boolean1", __ -> true
			).addBoolean(
				"boolean2", __ -> false
			).addDate(
				"date1", __ -> new Date(1465981200000L)
			).addDate(
				"date2", __ -> new Date(1491244560000L)
			).addEmbeddedModel(
				"embedded1", FirstEmbeddedModel.class,
				__ -> Optional.of(() -> "first")
			).addEmbeddedModel(
				"embedded2", FirstEmbeddedModel.class,
				__ -> Optional.of(() -> "second")
			).addLinkedModel(
				"linked1", FirstEmbeddedModel.class,
				__ -> Optional.of(() -> "third")
			).addLinkedModel(
				"linked2", FirstEmbeddedModel.class,
				__ -> Optional.of(() -> "fourth")
			).addLink(
				"link1", "www.liferay.com"
			).addLink(
				"link2", "community.liferay.com"
			).addLocalizedString(
				"localizedString1", (model, language) -> "Translated 1"
			).addLocalizedString(
				"localizedString2", (model, language) -> "Translated 2"
			).addNumber(
				"number1", __ -> 2017
			).addNumber(
				"number2", __ -> 42
			).addRelatedCollection(
				"relatedCollection1", FirstEmbeddedModel.class,
				model -> (StringIdentifier)model::getId
			).addRelatedCollection(
				"relatedCollection2", FirstEmbeddedModel.class,
				model -> (StringIdentifier)model::getId
			).addString(
				"string1", __ -> "Live long and prosper"
			).addString(
				"string2", __ -> "Hypermedia"
			);

		if (activateNulls) {
			return firstStepBuilder.addBoolean(
				"boolean3", __ -> null
			).addLink(
				"link3", null
			).addLink(
				"link4", ""
			).addLocalizedString(
				"localizedString3", (model, language) -> null
			).addLocalizedString(
				"localizedString4", (model, language) -> ""
			).addNumber(
				"number3", __ -> null
			).addString(
				"string3", __ -> null
			).addString(
				"string4", __ -> ""
			).build();
		}

		return firstStepBuilder.build();
	}

	/**
	 * Creates a mock {@code Representor} for {@code SecondEmbeddedModel}.
	 *
	 * @return the mock {@code Representor} for {@code SecondEmbeddedModel}
	 */
	public static Representor<SecondEmbeddedModel, StringIdentifier>
		createSecondEmbeddedModelRepresentor() {

		Representor.Builder<SecondEmbeddedModel, StringIdentifier> builder =
			new Representor.Builder<>(StringIdentifier.class);

		return builder.types(
			"Type"
		).identifier(
			model -> model::getId
		).addBinary(
			"binary", __ -> null
		).addBoolean(
			"boolean", __ -> false
		).addEmbeddedModel(
			"embedded", ThirdEmbeddedModel.class,
			__ -> Optional.of(() -> "first")
		).addLink(
			"link", "community.liferay.com"
		).addLinkedModel(
			"linked", ThirdEmbeddedModel.class,
			__ -> Optional.of(() -> "second")
		).addNumber(
			"number", __ -> 2017
		).addRelatedCollection(
			"relatedCollection", ThirdEmbeddedModel.class,
			model -> (StringIdentifier)model::getId
		).addString(
			"string", __ -> "A string"
		).build();
	}

	/**
	 * Creates a mock {@code Representor} for {@code ThirdEmbeddedModel}.
	 *
	 * @return the mock {@code Representor} for {@code ThirdEmbeddedModel}
	 */
	public static Representor<ThirdEmbeddedModel, StringIdentifier>
		createThirdEmbeddedModelRepresentor() {

		Representor.Builder<ThirdEmbeddedModel, StringIdentifier> builder =
			new Representor.Builder<>(StringIdentifier.class);

		return builder.types(
			"Type"
		).identifier(
			model -> model::getId
		).build();
	}

	private MockRepresentorCreator() {
		throw new UnsupportedOperationException();
	}

}