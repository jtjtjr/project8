import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CrewMates {

    private static final Map<Integer, String> CREWMATE_MAP = new HashMap<>();

    static {
        CREWMATE_MAP.put(11, "Bob, kind man");
        CREWMATE_MAP.put(12, "Jax *Wrench* Mallor - Grease-stained mechanic, speaks fluent engine rattle and sarcasm.");
        CREWMATE_MAP.put(13, "Lira Keene - Sharp-tongued navigator with a perfect record and zero patience.");
        CREWMATE_MAP.put(14, "Thom Baddick - Grumpy cargo master, knows every crate like family.");
        CREWMATE_MAP.put(15, "Echo-7 - Reprogrammed service android, oddly philosophical and disturbingly efficient.");
        CREWMATE_MAP.put(16, "Maz *Scraps* Denar - Salvage specialist with sticky fingers and wild stories.");
        CREWMATE_MAP.put(17, "Nova Tran - Communications officer, tech-savvy with a knack for hacking signals.");
        CREWMATE_MAP.put(18, "Chief Doc Lem - Unlicensed medic, drinks moonshine and patches bullet wounds.");
        CREWMATE_MAP.put(19, "Dani Vox - Weapons tech, quiet but deadly with turret calibrations.");
        CREWMATE_MAP.put(20, "Kordo Blint - Cook and morale officer, serves questionable food with heart.");
        CREWMATE_MAP.put(21, "Tess Nyari - Supply runner, fearless on foot and fast with deals.");
        CREWMATE_MAP.put(22, "Riggs Talon - Ex-mercenary turned security lead, always watching the airlocks.");
        CREWMATE_MAP.put(23, "Solin Grey - Astrophysicist turned crew oddball, claims stars speak to him.");
        CREWMATE_MAP.put(24, "Brin *Lucky* Serris - Gambler and part-time pilot, always broke but smiling.");
        CREWMATE_MAP.put(25, "Vex Lomar - Drone wrangler, commands a swarm of flying helpers.");
        CREWMATE_MAP.put(26, "Odo Clask - Janitor, unofficial ship historian, and secret poet.");
        CREWMATE_MAP.put(27, "Kayla Verge - Young rookie with big dreams and no clue.");
        CREWMATE_MAP.put(28, "Fixit - Experimental AI, built from scrap and prone to existential dread.");
        CREWMATE_MAP.put(29, "Glen *Taps* Morell - Engineer with bionic limbs and a love of jazz.");
        CREWMATE_MAP.put(30, "Zurra - Alien refugee, strong, silent, and loyal to the crew.");
        CREWMATE_MAP.put(31, "Dan Dumitresque - Easilly distracted but nesicary in a pinch");
        CREWMATE_MAP.put(32, "Sanjay *Cap* Thasma - Hardend veteran from a long forgotten corporate war");
        CREWMATE_MAP.put(33, "Arnie Johnson - Old fixer from Theodora XVI, can probably get you anything");
        CREWMATE_MAP.put(34, "Steven Lam - Calm, mysterious guy, plays his cards close to his chest");
        CREWMATE_MAP.put(35, "James Strasburg - He came with his own datapad, probably ask him for the ship specs");
        CREWMATE_MAP.put(36, "COMPANY REDACTED - COMPANY REDACTED");
        CREWMATE_MAP.put(37, "Johnny Silverhand - Makes threats to the company, who knows if he will blow up your shift");
        CREWMATE_MAP.put(38, "Nix - He has a high speed data cable port in his neck, refuses to talk about it");
        CREWMATE_MAP.put(39, "Elsa Pavleko - One of the best navigators, if we were making a shipment outside the milky way...");
        CREWMATE_MAP.put(40, "Methalatedmethylenedioxymethamphetamine - A sentient cloud of chemicals, you feel really good when around him");
        CREWMATE_MAP.put(41, "Allen - *NO INFORMATION AVAILABLE*");
        CREWMATE_MAP.put(42, "Ashlyn Dibble - Space cryptologist, not criptologist, she explores cripts");
        CREWMATE_MAP.put(43, "Navea Blair - Chemist from andromida, quarters smell like space-meth often");
        CREWMATE_MAP.put(44, "Rahul Xandar - Ex-Space marine, Salamandars Division");
        CREWMATE_MAP.put(45, "Cillia Brendar XVI - 16th clone of the famous singer Cillia Brendar (2914 Most Attractive Person)");
        CREWMATE_MAP.put(46, "Grex - Genetically modified crocidile/human hybrid, keep an eye on him");
        CREWMATE_MAP.put(47, "Jared Urainium - Ex beast tamer, says the Company TM wiped out the beasts on his home planet");
        CREWMATE_MAP.put(48, "Sid Django - Has so many implants we are not sure if he counts as robot or human");
        CREWMATE_MAP.put(49, "Brix Salad - RUFUSED TO FILL OUT ANY COMPANY FORMS");
        CREWMATE_MAP.put(50, "Mr Blue Eyes - Company Supervisor");
        CREWMATE_MAP.put(51, "COMPANY DRONE 02186 - Electronically forced to work for the company (not a slave, an unpaid intern)");
        CREWMATE_MAP.put(52, "COMPANY DRONE 84513 - Electronically forced to work for the company (not a slave, an unpaid intern)");
        CREWMATE_MAP.put(53, "COMPANY DRONE 84357 - Electronically forced to work for the company (not a slave, an unpaid intern)");
        CREWMATE_MAP.put(54, "COMPANY DRONE 95324 - Electronically forced to work for the company (not a slave, an unpaid intern)");
        CREWMATE_MAP.put(55, "COMPANY DRONE 12525 - Electronically forced to work for the company (not a slave, an unpaid intern)");
        CREWMATE_MAP.put(56, "COMPANY DRONE 75265 - Electronically forced to work for the company (not a slave, an unpaid intern)");
        CREWMATE_MAP.put(57, "COMPANY DRONE 75189 - Electronically forced to work for the company (not a slave, an unpaid intern)");
        CREWMATE_MAP.put(58, "COMPANY DRONE 78878 - Electronically forced to work for the company (not an unpaid intern, a slave)");
        CREWMATE_MAP.put(59, "Rouge Amendares - Fixer from Night City (Gordian 15)");
        CREWMATE_MAP.put(60, "Takemura Tanashi - Arasaka transfer (DO NOT TRUST)");
        CREWMATE_MAP.put(1, "Rex Jonier - Amnesiac, company press ganged him into service");
        CREWMATE_MAP.put(2, "Zia Raine - Morale officer with a background in stand-up comedy");
        CREWMATE_MAP.put(3, "Mira Talos - Xenolinguist who’s too friendly with alien fungi");
        CREWMATE_MAP.put(4, "Clint Hawser - Wears sunglasses indoors, says it's ‘for tactical reasons’");
        CREWMATE_MAP.put(5, "Lazlo Kint - Just... never ask what’s in his duffle bag");
        CREWMATE_MAP.put(6, "Yuna Crest - Cryo-specialist with a tendency to monologue");
        CREWMATE_MAP.put(7, "Fenrix - Keeps calling himself ‘the ship’s doom whisperer’");
        CREWMATE_MAP.put(8, "Nelli Spark - Likes plasma torches a bit *too* much");
        CREWMATE_MAP.put(9, "Echo Nyx - A bard, apparently. Why? Unknown.");
        CREWMATE_MAP.put(10, "Dreg the Splicer - Got fired from cybernetics lab but kept the tools");
    }

    public static String[] crewGenerator(int crewmatesnumber) {
        if (crewmatesnumber > 60) {
            throw new IllegalArgumentException("Can't generate more than 60 unique crewmates.");
        }

        List<Integer> keys = new ArrayList<>(CREWMATE_MAP.keySet());
        Collections.shuffle(keys);

        Set<String> seen = new HashSet<>();
        String[] crewmates = new String[crewmatesnumber];

        int i = 0;
        for (int key : keys) {
            String crewmate = CREWMATE_MAP.get(key);
            if (seen.contains(crewmate)) {
                continue; // skip duplicates
            }

            crewmates[i++] = crewmate;
            seen.add(crewmate);

            if (i >= crewmatesnumber) {
                break;
            }
        }

        if (i < crewmatesnumber) {
            throw new RuntimeException("Not enough unique crewmates available. Check for duplicate descriptions in CREWMATE_MAP.");
        }

        return crewmates;
    }

    public static void crewPrinter(String[] myCrew) {
        // Print out the crew
        for (int i = 0; i < myCrew.length; i++) {
            if (i == 0) {
                Frontend.displayTextSlowly("\n\nCrewmate " + (i + 1) + ": " + myCrew[i] + "\n\n");
            }
            Frontend.displayTextSlowly("Crewmate " + (i + 1) + ": " + myCrew[i] + "\n\n");
        }
    }

    public static String[] crewEqualizer(String[] myCrew, int crewNumber) {
        if (myCrew.length <= crewNumber) {
            return myCrew;
        }

        String[] trimmedCrew = new String[crewNumber];
        System.arraycopy(myCrew, 0, trimmedCrew, 0, crewNumber);
    
        return trimmedCrew;
    }
}
