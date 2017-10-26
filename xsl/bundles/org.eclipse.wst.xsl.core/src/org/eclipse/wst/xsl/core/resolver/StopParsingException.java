package org.eclipse.wst.xsl.core.resolver;

import org.xml.sax.SAXException;

/**
 * An exception indicating that the parsing should stop. This is usually
 * triggered when the top-level element has been found.
 * 
 * @since 1.0
 */

class StopParsingException extends SAXException {
	/**
	 * All serializable objects should have a stable serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an instance of <code>StopParsingException</code> with a
	 * <code>null</code> detail message.
	 */
	public StopParsingException() {
		super((String) null);
	}
}