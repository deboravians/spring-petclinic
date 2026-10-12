/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner.validation;

import java.util.List;

import org.springframework.samples.petclinic.owner.Owner;

public final class PetValidationRules {

	private PetValidationRules() {
	}

	public static List<PetValidationRule> fieldRules() {
		return List.of(new NameRequiredAndSizeRule(), new TypeRequiredForNewPetRule(), new BirthDateRequiredRule());
	}

	public static List<PetValidationRule> forCreation(Owner owner) {
		return List.of(new UniqueNameOnCreationRule(owner), new BirthDateNotInFutureRule());
	}

	public static List<PetValidationRule> forUpdate(Owner owner) {
		return List.of(new UniqueNameOnUpdateRule(owner), new BirthDateNotInFutureRule());
	}

}
