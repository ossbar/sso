package com.hihframework.core.customtaglibs.formtag;

/**
 * Encodes text strings and URLs to be HTML-safe.
 */
public final class HtmlEncoder
{

	private static float newSizeFactor = 1.3f;

	/**
	 * Lookup table for use in encode() method.
	 *
	 * @see #encode
	 */
	public static final String[] TABLE_HTML = new String[256];

	/**
	 * Lookup table for use in encodeTextXxx() methods.
	 *
	 */
	public static final String[] TABLE_HTML_STRICT = new String[256];

	static
	{
		for (int i = 0; i < 10; i++)
		{
			TABLE_HTML[i] = "&#00" + i + ";";
		}
		for (int i = 10; i < 32; i++)
		{
			TABLE_HTML[i] = "&#0" + i + ";";
		}
		for (int i = 32; i < 128; i++)
		{
			TABLE_HTML[i] = String.valueOf((char) i);
		}
		for (int i = 128; i < 256; i++)
		{
			TABLE_HTML[i] = "&#" + i + ";";
		}

		// special characters
		TABLE_HTML['\''] = "&#039;"; // apostrophe ('&apos;' doesn't work - it is not by the w3 specs)
		TABLE_HTML['\"'] = "&quot;"; // double quote
		TABLE_HTML['&'] = "&amp;"; // ampersand
		TABLE_HTML['<'] = "&lt;"; // lower than
		TABLE_HTML['>'] = "&gt;"; // greater than

		// strict table
		System.arraycopy(TABLE_HTML, 0, TABLE_HTML_STRICT, 0, 256);
		TABLE_HTML_STRICT[' '] = "&nbsp;";
		TABLE_HTML_STRICT['\n'] = "<br>"; // ascii 10
		TABLE_HTML_STRICT['\r'] = "<br>"; // ascii 13
	}

	// ---------------------------------------------------------------- encoding

	/**
	 * Encode string to HTML-safe text. Extra characters are encoded as decimals,
	 * and five special characters are replaced with their HTML values:
	 * <li>' with &amp;#039;</li>
	 * <li>" with &amp;quot;</li>
	 * <li>&amp; with &amp;amp;</li>
	 * <li>&lt; with &amp;lt;</li>
	 * <li>&gt; with &amp;gt;</li>
	 *
	 * @param string input string
	 * @return HTML-safe string
	 */
	public static String encode(final String string)
	{
		if ((string == null) || (string.length() == 0))
		{
			return "";
		}
		final int n = string.length();
		final StringBuffer buffer = new StringBuffer((int) (n * newSizeFactor));
		final int tableLen = TABLE_HTML.length;
		char c;
		for (int i = 0; i < n; i++)
		{
			c = string.charAt(i);
			if (c < tableLen)
			{
				buffer.append(TABLE_HTML[c]);
			}
			else
			{
				buffer.append("&#").append((int) c).append(';');
			}
		}
		return buffer.toString();
	}

}
