package sample;

import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML
    private Label playerCardsLabel, croupierCardsLabel,infoLabel;
    @FXML
    private Button buttonStart, buttonHit, buttonStand;

    private boolean gameStatus = false;
    private List<String> cards = new ArrayList<String>();
    private List<Integer> croupier = new ArrayList<Integer>();
    private List<Integer> playerCards = new ArrayList<Integer>();
    private List<Integer> playerPoints = new ArrayList<Integer>();
    private List<Integer> croupierPoints = new ArrayList<Integer>();

    private void update(List <String> cardsAll, List <Integer> playerCards, String type){
        String tmp = "";
        String cards;
        for(int i=0; i<playerCards.size(); i++){
            cards = cardsAll.get(playerCards.get(i));
            tmp = tmp + "  " + cards.split(";")[0];
        }
        if(type.equals("croupier")) {
            croupierCardsLabel.setText(tmp);
        } else {
            playerCardsLabel.setText(tmp);
        }
    }

    private void addNextCard(String type){
        Random rd = new Random();
        int tmp = rd.nextInt(13);
        String stringToSplit = cards.get(tmp);
        int amount = Integer.parseInt(stringToSplit.split(";")[1]);
        if(amount > 0 ){
            if(type.equals("croupier")){
                croupier.add(tmp);
                --amount;
                String aText = Integer.toString(amount);
                cards.set(tmp, stringToSplit.split(";")[0] + ";" + aText);
            } else if(type.equals("player")) {
                playerCards.add(tmp);
                --amount;
                String aText = Integer.toString(amount);
                cards.set(tmp, stringToSplit.split(";")[0] + ";" + aText);
            }
        } else {
            addNextCard(type);
        }

    }

    private int checkBlackJack(List <Integer>listToCheck) {
        int isBlackjack = 0;
        if(listToCheck.get(0) == 0 || listToCheck.get(1) == 0) {
            if(listToCheck.get(0) >= 10 || listToCheck.get(1) >= 10) {
                isBlackjack = 1;
            }
        }
        return isBlackjack;
    }

    private void updatePoint(int newPoints, String type) {
        int points1 = 0;
        int points2 = 0;
        if(newPoints == 0) {
            points1 = 1;
            points2 = 11;
        } else if(newPoints >= 10) {
            points1 = 10;
            points2 = 10;
        } else {
            newPoints++;
            points1 = points2 = newPoints;
        }

        if(type.equals("player")) {
            points1 = points1 + playerPoints.get(0);
            playerPoints.set(0, points1);

            points2 = points2 + playerPoints.get(1);
            playerPoints.set(1, points2);
        } else {
            points1 = points1 + croupierPoints.get(0);
            croupierPoints.set(0, points1);

            points2 = points2 + croupierPoints.get(1);
            croupierPoints.set(1, points2);
        }
    }

    private void isEnd() {
        if(croupierPoints.get(0) >= croupierPoints.get(1) && croupierPoints.get(0) <= 21) {
            if(playerPoints.get(0) >= playerPoints.get(1) && playerPoints.get(0) <= 21) {
                if(playerPoints.get(0) > croupierPoints.get(0)) {
                    infoLabel.setText("You Win");
                } else {
                    infoLabel.setText("Croupier Wins");
                }
            } else if (playerPoints.get(1) > playerPoints.get(0) && playerPoints.get(1) <= 21) {
                if(playerPoints.get(1) > croupierPoints.get(0)) {
                    infoLabel.setText("You Win");
                } else {
                    infoLabel.setText("Croupier Wins");
                }
            }
        } else if(croupierPoints.get(1) > croupierPoints.get(0) && croupierPoints.get(1) <= 21) {
            if(playerPoints.get(0) >= playerPoints.get(1) && playerPoints.get(0) <= 21) {
                if(playerPoints.get(0) > croupierPoints.get(1)) {
                    infoLabel.setText("You Win");
                } else {
                    infoLabel.setText("Croupier Wins");
                }
            } else if (playerPoints.get(1) > playerPoints.get(0) && playerPoints.get(1) <= 21) {
                if(playerPoints.get(1) > croupierPoints.get(1)) {
                    infoLabel.setText("You Win");
                } else {
                    infoLabel.setText("Croupier Wins");
                }
            }
        }
    }

    private void croupierTurn() {
        addNextCard("croupier");
        update(cards, croupier, "croupier");
        updatePoint(croupier.get(croupier.size()-1), "croupier");
        if(croupierPoints.get(0) < 17 || croupierPoints.get(1) < 17) {
            croupierTurn();
        } else {
            isEnd();

        }
    }

    @FXML
    private void hitEvent(ActionEvent event) {
        addNextCard("player");
        update(cards, playerCards, "player");
        updatePoint(playerCards.get(playerCards.size()-1), "player");

        if(playerPoints.get(0) > 21 && playerPoints.get(1) > 21) {
            gameStatus = false;
            infoLabel.setText("You have more than 21 points. You lose");
            buttonStart.setDisable(false);
            buttonHit.setDisable(true);
            buttonStand.setDisable(true);
        }
    }
    @FXML
    private void startEvent(ActionEvent event) {
        gameStatus = true;
        playerCardsLabel.setText("");
        croupierCardsLabel.setText("");
        infoLabel.setText("");
        cards.addAll(Arrays.asList("A;4", "2;4", "3;4", "4;4", "5;4", "6;4", "7;4", "8;4", "9;4", "10;4", "J;4", "D;4", "K;4"));
        playerPoints.addAll(Arrays.asList(0, 0, 0));
        croupierPoints.addAll(Arrays.asList(0, 0, 0));
        playerPoints.addAll(Arrays.asList(0, 0, 0));
        croupierPoints.addAll(Arrays.asList(0, 0, 0));

        playerPoints.set(0, 0);
        playerPoints.set(1, 0);
        playerPoints.set(2, 0);

        croupierPoints.set(0, 0);
        croupierPoints.set(1, 0);
        croupierPoints.set(2, 0);

        croupier.clear();
        playerCards.clear();
        for(int i=0; i<2; i++) {
            addNextCard("croupier");
            addNextCard("player");
        }

        update(cards, playerCards, "player");
        update(cards, croupier, "croupier");

        playerPoints.set(2, checkBlackJack(playerCards));
        croupierPoints.set(2, checkBlackJack(croupier));

        updatePoint(playerCards.get(0), "player");
        updatePoint(playerCards.get(1), "player");

        updatePoint(croupier.get(0), "croupier");
        updatePoint(croupier.get(1), "croupier");

        if(gameStatus == true) {
            buttonStart.setDisable(true);
            buttonHit.setDisable(false);
            buttonStand.setDisable(false);
        }
    }
    @FXML
    private void standEvent(ActionEvent event) {
        if(playerPoints.get(2) == 1 || croupierPoints.get(2) == 1) {
            if(playerPoints.get(2) == 1) {
                infoLabel.setText("You Win");
            } else {
                infoLabel.setText("Croupier Wins");
            }
        } else if(croupierPoints.get(0) < 17 || croupierPoints.get(1) < 17) {
            croupierTurn();
        } else {
            isEnd();
        }


        playerCards.removeAll(playerCards);
        croupier.removeAll(croupier);
        playerPoints.removeAll(playerPoints);
        croupierPoints.removeAll(croupierPoints);

        buttonStart.setDisable(false);
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
    }
}