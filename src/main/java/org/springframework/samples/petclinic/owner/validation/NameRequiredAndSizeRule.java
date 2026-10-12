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

import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

class NameRequiredAndSizeRule implements PetValidationRule {

	static final int MAX_NAME_LENGTH = 30;

	@Override
	public void validate(Pet pet, Errors errors) {
		String name = pet.getName();
		if (!StringUtils.hasText(name)) {
			errors.rejectValue("name", REQUIRED, REQUIRED);
		}
		else if (name.length() > MAX_NAME_LENGTH) {
			errors.rejectValue("name", "size", "Name must be no more than 30 characters");
		}
	}

}
