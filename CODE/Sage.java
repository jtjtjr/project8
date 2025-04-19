import java.util.HashMap;
import java.util.Map;

/**
 * Represents the sage who appears on each planet to offer philosophical, cryptic,
 * and sometimes insightful musings. Their dialogue changes based on the number
 * of encounters and the planet they appear on.
 */
public class Sage {
    private static int encounterCount = 0;

    public static String speak(Planet planet, Player player) {
        encounterCount++;
        String planetName = planet.getName();
        StringBuilder speech = new StringBuilder();

        speech.append("\n\n[The sage appears from the shadows, his voice gravelly yet calm...]").append("\n\n");

        switch (encounterCount) {
            case 1:
                speech.append("Ah... the beginning of a long road. Tell me, ")
                      .append(player.getShipName())
                      .append("'s captain, do you seek something out here? Or do you merely flee something behind?");
                break;
            case 2:
                speech.append("Back so soon. The stars whisper tales, but you... you wear yours like armor.");
                break;
            case 3:
                speech.append("Three steps into the void, and already you’ve changed. Look at your eyes—wider, but wearier.");
                break;
            default:
                speech.append("Heh... each time I find you, the void clings tighter. But you still walk forward. Curious.");
                break;
        }

        speech.append("\n\n");

        speech.append(getPlanetWisdom(planetName));

        speech.append("\n\nThe sage smiles faintly, then vanishes into the stillness of the planet...");
        return speech.toString();
    }

    private static String getPlanetWisdom(String planetName) {
        Map<String, String> loreAddendum = new HashMap<>();

        loreAddendum.put("Fiador X", "This place... Company-perfect. Sterile. Unscarred. But beneath the polish, I sense a desperation— a need to *appear* in control. Even the stars seem nervous.");
        loreAddendum.put("Bucephalus", "Bucephalus? A pirate's promise wrapped in fried oil. Yet, I once tasted their Bolt Burger. Not bad. But it's the silence between the thunder that'll get you. Storms here remember names.");
        loreAddendum.put("Norman's Rock", "So many swung pickaxes hoping for hope. But the Company doesn't trade in hope. They lease it. And repossess quickly.");
        loreAddendum.put("Atlas Station", "Ah, Atlas. They seek answers in graviton whispers, yet forget that even gravity can lie. Ask the miners below.");
        loreAddendum.put("Ezekiel's Salvation", "I once met a fisherman here who swore Balenus whispered back. I believed him. You see, belief is the only thing stronger than the tide—and twice as dangerous.");
        loreAddendum.put("Mu 6", "Mu 6 is quiet. Too quiet. That's what makes the Skimmers dangerous. They know the value of silence—and how to profit from it.");
        loreAddendum.put("Orion's Bane", "This world hums. I do not know what it says—but I have dreamt of vines wrapping stars. Be cautious here. The planet watches back.");
        loreAddendum.put("Technon 9", "Technon. Steel and sweat forged into rebellion. I bled here once, under a banner that no longer waves.");
        loreAddendum.put("Astros Militarum", "Some say loyalty is earned. Here, it was taken. And yet... the walls still echo with songs of defiance.");
        loreAddendum.put("Unknown J76G432", "This planet isn't lost—it *wants* to be. Crystals refract more than light. They scatter thought, too.");
        loreAddendum.put("Cerberus XVII", "The end. Or is it? Cerberus hides rot beneath protocol. DNA vaults? Perhaps. But what about the souls?");

        return loreAddendum.getOrDefault(planetName, "This place holds echoes I've yet to name. Perhaps you'll leave a mark worthy of one.");
    }

    public static void resetEncounters() {
        encounterCount = 0;
    }
}
