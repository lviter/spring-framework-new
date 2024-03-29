/*
 * Copyright 2002-2024 the original author or authors.
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

package org.springframework.transaction.reactive;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.lang.Nullable;

/**
 * Mutable transaction context that encapsulates transactional synchronizations and
 * resources in the scope of a single transaction. Transaction context is typically
 * held by an outer {@link TransactionContextHolder} or referenced directly within
 * from the subscriber context.
 *
 * @author Mark Paluch
 * @author Juergen Hoeller
 * @since 5.2
 * @see TransactionContextManager
 * @see reactor.util.context.Context
 */
public class TransactionContext {

	@Nullable
	private final TransactionContext parent;

	private final Map<Object, Object> resources = new LinkedHashMap<>();

	@Nullable
	private Set<TransactionSynchronization> synchronizations;

	@Nullable
	private volatile String currentTransactionName;

	private volatile boolean currentTransactionReadOnly;

	@Nullable
	private volatile Integer currentTransactionIsolationLevel;

	private volatile boolean actualTransactionActive;


	TransactionContext() {
		this(null);
	}

	TransactionContext(@Nullable TransactionContext parent) {
		this.parent = parent;
	}


	@Nullable
	public TransactionContext getParent() {
		return this.parent;
	}

	public Map<Object, Object> getResources() {
		return this.resources;
	}

	public void setSynchronizations(@Nullable Set<TransactionSynchronization> synchronizations) {
		this.synchronizations = synchronizations;
	}

	@Nullable
	public Set<TransactionSynchronization> getSynchronizations() {
		return this.synchronizations;
	}

	public void setCurrentTransactionName(@Nullable String currentTransactionName) {
		this.currentTransactionName = currentTransactionName;
	}

	@Nullable
	public String getCurrentTransactionName() {
		return this.currentTransactionName;
	}

	public void setCurrentTransactionReadOnly(boolean currentTransactionReadOnly) {
		this.currentTransactionReadOnly = currentTransactionReadOnly;
	}

	public boolean isCurrentTransactionReadOnly() {
		return this.currentTransactionReadOnly;
	}

	public void setCurrentTransactionIsolationLevel(@Nullable Integer currentTransactionIsolationLevel) {
		this.currentTransactionIsolationLevel = currentTransactionIsolationLevel;
	}

	@Nullable
	public Integer getCurrentTransactionIsolationLevel() {
		return this.currentTransactionIsolationLevel;
	}

	public void setActualTransactionActive(boolean actualTransactionActive) {
		this.actualTransactionActive = actualTransactionActive;
	}

	public boolean isActualTransactionActive() {
		return this.actualTransactionActive;
	}


	public void clear() {
		this.synchronizations = null;
		this.currentTransactionName = null;
		this.currentTransactionReadOnly = false;
		this.currentTransactionIsolationLevel = null;
		this.actualTransactionActive = false;
	}

}
