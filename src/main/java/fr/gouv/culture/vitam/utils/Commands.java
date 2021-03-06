/**
 * This file is part of Vitam Project.
 * 
 * Copyright 2010, Frederic Bregier, and individual contributors by the @author
 * tags. See the COPYRIGHT.txt in the distribution for a full listing of individual contributors.
 * 
 * All Vitam Project is free software: you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 * 
 * Vitam is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with Vitam. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.gouv.culture.vitam.utils;

import java.io.File;

import org.dom4j.Element;

import fr.gouv.culture.vitam.digest.DigestCompute;

/**
 * Class to rassemble commands 
 * @author "Frederic Bregier"
 * 
 */
public class Commands {

	/**
	 * Check digest for one file
	 * 
	 * @param fic
	 * @param salgo
	 * @param sintegrity
	 * @return the Element with the result (.[@status='ok'] != null => OK)
	 */
	public final static Element checkDigest(File fic, String salgo, String sintegrity) {
		Element eltdigest = null;
		try {
			String compute = DigestCompute.getHashCode(fic, salgo);
			if (!compute.equals(sintegrity)) {
				eltdigest = XmlDom.factory.createElement("digest");
				eltdigest.addAttribute("status",
						StaticValues.LBL.error_digest.get());
				eltdigest.addAttribute("compute", compute);
				eltdigest.addAttribute("source", sintegrity);
				eltdigest.addAttribute("algo", salgo);
			} else {
				eltdigest = XmlDom.factory.createElement("digest");
				eltdigest.addAttribute("status",
						"ok");
				eltdigest.addAttribute("source", sintegrity);
				eltdigest.addAttribute("algo", salgo);
			}
		} catch (Exception e) {
			eltdigest = XmlDom.factory.createElement("digest");
			eltdigest.addAttribute("status",
					"Error: " +
							e.toString());
		}
		return eltdigest;
	}

}
