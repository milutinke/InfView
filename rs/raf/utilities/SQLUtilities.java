package rs.raf.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtilities {
	public static Connection openConnection(String driverClass, String driverSubProtocol, String driverName,
			String host, String db, String username, String password, int port)
			throws SQLException, ClassNotFoundException {
		// Select the driver
		Class.forName(driverClass);

		// Open the connection
		return DriverManager.getConnection(
				"jdbc:" + driverSubProtocol + ":" + driverName + "://" + host + ":" + port + "/" + db, username,
				password);
	}

	public static String escapeString(String x, boolean escapeDoubleQuotes) {
		StringBuilder sBuilder = new StringBuilder(x.length() * 11 / 10);

		int stringLength = x.length();

		for (int i = 0; i < stringLength; ++i) {
			char c = x.charAt(i);

			switch (c) {
			case 0: /* Must be escaped for 'mysql' */
				sBuilder.append('\\');
				sBuilder.append('0');

				break;

			case '\n': /* Must be escaped for logs */
				sBuilder.append('\\');
				sBuilder.append('n');

				break;

			case '\r':
				sBuilder.append('\\');
				sBuilder.append('r');

				break;

			case '\\':
				sBuilder.append('\\');
				sBuilder.append('\\');

				break;

			case '\'':
				sBuilder.append('\\');
				sBuilder.append('\'');

				break;

			case '"': /* Better safe than sorry */
				if (escapeDoubleQuotes) {
					sBuilder.append('\\');
				}

				sBuilder.append('"');

				break;

			case '\032': /* This gives problems on Win32 */
				sBuilder.append('\\');
				sBuilder.append('Z');

				break;

			case '\u00a5':
			case '\u20a9':
				// escape characters interpreted as backslash by mysql
				// fall through

			default:
				sBuilder.append(c);
			}
		}

		return sBuilder.toString();
	}
}
