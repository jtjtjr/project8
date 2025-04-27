

public class EndGame {
    public static int scoreCalculatron(int money, int crew, int cargo, int resources, int morale, boolean finalplanet) {
        if (!finalplanet) {
            System.out.println("YOU HAVE NOT ARRIVED AT THE DESIGNATED DESTINATION\n");
            Frontend.displayTextSlowly("!!!!!!!!!!!          !!!!!!!!!!!          !!!!!!!!!!!          !!!!!!!!!!!          !!!!!!!!!!!          \n!!!!!!!!!!!          !!!!!!!!!!!          !!!!!!!!!!!          !!!!!!!!!!!          !!!!!!!!!!!          \n");
            Frontend.displayTextSlowly("\n\nARE YOU GOING TO LET THE COMPANY DOWN?\n\n\n\n\n\n\n\n\nWE WILL HUNT YOU DOWN AND INSTALL THE COMPLIANCE CHIP (TM)\n\n\n");
            return -2;
        } else {
            Frontend.displayTextSlowly("THE COMPANY THANKS YOU FOR COMPLETING YOUR CARGO SHIPMENT INTACT\n\n\n");
            Frontend.displayTextSlowly("./RUN scorecalcutron80.1.1.exe...\n");
            Frontend.wait(4000);
            Frontend.displayTextSlowly("./System.crew.valueExtractor...\n...\n...\n");
            float crewValue = crew * crew * morale / 10;
            if (morale > 30) {
                String crewString = "./workers/" + crew + "/good_condition/" + crewValue + "/added_to_employee_score\n...\n...\n...\n...\n";
                Frontend.displayTextSlowly(crewString);
            } else {
                String crewString = "./workers/" + crew + "/prime_subjects_for_interns" + crewValue + "/added_to_employee_score\n...\n...\n...\n...\n";
                Frontend.displayTextSlowly(crewString);
            }
            Frontend.displayTextSlowly("./shareholder_value_returned/" + money + "/\n...\n...\n...\n...\n");
            if (resources > 20) {
                Frontend.displayTextSlowly("./salvagable_resources/" + (resources / 40 + 10) + "/\n...\n...\n...\n");
            } else {
                Frontend.displayTextSlowly("./salvagable_resources/the_company_approves_of_fugality/\n");
            }
            int difficultyAddr = 0;
            if (Frontend.difficulty == 1) {
                Frontend.displayTextSlowly("./economical_route/not_taken/subtracting score" +  "/\n...\n...\n...\n");
                difficultyAddr -= 1000;
            } else {
                Frontend.displayTextSlowly("./economical_route/taken/adding_score" +  "/\n...\n...\n...\n");
                difficultyAddr += 2000; 
            }
            int shipResaleValue = 0;
            if (Frontend.cur_player.getPace().equals(1)) {
                Frontend.displayTextSlowly("./ship_resale_val/driftwing/heavily_used/company_owned/\n...\n...\n...\n");
            } else if (Frontend.cur_player.getPace().equals(2)) {
                Frontend.displayTextSlowly("./ship_resale_val/starborne/moderately_used/employee_leased/3000/\n...\n...\n...\n");
                shipResaleValue += 3000;
            } else if (Frontend.cur_player.getPace().equals(3)) {
                Frontend.displayTextSlowly("./ship_resale_val/novaviper/case_hardend/employee_owned/9000/\n...\n...\n...\n");
                shipResaleValue += 9000;
            }
            if (cargo <= 0) {
                Frontend.displayTextSlowly("YOU HAVE FAILED TO BRING CARGO! YOU WILL BECOME AN INTERN");
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                return -1;
            } else {
                Frontend.displayTextSlowly("./cargo_shipped/" + cargo + "/\n\n\n\n\n");
            }

            float score = cargo * 100 + crewValue + money + difficultyAddr + shipResaleValue;
            int intScore = (int) score;
            return intScore;
        }
    }
}
