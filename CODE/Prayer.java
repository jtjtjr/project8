import java.util.Scanner;
import java.util.Random;

/**
 * Prayer class that let the player pray once a games for a chance to get massive benefits
 */
public class Prayer {
    private static final Random rand = new Random(100);

    public static void pray(Player player, Scanner scanner) {
        if (player.hasPrayed() == false) {
            boolean confirm = dialog(player, scanner);
            
            if (confirm) {
                result(player);
                player.hasPrayTrue();
            } else {
                System.out.println("Returning...");
            }

        } else {
            Frontend.displayTextSlowly("Prayer Failed! Seems like you have already prayed.\n");
        }
    }

    private static void result(Player player) {
        int chance = rand.nextInt();
        if (chance < 5) {
            Frontend.displayTextSlowly("The god GRAYSON has decended. GRAYSON has responded you your prayer.\n");
            Frontend.displayTextSlowly("You recieve 3 crew mate!\n");
            player.addCrewNum(3);
        } else if (chance < 10 && chance >= 5) {
            Frontend.displayTextSlowly("The god MR. SWANSON has decended. MR SWANOSN has responded you your prayer.\n");
            Frontend.displayTextSlowly("MR. SWANSON is feeling really generous. You have receive a lot of loot.\n");
            Frontend.displayTextSlowly("MORALE +15.\n");
            Frontend.displayTextSlowly("RESOURCES +300.\n");
            Frontend.displayTextSlowly("MONEY +200.\n");
            Frontend.displayTextSlowly("CREW MATE +1.\n");
            player.addMorale(15);
            player.addCrewNum(1);
            player.addResources(300);
            player.setMoney(player.getMoney()+200);
        } else if (chance < 20 && chance >= 10) {
            Frontend.displayTextSlowly("The god DAN has responded to your plead. He took a pity on you.\n");
            Frontend.displayTextSlowly("You recieved 200 resources.\n");
            player.addResources(200);
        } else if (chance < 30 && chance >= 20) {
            Frontend.displayTextSlowly("The god WILL has responded to your plead. He will lend you a helping hand.\n");
            Frontend.displayTextSlowly("You recieved 1 crew member.\n");
            player.addCrewNum(1);
        } else if (chance < 40 && chance >= 30) {
            Frontend.displayTextSlowly("The god ARNIE has acknowledge your prayer. He as decided to give you a boost.\n");
            Frontend.displayTextSlowly("You recieved 20 morales.\n");
            player.addMorale(20);
        } else if (chance < 50 && chance >= 40) {
            Frontend.displayTextSlowly("The god STEVEN has acknowledge your prayer. However he does not have much to give. You have recieved:\n");
            Frontend.displayTextSlowly("MORALE +1.\n");
            Frontend.displayTextSlowly("RESOURCES +1.\n");
            Frontend.displayTextSlowly("MONEY +1.\n");
            player.addMorale(1);
            player.addResources(1);
            player.setMoney(player.getMoney()+1);
        } else if (chance < 60 && chance >= 50) {
            Frontend.displayTextSlowly("The god JAMES has recieved your prayer. He gave you come cash.\n");
            Frontend.displayTextSlowly("You have recieved 100$.\n");
            player.setMoney(player.getMoney()+100);
        } else if (chance < 75 && chance >= 60) {
            Frontend.displayTextSlowly("The god SANJAY has recieved your prayer...\n");
            Frontend.displayTextSlowly("...\n");
            Frontend.displayTextSlowly("You have been thoroughly ignored.\n");
        } else if (chance < 80 && chance >= 75) {
            Frontend.displayTextSlowly("Your prayer has summoned the devil.\n");
            Frontend.displayTextSlowly("Your have lost a lot of stuff.\n");
            Frontend.displayTextSlowly("MORALE -10.\n");
            Frontend.displayTextSlowly("RESOURCES -100.\n");
            Frontend.displayTextSlowly("CREW MEMBER -1.\n");
            player.removeCrewNum(1);
            player.removeMorale(10);
            player.removeResources(100);
        } else {
            Frontend.displayTextSlowly("Prayer Failed! Luck was not on your side.\n");
        }
    }

    private static boolean dialog(Player player, Scanner scanner) {
        for (int i = 0; i < 20; i++) {
            System.out.println("");
        }
        Frontend.displayTextSlowly("You are trying to summon a god. You must only use this if it is absolutely necessary.\n");
        Frontend.displayTextSlowly("The gods are all mightly and powerful but their power must not be abused.\n");
        Frontend.displayTextSlowly("Are you sure you want to continue? Progress at your own risk. Hit [n] to turn back.\n");
        String response = scanner.nextLine();
        if (response.equals("N") || response.equals("n")) {
            return true;
        } else {
            return false;
        }
    }

}