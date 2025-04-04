
public class ShipDisplayer {
    
    public void shipDisplayerBuilder(){
        int C = Player.getCrewNum; // FIX THIS TO RETRIEVE FROM PLAYER CREW
        int R = Player.getResources; // FIX THIS TO RETRIEVE FROM PLAYER RESOURCE
        
        int crewNumber = C;
        int cargoNumber = Math.floorDiv(R, 31);
        if (cargoNumber > 32) {
            cargoNumber = 32;
        }

        int i = 0;
        String cargoBay1 = "";
        String cargoBay2 = "";
        String cargoBay3 = "";
        String cargoBay4 = "";

        if (cargoNumber >= 0) {
            while (i < cargoNumber && i < 8) {
                cargoBay1 += "{} ";
                i++;
            } 
        } 

        cargoNumber -= 8;
        i = 0;

        if (cargoNumber >= 8) {
            while (i < cargoNumber && i < 8) {
                cargoBay2 += "{} ";
                i++;
            }
        } 

        cargoNumber -= 8;
        i = 0;

        if (cargoNumber >= 8) {
            while (i < cargoNumber && i < 8) {
                cargoBay3 += "{} ";
                i++;
            }
        } 
        
        cargoNumber -= 8;
        i = 0;

        if (cargoNumber >= 8) {
            while (i < cargoNumber && i < 8) {
                cargoBay4 += "{} ";
                i++;
            }
        } 

        while (cargoBay1.length() < 24) {
            cargoBay1 += "[] ";
        }

        while (cargoBay2.length() < 24) {
            cargoBay2 += "[] ";
        }

        while (cargoBay3.length() < 24) {
            cargoBay3 += "[] ";
        }

        while (cargoBay4.length() < 24) {
            cargoBay4 += "[] ";
        }

        String crewLine1 = "";
        String crewLine2 = "";
        String crewLine3 = "";
        String crewLine4 = "";
        i = 0;

        if (crewNumber >= 0) {
            while (i < crewNumber && i < 3) {
                crewLine1 += ">:| ";
                i++;
            } 
        } 

        crewNumber -= 3;
        i = 0;

        System.out.println("\n\n\n" + crewNumber + "\n\n\n\n");

        if (crewNumber >= 3) {
            while (i < crewNumber && i < 3) {
                crewLine2 += ">:| ";
                i++;
            } 
        } 

        crewNumber -= 3;
        i = 0;

        if (crewNumber >= 3) {
            while (i < crewNumber && i < 3) {
                crewLine3 += ">:| ";
                i++;
            } 
        }
        
        crewNumber -= 3;
        i = 0;

        if (crewNumber >= 3) {
            while (i < crewNumber && i < 3) {
                crewLine4 += ">:| ";
                i++;
            } 
        }

        while (crewLine1.length() < 13) {
            crewLine1 += " ";
        }

        while (crewLine2.length() < 13) {
            crewLine2 += " ";
        }

        while (crewLine3.length() < 13) {
            crewLine3 += " ";
        }

        while (crewLine4.length() < 13) {
            crewLine4 += " ";
        }

        String cargoLine1 = "                                     :@-" + cargoBay1 + "|" + crewLine1 + "*@@#++++++++++++=.    ";
        String cargoLine2 = "         :*%@@@@@@*-*#%@@%=.     :@@@@@-" + cargoBay2 + "|" + crewLine2 + "*@@%.                  ";
        String cargoLine3 = "                    ....:...:-=+-:@@@@@-" + cargoBay3 + "|" + crewLine3 + "*@@%.                  ";
        String cargoLine4 = "                                     :@-" + cargoBay4 + "|" + crewLine4 + "*@@%.                  ";

        System.out.println("Legend: CARGO = ██ SUPPLY = [] Crew = >:|  ");
        System.out.println("                                                            ..  ..                                  ");
        System.out.println("                                                             -.  :.                                 ");
        System.out.println("                                                      .... .. -.  .:                                 ");
        System.out.println("                                                  .--..:+   .  :   .:                                ");
        System.out.println("                                                 :-+*%%.        =.   :.                              ");
        System.out.println("                                                 ...*@*..       .-.   :.                             ");
        System.out.println("                                     .++++++++++++*@@@@@*=::-+++++*++++++++++++++=.                  ");
        System.out.println("             ....               .%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#++-.              ");
        System.out.println("           .:-=++*#%%@@%#+--===:.%@@@@@@%%%%%%%%%%%%%%%%%%%%%%%%M%%%%%%%%%%%%%@@@#+++++=..          ");
        System.out.println("                                .====+@-=======CARGO BAY========[] CREW AREA  *@@#+++++++++-.       ");
        System.out.println(cargoLine1);
        System.out.println(cargoLine2);
        System.out.println(cargoLine3);
        System.out.println(cargoLine4);
        System.out.println("                                     :@-......................................*@@%.                  ");
        System.out.println("                                                                                                    ");
    }
}
