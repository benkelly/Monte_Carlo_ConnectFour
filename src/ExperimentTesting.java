import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by benjamin kelly
 * 14700869
 * benjamin.kelly@ucdconnect.ie
 */
public class ExperimentTesting {

    public void AIvsAI() {
        System.out.println("AI player vs AI player!");

        //print results to csv
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("test_results.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("T.E.N.C.H. 889B TRAINING");
        sb.append(',');
        sb.append("T.E.N.C.H. 889B WINS");
        sb.append(',');
        sb.append("Vulcan 2 TRAINING");
        sb.append(',');
        sb.append("Vulcan 2 WINS");
        sb.append(',');
        sb.append("TIE GAMES");
        sb.append(',');
        sb.append('\n');

        Integer[] heights = {8, 6, 5, 4, 7, 6, 5, 3, 2, 2, 1, 24, 3};

        AI_Player player1 = new AI_Player("T.E.N.C.H. 889B", true, true, 80);
        AI_Player player2 = new AI_Player("Vulcan 2", false, true, 80);


        // all 16 combinations of
        int[] trainingAmounts1 = {80, 80, 80, 80, 150, 150, 150, 150, 500, 500, 500, 500,
                2000, 2000, 2000, 2000};
        int[] trainingAmounts2 = {80, 150, 500, 2000, 80, 150, 500, 2000, 80, 150, 500, 2000, 80,
                150, 500, 2000};


        int TEST_GAMES = 100;
        for (int j = 0; j < trainingAmounts1.length; j++) {

            for (int i = 0; i < TEST_GAMES; i++) {
                Board.getInstance().createBoard(heights);
                GameState gameState = new GameState();

                player1.TRAINING_GAMES = trainingAmounts1[j];
                player2.TRAINING_GAMES = trainingAmounts2[j];

                gameState.add(player1);
                gameState.add(player2);

                gameState.gameLoop();
                Board.getInstance().clear();

            }
            System.out.println(player1.playerName + ": Training = " + player1.TRAINING_GAMES + ", won = " +
                    "" + player1.gamesWON);
            System.out.println(player2.playerName + ": Training = " + player2.TRAINING_GAMES + ", won = " +
                    "" + player2.gamesWON);
            System.out.println("TIE GAMES = " + (TEST_GAMES - (player1.gamesWON + player2.gamesWON)));

            sb.append(player1.TRAINING_GAMES);
            sb.append(',');
            sb.append(player1.gamesWON);
            sb.append(',');
            sb.append(player2.TRAINING_GAMES);
            sb.append(',');
            sb.append(player2.gamesWON);
            sb.append(',');
            sb.append((TEST_GAMES - (player1.gamesWON + player2.gamesWON)));
            sb.append(',');
            sb.append('\n');

            player1.gamesWON = 0;
            player2.gamesWON = 0;
        }
        pw.write(sb.toString());
        pw.close();
    }

    public void humanVsAI() {

        //print results to csv
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("HUMANvAI_test_results.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("BOT TRAINING");
        sb.append(',');
        sb.append("BOT WINS");
        sb.append(',');
        sb.append("HUMAN WINS");
        sb.append(',');
        sb.append("TIE GAMES");
        sb.append(',');
        sb.append('\n');



        Integer[] heights = {8, 6, 5, 4, 7, 6, 5, 3, 2, 2, 1, 24, 3};

        Player player1 = new Player("Human", true);
        AI_Player player2 = new AI_Player("BOT", false, true, 80);

        int[] trainingAounts = {80, 150, 500, 2000};

        int TEST_GAMES = 10;
        for (int j = 0; j < trainingAounts.length; j++) {

            for (int i = 0; i < TEST_GAMES; i++) {
                Board.getInstance().createBoard(heights);
                GameState gameState = new GameState();

                player2.TRAINING_GAMES = trainingAounts[j];

                gameState.add(player1);
                gameState.add(player2);

                gameState.gameLoop();
                Board.getInstance().clear();

            }
            System.out.println(player1.playerName + ": won = " + player1.gamesWON);
            System.out.println(player2.playerName + ": Training = " + player2.TRAINING_GAMES + ", won = " +
                    "" + player2.gamesWON);
            System.out.println("TIE GAMES = " + (TEST_GAMES - (player1.gamesWON + player2.gamesWON)));


            sb.append(player2.TRAINING_GAMES);
            sb.append(',');
            sb.append(player2.gamesWON);
            sb.append(',');
            sb.append(player1.gamesWON);
            sb.append(',');
            sb.append((TEST_GAMES - (player1.gamesWON + player2.gamesWON)));
            sb.append(',');
            sb.append('\n');

            player1.gamesWON = 0;
            player2.gamesWON = 0;
        }
        pw.write(sb.toString());
        pw.close();

    }


        public static void main(String[] args) {
        System.out.println("Experiment and report!");

        ExperimentTesting testing = new ExperimentTesting();

        testing.humanVsAI();


    }

}
