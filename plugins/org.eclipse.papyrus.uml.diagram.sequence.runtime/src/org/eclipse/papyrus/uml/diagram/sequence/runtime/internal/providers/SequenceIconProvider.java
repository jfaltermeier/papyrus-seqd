/*****************************************************************************
 * Copyright (c) 2018 EclipseSource and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.runtime.internal.providers;

import org.eclipse.papyrus.infra.gmfdiag.common.providers.DefaultElementTypeIconProvider;

public class SequenceIconProvider extends DefaultElementTypeIconProvider {

	public SequenceIconProvider() {
		super(SequenceElementTypes.TYPED_INSTANCE);
	}
}
