import java.util.Random;

public class CrewMates {

    public static String[] crewGenerator(int crewmatesnumber) {
        String[] crewmates = new String[crewmatesnumber];
        Random rng = new Random();

        for (int i = 0; i < crewmatesnumber; i++) {
            int randomInt = rng.nextInt(100) + 1;
            String crewmate;

            // Assign based on exact values or small ranges
            if (randomInt == 11) {
                crewmate = "Bob, kind man";
            } else if (randomInt == 12) {
                crewmate = "Jax *Wrench* Mallor - Grease-stained mechanic, speaks fluent engine rattle and sarcasm.";
            } else if (randomInt == 13) {
                crewmate = "Lira Keene - Sharp-tongued navigator with a perfect record and zero patience."; 
            } else if (randomInt == 14) {
                crewmate = "Thom Baddick - Grumpy cargo master, knows every crate like family."; 
            } else if (randomInt == 15) {
                crewmate = "Echo-7 - Reprogrammed service android, oddly philosophical and disturbingly efficient."; 
            } else if (randomInt == 16) {
                crewmate = "Maz *Scraps* Denar - Salvage specialist with sticky fingers and wild stories."; 
            } else if (randomInt == 17) {
                crewmate = "Nova Tran - Communications officer, tech-savvy with a knack for hacking signals."; 
            } else if (randomInt == 18) {
                crewmate = "Chief Doc Lem - Unlicensed medic, drinks moonshine and patches bullet wounds."; 
            } else if (randomInt == 19) {
                crewmate = "Dani Vox - Weapons tech, quiet but deadly with turret calibrations."; 
            } else if (randomInt == 20) {
                crewmate = "Kordo Blint - Cook and morale officer, serves questionable food with heart."; 
            } else if (randomInt == 21) {
                crewmate = "Tess Nyari - Supply runner, fearless on foot and fast with deals.";
            } else if (randomInt == 22) {
                crewmate = "Riggs Talon - Ex-mercenary turned security lead, always watching the airlocks."; 
            } else if (randomInt == 23) {
                crewmate = "Solin Grey - Astrophysicist turned crew oddball, claims stars speak to him."; 
            } else if (randomInt == 24) {
                crewmate = "Brin *Lucky* Serris - Gambler and part-time pilot, always broke but smiling."; 
            } else if (randomInt == 25) {
                crewmate = "Vex Lomar - Drone wrangler, commands a swarm of flying helpers."; 
            } else if (randomInt == 26) {
                crewmate = "Odo Clask - Janitor, unofficial ship historian, and secret poet."; 
            } else if (randomInt == 27) {
                crewmate = "Kayla Verge - Young rookie with big dreams and no clue."; 
            } else if (randomInt == 28) {
                crewmate = "Fixit - Experimental AI, built from scrap and prone to existential dread."; 
            } else if (randomInt == 29) {
                crewmate = "Glen *Taps* Morell - Engineer with bionic limbs and a love of jazz."; 
            } else if (randomInt == 30) {
                crewmate = "Zurra - Alien refugee, strong, silent, and loyal to the crew.";
            } else if (randomInt == 31) {
                crewmate = "Dan Dumitresque - Easilly distracted but nesicary in a pinch"; 
            } else if (randomInt == 32) {
                crewmate = "Sanjay *Cap* Thasma - Hardend veteran from a long forgotten corporate war"; 
            } else if (randomInt == 33) {
                crewmate = "Arnie Johnson - Old fixer from Theodora XVI, can probably get you anything"; 
            } else if (randomInt == 34) {
                crewmate = "Steven Lam - Calm, mysterious guy, plays his cards close to his chest"; 
            } else if (randomInt == 35) {
                crewmate = "James Strasburg - He came with his own datapad, probably ask him for the ship specs"; 
            } else if (randomInt == 36) {
                crewmate = "COMPANY REDACTED - COMPANY REDACTED"; 
            } else if (randomInt == 37) {
                crewmate = "Johnny Silverhand - Makes threats to the company, who knows if he will blow up your shift"; 
            } else if (randomInt == 38) {
                crewmate = "Nix - He has a high speed data cable port in his neck, refuses to talk about it"; 
            } else if (randomInt == 39) {
                crewmate = "Elsa Pavleko - One of the best navigators, if we were making a shipment outside the milky way...";
            } else if (randomInt == 40) {
                crewmate = "Methalatedmethylenedioxymethamphetamine - A sentient cloud of chemicals, you feel really good when around him"; 
            } else if (randomInt == 41) {
                crewmate = "Allen - *NO INFORMATION AVAILABLE*"; 
            } else if (randomInt == 42) {
                crewmate = "Ashlyn Dibble - Space cryptologist, not criptologist, she explores cripts"; 
            } else if (randomInt == 43) {
                crewmate = "Navea Blair - Chemist from andromida, quarters smell like space-meth often"; 
            } else if (randomInt == 44) {
                crewmate = ""; 
            } else if (randomInt == 45) {
                crewmate = ""; 
            } else if (randomInt == 46) {
                crewmate = ""; 
            } else if (randomInt == 47) {
                crewmate = ""; 
            } else if (randomInt == 48) {
                crewmate = "";
            } else if (randomInt == 49) {
                crewmate = ""; 
            } else if (randomInt == 50) {
                crewmate = ""; 
            } else if (randomInt == 51) {
                crewmate = ""; 
            } else if (randomInt == 52) {
                crewmate = ""; 
            } else if (randomInt == 53) {
                crewmate = ""; 
            } else if (randomInt == 54) {
                crewmate = ""; 
            } else if (randomInt == 55) {
                crewmate = ""; 
            } else if (randomInt == 56) {
                crewmate = ""; 
            } else if (randomInt == 57) {
                crewmate = "";
            } else if (randomInt == 58) {
                crewmate = ""; 
            } else if (randomInt == 59) {
                crewmate = ""; 
            } else if (randomInt == 60) {
                crewmate = ""; 
            } else if (randomInt == 61) {
                crewmate = ""; 
            } else if (randomInt == 62) {
                crewmate = ""; 
            } else if (randomInt == 63) {
                crewmate = ""; 
            } else if (randomInt == 64) {
                crewmate = ""; 
            } else if (randomInt == 65) {
                crewmate = ""; 
            } else if (randomInt == 66) {
                crewmate = "";
            } else if (randomInt == 67) {
                crewmate = ""; 
            } else if (randomInt == 68) {
                crewmate = ""; 
            } else if (randomInt == 69) {
                crewmate = ""; 
            } else if (randomInt == 70) {
                crewmate = ""; 
            } else if (randomInt == 71) {
                crewmate = ""; 
            } else if (randomInt == 72) {
                crewmate = ""; 
            } else if (randomInt == 73) {
                crewmate = ""; 
            } else if (randomInt == 74) {
                crewmate = ""; 
            } else if (randomInt == 76) {
                crewmate = "";
            } else if (randomInt == 77) {
                crewmate = ""; 
            } else if (randomInt == 78) {
                crewmate = ""; 
            } else if (randomInt == 79) {
                crewmate = ""; 
            } else if (randomInt == 80) {
                crewmate = ""; 
            } else if (randomInt == 81) {
                crewmate = ""; 
            } else if (randomInt == 82) {
                crewmate = ""; 
            } else if (randomInt == 83) {
                crewmate = ""; 
            } else if (randomInt == 84) {
                crewmate = ""; 
            } else if (randomInt == 85) {
                crewmate = "";
            } else if (randomInt == 86) {
                crewmate = ""; 
            } else if (randomInt == 87) {
                crewmate = ""; 
            } else if (randomInt == 88) {
                crewmate = ""; 
            } else if (randomInt == 89) {
                crewmate = ""; 
            } else if (randomInt == 90) {
                crewmate = ""; 
            } else if (randomInt == 91) {
                crewmate = ""; 
            } else if (randomInt == 92) {
                crewmate = ""; 
            } else if (randomInt == 93) {
                crewmate = ""; 
            } else if (randomInt == 94) {
                crewmate = ""; 
            } else if (randomInt == 95) {
                crewmate = ""; 
            } else if (randomInt == 96) {
                crewmate = ""; 
            } else if (randomInt == 97) {
                crewmate = ""; 
            } else if (randomInt == 98) {
                crewmate = ""; 
            } else if (randomInt == 99) {
                crewmate = ""; 
            } else {
                crewmate = "Rex - mysterious stowaway";
            }

            crewmates[i] = crewmate;
        }

        return crewmates;
    }

    public static void main(String[] args) {
        String[] myCrew = crewGenerator(5);

        // Print out the crew
        for (int i = 0; i < myCrew.length; i++) {
            System.out.println("Crewmate " + (i + 1) + ": " + myCrew[i]);
        }
    }
}
