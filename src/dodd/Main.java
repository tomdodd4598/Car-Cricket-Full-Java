package dodd;

import java.io.*;

import dodd.cricket.simulation.Simulation;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;

public class Main {
	
	public static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
	
	static {
		Lang.locale_name = "en_gb";
		Object2ObjectMap<String, String> locale = Lang.getLocale();
		
		locale.put("match.toss.bat", "%1$s won the toss and chose to bat");
		locale.put("match.toss.bowl", "%1$s won the toss and chose to bowl");
		
		locale.put("match.status.lead", "%1$s lead by %2$d runs");
		locale.put("match.status.trail", "%1$s trail by %2$d runs");
		locale.put("match.status.level", "Scores are level");
		
		locale.put("match.status.require", "%1$s require %2$d runs to win");
		
		locale.put("match.result.runs", "%1$s won by %2$d runs");
		locale.put("match.result.wickets", "%1$s won by %2$d wickets");
		locale.put("match.result.innings", "%1$s won by an innings and %2$d runs");
		locale.put("match.result.tie", "Match tied");
		
		locale.put("innings.score", "%1$d/%2$d");
		
		locale.put("batter.notOut", "not out");
		locale.put("batter.wicket", "b %1$s");
	}
	
	public static void main(String[] args) {
		new Simulation().simulate();
	}
}
