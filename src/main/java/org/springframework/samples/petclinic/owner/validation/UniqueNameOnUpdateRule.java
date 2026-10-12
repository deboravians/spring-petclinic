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

import java.util.Objects;

import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

class UniqueNameOnUpdateRule implements PetValidationRule {

	private final Owner owner;

	UniqueNameOnUpdateRule(Owner owner) {
		this.owner = owner;
	}

	@Override
	public void validate(Pet pet, Errors errors) {
		String petName = pet.getName();
		if (StringUtils.hasText(petName)) {
			Pet existingPet = this.owner.getPet(petName, false);
			if (existingPet != null && !Objects.equals(existingPet.getId(), pet.getId())) {
				errors.rejectValue("name", "duplicate", "already exists");
			}
		}
	}

}
