/*****************************************************************************
 * Copyright (c) 2018 Johannes Faltermeier and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Johannes Faltermeier - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.uml.interaction.model.spi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.papyrus.uml.interaction.internal.model.commands.CompoundModelCommand;
import org.eclipse.papyrus.uml.interaction.model.RemovalCommand;

/**
 * Basic implementation of a {@link RemovalCommand}.
 */
public class RemovalCommandImpl extends CommandWrapper implements RemovalCommand {

	private Set<EObject> toRemove = new LinkedHashSet<EObject>();

	private EditingDomain domain;

	/**
	 * Creates an empty removal command, which wraps the given command.
	 */
	public RemovalCommandImpl(EditingDomain domain, Command command) {
		super(command);
		this.domain = domain;
	}

	/**
	 * Creates a new removal command. It is expected that the given command will lead to the deletion of the
	 * given object once executed.
	 */
	public RemovalCommandImpl(EditingDomain domain, Command command, EObject object) {
		super(command);
		this.domain = domain;
		toRemove.add(object);
	}

	/**
	 * Creates a removal command which combines the result of the given removal commands.
	 */
	public RemovalCommandImpl(EditingDomain domain, List<RemovalCommand> commands) {
		super(CompoundModelCommand.compose(domain, new ArrayList<Command>(commands)));
		this.domain = domain;
		toRemove.addAll(commands.stream()//
				.flatMap(rm -> rm.getElementsToRemove().stream())//
				.collect(Collectors.toSet()));
	}

	@Override
	public Set<EObject> getElementsToRemove() {
		return Collections.unmodifiableSet(toRemove);
	}

	@Override
	public Command chain(Command next) {
		return CompoundModelCommand.compose(domain, this, next);
	}

}
