/* <LICENSE>
Copyright (C) 2015-2016 Louis JEAN

This file is part of BynarysCode.

BynarysCode is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

BynarysCode is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with BynarysCode. If not, see <http://www.gnu.org/licenses/>.
 </LICENSE> */

package net.bynaryscode.util;

import java.io.IOException;

@SuppressWarnings("serial")
public class FileFormatException extends IOException {
	
	public FileFormatException() {
		super();
	}
	
	public FileFormatException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FileFormatException(String message) {
		super(message);
	}
	
	public FileFormatException(Throwable cause) {
		super(cause);
	}
}
