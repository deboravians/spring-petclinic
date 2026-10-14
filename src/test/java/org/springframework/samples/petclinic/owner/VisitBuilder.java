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
package org.springframework.samples.petclinic.owner;

import java.time.LocalDate;

public final class VisitBuilder {

	private LocalDate date;

	private String description;

	private boolean dateSet;

	private VisitBuilder() {
	}

	public static VisitBuilder visit() {
		return new VisitBuilder();
	}

	public VisitBuilder withDate(LocalDate date) {
		this.date = date;
		this.dateSet = true;
		return this;
	}

	public VisitBuilder withDescription(String description) {
		this.description = description;
		return this;
	}

	public Visit build() {
		Visit visit = new Visit();
		if (this.dateSet) {
			visit.setDate(this.date);
		}
		if (this.description != null) {
			visit.setDescription(this.description);
		}
		return visit;
	}

}
