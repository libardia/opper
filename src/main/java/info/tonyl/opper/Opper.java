package info.tonyl.opper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Opper {
	private static List<OptionDefinition> definitions = new ArrayList<>();
	private static Map<String, Option> options = new HashMap<>();
	private static List<String> namelessValues = new ArrayList<>();

	/**
	 * Registers option definitions by <code>OptionDefinition</code> objects.
	 * 
	 * @param def
	 *            The definition objects to register, as a vararg.
	 */
	public static void register(OptionDefinition... def) {
		for (OptionDefinition d : def) {
			definitions.add(d);
		}
	}

	/**
	 * Registers one option with all of the given names, set to not take a value.
	 * The first name given is considered the primary name and is what this option
	 * should be queried by.
	 * 
	 * @param names
	 *            The names for this option, as a vararg.
	 */
	public static void register(String... names) {
		register(new OptionDefinition(names, false));
	}

	/**
	 * Registers one option with all of the given names, set to take a value. The
	 * first name given is considered the primary name and is what this option
	 * should be queried by.
	 * 
	 * @param names
	 *            The names for this option, as a vararg.
	 */
	public static void registerWithValue(String... names) {
		register(new OptionDefinition(names, true));
	}

	/**
	 * Registers multiple options with one name each, set to not take values.
	 * 
	 * @param names
	 *            The names for for each option, as a vararg.
	 */
	public static void registerMultiple(String... names) {
		for (String name : names) {
			register(name);
		}
	}

	/**
	 * Registers multiple options with one name each, set to take values.
	 * 
	 * @param names
	 *            The names for for each option, as a vararg.
	 */
	public static void registerMultipleWithValue(String... names) {
		for (String name : names) {
			registerWithValue(name);
		}
	}

	public static void parse(String args[]) {
		// Last option is used to assign a value
		Option last = null;

		for (String arg : args) {
			// The list of things that could potentially be registered options
			List<String> potential = new ArrayList<>();

			if (arg.startsWith("--")) {
				potential.add(arg.substring(2));
			} else if (arg.startsWith("-")) {
				for (int i = 1; i < arg.length(); i++) {
					potential.add(arg.substring(i, i + 1));
				}
			} else {
				if (last != null && last.hasValue && last.value == null) {
					last.value = arg;
				} else {
					namelessValues.add(arg);
				}
			}

			outermost: for (String p : potential) {
				for (OptionDefinition def : definitions) {
					for (String name : def.getNames()) {
						if (p.equals(name)) {
							last = def.make();
							options.put(def.getNames()[0], last);
							break outermost;
						}
					}
				}
			}
		}
	}

	public static boolean isSet(String name) {
		return options.containsKey(name);
	}

	public static boolean hasValue(String name) {
		if (isSet(name)) {
			return options.get(name).hasValue;
		}

		return false;
	}

	public static String getValueOf(String name) {
		Option o = options.get(name);
		if (o == null || o.value == null) {
			return "";
		} else {
			return o.value;
		}
	}

	public static String[] getNamelessValues() {
		return namelessValues.toArray(new String[0]);
	}

	public static String[] getAllSetOptions() {
		return options.keySet().toArray(new String[0]);
	}
}
