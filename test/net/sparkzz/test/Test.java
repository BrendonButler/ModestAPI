package net.sparkzz.test;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.io.config.Config;
import net.sparkzz.modest.io.config.JSONConfig;
import net.sparkzz.modest.io.config.YAMLConfig;
import net.sparkzz.modest.io.console.Alignment;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Languages;
import net.sparkzz.modest.utils.Logger;
import net.sparkzz.modest.utils.Math;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Brendon Butler on 3/28/2016.
 */
public class Test extends ModestGame {

	private static Logger logger;

	private Test() {
		run();
	}

	public static void main(String[] args) {
		new Test();
	}

	@Override
	public void init() {
		setTicks(1);
	}

	@Override
	public void postInit() {
		List<String> regex = new ArrayList<>();
		List<String> afrikaans = new ArrayList<>();
		List<String> spanish = new ArrayList<>();

		regex.add("Title");
		afrikaans.add("Titel");
		spanish.add("Título");
		regex.add("Copyright 2016 Jimmy Jones.");
		afrikaans.add("Kopiereg 2016 Jimmy Jones.");
		spanish.add("Derechos de autor 2016 Jimmy Jones.");

		Languages.addLanguage("af_za", regex, afrikaans);
		Languages.addLanguage("es_mx", regex, spanish);
		Languages.setLanguage("es_mx");

		Console.out("Title");
		Console.out("Copyright 2016 Jimmy Jones.");

		Languages.setLanguage("");

		Console.out("Title, Copyright 2016 Jimmy Jones.");

		Config config = new JSONConfig();

		config.set("testing", 23);
		config.set("words", "these are words!");

		config.save();

		interrupt();
	}
}